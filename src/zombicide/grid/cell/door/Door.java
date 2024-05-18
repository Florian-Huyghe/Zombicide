package zombicide.grid.cell.door;

/**
 * Represents a door in a cell.
 */
public class Door {

    protected String position; // The position of the door (e.g., "North", "South", "East", "West")
    private boolean open; // Indicates whether the door is open or closed

    /**
     * Constructor for the Door class.
     * Initializes the door as closed.
     */
    public Door(){
        this.open = false;
    }

    /**
     * Sets the position of the door.
     *
     * @param p The position of the door (e.g., "North", "South", "East", "West").
     */
    public void setPosition(String p){
        this.position = p;
    }

    /**
     * Opens the door.
     */
    public void openTheDoor(){
        this.open = true;
    }
    
    /**
     * Checks if the door is open.
     *
     * @return true if the door is open, false otherwise.
     */
    public boolean isOpen(){
        return this.open;
    }

    /**
     * Returns a string representation of the door.
     *
     * @return A string representing the state (open/closed) and position of the door.
     */
    public String toString(){
        if(this.position != null){
            return "The door is open: " + this.open + " | " + this.position;
        }
        return "The door is open: " + this.open + " | " ;
    } 
}
