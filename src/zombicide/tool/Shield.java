package zombicide.tool;

import zombicide.action.UseShield;


/**
 * The Shield class represents a Shield,
 * The Shield give a HP bonus when you use it 
 */
public class Shield extends Tool {
    
    /**
     * This code snippet represents a constructor for the Shield class in the zombicide.tool package.
     * It initializes a Shield object and sets its action to a UseMasterKey object with the description "use master key".
     */
    public Shield(){
        super("Shield");
        this.action = new UseShield("Use Shield");
    }


    /**
     * Returns a string representation of the Shield object.
     * 
     * @return the name of the Shield object
     */
    public String toString(){
        return this.name +"";
    }
}
