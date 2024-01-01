package com.github.ofsouzap.distributedsystemsim.simulation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.logging.EventLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.UpdateIntent;

public class Simulator implements SimulationController {
    protected final EventLogger logger;
    protected SimulatorContext context;
    protected final Network network;
    protected final Queue<MessageDeliveryEvent> messageQueue; // Queue of message deliveries that must be ordered by message delivery time

    public Simulator(EventLogger logger, Network network) {
        this.logger = logger;
        this.context = new SimulatorContext();
        this.network = network;

        // Using a priority queue means that the ordering of messages in the queue is done by their delivery times which is the required functionality
        this.messageQueue = new PriorityQueue<MessageDeliveryEvent>(new Comparator<MessageDeliveryEvent>() {
            @Override
            public int compare(MessageDeliveryEvent md1, MessageDeliveryEvent md2) {
                return md1.deliveryTime.compareTo(md2.deliveryTime);
            }
        });
    }

    @Override
    public Network getNetwork() { return network; }
    @Override
    public SimulationContext getContext() { return context; }

    private void deliverAllNowMessageDeliveries() {
        // Iterate through all messages for this simulation step
        while (!messageQueue.isEmpty() && messageQueue.peek().deliveryTime == getContext().getTime()) {
            MessageDeliveryEvent messageDelivery = messageQueue.remove(); // Remove the delivery event item
            messageDelivery.dst.receiveMessage(messageDelivery.message); // Deliver the message
            this.logger.logMessageReceived(context, messageDelivery.dst, messageDelivery.message);
        }
    }

    private void handleUpdateIntent(UpdateIntent intent) {
        if (intent == null) { return; }

        if (intent.messageTransmissions != null) {
            for (MessageTransmission tx : intent.messageTransmissions) {
                this.transmitMessage(tx);
            }
        }
    }

    private void updateAllNodes() {
        for (Node node : getNetwork().getNodes()) {
            UpdateIntent intent = node.update(getContext());
            handleUpdateIntent(intent);
        }
    }

    @Override
    public void step() {
        this.context.incrementTime();

        this.logger.logStep(context);

        this.deliverAllNowMessageDeliveries();

        this.updateAllNodes();
    }

    protected void transmitMessage(MessageTransmission tx) {
        Set<MessageDeliveryEvent> messageDeliveryEvents = getNetwork().generateMessageDeliveries(getContext(), tx);
        this.logger.logMessageTransmitted(context, tx);

        messageQueue.addAll(messageDeliveryEvents);
        for (MessageDeliveryEvent x : messageDeliveryEvents)
            this.logger.logMessageDeliveryAdded(context, x);
    }
}
