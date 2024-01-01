package com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes;

import com.github.ofsouzap.distributedsystemsim.simulation.MessageTransmission;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public class TimedBroadcastNode implements Node {
    protected final NodeBehaviour nodeBehaviour;
    protected final Integer sendTime;
    protected final Message msg;

    public TimedBroadcastNode(Integer sendTime, Message msg) {
        this.nodeBehaviour = new NiceNodeBehaviour();
        this.sendTime = sendTime;
        this.msg = msg;
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    @Override
    public void receiveMessage(Message msg) { }

    @Override
    public UpdateIntent update(SimulationContext context) {
        UpdateIntent intent = new UpdateIntent();

        if (context.getTime() == sendTime) {
            intent.messageTransmissions.add(new MessageTransmission(this, msg));
        }

        return intent;
    }
}
