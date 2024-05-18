package zombicide.tool;

import zombicide.action.UseMasterKey;

/**
 * Represents a master key tool.
 */
public class MasterKey extends Tool {
    
    /**
     * Constructor for the MasterKey class.
     * Initializes the master key with a name and an action to use it.
     */
    public MasterKey() {
        super("Master Key");
        this.action = new UseMasterKey("use master key");
    }

    /**
     * Returns a string representation of the master key.
     *
     * @return The name of the master key.
     */
    public String toString(){
        return this.name +"";
    }
}
