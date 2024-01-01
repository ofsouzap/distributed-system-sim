package com.github.ofsouzap.distributedsystemsim.simulation.network.links;

import java.util.Collections;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;

public class ReliableNetworkLink implements NetworkLink {
    @Override
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, Message msg) {
        // TODO - when network timing behaviours implemented, have delivery of messages delayed as needed
        return Collections.singleton(new MessageDeliveryEvent(context.getTime() + 1, msg.getIntendedTarget(), msg));
    }
}
