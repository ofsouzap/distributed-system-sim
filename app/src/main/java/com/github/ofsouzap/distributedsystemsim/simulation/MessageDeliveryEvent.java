package com.github.ofsouzap.distributedsystemsim.simulation;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class MessageDeliveryEvent {
    protected Integer deliveryTime;
    protected Node dst;
    protected Message message;

    public MessageDeliveryEvent(Integer deliveryTime, Node dst, Message message) {
        this.deliveryTime = deliveryTime;
        this.dst = dst;
        this.message = message;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public Node getDst() {
        return dst;
    }

    public Message getMessage() {
        return message;
    }
}
