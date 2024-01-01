package com.github.ofsouzap.distributedsystemsim.examples.basicExample;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;

public class StringMessage implements Message {
    protected final String content;

    public StringMessage(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
