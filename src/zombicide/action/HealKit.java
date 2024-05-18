package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.tool.FirstAidKit;

/**
 * The HealKit class represents an action of using a first aid kit to heal another survivor in the game Zombicide.
 * This action allows survivors to heal other survivors within their vicinity using a first aid kit.
 */
public class HealKit extends Action {

    /**
     * Constructor for the HealKit class.
     *
     * @param description The description of the action.
     */
    public HealKit(String description) {
        super(description);
    }

    /**
     * Method to execute the healing action using a first aid kit.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        if (s.getToolInHand() instanceof FirstAidKit) {
            InteractiveListChooser<Survivor> ilc = new InteractiveListChooser<Survivor>();
            Survivor chosenSurvivor = ilc.choose("Which Survivor do you choose?", s.getCell().getSurvivorInCell());
            if (chosenSurvivor != null) {
                chosenSurvivor.healing(1);
                s.decrementedActionPoints();
                s.removeAction(s.getToolInHand());
            }
        } else {
            System.out.println("Please select a first aid kit in your hand");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "HealKit";
    }
}
