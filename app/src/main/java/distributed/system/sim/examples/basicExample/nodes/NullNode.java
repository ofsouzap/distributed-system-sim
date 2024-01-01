package distributed.system.sim.examples.basicExample.nodes;

import distributed.system.sim.simulation.SimulationContext;
import distributed.system.sim.simulation.messages.Message;
import distributed.system.sim.simulation.network.nodes.Node;
import distributed.system.sim.simulation.network.nodes.UpdateIntent;
import distributed.system.sim.simulation.network.nodes.behaviours.NodeBehaviour;

public class NullNode implements Node {
    private NiceNodeBehaviour nodeBehaviour;

    public NullNode() {
        this.nodeBehaviour = new NiceNodeBehaviour();
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    @Override
    public void receiveMessage(Message msg) { }

    @Override
    public UpdateIntent update(SimulationContext context) { return null; }

}
