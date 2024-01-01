package distributed.system.sim.examples.basicExample.nodes;

import distributed.system.sim.simulation.MessageTransmission;
import distributed.system.sim.simulation.SimulationContext;
import distributed.system.sim.simulation.messages.Message;
import distributed.system.sim.simulation.network.nodes.Node;
import distributed.system.sim.simulation.network.nodes.UpdateIntent;
import distributed.system.sim.simulation.network.nodes.behaviours.NodeBehaviour;

public class TimedBroadcastNode implements Node {
    protected final NodeBehaviour nodeBehaviour;
    protected final Integer sendTime;
    protected final Message msg;

    public TimedBroadcastNode(Integer sendTime, Message msg) {
        this.nodeBehaviour = new NiceNodeBehaviour();
        this.sendTime = sendTime;
        this.msg = msg;
    }

    @Override
    public NodeBehaviour getNodeBehaviour() { return nodeBehaviour; }

    @Override
    public void receiveMessage(Message msg) { }

    @Override
    public UpdateIntent update(SimulationContext context) {
        UpdateIntent intent = new UpdateIntent();

        if (context.getTime() == sendTime) {
            intent.messageTransmissions.add(new MessageTransmission(this, msg));
        }

        return intent;
    }
}
