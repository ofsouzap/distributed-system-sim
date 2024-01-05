package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.SingleTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

/**
 * A message where a server is answering a client's ask request for the value associated with a specified key.
 * These messages are sent directly back to the client that asked for the data.
 */
public class AnswerMessage implements Message {
    protected final Node src;
    protected final MessageTarget target;
    /** The ID of the request that was sent */
    protected final int requestId;
    /** The value associated with the key queried */
    protected final Object value;

    public AnswerMessage(Node src, Node target, int requestId, Object value) {
        this.src = src;
        this.target = new SingleTarget(target);
        this.requestId = requestId;
        this.value = value;
    }

    @Override
    public Node getSrc() { return src; }
    @Override
    public MessageTarget getIntendedTarget() { return target; }
    public int getRequestId() { return requestId; }
    public Object getValue() { return value; }
}
