package com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours;

public class CrashStopNodeBehvaiour implements NodeBehaviour {
    @Override
    public CrashBehaviour getCrashBehaviour() {
        return CrashBehaviour.Stop;
    }
}
