package com.github.ofsouzap.distributedsystemsim.simulation.network.links;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.ofsouzap.distributedsystemsim.simulation.SimulationContext;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.Message;
import com.github.ofsouzap.distributedsystemsim.simulation.messages.MessageDeliveryEvent;
import com.github.ofsouzap.distributedsystemsim.simulation.network.links.linkTimingBehaviour.LinkTimingBehaviour;

public class FairLossNetworkLink implements NetworkLink {
    public static final int defaultMessageNoInterferenceChance = 8;
    public static final int defaultMessageLossChance = 3;
    public static final int defaultMessageDuplicateChance = 1;

    protected final LinkTimingBehaviour timingBehaviour;
    protected final int messageNoInterferenceChance;
    protected final int messageLossChance;
    protected final int messageDuplicateChance;

    public FairLossNetworkLink(LinkTimingBehaviour timingBehaviour, Integer messageNoInterferenceChance, Integer messageLossChance, Integer messageDuplicateChance) {
        if (messageNoInterferenceChance < 0 || messageLossChance < 0 || messageDuplicateChance < 0)
            throw new IllegalArgumentException("Chances must be non-negative");
        if (messageNoInterferenceChance + messageLossChance + messageDuplicateChance <= 0)
            throw new IllegalArgumentException("Must have at least one positive chance");

        this.timingBehaviour = timingBehaviour;
        this.messageNoInterferenceChance = (messageNoInterferenceChance != null) ? messageNoInterferenceChance : defaultMessageNoInterferenceChance;
        this.messageLossChance = (messageLossChance != null) ? messageLossChance : defaultMessageLossChance;
        this.messageDuplicateChance = (messageDuplicateChance != null) ? messageDuplicateChance : defaultMessageDuplicateChance;
    }

    public FairLossNetworkLink(LinkTimingBehaviour timingBehaviour) {
        this(timingBehaviour, null, null, null);
    }

    @Override
    public LinkTimingBehaviour getTimingBehaviour() {
        return timingBehaviour;
    }

    /** Chance of a messsage not being altered.
     * Chances are interpreted as relative probabilities relative to the other effects' chances
     */
    public int getMessageNoInterferenceChance() {
        return messageNoInterferenceChance;
    }

    /** Chance of a message being lost.
     * Chances are interpreted as relative probabilities relative to the other effects' chances
     */
    public int getMessageLossChance() {
        return messageLossChance;
    }

    /** Get chance of a message being duplicated.
     * Chances are interpreted as relative probabilities relative to the other effects' chances
     * The two copies of a duplicated message then have random effects recursively applied to them so may themselves be duplicated */
    public int getMessageDuplicateChance() {
        return messageDuplicateChance;
    }

    protected int getTotalChanceVal() {
        return getMessageNoInterferenceChance() + getMessageLossChance() + getMessageDuplicateChance();
    }

    /** Apply duplication and loss effects to a single message using weighted probabilities */
    protected Set<Message> applyEffectsByChance(Message x) {
        int val = new Random().nextInt(this.getTotalChanceVal());

        // Leave the message as-is
        if (val < getMessageNoInterferenceChance()) {
            return Collections.singleton(x);
        } else {
            val -= getMessageNoInterferenceChance();
        }

        // Lose the message
        if (val < getMessageLossChance()) {
            return Collections.emptySet();
        } else {
            val -= getMessageLossChance();
        }

        // Otherwise, duplicate the message
        Set<Message> xs = new HashSet<>();
        xs.addAll(applyEffectsByChance(x)); // Recuse on the first version
        xs.addAll(applyEffectsByChance(x)); // Recuse on the second version
        return xs;
    }

    @Override
    public Set<MessageDeliveryEvent> generateMessageDeliveries(SimulationContext context, Message msg) {
        Integer deliveryTime = getTimingBehaviour().generateDeliveryTime(context);
        return applyEffectsByChance(msg)
            .stream()
            .map(new Function<Message, MessageDeliveryEvent>() {
                public MessageDeliveryEvent apply(Message x) {
                    return new MessageDeliveryEvent(deliveryTime, x.getIntendedTarget(), x);
                };
            })
            .collect(Collectors.toSet());
    }
}
