package com.github.ofsouzap.distributedsystemsim.simulation.network.links;

import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.MessageTransmission;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;

public interface NetworkLink {
    /** Take a message and decide the (possibly empty) set of message deliveries that will occur in the simulation */
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, MessageTransmission tx);
}
