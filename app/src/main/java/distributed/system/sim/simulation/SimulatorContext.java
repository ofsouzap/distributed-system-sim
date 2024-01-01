package distributed.system.sim.simulation;

public class SimulatorContext implements SimulationContext {
    public Integer time;

    public SimulatorContext() {
        this.time = 0;
    }

    @Override
    public Integer getTime() { return time; }

    public void incrementTime() { time++; }
}
