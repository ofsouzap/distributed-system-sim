package com.github.ofsouzap.distributedsystemsim.simulation.network;

import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public interface Network {
    public NetworkLink getNetworkLink();

    public Set<Node> getNodes();
    public void addNode(Node node);
    public void removeNode(Node node);

    public default Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, Message msg) {
        return getNetworkLink().generateMessageDeliveries(context, msg);
    }
}
