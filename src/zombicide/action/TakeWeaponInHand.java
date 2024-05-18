package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.weapon.Weapon;

/**
 * The TakeWeaponInHand class represents an action of taking a weapon in hand in the game Zombicide.
 * This action allows survivors to select a weapon from their inventory and equip it as their current weapon.
 */
public class TakeWeaponInHand extends Action {

    /**
     * Constructor for the TakeWeaponInHand class.
     *
     * @param description The description of the action.
     */
    public TakeWeaponInHand(String description) {
        super(description);
    }

    /**
     * Method to execute the action of taking a weapon in hand.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        InteractiveListChooser<Weapon> ilc = new InteractiveListChooser<Weapon>();
        Weapon chosenWeapon = ilc.choose("Which weapon do you choose?", s.getWeapons());
        if (chosenWeapon != null) {
            s.setWeaponInHand(chosenWeapon);
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Take weapon in hand";
    }
}
