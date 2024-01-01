package com.github.ofsouzap.distributedsystemsim.simulation.network.nodes;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;

public class UpdateIntent {
    public final Set<Message> messagesToSend;

    public static UpdateIntent emptyIntent() {
        return new UpdateIntent();
    }

    public UpdateIntent() {
        this(new HashSet<>());
    }

    public UpdateIntent(Set<Message> messageTransmissions) {
        this.messagesToSend = messageTransmissions;
    }

    public Set<Message> getMessagesToSend() { return messagesToSend; }
}
