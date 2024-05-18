package zombicide.grid.cell.door;

/**
 * Represents an open door in a cell.
 */
public class OpenDoor extends Door {
    
    private final boolean open = true; // Indicates that the door is open

    /**
     * Constructor for the OpenDoor class.
     * Initializes the open door.
     */
    public OpenDoor(){
        super();
    }

    /**
     * Checks if the door is open.
     *
     * @return Always returns true, indicating the door is open.
     */
    @Override
    public boolean isOpen() {
        return this.open;
    }

    /**
     * Attempts to open the door.
     * Prints a message indicating that the door is already open.
     */
    @Override
    public void openTheDoor(){
        System.out.println("This door is already open.");
    }

    /**
     * Returns a string representation of the open door.
     *
     * @return A string indicating that the door is open.
     */
    @Override
    public String toString(){
        return "The door is open: true |";
    }

}
