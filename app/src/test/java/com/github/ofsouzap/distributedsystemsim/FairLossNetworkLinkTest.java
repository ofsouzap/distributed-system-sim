package com.github.ofsouzap.distributedsystemsim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.targets.BroadcastTarget;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.FairLossNetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.NetworkLink;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour.SynchronousTimingBehaviour;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.testUtils.SimpleNetwork;
import com.github.ofsouzap.distributedsystemsim.testUtils.SimpleNode;
import com.github.ofsouzap.distributedsystemsim.testUtils.StaticSimulationContext;
import com.github.ofsouzap.distributedsystemsim.testUtils.StringMessage;

class FairLossNetworkLinkTest {
    private NetworkLink neverInterfereLink;
    private Network neverInterfereNet;
    private NetworkLink alwaysLoseLink;
    private Network alwaysLoseNet;
    private NetworkLink sometimesDuplicateLink;
    private Network sometimesDuplicateNet;
    private Node n1;
    private SimulationContext context;

    @BeforeEach void basicSetup() {
        neverInterfereLink = new FairLossNetworkLink(new SynchronousTimingBehaviour(1), 1, 0, 0);
        neverInterfereNet = new SimpleNetwork(neverInterfereLink);

        alwaysLoseLink = new FairLossNetworkLink(new SynchronousTimingBehaviour(1), 0, 1, 0);
        alwaysLoseNet = new SimpleNetwork(alwaysLoseLink);

        sometimesDuplicateLink = new FairLossNetworkLink(new SynchronousTimingBehaviour(1), 1, 0, 1);
        sometimesDuplicateNet = new SimpleNetwork(sometimesDuplicateLink);

        n1 = new SimpleNode();
        neverInterfereNet.addNode(n1);

        context = new StaticSimulationContext();
    }

    @Test void neverInterfere_generateSingleMessageDeliveries_deliveryExists() {
        // Act
        Set<MessageDeliveryEvent> outs = neverInterfereNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), "message1"));

        // Assert
        assertEquals(1, outs.size());
        for (MessageDeliveryEvent evt : outs) {
            assertInstanceOf(StringMessage.class, evt.getMessage());
            assertEquals("message1", ((StringMessage)evt.getMessage()).getContent());
        }
    }

    @Test void neverInterfere_generateManyMessageDeliveries_correctNumberOfDeliveries() {
        final int N = 100;

        // Act
        Set<MessageDeliveryEvent> outs = new HashSet<>();
        for (Integer i = 0; i < N; i++) {
            outs.addAll(neverInterfereNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), i.toString())));
        }

        // Assert
        assertEquals(N, outs.size());
    }

    @Test void alwaysLose_generateSingleMessageDeliveries_deliveryExists() {
        // Act
        Set<MessageDeliveryEvent> outs = alwaysLoseNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), "message1"));

        // Assert
        assertEquals(0, outs.size());
    }

    @Test void alwaysLose_generateManyMessageDeliveries_correctNumberOfDeliveries() {
        final int N = 100;

        // Act
        Set<MessageDeliveryEvent> outs = new HashSet<>();
        for (Integer i = 0; i < N; i++) {
            outs.addAll(alwaysLoseNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), i.toString())));
        }

        // Assert
        assertEquals(0, outs.size());
    }

    @Test void sometimesDuplicate_generateSingleMessageDeliveries_deliveryExists() {
        // Act
        Set<MessageDeliveryEvent> outs = sometimesDuplicateNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), "message1"));

        // Assert
        assertThat(outs.size(), greaterThanOrEqualTo(1));
        for (MessageDeliveryEvent evt : outs) {
            assertInstanceOf(StringMessage.class, evt.getMessage());
            assertEquals("message1", ((StringMessage)evt.getMessage()).getContent());
        }
    }

    @Test void sometimesDuplicate_generateManyMessageDeliveries_noLessDeliveries() {
        final int N = 100;

        // Act
        Set<MessageDeliveryEvent> outs = new HashSet<>();
        for (Integer i = 0; i < N; i++) {
            outs.addAll(sometimesDuplicateNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), i.toString())));
        }

        // Assert
        assertThat(outs.size(), greaterThanOrEqualTo(N));
    }
}
