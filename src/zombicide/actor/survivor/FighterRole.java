package zombicide.actor.survivor;

import zombicide.action.Action;
import zombicide.action.FightAction;

/**
 * The FighterRole class represents the role of a survivor specialized in combat.
 * 
 * Survivors with the Fighter role excel in combat situations, often equipped with powerful weapons. 
 * This class initializes and provides access to actions
 * available specifically for survivors with the Fighter role.
 */
public class FighterRole implements SurvivorRole {

    private String name = "Fighter"; 
    private Action availableActions; 

    /**
     * Constructs a FighterRole object and initializes available actions for the fighter survivor.
     */
    public FighterRole(){
        FightAction  A = new FightAction(this.name); 
        this.availableActions = A; 
    }

    /**
     * Returns a string representation of the fighter role.
     * 
     * @return The name of the fighter role.
     */
    public String toString(){
        return this.name; 
    }

    /**
     * Retrieves the available actions for the fighter role.
     * 
     * @return The available actions for the fighter survivor role.
     */
    public Action getAvailableActions(){
        return this.availableActions;
    }
}
