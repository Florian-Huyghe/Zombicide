package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;

/**
 * The Search class represents an action of searching in the game Zombicide.
 * This action allows survivors to search for items or zombies in their current cell.
 */
public class Search extends Action {

    /**
     * Constructor for the Search class.
     *
     * @param description The description of the action.
     */
    public Search(String description) {
        super(description);
    }

    /**
     * Method to execute the action of searching.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        if (!s.getCell().isStreet()) {
            System.out.println(s.getCell().toString1());
        } else {
            System.out.println("You're in a Street");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Search";
    }
}
