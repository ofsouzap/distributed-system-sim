package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes;

public class ClientRequest {
    protected final int requestId;
    protected final int sendTime;
    protected final String key;
    protected final ResponseCallback callback;

    public ClientRequest(int requestId, int sendTime, String key, ResponseCallback callback) {
        this.requestId = requestId;
        this.sendTime = sendTime;
        this.key = key;
        this.callback = callback;
    }

    public int getRequestId() { return requestId; }
    public int getSendTime() { return sendTime; }
    public String getKey() { return key; }
    public ResponseCallback getCallback() { return callback; }
}
