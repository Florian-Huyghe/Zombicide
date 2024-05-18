package zombicide.tool;

import zombicide.action.HealKit;

/**
 * Represents a first aid kit tool.
 */
public class FirstAidKit extends Tool {

    /**
     * Constructor for the FirstAidKit class.
     * Initializes the first aid kit with a name and an action.
     */
    public FirstAidKit() {
        super("First Aid Kit");
        action = new HealKit("Use heal kit");
    }

    /**
     * Returns a string representation of the first aid kit.
     *
     * @return The name of the first aid kit.
     */
    public String toString(){
        return this.name +"";
    }
}
