package com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour;

import java.util.Random;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;

/**
 * Implementation of partially-synchronous behaviour where the network may be asynchronous for unknown but finite periods of time.
 * This is implemented by having a random number generator using the time of a transmission as part of its seed to generate a random number
 * which is compared with a provided asynchronony chance to deterministically decide if the network is asynchronous at a specified timestamp.
 */
public class PartiallySynchronousTimingBehaviour implements LinkTimingBehaviour {
    public static final int defaultLatencyBound = 4;
    public static final double defaultAsynchronyChance = 0.2;

    /** Upper bound on the latency of the link */
    protected final int latencyBound;
    /** The random seed for deciding between synchronous and asynchronous behaviour */
    protected final long behaviourSeed;
    /** Random generator used for calculating delay values */
    protected final Random delayValueRandom;
    /** Chance of the network being asynchonous at a given point in time */
    protected final double asynchrononyChance;

    public PartiallySynchronousTimingBehaviour(Integer latencyBound, Long behaviourSeed, Long delaySeed, Double asynchrononyChance) {
        if (latencyBound != null && latencyBound <= 0)
            throw new IllegalArgumentException("Latency bound must be positive");
        if (asynchrononyChance != null && (asynchrononyChance < 0. || asynchrononyChance > 1.))
            throw new IllegalArgumentException("Asynchrony chance must be in the range [0,1]");

        this.latencyBound = (latencyBound != null) ? latencyBound : defaultLatencyBound;
        this.behaviourSeed = (behaviourSeed != null) ? behaviourSeed : System.currentTimeMillis();
        this.delayValueRandom = new Random((delaySeed != null) ? delaySeed : System.currentTimeMillis());
        this.asynchrononyChance = (asynchrononyChance != null) ? asynchrononyChance : defaultAsynchronyChance;
    }

    public PartiallySynchronousTimingBehaviour(Integer latencyBound, Double asynchronyChance) { this(latencyBound, null, null, asynchronyChance); }

    public PartiallySynchronousTimingBehaviour() { this(null, null, null, null); }

    public int getLatencyBound() { return latencyBound; }
    public long getBehaviourSeed() { return behaviourSeed; }
    public Random getDelayValueRandom() { return delayValueRandom; }
    public double getAsynchrononyChance() { return asynchrononyChance; }

    protected static double generateTimeAsynchronyChance(long seed, Integer t) {
        Random random = new Random(seed * t);
        return random.nextDouble();
    }

    public static int generateDelay(Random delayRandom, long behvaiourSeed, Integer t, double asynchrononyChance, int latencyBound) {
        double r = generateTimeAsynchronyChance(behvaiourSeed, t);
        if (r <= asynchrononyChance) {
            return AsynchronousTimingBehaviour.generateDelay(delayRandom);
        } else {
            return SynchronousTimingBehaviour.generateDelay(delayRandom, latencyBound);
        }
    }

    @Override
    public Integer generateDeliveryTime(SimulationContext context) {
        return context.getTime() + PartiallySynchronousTimingBehaviour.generateDelay(getDelayValueRandom(), getBehaviourSeed(), context.getTime(), getAsynchrononyChance(), getLatencyBound());
    }
}
