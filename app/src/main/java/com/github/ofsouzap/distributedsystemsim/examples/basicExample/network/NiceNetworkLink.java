package com.github.ofsouzap.distributedsystemsim.examples.basicExample.network;

import java.util.Set;
import java.util.Collections;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;

public class NiceNetworkLink implements NetworkLink {
    private Network network;

    public NiceNetworkLink(Network network) {
        this.network = network;
    }

    protected Network getNetwork() { return network; }

    @Override
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, Message msg) {
        return Collections.singleton(new MessageDeliveryEvent(context.getTime() + 1, msg.getTarget(), msg));
    }
}
