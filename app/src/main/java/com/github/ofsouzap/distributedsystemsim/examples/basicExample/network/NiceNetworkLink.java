package com.github.ofsouzap.distributedsystemsim.examples.basicExample.network;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.MessageTransmission;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class NiceNetworkLink implements NetworkLink {
    private Network network;

    public NiceNetworkLink(Network network) {
        this.network = network;
    }

    protected Network getNetwork() { return network; }

    @Override
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, MessageTransmission tx) {
        HashSet<MessageDeliveryEvent> xs = new HashSet<>();

        for (Node dst : getNetwork().getNodes())
            if (dst != this)
                xs.add(new MessageDeliveryEvent(context.getTime() + 1, dst, tx.getMsg()));

        return xs;
    }
}
