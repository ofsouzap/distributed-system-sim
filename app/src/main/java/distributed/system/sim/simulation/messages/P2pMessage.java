package distributed.system.sim.simulation.messages;

import distributed.system.sim.simulation.network.nodes.Node;

public interface P2pMessage extends Message {
    public Node getSrc();
    public Node getDst();
}
