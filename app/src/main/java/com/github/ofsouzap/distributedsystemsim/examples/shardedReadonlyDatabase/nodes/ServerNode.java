package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages.AnswerMessage;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages.AskMessage;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;
import com.google.common.collect.ImmutableMap;

public class ServerNode implements Node {
    protected final NodeBehaviour nodeBehaviour;
    protected final Set<Message> messageQueue;
    protected final ImmutableMap<String, Object> keyStore;

    public ServerNode(NodeBehaviour nodeBehaviour, Map<String, Object> keyStore) {
        this.nodeBehaviour = nodeBehaviour;
        this.messageQueue = new HashSet<Message>();
        this.keyStore = ImmutableMap.copyOf(keyStore);
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    /**
     * Enqueue a response message for a given value
     * @param client The client to send the response to
     * @param requestId The ID of the request being responded to
     * @param value The value to put in the message
     */
    protected void enqueueResponse(Node client, int requestId, Object value) {
        Message msg = new AnswerMessage(this, client, requestId, value);
        messageQueue.add(msg);
    }

    /**
     * Process a request for a key's value.
     * If the key is found in the store, enqueue a response message.
     * @param client The client that sent the request
     * @param requestId The ID of the request
     * @param key The key being queried
     */
    protected void processRequest(Node client, int requestId, String key) {
        if (keyStore.containsKey(key)) {
            enqueueResponse(client, requestId, keyStore.get(key));
        }
    }

    @Override
    public void receiveMessage(Message msg) {
        if (msg instanceof AskMessage) {
            AskMessage askMsg = (AskMessage)msg;
            processRequest(askMsg.getSrc(), askMsg.getRequestId(), askMsg.getKey());
        }
    }

    @Override
    public UpdateIntent update(SimulationContext context) {
        Set<Message> msgs = new HashSet<>();

        msgs.addAll(messageQueue);
        messageQueue.clear();

        return UpdateIntent.sendMessagesIntent(msgs);
    }
}
