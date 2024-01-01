package com.github.ofsouzap.distributedsystemsim.simulation.messages;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;

public class MessageDeliveryEvent {
    protected Integer deliveryTime;
    protected MessageTarget target;
    protected Message message;

    public MessageDeliveryEvent(Integer deliveryTime, MessageTarget intendedTarget, Message message) {
        this.deliveryTime = deliveryTime;
        this.target = intendedTarget;
        this.message = message;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public MessageTarget getTarget() {
        return target;
    }

    public Message getMessage() {
        return message;
    }
}
