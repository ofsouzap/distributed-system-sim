package distributed.system.sim.examples.basicExample;

import distributed.system.sim.simulation.messages.Message;

public class StringMessage implements Message {
    protected final String content;

    public StringMessage(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
