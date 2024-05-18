package zombicide.tool;

import zombicide.action.UseMap;
import zombicide.grid.Board;

/**
 * Represents a map tool.
 */
public class Map extends Tool {

    private Board grid;
    
    /**
     * Constructor for the Map class.
     * Initializes the map with a name and a reference to the game board.
     *
     * @param grid The game board associated with the map.
     */
    public Map(Board grid) {
        super("Map");
        this.grid = grid;
        this.action = new UseMap("use map");
    }

    /**
     * Returns a string representation of the map.
     *
     * @return The name of the map.
     */
    public String toString(){
        return this.name +"";
    }

    /**
     * Displays the grid associated with the map.
     */
    public void getGrid(){
        this.grid.toString();
        this.grid.afficherCarte();
    }
}
