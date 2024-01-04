package com.github.ofsouzap.distributedsystemsim.simulation.network.links;

import java.util.Collections;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour.LinkTimingBehaviour;

public class ReliableNetworkLink implements NetworkLink {
    protected final LinkTimingBehaviour timingBehaviour;

    public ReliableNetworkLink(LinkTimingBehaviour timingBehaviour) {
        this.timingBehaviour = timingBehaviour;
    }

    @Override
    public LinkTimingBehaviour getTimingBehaviour() { return timingBehaviour; }

    @Override
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, Message msg) {
        Integer deliveryTime = getTimingBehaviour().generateDeliveryTime(context);
        return Collections.singleton(new MessageDeliveryEvent(deliveryTime, msg.getIntendedTarget(), msg));
    }
}
