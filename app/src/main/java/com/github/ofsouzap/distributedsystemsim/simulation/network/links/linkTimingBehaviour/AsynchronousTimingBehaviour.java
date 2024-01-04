package com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour;

import java.util.Random;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;

/**
 * Implementation of asynchronous behaviour where the network may delay messages for arbitrarily long
 * This is implemented by generating random numbers in the range [0,1] and then taking the negation of the natural logarithm of the number
 * (and then rounding to the bounds of the integer data type)
 * which means that shorter delays are more likely than longer ones.
 * If this behvaiour is undesirable, go make your own implementation.
 */
public class AsynchronousTimingBehaviour implements LinkTimingBehaviour {

    protected final Random random;

    public AsynchronousTimingBehaviour() {
        this.random = new Random();
    }

    public static int generateDelay(Random random) {
        double r = random.nextDouble(); // 0 < r < 1
        double v = -Math.log(r); // 1 < v < inf
        if (v >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int)Math.round(v); // Safe to cast to int as I've already checked if the value is out of the int range
        }
    }

    @Override
    public Integer generateDeliveryTime(SimulationContext context) {
        int delay = AsynchronousTimingBehaviour.generateDelay(random);
        return context.getTime() + delay;
    }
}
