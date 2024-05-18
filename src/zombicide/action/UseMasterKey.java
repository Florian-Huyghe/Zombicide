package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.grid.cell.door.Door;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.tool.MasterKey;

/**
 * The UseMasterKey class represents an action of using a master key in the game Zombicide.
 * This action allows survivors to open locked doors using a master key.
 */
public class UseMasterKey extends Action {

    /**
     * Constructor for the UseMasterKey class.
     *
     * @param description The description of the action.
     */
    public UseMasterKey(String description) {
        super(description);
    }

    /**
     * Method to execute the action of using a master key.
     * This method allows survivors to open locked doors using a master key.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;

        if (s.getToolInHand() instanceof MasterKey) {
            InteractiveListChooser<Door> ilc = new InteractiveListChooser<Door>();
            Door chosenDoor = ilc.choose("Which door do you choose ?", s.getCell().getDoors());
            if (chosenDoor != null) {
                
                chosenDoor.openTheDoor();
                s.decrementedActionPoints();
                s.removeAction(s.getToolInHand());
            }
        } else {
            System.out.println("Please select a master key in your hand.");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Use master key";
    }
}
