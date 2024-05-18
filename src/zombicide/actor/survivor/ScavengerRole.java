package zombicide.actor.survivor;

import zombicide.action.Action;

/**
 * The ScavengerRole class represents the role of a survivor specialized in scavenging resources.
 */
public class ScavengerRole implements SurvivorRole {
    
    private String name = "Scavenger"; 
    private Action availableActions; 

    /**
     * Constructs a ScavengerRole object without initializing any specific actions.
     */
    public ScavengerRole(){}

    /**
     * Returns a string representation of the Scavenger role.
     * 
     * @return The name of the Scavenger role.
     */
    public String toString(){
        return this.name; 
    }

    /**
     * Retrieves the available actions for the Scavenger role.
     * 
     * @return The available actions for the Scavenger survivor role.
     */
    public Action getAvailableActions(){
        return this.availableActions; 
    }
}
