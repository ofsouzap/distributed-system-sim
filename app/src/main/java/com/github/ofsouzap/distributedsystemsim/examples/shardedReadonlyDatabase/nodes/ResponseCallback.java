package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes;

@FunctionalInterface
public interface ResponseCallback {
    public void handle(String key, Object value);
}
