package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.tool.Map;

/**
 * The UseMap class represents an action of using a map in the game Zombicide.
 * This action allows survivors to use a map to view the entire grid.
 */
public class UseMap extends Action {

    /**
     * Constructor for the UseMap class.
     *
     * @param description The description of the action.
     */
    public UseMap(String description) {
        super(description);
    }

    /**
     * Method to execute the action of using a map.
     * This method allows survivors to view the entire grid.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;

        // Check if the survivor has a map in hand
        if (s.getToolInHand() instanceof Map) {
            s.getGrid().afficherCarteAll();
            s.decrementedActionPoints();
            s.getCell().makeNoise();
            s.removeAction(s.getToolInHand());
        } else {
            System.out.println("Please select a map in your hand.");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Use map";
    }
}
