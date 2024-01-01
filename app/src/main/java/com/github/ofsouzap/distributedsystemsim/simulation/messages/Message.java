package com.github.ofsouzap.distributedsystemsim.simulation.messages;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public interface Message {
    public Node getSrc();
    public MessageTarget getTarget();
}
