package com.github.ofsouzap.distributedsystemsim.simulation.network.nodes;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.network.nodes.behaviours.NodeBehaviour;

public interface Node {
    public NodeBehaviour getNodeBehaviour();
    public void receiveMessage(Message msg);
    /**
     * Update the node for a single timestep.
     * This will be run once for each simulation step.
     * @param context The current context of the simulation
     * @return The resulting actions that the node would like to take. The simulator should handle this
     */
    public UpdateIntent update(SimulationContext context);
}
