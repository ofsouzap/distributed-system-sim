package com.github.ofsouzap.distributedsystemsim.simulation.logging;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;

public class NullLogger implements EventLogger {
    @Override
    public void logStep(SimulationContext context) { }
    @Override
    public void logMessageTransmitted(SimulationContext context, Message msg) { }
    @Override
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery) { }
    @Override
    public void logMessageDelivered(SimulationContext context, MessageTarget deliveryTarget, Message msg) { }
}
