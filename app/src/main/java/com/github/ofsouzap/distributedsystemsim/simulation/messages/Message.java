package com.github.ofsouzap.distributedsystemsim.simulation.messages;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public interface Message {
    public Node getSrc();
    /** Get the intended target of the message. This could differ from the actual target due to message loss/alteration */
    public MessageTarget getIntendedTarget();
}
