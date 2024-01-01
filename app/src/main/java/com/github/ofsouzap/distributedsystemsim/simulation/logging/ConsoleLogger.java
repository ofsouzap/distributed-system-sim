package com.github.ofsouzap.distributedsystemsim.simulation.logging;

import com.github.ofsouzap.distributedsystemsim.simulation.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.MessageTransmission;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class ConsoleLogger implements EventLogger {
    @Override
    public void logStep(SimulationContext context) {
        System.out.println("Simulation stepped to t=" + context.getTime());
    }

    @Override
    public void logMessageTransmitted(SimulationContext context, MessageTransmission tx) {
        System.out.println("Node [" + tx.getSrc().toString() + "] transmitted message [" + tx.getMsg().toString() + "]");
    }

    @Override
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery) {
        System.out.println("Adding message delivery at t="
        + messageDelivery.getDeliveryTime()
        + " of message ["
        + messageDelivery.getMessage().toString()
        + "] to node ["
        + messageDelivery.getDst().toString()
        + "]");
    }

    @Override
    public void logMessageReceived(SimulationContext context, Node dst, Message msg) {
        System.out.println("Node [" + dst.toString() + "] received message [" + msg.toString() + "]");
    }
}
