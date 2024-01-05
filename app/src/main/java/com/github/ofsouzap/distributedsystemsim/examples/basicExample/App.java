package com.github.ofsouzap.distributedsystemsim.examples.basicExample;

import com.github.ofsouzap.distributedsystemsim.examples.ExampleApp;
import com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes.NullNode;
import com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes.TimedBroadcastNode;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationController;
import com.github.ofsouzap.distributedsystemsim.simulation.Simulator;
import com.github.ofsouzap.distributedsystemsim.simulation.logging.ConsoleLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.logging.EventLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.ReliableSynchronousNetwork;

public class App implements ExampleApp {
    @Override
    public void run(String[] args) {
        final int N = 10;

        // Create logger
        EventLogger logger = new ConsoleLogger();

        // Create network
        Network network = new ReliableSynchronousNetwork();
        network.addNode(new TimedBroadcastNode(1, "message1"));
        network.addNode(new TimedBroadcastNode(5, "message2"));
        network.addNode(new NullNode());

        // Create simulator
        SimulationController simulator = new Simulator(logger, network);

        // Simulate
        for (int i = 0; i < N; i++){
            simulator.step();
        }
    }
}
