package com.github.ofsouzap.distributedsystemsim.simulation.network;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.ReliableNetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class ReliableNetwork implements Network {
    protected NetworkLink networkLink;
    protected Set<Node> nodes;

    public ReliableNetwork() {
        this.networkLink = new ReliableNetworkLink();
        this.nodes = new HashSet<>();
    }
    @Override
    public NetworkLink getNetworkLink() { return networkLink; }
    @Override
    public Set<Node> getNodes() { return nodes; }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void removeNode(Node node) {
        nodes.remove(node);
    }
}
