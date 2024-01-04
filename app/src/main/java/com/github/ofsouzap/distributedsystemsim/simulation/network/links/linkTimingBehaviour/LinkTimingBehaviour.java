package com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;

public interface LinkTimingBehaviour {
    public Integer generateDeliveryTime(SimulationContext context);
}
