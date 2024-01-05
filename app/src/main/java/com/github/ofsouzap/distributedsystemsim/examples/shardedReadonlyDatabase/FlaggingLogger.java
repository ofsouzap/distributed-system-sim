package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase;

import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages.AnswerMessage;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages.AskMessage;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes.ClientNode;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes.ServerNode;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.logging.EventLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.SingleTarget;

public class FlaggingLogger implements EventLogger {
    protected boolean readyToBreak;

    public FlaggingLogger() {
        this.readyToBreak = false;
    }

    public void clearReadyToBreak() { readyToBreak = false; }
    public boolean isReadyToBreak() { return readyToBreak; }

    protected void info(String s) { System.out.println("[LOG] " + s); }

    @Override
    public void logStep(SimulationContext context) { }

    @Override
    public void logMessageTransmitted(SimulationContext context, Message msg) { }

    @Override
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery) { }

    @Override
    public void logMessageDelivered(SimulationContext context, MessageTarget deliveryTarget, Message msg) {
        if (deliveryTarget instanceof SingleTarget) {
            SingleTarget singleTarget = (SingleTarget)deliveryTarget;
            if (singleTarget.getDst() instanceof ClientNode)
                readyToBreak = true;
        }

        if (msg instanceof AskMessage && deliveryTarget instanceof SingleTarget) {
            AskMessage askMsg = (AskMessage)msg;
            SingleTarget singleTarget = (SingleTarget)deliveryTarget;

            if (singleTarget.getDst() instanceof ServerNode) {
                readyToBreak = true;
                this.info("Ask message for \"" + askMsg.getKey() + "\" delivered to a server");
            }
        } else if (msg instanceof AnswerMessage && deliveryTarget instanceof SingleTarget) {
            SingleTarget singleTarget = (SingleTarget)deliveryTarget;

            if (singleTarget.getDst() instanceof ClientNode) {
                info("Answer message delivered to client");
            }
        }
    }
}
