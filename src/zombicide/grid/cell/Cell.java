package zombicide.grid.cell;

import java.util.ArrayList;

import java.util.List;


import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.cell.door.Door;
import zombicide.tool.Tool;
import zombicide.weapon.Weapon;


/**
 * This class is an abstract representation of a cell.
 */
public abstract class Cell {
    protected Door northDoor; 

    protected Door southDoor;

    protected Door eastDoor;

    protected Door westDoor;
    
    protected List<Door> doors;


    
    protected List<Survivor> survivorInCell;

    protected List<Zombie> zombieInCell;
    
    protected List<Weapon> weaponInCell;

    protected List<Tool> toolsIncell;




    protected List<Cell> neighborCells;

    protected Cell northCell;

    protected Cell southCell;

    protected Cell eastCell;

    protected Cell westCell;

    protected String position;


    protected int positionI;

    protected int positionJ;

    protected int noiseLevel;

    


    /**
     * Constructor for the Cell class.
     */
    public Cell(int i, int j){
        this.survivorInCell = new ArrayList<>();
        this.zombieInCell = new ArrayList<>();
        this.weaponInCell = new ArrayList<>();
        this.toolsIncell = new ArrayList<>();
        this.neighborCells = new ArrayList<>();
        this.doors = new ArrayList<>();

        this.noiseLevel = 0;

        this.positionI = i;
        this.positionJ = j;
        
    }

    /**
     * Returns the list of doors in the cell.
     *
     * @return The list of doors.
     */
    public List<Door> getDoors(){
        this.doors.clear();
        if (!this.northDoor.isOpen()) {
            this.doors.add(this.northDoor);
            this.northDoor.setPosition("North");
        }
        if (!this.southDoor.isOpen()) {
            this.doors.add(this.southDoor);
            this.southDoor.setPosition("South");
        }
        if (!this.eastDoor.isOpen()) {
            this.doors.add(this.eastDoor);
            this.eastDoor.setPosition("East");
        }
        if (!this.westDoor.isOpen()) {
            this.doors.add(this.westDoor);
            this.westDoor.setPosition("West");
        }
        
        return this.doors;
    }

    /**
     * Returns the list of neighbor cells.
     *
     * @return The list of neighbor cells.
     */
    public List<Cell> getNeighborCells(){
        this.neighborCells.clear();
        if (this.northCell != null) {
            this.neighborCells.add(this.northCell);
            this.northCell.setPosition("North");
        }
        if (this.southCell != null) {
            this.neighborCells.add(this.southCell);
            this.southCell.setPosition("South");
        }
        if (this.westCell != null) {
            this.neighborCells.add(this.westCell);
            this.westCell.setPosition("West");
        }
        if (this.eastCell != null) {
            this.neighborCells.add(this.eastCell);
            this.eastCell.setPosition("East");
        }
        
        return this.neighborCells;
    }

    /**
     * Sets the position of the cell.
     *
     * @param p The position to set.
     */
    public void setPosition(String p){
        this.position = p;
    }

    /**
     * Sets the north cell of this cell.
     * 
     * @param c the cell to set as the north cell
     */
    public void setNorthCell(Cell c) {
        this.northCell = c;
    }

    /**
     * Sets the south cell of this cell.
     * 
     * @param c the south cell to be set
     */
    public void setSouthCell(Cell c) {
        this.southCell = c;
    }

    /**
     * Sets the east cell of this cell.
     * 
     * @param c the cell to set as the east cell
     */
    public void setEastCell(Cell c) {
        this.eastCell = c;
    }

    /**
     * Sets the west cell of the current cell.
     * 
     * @param c the cell to set as the west cell
     */
    public void setWestCell(Cell c) {
        this.westCell = c;
    }


    /**
     * Returns a list of neighboring cells that are within a range of 1 cell.
     * The neighboring cells include the current cell itself.
     * 
     * @param grid the 2D array of cells representing the game grid
     * @return a list of neighboring cells within a range of 1 cell
     */
    public List<Cell> inRange1(Cell[][] grid ){
        int numRows = grid.length;
        int numCols = grid[0].length;

        List<Cell> l = new ArrayList<>();

        l.add(this);

        if(positionI >0){
            l.add(grid[positionI-1][positionJ]);
        }

        if (positionI < numRows - 1) {
            l.add(grid[positionI + 1][positionJ]);
        }

        if (positionJ > 0){
            l.add(grid[positionI][positionJ - 1]);
        }

        if (positionJ < numCols - 1) {
            l.add(grid[positionI][positionJ + 1]);  
        }

        return l;

    }

    /**
     * Returns a list of cells that are within a range of 3 cells from the current cell.
     * The range is determined by the number of rows and columns in the grid.
     * 
     * @param grid the 2D array of cells representing the game grid
     * @return a list of cells within the range of 3 cells from the current cell
     */
    public List<Cell> inRange3(Cell[][] grid){
        int numRows = grid.length;
        int numCols = grid[0].length;

        List<Cell> l = new ArrayList<>();

        l.add(this);

        if(positionI>2){
            l.add(grid[positionI - 3][positionJ]);
            l.add(grid[positionI - 2][positionJ]);
            l.add(grid[positionI - 1][positionJ]);
        }else if(positionI>1){
            l.add(grid[positionI - 2][positionJ]);
            l.add(grid[positionI - 1][positionJ]);
        }else if(positionI > 0) {
            l.add(grid[positionI - 1][positionJ]);
        }
    

        if(positionI < numRows - 3){
            l.add(grid[positionI + 3][positionJ]);
            l.add(grid[positionI + 2][positionJ]);
            l.add(grid[positionI + 1][positionJ]);
        }else if( positionI < numRows - 2){
            l.add(grid[positionI + 2][positionJ]);
            l.add(grid[positionI + 1][positionJ]);
        }else if (positionI < numRows - 1) {
            l.add(grid[positionI + 1][positionJ]);
        }

        if(positionJ > 2){
            l.add(grid[positionI][positionJ - 3]);
            l.add(grid[positionI][positionJ - 2]);
            l.add(grid[positionI][positionJ - 1]);
        }else if(positionJ > 1){
            l.add(grid[positionI][positionJ - 2]);
            l.add(grid[positionI][positionJ - 1]);
        }else if (positionJ > 0){
            l.add(grid[positionI][positionJ - 1]);
        }

        if (positionJ < numCols - 3) {
            l.add(grid[positionI][positionJ + 3]);
            l.add(grid[positionI][positionJ + 2]);
            l.add(grid[positionI][positionJ + 1]);
        }else if (positionJ < numCols - 2) {
            l.add(grid[positionI][positionJ + 2]);
            l.add(grid[positionI][positionJ + 1]);
        }else if (positionJ < numCols - 1) {
            l.add(grid[positionI][positionJ + 1]);
        }

        return l;
    }
    


    // Method to reset the noise level of the area to zero
    public void resetNoise() {
        noiseLevel = 0; // Reset the noise level
    }

    // Method to get the noise level of the area
    public int getNoiseLevel() {
        return noiseLevel;
    }
       // Method to generate noise in the area
       public void makeNoise() {
        noiseLevel++; // Increase the noise level
    } 

    /**
     * Returns the value of the 'positionI' variable, which represents the row position of the cell in the grid.
     * 
     * @return the row position of the cell in the grid
     */
    public int getPositionI(){
        return this.positionI;
    }


    /**
     * Returns the position of the cell in the J direction.
     *
     * @return the position of the cell in the J direction
     */
    public int getPositionJ(){
        return this.positionJ;
    }


    /**
     * Sets a survivor in the cell.
     *
     * @param s The Survivor object to be added to the cell.
     */
    public void setSurvivorInCell(Survivor s){
        this.survivorInCell.add(s);
    }

    /**
     * Retrieves the list of survivors present in the cell.
     *
     * @return A List of Survivor objects in the cell.
     */
    public List<Survivor> getSurvivorInCell(){
        return this.survivorInCell;
    }

    /**
     * Removes the specified survivor from the cell.
     *
     * @param s The Survivor object to be removed from the cell.
     */
    public void removeSurvivorInCell(Survivor s){
        this.survivorInCell.remove(s);
    }

    /**
     * Retrieves the list of weapons present in the cell.
     *
     * @return A List of Weapon objects in the cell.
     */
    public List<Weapon> getWeaponInCell(){
        return this.weaponInCell;
    }

    /**
     * Sets a weapon in the cell.
     *
     * @param w The Weapon object to be added to the cell.
     */
    public void setWeaponInCell(Weapon w ){
        this.weaponInCell.add(w);
    }

    /**
     * Removes the specified weapon from the cell.
     *
     * @param w The Weapon object to be removed from the cell.
     */
    public void removeWeaponInCell(Weapon w){
        this.weaponInCell.remove(w);
    }

    /**
     * Retrieves the list of tools present in the cell.
     *
     * @return A List of Tool objects in the cell.
     */
    public List<Tool> getToolsInCell(){
        return this.toolsIncell;
    }

    /**
     * Sets a tool in the cell.
     *
     * @param t The Tool object to be added to the cell.
     */
    public void setToolsInCell(Tool t){
        this.toolsIncell.add(t);
    }

    /**
     * Removes the specified tool from the cell.
     *
     * @param t The Tool object to be removed from the cell.
     */
    public void removeToolsInCell(Tool t){
        this.toolsIncell.remove(t);
    }

    /**
     * Removes the specified zombie from the cell.
     *
     * @param z The Zombie object to be removed from the cell.
     */
    public void removeZombieInCell(Zombie z){
        this.zombieInCell.remove(z);
    }

    /**
     * Sets a zombie in the cell.
     *
     * @param z The Zombie object to be added to the cell.
     */
    public void setZombieInCell(Zombie z){
        this.zombieInCell.add(z);
    }

    /**
     * Retrieves the list of zombies present in the cell.
     *
     * @return A List of Zombie objects in the cell.
     */
    public List<Zombie> getZombieInCell(){
        return this.zombieInCell;
    }

    /**
     * Returns the north door of the cell.
     * @return the north door
     */
    public Door getNorthDoor(){
        return this.northDoor;
    }

    /**
     * Returns the south door of the cell.
     * @return the south door
     */
    public Door getSouthDoor(){
        return this.southDoor;
    }

    /**
     * Returns the east door of the cell.
     * @return the east door
     */
    public Door getEastDoor(){
        return this.eastDoor;
    }

    /**
     * Returns the west door of the cell.
     * @return the west door
     */
    public Door getWestDoor(){
        return this.westDoor;
    }

    /**
     * Sets the north door of the cell.
     * @param d the door to set as the north door
     */
    public void setNorthDoor(Door d){
        this.northDoor = d;
    }

    /**
     * Sets the south door of the cell.
     * @param d the door to set as the south door
     */
    public void setSouthDoor(Door d){
        this.southDoor = d;
    }

    /**
     * Sets the east door of the cell.
     * @param d the door to set as the east door
     */
    public void setEastDoor(Door d){
        this.eastDoor = d;
    }

    /**
     * Sets the west door of the cell.
     * @param d the door to set as the west door
     */
    public void setWestDoor(Door d){
        this.westDoor = d;
    }

    /**
     * Returns a string representation of the cell.
     * @return a string representation
     */
    public abstract String getRepresentation();

    /**
     * Checks if the cell can be placed.
     * @return false
     */
    public boolean canPlace() {
        return false;
    }

    /**
     * Checks if the cell is a street.
     * @return false
     */
    public boolean isStreet() {
        return false;
    }


    /**
     * Displays the cell representation and the number of zombies and survivors in the cell.
     */
    public void AfficheCell(){

        if(this.northDoor.isOpen()){
            System.out.println("+   +");
        }else{
            System.out.println("+---+");
        }

        String str ="";
        if(this.westDoor.isOpen()){
            str = "  ";
        }else{
            str = " |";
        }

        str += this.getRepresentation();

        if(this.eastDoor.isOpen()){
            str += " ";
        }else{
            str += "|";
        }

        System.out.println(str);

        if(this.southDoor.isOpen()){
            System.out.println("+   +");
        }else{
            System.out.println("+---+");
        }

        System.out.println("Z"+this.getZombieInCell().size());
        System.out.println("S"+this.getSurvivorInCell().size());

    }

    /**
     * Returns a string representation of the cell.
     * @return A string representation.
     */
    public String toString(){
        return this.position;
    }

    /**
     * Returns a string representation of the cell, including details of occupants.
     * @return A string representation with occupant details.
     */
    public String toString1(){
        String str = " Cell : North door: " + northDoor.toString() + " East door : "+ eastDoor.toString() + " West door : " + westDoor.toString() +" South door :"+  southDoor.toString() + " | \n ";

        if(this.survivorInCell.size() >0){
            str += "Survivor :\n";
            for(int i = 0; i<survivorInCell.size();i++){
                str += survivorInCell.get(i).toString() + "\n";
            }
            str += " | ";
        }
        

        if(this.zombieInCell.size() >0){
            str += "Zombie :\n";
            for(int i = 0; i<zombieInCell.size();i++){
                str += zombieInCell.get(i).toString() + "\n";
            }
            str += " | ";
        }

        if (this.weaponInCell.size()>0) {

            str += "Weapons : \n";
            for(int i = 0; i<weaponInCell.size();i++){
                str += weaponInCell.get(i).toString() + "\n";
            }
            str += " | ";
        }
        

        if(this.toolsIncell.size()>0){
            str += "Tools : \n";
            for(int i = 0; i<toolsIncell.size();i++){
                str += toolsIncell.get(i).toString() + "\n";
            }
            
        }
        

        return str;
    }
}
