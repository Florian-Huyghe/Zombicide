package zombicide.action;

import zombicide.actor.Actor;

/**
 * The abstract class Action represents an action performed by an actor in the game Zombicide.
 * This class is intended to be extended by specific actions.
 */
public abstract class Action {
    
    /** Description of the action. */
    protected String description;

    /**
     * Constructor for the Action class.
     *
     * @param description The description of the action.
     */
    public Action(String description){
        this.description = description;
    }

    /**
     * Get the description of the action.
     *
     * @return The description of the action.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Override of the toString method to get the description of the action.
     *
     * @return The description of the action.
     */
    public String toString(){
        return this.description;
    }

    /**
     * Abstract method to be implemented in subclasses to perform the action.
     *
     * @param actor The actor performing the action.
     */
    public abstract void doIt(Actor actor);  
}
