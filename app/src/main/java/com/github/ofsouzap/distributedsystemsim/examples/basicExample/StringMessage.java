package com.github.ofsouzap.distributedsystemsim.examples.basicExample;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class StringMessage implements Message {
    protected final String content;
    protected final Node src;
    protected final MessageTarget target;

    public StringMessage(Node src, MessageTarget target, String content) {
        this.src = src;
        this.target = target;
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public Node getSrc() {
        return src;
    }

    @Override
    public MessageTarget getTarget() {
        return target;
    }
}
