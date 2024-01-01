package com.github.ofsouzap.distributedsystemsim.simulation.messages;

import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public interface P2pMessage extends Message {
    public Node getSrc();
    public Node getDst();
}
