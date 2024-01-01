package com.github.ofsouzap.distributedsystemsim.simulation.logging;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;

public class ConsoleLogger implements EventLogger {
    @Override
    public void logStep(SimulationContext context) {
        System.out.println("Simulation stepped to t=" + context.getTime());
    }

    @Override
    public void logMessageTransmitted(SimulationContext context, Message msg) {
        System.out.println("Node [" + msg.getSrc().toString() + "] transmitted message [" + msg.toString() + "]");
    }

    @Override
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery) {
        System.out.println("Adding message delivery at t="
        + messageDelivery.getDeliveryTime()
        + " of message ["
        + messageDelivery.getMessage().toString()
        + "] to node ["
        + messageDelivery.getIntendedTarget().toString()
        + "]");
    }

    @Override
    public void logMessageDelivered(SimulationContext context, MessageTarget deliveryTarget, Message msg) {
        System.out.println("Target [" + deliveryTarget.toString() + "] received message [" + msg.toString() + "]");
    }
}
