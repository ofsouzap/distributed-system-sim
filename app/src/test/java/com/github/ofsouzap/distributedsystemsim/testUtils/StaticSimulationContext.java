package com.github.ofsouzap.distributedsystemsim.testUtils;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;

public class StaticSimulationContext implements SimulationContext {
    @Override
    public Integer getTime() {
        return 1;
    }
}
