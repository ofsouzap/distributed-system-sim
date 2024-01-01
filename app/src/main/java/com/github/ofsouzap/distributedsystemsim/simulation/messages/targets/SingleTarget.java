package com.github.ofsouzap.distributedsystemsim.simulation.messages.targets;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class SingleTarget implements MessageTarget {
    protected Node dst;

    public SingleTarget(Node dst) {
        this.dst = dst;
    }

    public Node getDst() { return this.dst; }

    @Override
    public String toString() {
        return dst.toString();
    }

    @Override
    public void deliverMessage(Network network, Message msg) {
        this.dst.receiveMessage(msg);
    }
}
