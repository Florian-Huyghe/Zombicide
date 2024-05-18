package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;

/**
 * The MakeNoise class represents an action of making noise in the game Zombicide.
 * This action allows survivors to make noise, attracting zombies to their location.
 */
public class MakeNoise extends Action {

    /**
     * Constructor for the MakeNoise class.
     *
     * @param description The description of the action.
     */
    public MakeNoise(String description) {
        super(description);
    }

    /**
     * Method to execute the action of making noise.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        s.getCell().makeNoise();
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Make noise";
    }
}
