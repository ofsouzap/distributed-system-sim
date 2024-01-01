package com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes;

import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.CrashBehaviour;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public class NiceNodeBehaviour implements NodeBehaviour {
    @Override
    public CrashBehaviour getCrashBehaviour() { return CrashBehaviour.Stop; }
}
