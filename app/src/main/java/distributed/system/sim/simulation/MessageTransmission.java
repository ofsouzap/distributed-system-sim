package distributed.system.sim.simulation;

import distributed.system.sim.simulation.messages.Message;
import distributed.system.sim.simulation.network.nodes.Node;

public class MessageTransmission {
    protected final Node src;
    protected final Message msg;

    public MessageTransmission(Node src, Message msg) {
        this.src = src;
        this.msg = msg;
    }

    public Node getSrc() {
        return src;
    }

    public Message getMsg() {
        return msg;
    }
}
