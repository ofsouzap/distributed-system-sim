package distributed.system.sim.examples.basicExample.nodes;

import distributed.system.sim.simulation.network.nodes.behaviours.CrashBehaviour;
import distributed.system.sim.simulation.network.nodes.behaviours.NodeBehaviour;

public class NiceNodeBehaviour implements NodeBehaviour {
    @Override
    public CrashBehaviour getCrashBehaviour() { return CrashBehaviour.Stop; }
}
