package com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public class NullNode implements Node {
    private NiceNodeBehaviour nodeBehaviour;

    public NullNode() {
        this.nodeBehaviour = new NiceNodeBehaviour();
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    @Override
    public void receiveMessage(Message msg) { }

    @Override
    public UpdateIntent update(SimulationContext context) { return null; }

}
