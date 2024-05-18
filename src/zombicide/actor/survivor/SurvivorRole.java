package zombicide.actor.survivor;

import zombicide.action.Action;

/**
 * Interface defining a Survivor Role.
 */
public interface SurvivorRole {

    /**
     * Retrieves a string representation of the Survivor Role.
     * 
     * @return a string representing the Survivor Role
     */
    String toString();

    /**
     * Retrieves the available actions associated with the Survivor Role.
     * 
     * @return the available actions associated with the Survivor Role
     */
    Action getAvailableActions();
}
