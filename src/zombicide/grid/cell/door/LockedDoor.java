package zombicide.grid.cell.door;

/**
 * Represents a locked door in a cell.
 */
public class LockedDoor extends Door {
    
    private final boolean open = false; // Indicates that the locked door is always closed

    /**
     * Constructor for the LockedDoor class.
     * Initializes the locked door as closed.
     */
    public LockedDoor(){
        super();
    }

    /**
     * Checks if the locked door is open.
     * Since it's a locked door, it's always closed.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isOpen() {
        return this.open;
    }

    /**
     * Attempts to open the locked door.
     * Prints a message indicating that the door cannot be opened.
     */
    @Override
    public void openTheDoor(){
        System.out.println("This door cannot be opened.");
    }
}
