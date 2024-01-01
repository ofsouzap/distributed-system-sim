package distributed.system.sim.simulation.logging;

import distributed.system.sim.simulation.MessageDeliveryEvent;
import distributed.system.sim.simulation.MessageTransmission;
import distributed.system.sim.simulation.SimulationContext;
import distributed.system.sim.simulation.messages.Message;
import distributed.system.sim.simulation.network.nodes.Node;

public interface EventLogger {
    /** Log the simulation having stepped once */
    public void logStep(SimulationContext context);
    /** Log a message having been transmitted by a node */
    public void logMessageTransmitted(SimulationContext context, MessageTransmission tx);
    /** Log a message delivery having been added to the timeline */
    public void logMessageDeliveryAdded(SimulationContext context, MessageDeliveryEvent messageDelivery);
    /** Log a message having been received by a node */
    public void logMessageReceived(SimulationContext context, Node dst, Message msg);
}
