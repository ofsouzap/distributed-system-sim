package com.github.ofsouzap.distributedsystemsim;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

class SynchronousNetworkLinkTest {
    public static final int latencyBound = 10;
    public static final long seed = 1;

    private NetworkLink link;
    private Network net;
    private Node n1;
    private SimulationContext context;

    @BeforeEach void basicSetup() {
        link = new ReliableNetworkLink(new SynchronousTimingBehaviour(latencyBound, seed));
        net = new SimpleNetwork(link);

        n1 = new SimpleNode();
        net.addNode(n1);

        context = new StaticSimulationContext();
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 4, 2, 7, 99, 1000, 9999 })
    void constructorLatencyBound_valid_accepts(int x) {
        new SynchronousTimingBehaviour(x);
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, -50 })
    void constructorLatencyBound_invalid_rejects(int x) {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new SynchronousTimingBehaviour(x);
            }
        });
    }

    @Test void generateManyMessageDeliveries_deliveriesWithinBound() {
        // Arrange
        final int N = 100;
        Integer sendTime = context.getTime();

        // Act
        Set<MessageDeliveryEvent> outs = new HashSet<>();
        for (Integer i = 0; i < N; i++) {
            outs.addAll(net.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), i.toString())));
        }

        // Assert
        for (MessageDeliveryEvent evt : outs) {
            assertThat(evt.getDeliveryTime() - sendTime, both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(latencyBound)));
        }
    }
}
