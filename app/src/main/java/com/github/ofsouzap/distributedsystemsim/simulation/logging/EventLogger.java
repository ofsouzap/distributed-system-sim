package com.github.ofsouzap.distributedsystemsim.simulation.logging;

import com.github.ofsouzap.distributedsystemsim.simulation.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.MessageTransmission;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public interface EventLogger {
    /** Log the simulation having stepped once */
    public void logStep(SimulationContext context);
    /** Log a message having been transmitted by a node */
    public void logMessageTransmitted(SimulationContext context, MessageTransmission tx);
    /** Log a message delivery having been added to the timeline */
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery);
    /** Log a message having been received by a node */
    public void logMessageReceived(SimulationContext context, Node dst, Message msg);
}
