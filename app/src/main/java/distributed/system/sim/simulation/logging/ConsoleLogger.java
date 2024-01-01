package distributed.system.sim.simulation.logging;

import distributed.system.sim.simulation.MessageDeliveryEvent;
import distributed.system.sim.simulation.MessageTransmission;
import distributed.system.sim.simulation.SimulationContext;
import distributed.system.sim.simulation.messages.Message;
import distributed.system.sim.simulation.network.nodes.Node;

public class ConsoleLogger implements EventLogger {
    @Override
    public void logStep(SimulationContext context) {
        System.out.println("Simulation stepped to t=" + context.getTime());
    }

    @Override
    public void logMessageTransmitted(SimulationContext context, MessageTransmission tx) {
        System.out.println("Node [" + tx.getSrc().toString() + "] transmitted message [" + tx.getMsg().toString() + "]");
    }

    @Override
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery) {
        System.out.println("Adding message delivery at t="
        + messageDelivery.getDeliveryTime()
        + " of message ["
        + messageDelivery.getMessage().toString()
        + "] to node ["
        + messageDelivery.getDst().toString()
        + "]");
    }

    @Override
    public void logMessageReceived(SimulationContext context, Node dst, Message msg) {
        System.out.println("Node [" + dst.toString() + "] received message [" + msg.toString() + "]");
    }
}
