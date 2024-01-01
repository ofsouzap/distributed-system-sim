package distributed.system.sim.simulation;

import distributed.system.sim.simulation.network.Network;

public interface SimulationController {
    public Network getNetwork();
    public SimulationContext getContext();
    /** Step the simulation forwards one time unit */
    public void step();
}
