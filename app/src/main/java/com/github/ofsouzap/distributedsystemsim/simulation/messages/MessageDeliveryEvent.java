package com.github.ofsouzap.distributedsystemsim.simulation.messages;

import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.MessageTarget;

public class MessageDeliveryEvent {
    protected Integer deliveryTime;
    /** The intended target of the message. This could differ from the actual target due to message loss/alteration */
    protected MessageTarget intendedTarget;
    protected Message message;

    public MessageDeliveryEvent(Integer deliveryTime, MessageTarget intendedTarget, Message message) {
        this.deliveryTime = deliveryTime;
        this.intendedTarget = intendedTarget;
        this.message = message;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public MessageTarget getIntendedTarget() {
        return intendedTarget;
    }

    public Message getMessage() {
        return message;
    }
}
