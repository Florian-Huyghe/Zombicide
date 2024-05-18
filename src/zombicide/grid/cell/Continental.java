package zombicide.grid.cell;

/**
 * Represents a Continental room cell in the game grid.
 */
public class Continental extends Room {
    
    /**
     * Constructor for the Continental class.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
    public Continental(int i,int j){
        super(i,j);
    }

    /**
     * Returns a string representation of the cell.
     *
     * @return A string representation of a Continental cell.
     */
    @Override
    public String getRepresentation() {
        return "\u001B[31mC\u001B[0m"; // Red 'C' representing Continental
    }

    /**
     * Checks if the cell can be placed.
     *
     * @return false, indicating a Continental cell cannot be placed.
     */
    @Override
    public boolean canPlace() {
        return false;
    }
}
