package com.github.ofsouzap.distributedsystemsim.simulation.logging;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;

public interface EventLogger {
    /** Log the simulation having stepped once */
    public void logStep(SimulationContext context);
    /** Log a message having been transmitted by a node */
    public void logMessageTransmitted(SimulationContext context, Message msg);
    /** Log a message delivery having been added to the timeline */
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery);
    /** Log a message having been received by a node */
    public void logMessageDelivered(SimulationContext context, MessageTarget deliveryTarget, Message msg);
}
