package com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes;

import com.github.ofsouzap.distributedsystemsim.examples.basicExample.StringMessage;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.BroadcastTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.CrashStopNodeBehvaiour;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public class TimedBroadcastNode implements Node {
    protected final NodeBehaviour nodeBehaviour;
    protected final Integer sendTime;
    protected final String msgContent;

    public TimedBroadcastNode(Integer sendTime, String msgContent) {
        this.nodeBehaviour = new CrashStopNodeBehvaiour();
        this.sendTime = sendTime;
        this.msgContent = msgContent;
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    @Override
    public void receiveMessage(Message msg) { }

    @Override
    public UpdateIntent update(SimulationContext context) {
        UpdateIntent intent = new UpdateIntent();

        if (context.getTime() == sendTime) {
            intent.messagesToSend.add(new StringMessage(this, new BroadcastTarget(), msgContent));
        }

        return intent;
    }
}
