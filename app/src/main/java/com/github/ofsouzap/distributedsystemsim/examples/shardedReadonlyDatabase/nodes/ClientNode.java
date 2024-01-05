package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages.AnswerMessage;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.messages.AskMessage;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public class ClientNode implements Node {
    protected final NodeBehaviour nodeBehaviour;
    /** Requests that the client has sent and is still waiting for responses to */
    protected final Set<ClientRequest> sentRequests;
    /** Messages that the client is waiting to send */
    protected final Set<Message> messageQueue;

    public ClientNode(NodeBehaviour nodeBehaviour) {
        this.nodeBehaviour = nodeBehaviour;
        this.sentRequests = new HashSet<>();
        this.messageQueue = new HashSet<>();
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    /**
     * Look at a response received and see if it matches any of the requests the client is waiting for an answer to.
     * If so, call the request callback function on it.
     * @param requestId The ID of the request that the response is for
     * @param value The value that has been returned
     */
    protected void processResponse(int requestId, Object value) {
        for (ClientRequest req : sentRequests) {
            if (req.getRequestId() == requestId) {
                req.callback.handle(req.getKey(), value);
                sentRequests.remove(req);
                break;
            }
        }
    }

    protected void recordSentRequest(int requestId, int sendTime, String key, ResponseCallback callback) {
        sentRequests.add(new ClientRequest(requestId, sendTime, key, callback));
    }

    /**
     * Ask to send a request for data from the datastore.
     * @param key The key of the data being queried for
     * @param callback A callback function to call when the response is received
     */
    public void requestData(SimulationContext context, String key, ResponseCallback callback) {
        AskMessage msg = new AskMessage(this, key);

        messageQueue.add(msg);
        recordSentRequest(msg.getRequestId(), context.getTime(), msg.getKey(), callback);
    }

    /**
     * Forget any requests that should be timed out.
     * @param timeNow The time now
     * @param timeout How long each request is allowed to have a response before being timed out
     * @return The timed out requests
     */
    public Set<ClientRequest> popTimedOutRequests(int timeNow, int timeout) {
        Set<ClientRequest> outs = new HashSet<>();

        for (ClientRequest req : sentRequests) {
            if (timeNow >= req.getSendTime() + timeout) {
                outs.add(req);
            }
        }

        for (ClientRequest out : outs) {
            sentRequests.remove(out);
        }

        return outs;
    }

    @Override
    public void receiveMessage(Message msg) {
        if (msg instanceof AnswerMessage) {
            AnswerMessage ansMsg = (AnswerMessage)msg;
            processResponse(ansMsg.getRequestId(), ansMsg.getValue());
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
