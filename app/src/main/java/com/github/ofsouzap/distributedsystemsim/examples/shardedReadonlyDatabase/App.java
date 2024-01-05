package com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase;

import java.util.Collections;
import java.util.Map;

import com.github.ofsouzap.distributedsystemsim.examples.ExampleApp;
import com.github.ofsouzap.distributedsystemsim.examples.Scanning;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes.ClientNode;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes.ClientRequest;
import com.github.ofsouzap.distributedsystemsim.examples.shardedReadonlyDatabase.nodes.ServerNode;
import com.github.ofsouzap.distributedsystemsim.simulation.SimulationController;
import com.github.ofsouzap.distributedsystemsim.simulation.Simulator;
import com.github.ofsouzap.distributedsystemsim.simulation.network.Network;
import com.github.ofsouzap.distributedsystemsim.simulation.network.ReliableSynchronousNetwork;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.CrashStopNodeBehvaiour;

public class App implements ExampleApp {
    public static final int maxRequestWaitTime = 100;

    protected FlaggingLogger logger;
    protected Network network;
    protected ClientNode client;
    protected SimulationController simulator;

    @Override
    public void run(String[] args) {
        // Create logger
        this.logger = new FlaggingLogger();

        // Create network

        this.network = new ReliableSynchronousNetwork();
        this.network.addNode(new ServerNode(new CrashStopNodeBehvaiour(), Map.of(
            "1", "one",
            "story-ette", "this is a short story thing"
        )));
        this.network.addNode(new ServerNode(new CrashStopNodeBehvaiour(), Map.of(
            "2", "two",
            "poem", "there was a woman,\nwho lived in a shoe,\nand stuck stickers with glue"
        )));
        this.network.addNode(new ServerNode(new CrashStopNodeBehvaiour(), Collections.emptyMap()));

        this.client = new ClientNode(new CrashStopNodeBehvaiour());
        this.network.addNode(this.client);

        // Create simulator
        this.simulator = new Simulator(this.logger, this.network);

        // Run main loop
        mainLoop();
    }

    protected void mainLoop() {
        while (mainLoopIteration()) { } // Loop until exited
    }

    private enum MenuChoice {
        Quit,
        SendRequest
    }

    private MenuChoice getUserMenuChoice() {
        System.out.println(
            "====================\n" +
            "Q) Quit\n" +
            "S) Send a request\n" +
            "====================\n" +
            ""
        );

        while (true) {
            String inp;
            inp = Scanning.scanner.nextLine().toUpperCase();

            switch (inp) {
                case "Q":
                    return MenuChoice.Quit;
                case "S":
                    return MenuChoice.SendRequest;
                case "":
                break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }
        }
    }

    private String getSendRequestKeyInput() {
        System.out.println("Enter key to request value for:");
        return Scanning.scanner.nextLine();
    }

    protected boolean mainLoopIteration() {
        if (getUserInteraction()) {
            simulateStepUntilBreakOrMax(App.maxRequestWaitTime);

            // Check if any requests timed-out and report this if so
            for (ClientRequest req : client.popTimedOutRequests(simulator.getContext().getTime(), maxRequestWaitTime)) {
                handleTimedOutRequest(req.getKey());
            }

            return true;
        } else {
            return false;
        }
    }

    protected boolean getUserInteraction() {
        switch (getUserMenuChoice()) {
            case Quit:
                return false;
            case SendRequest:
                String key = getSendRequestKeyInput();
                client.requestData(simulator.getContext(), key, this::responseCallback);
                return true;
            default:
                return false;
        }
    }

    protected void simulateStepUntilBreakOrMax(int maxSteps) {
        for (int i = 0; i < maxSteps && !logger.isReadyToBreak(); i++) {
            simulator.step();
        }
        logger.clearReadyToBreak();
    }

    protected void handleTimedOutRequest(String key) {
        System.out.println("[!] Request for key " + key + " has timed out.");
    }

    protected void responseCallback(String key, Object value) {
        System.out.println("[✓] Response received for key " + key + ":\n" + value.toString());
    }
}
