package distributed.system.sim.examples.basicExample.network;

import java.util.HashSet;
import java.util.Set;

import distributed.system.sim.simulation.network.Network;
import distributed.system.sim.simulation.network.links.NetworkLink;
import distributed.system.sim.simulation.network.nodes.Node;

public class BasicNetwork implements Network {
    protected final NetworkLink networkLink;
    protected final Set<Node> nodes;

    public BasicNetwork() {
        this.networkLink = new NiceNetworkLink(this);
        this.nodes = new HashSet<>();
    }

    @Override
    public NetworkLink getNetworkLink() { return this.networkLink; }

    @Override
    public Set<Node> getNodes() { return this.nodes; }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void removeNode(Node node) {
        nodes.remove(node);
    }
}
