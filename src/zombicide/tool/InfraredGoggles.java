package zombicide.tool;

import zombicide.action.UseInfraGoggles;

/**
 * Represents a tool for infrared goggles.
 */
public class InfraredGoggles extends Tool {

    /**
     * Constructor for the InfraredGoggles class.
     * Initializes the infrared goggles with a name and an action.
     */
    public InfraredGoggles() {
        super("Infrared Goggles");
        this.action = new UseInfraGoggles("infrared goggles");
    }

    /**
     * Returns a string representation of the infrared goggles.
     *
     * @return The name of the infrared goggles.
     */
    public String toString(){
        return this.name +"";
    }
}
