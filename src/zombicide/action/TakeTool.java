package zombicide.action;

import zombicide.tool.Tool;
import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.listchooser.InteractiveListChooser;

/**
 * The TakeTool class represents an action of taking a tool in the game Zombicide.
 * This action allows survivors to pick up tools present in their current cell.
 */
public class TakeTool extends Action {

    /**
     * Constructor for the TakeTool class.
     *
     * @param description The description of the action.
     */
    public TakeTool(String description) {
        super(description);
    }

    /**
     * Method to execute the action of taking a tool.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        InteractiveListChooser<Tool> ilc = new InteractiveListChooser<Tool>();
        Tool chosenTool = ilc.choose("Which tool do you choose?", s.getCell().getToolsInCell());
        if (chosenTool != null) {
            s.setTools(chosenTool);
        } else {
            System.out.println("No tool in this cell");
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Take tool";
    }
}
