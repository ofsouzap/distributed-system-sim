/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.ofsouzap.distributedsystemsim;

import com.github.ofsouzap.distributedsystemsim.examples.basicExample.network.BasicNetwork;
import com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes.NullNode;
import com.github.ofsouzap.distributedsystemsim.examples.basicExample.nodes.TimedBroadcastNode;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationController;
import com.github.ofsouzap.distributedsystemsim.simulation.Simulator;
import com.github.ofsouzap.distributedsystemsim.simulation.logging.ConsoleLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.logging.EventLogger;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;

public class App {
    public static void main(String[] args) {
        final int N = 10;

        // Create logger
        EventLogger logger = new ConsoleLogger();

        // Create network
        Network network = new BasicNetwork();
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
