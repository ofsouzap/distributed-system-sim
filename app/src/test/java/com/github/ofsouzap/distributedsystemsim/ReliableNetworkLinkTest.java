package com.github.ofsouzap.distributedsystemsim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.BroadcastTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.ReliableNetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour.SynchronousTimingBehaviour;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.testUtils.SimpleNetwork;
import com.github.ofsouzap.distributedsystemsim.testUtils.SimpleNode;
import com.github.ofsouzap.distributedsystemsim.testUtils.StaticSimulationContext;
import com.github.ofsouzap.distributedsystemsim.testUtils.StringMessage;

class ReliableNetworkLinkTest {
    private NetworkLink link;
    private Network net;
    private Node n1;
    private SimulationContext context;

    @BeforeEach void basicSetup() {
        link = new ReliableNetworkLink(new SynchronousTimingBehaviour(1));
        net = new SimpleNetwork(link);

        n1 = new SimpleNode();
        net.addNode(n1);

        context = new StaticSimulationContext();
    }

    @Test void generateSingleMessageDeliveries_deliveryExists() {
        // Act
        Set<MessageDeliveryEvent> outs = net.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), "message1"));

        // Assert
        assertEquals(1, outs.size());
        for (MessageDeliveryEvent evt : outs) {
            assertInstanceOf(StringMessage.class, evt.getMessage());
            assertEquals("message1", ((StringMessage)evt.getMessage()).getContent());
        }
    }

    @Test void generateManyMessageDeliveries_correctNumberOfDeliveries() {
        final int N = 100;

        // Act

        Set<MessageDeliveryEvent> outs = new HashSet<>();
        for (Integer i = 0; i < N; i++) {
            outs.addAll(net.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), i.toString())));
        }

        // Assert
        assertEquals(N, outs.size());
    }
}
