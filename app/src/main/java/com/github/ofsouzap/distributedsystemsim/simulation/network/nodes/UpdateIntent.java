package com.github.ofsouzap.distributedsystemsim.simulation.network.nodes;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;

public class UpdateIntent {
    public Set<Message> messagesToSend;

    public UpdateIntent() {
        this.messagesToSend = new HashSet<>();
    }

    public UpdateIntent(Set<Message> messageTransmissions) {
        this.messagesToSend = messageTransmissions;
    }

    public Set<Message> getMessagesToSend() { return messagesToSend; }
}
