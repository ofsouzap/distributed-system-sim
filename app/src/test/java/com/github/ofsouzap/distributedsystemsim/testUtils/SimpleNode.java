package com.github.ofsouzap.distributedsystemsim.testUtils;

import java.util.ArrayList;
import java.util.List;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.CrashStopNodeBehvaiour;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public class SimpleNode implements Node {
    protected final NodeBehaviour nodeBehaviour;
    protected final List<Message> receivedMessages;

    public SimpleNode() {
        this.nodeBehaviour = new CrashStopNodeBehvaiour();
        this.receivedMessages = new ArrayList<>();
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    @Override
    public void receiveMessage(Message msg) {
        receivedMessages.add(msg);
    }

    @Override
    public UpdateIntent update(SimulationContext context) { return UpdateIntent.emptyIntent(); }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }
}
