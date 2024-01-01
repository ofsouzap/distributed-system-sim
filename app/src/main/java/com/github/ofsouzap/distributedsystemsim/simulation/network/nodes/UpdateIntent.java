package com.github.ofsouzap.distributedsystemsim.simulation.network.nodes;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.MessageTransmission;

public class UpdateIntent {
    public Set<MessageTransmission> messageTransmissions;

    public UpdateIntent() {
        this.messageTransmissions = new HashSet<>();
    }

    public UpdateIntent(Set<MessageTransmission> messageTransmissions) {
        this.messageTransmissions = messageTransmissions;
    }

    public Set<MessageTransmission> getMessageTransmissions() { return messageTransmissions; }
}
