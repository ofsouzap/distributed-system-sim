package com.github.ofsouzap.distributedsystemsim.testUtils;

import java.util.HashSet;
import java.util.Set;

import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;

public class SimpleNetwork implements Network {
    protected final NetworkLink networkLink;
    protected Set<Node> nodes;

    public SimpleNetwork(NetworkLink networkLink) {
        this.networkLink = networkLink;
        this.nodes = new HashSet<>();
    }

    @Override
    public NetworkLink getNetworkLink() { return networkLink; }
    @Override
    public Set<Node> getNodes() { return nodes; }

    @Override
    public void addNode(Node node) { nodes.add(node); }

    @Override
    public void removeNode(Node node) { nodes.remove(node); }
}
