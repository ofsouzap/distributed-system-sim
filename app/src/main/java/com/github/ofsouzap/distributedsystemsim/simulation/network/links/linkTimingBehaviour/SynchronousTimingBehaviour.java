package com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour;

import java.util.Random;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;

public class SynchronousTimingBehaviour implements LinkTimingBehaviour {
    public static final int defaultLatencyBound = 4;

    protected final Random random;
    /** Upper bound on the latency of the link */
    protected final int latencyBound;

    public SynchronousTimingBehaviour(Integer latencyBound, Long seed) {
        if (latencyBound != null && latencyBound <= 0)
            throw new IllegalArgumentException("Latency bound must be positive");

        this.latencyBound = (latencyBound != null) ? latencyBound : defaultLatencyBound;
        this.random = new Random((seed != null) ? seed : System.currentTimeMillis());
    }

    public SynchronousTimingBehaviour(Integer latencyBound) { this(latencyBound, null); }

    public SynchronousTimingBehaviour() { this(null); }

    public int getLatencyBound() {
        return latencyBound;
    }

    public static int generateDelay(Random random, int latencyBound) {
        return random.nextInt(latencyBound) + 1; // Delay is in range [1,latencyBound]
    }

    @Override
    public Integer generateDeliveryTime(SimulationContext context) {
        int delay = SynchronousTimingBehaviour.generateDelay(random, getLatencyBound());
        return context.getTime() + delay;
    }
}
