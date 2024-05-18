package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;

/**
 * The UseInfraGoggles class represents an action of using infrared goggles in the game Zombicide.
 * This action allows survivors to use infrared goggles to observe neighboring cells.
 */
public class UseInfraGoggles extends Action {

    /**
     * Constructor for the UseInfraGoggles class.
     *
     * @param description The description of the action.
     */
    public UseInfraGoggles(String description) {
        super(description);
    }

    /**
     * Method to execute the action of using infrared goggles.
     * This method allows survivors to observe neighboring cells.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        // Iterate through neighboring cells and print their information
        for (int i = 0; i < s.getCell().getNeighborCells().size(); i++) {
            s.getCell().getNeighborCells().get(i).toString1();
        }
        // Decrement action points and remove the tool from survivor's inventory
        s.decrementedActionPoints();
        s.removeAction(s.getToolInHand());
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Use infrared goggles";
    }
}
