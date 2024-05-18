package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.tool.HealingVial;

/**
 * The HealPotion class represents an action of using a healing potion (healing vial) to heal a survivor in the game Zombicide.
 * This action allows survivors to heal themselves or other survivors using a healing vial.
 */
public class HealPotion extends Action {

    /**
     * Constructor for the HealPotion class.
     *
     * @param description The description of the action.
     */
    public HealPotion(String description) {
        super(description);
    }

    /**
     * Method to execute the healing action using a healing vial.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        if (s.getToolInHand() instanceof HealingVial) {
            s.healing(1);
            s.decrementedActionPoints();
            s.removeAction(s.getToolInHand());
        } else {
            System.out.println("Please select a healing vial in your hand");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "HealPotion";
    }
}
