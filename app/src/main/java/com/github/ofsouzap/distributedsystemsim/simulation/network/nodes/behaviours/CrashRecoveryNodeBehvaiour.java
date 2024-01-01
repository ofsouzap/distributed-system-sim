package com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours;

public class CrashRecoveryNodeBehvaiour implements NodeBehaviour {
    @Override
    public CrashBehaviour getCrashBehaviour() {
        return CrashBehaviour.Recovery;
    }
}
