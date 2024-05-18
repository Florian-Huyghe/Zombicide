package zombicide.tool;

import zombicide.action.HealPotion;

/**
 * Represents a healing vial tool.
 */
public class HealingVial extends Tool {

    /**
     * Constructor for the HealingVial class.
     * Initializes the healing vial with a name and an action.
     */
    public HealingVial() {
        super("Healing Vial");
        this.action = new HealPotion("heal potion");
    }

    /**
     * Returns a string representation of the healing vial.
     *
     * @return The name of the healing vial.
     */
    public String toString(){
        return this.name +"";
    }
}
