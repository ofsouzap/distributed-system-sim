package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages;

import java.util.Random;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.BroadcastTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

/**
 * A message where a client is *ask*-ing the server for the value associated with a specified key.
 * These messages are always broadcast to the entire network so that the server node with the data can respond with the data.
 */
public class AskMessage implements Message {
    protected final Node src;
    protected final MessageTarget target;
    /** The ID of the request that was sent */
    protected final int requestId;
    /** The key of the data being asked for */
    protected final String key;

    public AskMessage(Node src, String key) {
        this.src = src;
        this.target = new BroadcastTarget();
        this.requestId = new Random().nextInt(); // Pick a random request ID. It will likely be unique
        this.key = key;
    }

    @Override
    public Node getSrc() { return src; }
    @Override
    public MessageTarget getIntendedTarget() { return target; }
    public int getRequestId() { return requestId; }
    public String getKey() { return key; }
}
