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
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour.PartiallySynchronousTimingBehaviour;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.Node;
import com.github.ofsouzap.distributedsystemsim.testUtils.SimpleNetwork;
import com.github.ofsouzap.distributedsystemsim.testUtils.SimpleNode;
import com.github.ofsouzap.distributedsystemsim.testUtils.StaticSimulationContext;
import com.github.ofsouzap.distributedsystemsim.testUtils.StringMessage;

class PartiallySynchronousNetworkLinkTest {
    public static final int synchronousLatencyBound = 3;
    public static final long behaviourSeed = 1;
    public static final long delaySeed = 2;

    private NetworkLink onlySynchronousLink;
    private Network onlySynchronousNet;
    private Node n1;
    private SimulationContext context;

    @BeforeEach void basicSetup() {
        onlySynchronousLink = new ReliableNetworkLink(new PartiallySynchronousTimingBehaviour(synchronousLatencyBound, behaviourSeed, delaySeed, 0.0));
        onlySynchronousNet = new SimpleNetwork(onlySynchronousLink);

        n1 = new SimpleNode();
        onlySynchronousNet.addNode(n1);

        context = new StaticSimulationContext();
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 4, 2, 7, 99, 1000, 9999 })
    void constructorSynchronousLatencyBound_valid_accepts(int x) {
        new PartiallySynchronousTimingBehaviour(x, 0.5);
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, -50 })
    void constructorSynchronousLatencyBound_invalid_rejects(int x) {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new PartiallySynchronousTimingBehaviour(x, 0.5);
            }
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, 0.1, 0.1235, 0.652, 0.5, 1.0 })
    void constructorAsynchronyChance_valid_accepts(double x) {
        new PartiallySynchronousTimingBehaviour(5, x);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -0.1, 1.1, 5.0, -0.4, -1.1, 6.0 })
    void constructorAsynchronyChance_invalid_rejects(double x) {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new PartiallySynchronousTimingBehaviour(5, x);
            }
        });
    }

    @Test void alwaysSynchronousLink_generateManyMessageDeliveries_deliveriesWithinBound() {
        // Arrange
        final int N = 100;
        Integer sendTime = context.getTime();

        // Act
        Set<MessageDeliveryEvent> outs = new HashSet<>();
        for (Integer i = 0; i < N; i++) {
            outs.addAll(onlySynchronousNet.generateMessageDeliveries(context, new StringMessage(n1, new BroadcastTarget(), i.toString())));
        }

        // Assert
        for (MessageDeliveryEvent evt : outs) {
            assertThat(evt.getDeliveryTime() - sendTime, both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(synchronousLatencyBound)));
        }
    }
}
