package com.github.ofsouzap.distributedsystemsim.simulation.messages.targets;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;

public interface MessageTarget {
    public void deliverMessage(Network network, Message msg);
}
