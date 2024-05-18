package zombicide.grid.cell;

/**
 * Represents a generic Room cell in the game grid.
 */
public class Room extends Cell{

    /**
     * Constructor for the Room class.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
    public Room(int i, int j) {
        super(i,j);
    }
    
    /**
     * Returns a string representation of the cell.
     *
     * @return A string representation of a Room cell.
     */
    @Override
    public String getRepresentation() {
        return "R"; // Representation of a Room cell
    }

    /**
     * Checks if the cell can be placed.
     *
     * @return true, indicating a Room cell can be placed.
     */
    @Override 
    public boolean canPlace() {
        return true;
    }

}
