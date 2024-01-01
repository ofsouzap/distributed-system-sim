package distributed.system.sim.simulation;

import distributed.system.sim.simulation.messages.Message;
import distributed.system.sim.simulation.network.nodes.Node;

public class MessageDeliveryEvent {
    protected Integer deliveryTime;
    protected Node dst;
    protected Message message;

    public MessageDeliveryEvent(Integer deliveryTime, Node dst, Message message) {
        this.deliveryTime = deliveryTime;
        this.dst = dst;
        this.message = message;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public Node getDst() {
        return dst;
    }

    public Message getMessage() {
        return message;
    }
}
