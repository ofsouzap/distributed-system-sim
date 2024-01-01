package distributed.system.sim.examples.basicExample.network;

import java.util.HashSet;
import java.util.Set;

import distributed.system.sim.simulation.MessageDeliveryEvent;
import distributed.system.sim.simulation.MessageTransmission;
import distributed.system.sim.simulation.SimulationContext;
import distributed.system.sim.simulation.network.Network;
import distributed.system.sim.simulation.network.links.NetworkLink;
import distributed.system.sim.simulation.network.nodes.Node;

public class NiceNetworkLink implements NetworkLink {
    private Network network;

    public NiceNetworkLink(Network network) {
        this.network = network;
    }

    protected Network getNetwork() { return network; }

    @Override
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, MessageTransmission tx) {
        HashSet<MessageDeliveryEvent> xs = new HashSet<>();

        for (Node dst : getNetwork().getNodes())
            if (dst != this)
                xs.add(new MessageDeliveryEvent(context.getTime() + 1, dst, tx.getMsg()));

        return xs;
    }
}
