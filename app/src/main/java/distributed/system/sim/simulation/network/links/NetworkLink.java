package distributed.system.sim.simulation.network.links;

import java.util.Set;

import distributed.system.sim.simulation.MessageDeliveryEvent;
import distributed.system.sim.simulation.MessageTransmission;
import distributed.system.sim.simulation.SimulationContext;

public interface NetworkLink {
    /** Take a message and decide the (possibly empty) set of message deliveries that will occur in the simulation */
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, MessageTransmission tx);
}
