package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.listchooser.InteractiveListChooser;

/**
 * The Heal class represents an action of healing performed by a survivor in the game Zombicide.
 * This action allows survivors to heal other survivors within their vicinity.
 */
public class Heal extends Action {

    /**
     * Constructor for the Heal class.
     *
     * @param description The description of the action.
     */
    public Heal(String description) {
        super(description);
    }

    /**
     * Method to execute the healing action.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        InteractiveListChooser<Survivor> ilc = new InteractiveListChooser<Survivor>();
        Survivor chosenSurvivor = ilc.choose("Which Survivor do you choose?", s.getCell().getSurvivorInCell());
        if (chosenSurvivor != null) {
            chosenSurvivor.healing(1);
            s.decrementedActionPoints();
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Heal";
    }
}
