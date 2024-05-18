package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.weapon.Weapon;

/**
 * The TakeWeapon class represents an action of taking a weapon in the game Zombicide.
 * This action allows survivors to pick up weapons present in their current cell.
 */
public class TakeWeapon extends Action {

    /**
     * Constructor for the TakeWeapon class.
     *
     * @param description The description of the action.
     */
    public TakeWeapon(String description) {
        super(description);
    }

    /**
     * Method to execute the action of taking a weapon.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        InteractiveListChooser<Weapon> ilc = new InteractiveListChooser<Weapon>();
        Weapon chosenWeapon = ilc.choose("Which weapon do you choose?", s.getCell().getWeaponInCell());
        if (chosenWeapon != null) {
            s.setWeapon(chosenWeapon);
        } else {
            System.out.println("No weapon in this cell");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Take weapon";
    }
}
