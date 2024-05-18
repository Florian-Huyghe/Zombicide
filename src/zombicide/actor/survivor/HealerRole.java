package zombicide.actor.survivor;

import zombicide.action.Action;
import zombicide.action.Heal;

/**
 * The HealerRole class represents the role of a survivor specialized in providing healing support.
 * 
 * Survivors with the Healer role excel in providing medical assistance to fellow survivors, 
 * using their skills to heal injuries and ailments. This class initializes and provides
 * access to actions available specifically for survivors with the Healer role.
 */
public class HealerRole implements SurvivorRole {
    
    private String name = "Healer"; 
    private Action availableActions; 

    /**
     * Constructs a HealerRole object and initializes available actions for the healer survivor.
     */
    public HealerRole(){
        Heal h = new Heal("Heal a survivor");
        this.availableActions = h; 
    }

    /**
     * Returns a string representation of the healer role.
     * 
     * @return The name of the healer role.
     */
    public String toString(){
        return this.name; 
    }

    /**
     * Retrieves the available actions for the healer role.
     * 
     * @return The available actions for the healer survivor role.
     */
    public Action getAvailableActions(){
        return this.availableActions; 
    }
}
