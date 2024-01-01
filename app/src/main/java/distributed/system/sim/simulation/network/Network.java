package distributed.system.sim.simulation.network;

import java.util.Set;

import distributed.system.sim.simulation.MessageDeliveryEvent;
import distributed.system.sim.simulation.MessageTransmission;
import distributed.system.sim.simulation.SimulationContext;
import distributed.system.sim.simulation.network.links.NetworkLink;
import distributed.system.sim.simulation.network.nodes.Node;

public interface Network {
    public NetworkLink getNetworkLink();

    public Set<Node> getNodes();
    public void addNode(Node node);
    public void removeNode(Node node);

    public default Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, MessageTransmission tx) {
        return getNetworkLink().generateMessageDeliveries(context, tx);
    }
}
