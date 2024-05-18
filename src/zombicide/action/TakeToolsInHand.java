package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.tool.Tool;

/**
 * The TakeToolsInHand class represents an action of taking a tool in hand in the game Zombicide.
 * This action allows survivors to select a tool from their inventory and equip it as their current tool in hand.
 */
public class TakeToolsInHand extends Action {

    /**
     * Constructor for the TakeToolsInHand class.
     *
     * @param description The description of the action.
     */
    public TakeToolsInHand(String description) {
        super(description);
    }

    /**
     * Method to execute the action of taking a tool in hand.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        InteractiveListChooser<Tool> ilc = new InteractiveListChooser<Tool>();
        Tool chosenTool = ilc.choose("Which tool do you choose?", s.getTools());
        if (chosenTool != null) {
            s.setToolInHand(chosenTool);
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Take tool in hand";
    }
}
