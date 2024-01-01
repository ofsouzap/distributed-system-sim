package com.github.ofsouzap.distributedsystemsim.simulation.network.links;

import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;

public interface NetworkLink {
    /** Take a message and decide the (possibly empty) set of message deliveries that will occur in the simulation */
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, Message msg);
}
