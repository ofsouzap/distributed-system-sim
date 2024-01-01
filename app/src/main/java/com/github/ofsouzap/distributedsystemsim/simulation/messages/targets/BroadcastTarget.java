package com.github.ofsouzap.distributedsystemsim.simulation.messages.targets;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class BroadcastTarget implements MessageTarget {
    @Override
    public String toString() {
        return "broadcast";
    }

    @Override
    public void deliverMessage(Network network, Message msg) {
        for (Node n : network.getNodes()) {
            n.receiveMessage(msg);
        }
    }
}
