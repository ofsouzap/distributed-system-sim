package com.github.ofsouzap.distributedsystemsim.simulation;

import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;

public interface SimulationController {
    public Network getNetwork();
    public SimulationContext getContext();
    /** Step the simulation forwards one time unit */
    public void step();
}
