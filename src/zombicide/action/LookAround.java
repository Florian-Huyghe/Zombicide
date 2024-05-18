package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;


/**
 * The LookAround class represents an action of looking around.
 */
public class LookAround extends Action{


    /**
     * Constructor for the LookAround class.
     *
     * @param description The description of the action.
     */
    public LookAround(String description){
        super(description);
    }

    /**
     * Method to execute the action of looking.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        s.getCell().AfficheCell();
        
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Look Around";
    }
}
