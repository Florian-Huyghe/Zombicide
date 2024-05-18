package zombicide.grid.cell;

import zombicide.tool.HealingVial;

/**
 * Represents a Medical room cell in the game grid.
 */
public class Medical extends Room {
    
    /**
     * Constructor for the Medical class.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
    public Medical(int i,int j){
        super(i,j);
    }

    /**
     * Returns a string representation of the cell.
     *
     * @return A string representation of a Medical cell.
     */
    @Override
    public String getRepresentation() {
        return "\u001B[32mM\u001B[0m"; // Green 'M' representing Medical
    }

    /**
     * Checks if the cell can be placed.
     *
     * @return false, indicating a Medical cell cannot be placed.
     */
    @Override
    public boolean canPlace() {
        return false;
    }

    /**
     * Adds a healing vial to the Medical cell.
     */
    public void addPotion(){
        this.toolsIncell.add(new HealingVial());
    }

}
