package zombicide.actor.survivor;

import zombicide.action.Action;

/**
 * The LuckyRole class represents the role of a survivor with exceptional luck.
 */
public class LuckyRole implements SurvivorRole {
    
    private String name = "Lucky";
    private Action availableActions; 

    /**
     * Constructs a LuckyRole object without initializing any specific actions.
     */
    public LuckyRole(){
        
    }

    /**
     * Returns a string representation of the Lucky role.
     * 
     * @return The name of the Lucky role.
     */
    public String toString(){
        return this.name; 
    }

    /**
     * Retrieves the available actions for the Lucky role.
     * 
     * @return The available actions for the Lucky survivor role.
     */
    public Action getAvailableActions(){
        return this.availableActions; 
    }
}
