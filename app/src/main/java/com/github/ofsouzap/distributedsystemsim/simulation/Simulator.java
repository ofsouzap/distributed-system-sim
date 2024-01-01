package com.github.ofsouzap.distributedsystemsim.simulation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.logging.EventLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
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
                return md1.getDeliveryTime().compareTo(md2.getDeliveryTime());
            }
        });
    }

    @Override
    public Network getNetwork() { return network; }
    @Override
    public SimulationContext getContext() { return context; }

    private void deliverAllNowMessageDeliveries() {
        // Iterate through all messages for this simulation step
        while (!messageQueue.isEmpty() && messageQueue.peek().getDeliveryTime() == getContext().getTime()) {
            MessageDeliveryEvent messageDelivery = messageQueue.remove(); // Remove the delivery event item

            messageDelivery.getTarget().deliverMessage(network, messageDelivery.getMessage());

            this.logger.logMessageDelivered(context, messageDelivery.getTarget(), messageDelivery.getMessage());
        }
    }

    private void handleUpdateIntent(UpdateIntent intent) {
        if (intent == null) { return; }

        if (intent.messagesToSend != null) {
            for (Message msg : intent.messagesToSend) {
                this.transmitMessage(msg);
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

    protected void transmitMessage(Message msg) {
        Set<MessageDeliveryEvent> messageDeliveryEvents = getNetwork().generateMessageDeliveries(getContext(), msg);
        this.logger.logMessageTransmitted(context, msg);

        messageQueue.addAll(messageDeliveryEvents);
        for (MessageDeliveryEvent x : messageDeliveryEvents)
            this.logger.logMessageDeliveryAdded(context, x);
    }
}
