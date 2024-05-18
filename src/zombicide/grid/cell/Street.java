package zombicide.grid.cell;

/**
 * Represents a Street cell in the game grid.
 */
public class Street extends Cell {

    /**
     * Constructor for the Street class.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
    public Street(int i, int j) {
        super(i,j);
    }

    /**
     * Returns a string representation of the cell.
     *
     * @return A string representation of a Street cell.
     */
    @Override
    public String getRepresentation() {
        return "-"; // Representation of a Street cell
    }

    /**
     * Checks if the cell can be placed.
     *
     * @return false, indicating a Street cell cannot be placed.
     */
    @Override
    public boolean canPlace() {
        return false;
    }
    
    /**
     * Checks if the cell is a street.
     *
     * @return true, indicating the cell is a street.
     */
    public boolean isStreet() {
        return true;
    }
    
}
