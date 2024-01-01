package com.github.ofsouzap.distributedsystemsim.testUtils;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class StringMessage implements Message {
    protected final String content;
    protected final Node src;
    protected final MessageTarget intendedTarget;

    public StringMessage(Node src, MessageTarget intendedTarget, String content) {
        this.src = src;
        this.intendedTarget = intendedTarget;
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
    public MessageTarget getIntendedTarget() {
        return intendedTarget;
    }

    public String getContent() {
        return content;
    }
}
