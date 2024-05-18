package zombicide.tool;

import zombicide.action.*;

/**
 * Represents a tool in the game.
 */
public abstract class Tool {
    protected String name;
    protected Action action;

    /**
     * Constructor for the Tool class.
     *
     * @param name The name of the tool.
     */
    public Tool(String name) {
        this.name = name;
    }

    /**
     * Gets the action associated with the tool.
     *
     * @return The action of the tool.
     */
    public Action getAction(){
        return this.action;
    }
}
