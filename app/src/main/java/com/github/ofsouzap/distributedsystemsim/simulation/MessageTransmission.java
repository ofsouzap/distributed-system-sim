package com.github.ofsouzap.distributedsystemsim.simulation;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class MessageTransmission {
    protected final Node src;
    protected final Message msg;

    public MessageTransmission(Node src, Message msg) {
        this.src = src;
        this.msg = msg;
    }

    public Node getSrc() {
        return src;
    }

    public Message getMsg() {
        return msg;
    }
}
