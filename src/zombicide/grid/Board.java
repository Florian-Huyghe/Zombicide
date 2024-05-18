package zombicide.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.cell.Cell;
import zombicide.grid.cell.Continental;
import zombicide.grid.cell.Medical;
import zombicide.grid.cell.Room;
import zombicide.grid.cell.Street;
import zombicide.grid.cell.door.Door;
import zombicide.grid.cell.door.LockedDoor;
import zombicide.grid.cell.door.OpenDoor;
import zombicide.tool.Tool;
import zombicide.weapon.Weapon;


/**
 * The Board class represents the game board in Zombicide.
 * It manages the grid cells, survivors, zombies, and other game elements.
 */
public class Board {

    private Cell[][] carte;
    private int nbLigne;
    private int nbColonne;
    private Random random;
    private List<Survivor> survivorsInBoard;
    private List<Zombie> zombieInBoard;
    

    /**
     * This attribute serves as a reference to know if survivors have already spawned or not.
     * if it is equal to 1 the survivors can spawn otherwise no
     */
    private int firstStreetCol;
    private int firstStreetRow;
    private int initStreet = 0;


    /**
     * Constructor for the Board class, initializes the board and sets up the initial configuration.
     *
     * @param nbLigne    Number of rows on the board.
     * @param nbColonne  Number of columns on the board.
     * @param s it's an ArrayList of the survivors
     */
    public Board(int nbLigne, int nbColonne, List<Survivor> s, List<Zombie> z) {
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;
        this.carte = new Cell[nbLigne][nbColonne];
        this.random = new Random();
        this.survivorsInBoard = new ArrayList<>();
        this.zombieInBoard = new ArrayList<>();
        this.initAll(s,z);
    }

    

    /**
     * init the board
     * 
     * @param s it's an ArrayList of the survivors
     */
    private void initAll(List<Survivor> s, List<Zombie> z){
        this.initZombies(z);
        this.initSurvivors( s);
        this.initCarte();
        this.creerCarte();
        this.placeRoom();        
        this.initDoor();
        this.linkDoor();
        this.initNeighbor();
    }

    /**
     * Adds a zombie to the specified cell on the board.
     *
     * @param cell   The cell to add the zombie to.
     * @param zombie The zombie to add.
     */
    public void addZombie(Cell cell, Zombie zombie){
        this.zombieInBoard.add(zombie);
        cell.setZombieInCell(zombie);
        zombie.initZombie(this, cell);
    }

    /**
     * Checks if the game has ended based on certain conditions.
     *
     * @return True if the game has ended, otherwise false.
     */
    public boolean endGame(){

        
        int total=0;
        for(int i=0;i<this.survivorsInBoard.size();i++){
            total=this.survivorsInBoard.get(i).getLevel()+total;
        }
        if (total>=30||  this.zombieInBoard.isEmpty() || this.survivorsInBoard.isEmpty()){
            return  true;
        }
        return false;
    }


    /**
     * Removes a survivor from the board.
     * 
     * @param s the survivor to be removed
     */
    public void removeSurvivorInBoard(Survivor s){
        this.survivorsInBoard.remove(s);
    }


    /**
     * Gets the column index of the first street on the board.
     *
     * @return The column index of the first street.
     */
    public int getFirstStreetCol(){
        return this.firstStreetCol;
    }

    /**
     * Gets the row index of the first street on the board.
     *
     * @return The row index of the first street.
     */
    public int getFirstStreetRow(){
        return this.firstStreetRow;
    }

    /**
     * Gets the list of survivors currently on the board.
     *
     * @return The list of survivors.
     */
    public List<Survivor> getSurvivorInBoard(){
        return this.survivorsInBoard;
    }

    /**
     * Gets the list of survivors currently on the board.
     *
     * @return The list of survivors.
     */
    public List<Zombie> getZombieInBoard(){
        return this.zombieInBoard;
    }

    /**
     * Gets the grid representing the board.
     *
     * @return The grid of cells.
     */
    public Cell[][] getCells(){
        return this.carte;
    }
    
    /**
     * Initializes the neighbor cells of each cell on the board.
     */
    private void initNeighbor(){
        for(int i = 0 ; i<this.nbLigne; i++){
            for(int j = 0 ; j<this.nbColonne; j++){

                if (i > 0) {
                    this.carte[i][j].setNorthCell(this.carte[i-1][j]);
                }
        
                if (i < this.nbLigne - 1) {
                    this.carte[i][j].setSouthCell(this.carte[i+1][j]);
                }
            
                if (j > 0) {
                    this.carte[i][j].setWestCell(this.carte[i][j-1]);
                }
            
                if (j < this.nbColonne - 1) {
                    this.carte[i][j].setEastCell(this.carte[i][j+1]);
                }
    
            }
        }
    }

    /**
     * init the survivors List
     * 
     * @param s it's an ArrayList of the survivors
     */
    private void initSurvivors(List<Survivor> s){
        for(int i = 0; i<s.size();i++){
            survivorsInBoard.add(s.get(i));

        }
    }


    /**
     * Initializes the list of zombies on the board.
     *
     * @param zombies An ArrayList containing the zombies.
     */
    public void initZombies(List<Zombie> z){
        for(int i = 0; i<z.size();i++){
            zombieInBoard.add(z.get(i));
        }
    }
    
    /**
     * Removes a zombie from the board.
     *
     * @param zombie The zombie to remove.
     */
    public void removeZombiesBoard(Zombie z){
        this.zombieInBoard.remove(z);
    }    

    /**
     * Gets the number of rows on the board.
     *
     * @return The number of rows.
     */
    public int getLigne() {
        return this.nbLigne;
    }

    /**
     * Gets the number of columns on the board.
     *
     * @return The number of columns.
     */
    public int getColonne() {
        return this.nbColonne;
    }

    
    /**
     * Initializes the board with Room cells.
     */
    public void initCarte() {
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {
                this.carte[i][j] = new Room(i,j);
            }
        }

    }
       

    /**
     * Links doors between cells on the board.
     */
    public void linkDoor(){
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {

                if(i==0){
                    if(j==0){

                        this.carte[i][j].setSouthDoor(new Door());
                        this.carte[i][j].setEastDoor(new Door());

                    }else if (j == this.nbColonne-1){

                        this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());

                        this.carte[i][j].setSouthDoor(new Door());
                        
                    }else{

                        if(this.carte[i][j].isStreet()){

                            this.carte[i][j].setEastDoor(this.carte[i][j+1].getWestDoor());
                            this.carte[i][j].setSouthDoor(this.carte[i+1][j].getNorthDoor());
                            this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());


                        }else{

                            this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());

                            this.carte[i][j].setSouthDoor(new Door());
                            this.carte[i][j].setEastDoor(new Door());
                        }


                        
                    }
                    
                }else if (i>0 && i<this.nbLigne-1){

                    if(j==0){

                        if(this.carte[i][j].isStreet()){
                            this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                            this.carte[i][j].setSouthDoor(this.carte[i+1][j].getNorthDoor());
                            this.carte[i][j].setEastDoor(this.carte[i][j+1].getWestDoor());
                        }else{
                            this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                            this.carte[i][j].setSouthDoor(new Door());
                            this.carte[i][j].setEastDoor(new Door());
                        }
                        

                    }else if (j == this.nbColonne-1){
                        this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());

                        this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());

                        this.carte[i][j].setSouthDoor(new Door());
                        
                    }else{

                        if(this.carte[i][j].isStreet()){

                            this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                            this.carte[i][j].setEastDoor(this.carte[i][j+1].getWestDoor());
                            this.carte[i][j].setSouthDoor(this.carte[i+1][j].getNorthDoor());
                            this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());


                        }else{

                            this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                            this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());
                            this.carte[i][j].setSouthDoor(new Door());
                            this.carte[i][j].setEastDoor(new Door());
                        }
                    }
                        
                }else{

                    if(j==0){

                        this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                        this.carte[i][j].setEastDoor(new Door());

                    }else if (j == this.nbColonne-1){

                        this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());
                        this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                        
                    }else{
                        if(this.carte[i][j].isStreet()){

                            this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                            this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());
                            this.carte[i][j].setEastDoor(this.carte[i][j+1].getWestDoor());


                        }else{

                            this.carte[i][j].setNorthDoor(this.carte[i-1][j].getSouthDoor());
                            this.carte[i][j].setWestDoor(this.carte[i][j-1].getEastDoor());
                            this.carte[i][j].setEastDoor(new Door());

                        }
                    }
                }
                if(i == 0){
                    this.carte[i][j].setNorthDoor(new LockedDoor());
                }
                if(j == 0){
                    this.carte[i][j].setWestDoor(new LockedDoor());
                }
                if(i == this.nbLigne - 1){
                    this.carte[i][j].setSouthDoor(new LockedDoor());
                }
                if(j == this.nbColonne - 1){
                    this.carte[i][j].setEastDoor(new LockedDoor());
                }
            }     
                
        }
    }

    /**
     * Initializes doors on the grid.
     */
    public void initDoor(){
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {

                
                if (this.carte[i][j].isStreet()) {
                    this.carte[i][j].setNorthDoor(new OpenDoor());
                    this.carte[i][j].setWestDoor(new OpenDoor());
                    this.carte[i][j].setSouthDoor(new OpenDoor());
                    this.carte[i][j].setEastDoor(new OpenDoor());
                } else {
                    this.carte[i][j].setNorthDoor(new Door());
                    this.carte[i][j].setWestDoor(new Door());
                    this.carte[i][j].setSouthDoor(new Door());
                    this.carte[i][j].setEastDoor(new Door());
                }

                
            }
        }
    }


    /**
     * Adds a survivor to the specified cell on the board.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
    public void placeSurvivor(int i, int j){
  
        for(int x = 0; x < survivorsInBoard.size();x++){
            this.carte[i][j].setSurvivorInCell(survivorsInBoard.get(x));
            survivorsInBoard.get(x).initSurvivor(this, this.carte[i][j]);
        }
        
    }


    /**
     * Places zombies on the board within specified coordinates.
     *
     * @param rowStart    The starting row index.
     * @param colStart    The starting column index.
     * @param rowEnd      The ending row index.
     * @param colEnd      The ending column index.
     * @param streetRow   The row index of the street.
     * @param streetCol   The column index of the street.
     */
    public void placeZombie(int rowStart, int colStart, int rowEnd, int colEnd, int streetRow, int streetCol){
        
            
            int i = 0;
            while(zombieInBoard.size()-i >=4){

                this.carte[rowStart][streetCol].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[rowStart][streetCol]);
                i++;

                this.carte[streetRow][colEnd].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[streetRow][colEnd]);
                i++;

                this.carte[rowEnd][streetCol].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[rowEnd][streetCol]);
                i++;

                this.carte[streetRow][colStart].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[streetRow][colStart]);
                i++;
            }
            if(zombieInBoard.size()-i==3){
                this.carte[rowStart][streetCol].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[rowStart][streetCol]);
                i++;

                this.carte[streetRow][colEnd].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[streetRow][colEnd]);
                i++;

                this.carte[rowEnd][streetCol].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[rowEnd][streetCol]);
            }
            else if(zombieInBoard.size()-i==2){
                this.carte[rowStart][streetCol].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[rowStart][streetCol]);
                i++;

                this.carte[streetRow][colEnd].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[streetRow][colEnd]);
            }
            else if(zombieInBoard.size()-i==1){
                this.carte[rowStart][streetCol].setZombieInCell(zombieInBoard.get(i));
                zombieInBoard.get(i).initZombie(this, this.carte[rowStart][streetCol]);
            }
        
    }


    public void placeNewZombie(int rowStart, int colStart, int rowEnd, int colEnd, int streetRow, int streetCol, List<Zombie> z){
        
            
        int i = 0;
        while(z.size()-i >=4){

            this.carte[rowStart][streetCol].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[rowStart][streetCol]);
            i++;

            this.carte[streetRow][colEnd].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[streetRow][colEnd]);
            i++;

            this.carte[rowEnd][streetCol].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[rowEnd][streetCol]);
            i++;

            this.carte[streetRow][colStart].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[streetRow][colStart]);
            i++;
        }
        if(z.size()-i==3){
            this.carte[rowStart][streetCol].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[rowStart][streetCol]);
            i++;

            this.carte[streetRow][colEnd].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[streetRow][colEnd]);
            i++;

            this.carte[rowEnd][streetCol].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[rowEnd][streetCol]);
        }
        else if(z.size()-i==2){
            this.carte[rowStart][streetCol].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[rowStart][streetCol]);
            i++;

            this.carte[streetRow][colEnd].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[streetRow][colEnd]);
        }
        else if(z.size()-i==1){
            this.carte[rowStart][streetCol].setZombieInCell(z.get(i));
            z.get(i).initZombie(this, this.carte[rowStart][streetCol]);
        }
    
}

    /**
     * Places a tool in the specified cell on the board.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @param t The tool to place.
     */
    public void placeTool(int i, int j,Tool t){
        this.carte[i][j].setToolsInCell(t);
    }

    /**
     * Places a weapon in the specified cell on the board.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @param w The weapon to place.
     */
    public void placeWeapon(int i, int j,Weapon w){
        this.carte[i][j].setWeaponInCell(w);
    }

    /**
     * Gets the total level of all survivors on the board.
     *
     * @return The total level of survivors.
     */
    public int getAllSurvivorsLevel(){
        int totalLevel=0;
        for( int i=0;i<this.survivorsInBoard.size();i++){
            if(this.survivorsInBoard.get(i).isAlive()){
                totalLevel+=this.survivorsInBoard.get(i).getLevel();
            }
        }
        return totalLevel;
    }

    /**
     * Creates the initial configuration of the board.
     */
    public void creerCarte() {
        this.create(0, 0, this.nbLigne - 1, this.nbColonne - 1);
        this.placeSurvivor(this.firstStreetRow,this.firstStreetCol);
        this.placeZombie(0, 0, this.carte.length-1, this.carte[0].length-1, firstStreetRow, firstStreetCol);
    }

    /**
     * Recursively creates streets on the board based on a given range.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void create(int rowStart, int colStart, int rowEnd, int colEnd) {

        int rowLength = rowEnd - rowStart + 1;
        int colLength = colEnd - colStart + 1;

        
        if (rowLength > 4 && colLength > 4) {
            this.createStreetInCross(rowStart, colStart, rowEnd, colEnd);
        }
        else if (rowLength > 4 ) {
            this.createStreetInRow(rowStart, colStart, rowEnd, colEnd);
        }
        else if (colLength > 4 ) {
            this.createStreetInColumn(rowStart, colStart, rowEnd, colEnd);
        }
    }

    /**
     * Creates a street in a specific row.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void createStreetInRow(int rowStart, int colStart, int rowEnd, int colEnd) {

        int streetRow = random.nextInt((rowEnd - rowStart - 3)) + rowStart + 2;
        for (int i = colStart; i <= colEnd; i++) {
                this.carte[streetRow][i] = new Street(streetRow,i);
            }
        if (rowEnd - rowStart >= 1) {
            this.create(rowStart, colStart, streetRow - 1, colEnd);
            this.create(streetRow + 1, colStart, rowEnd, colEnd);
        }
    }

    /**
     * Creates a street in a specific column.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void createStreetInColumn(int rowStart, int colStart, int rowEnd, int colEnd) {

        int streetCol = random.nextInt((colEnd - colStart - 3)) + colStart + 2 ;
        for (int i = rowStart; i <= rowEnd; i++) {
                this.carte[i][streetCol] = new Street(i,streetCol);
            }
        if (colEnd - colStart >= 1) {
            this.create(rowStart, colStart, rowEnd, streetCol - 1);
            this.create(rowStart, streetCol + 1, rowEnd, colEnd);
        }
    }

    /**
     * Creates a street in both rows and columns forming a cross shape.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void createStreetInCross(int rowStart, int colStart, int rowEnd, int colEnd) {
            
            int streetRow = random.nextInt((rowEnd - rowStart - 3)) + rowStart + 2;
            int streetCol = random.nextInt((colEnd - colStart - 3)) + colStart + 2;

            if(this.initStreet == 0){
                this.firstStreetCol = streetCol;
                this.firstStreetRow = streetRow;
                this.initStreet++;
            }


            for (int i = colStart; i <= colEnd; i++) {
                this.carte[streetRow][i] = new Street(streetRow,i);
            }

            for (int i = rowStart; i <= rowEnd; i++) {
                this.carte[i][streetCol] = new Street(i,streetCol);
            }
    
        this.create(rowStart, colStart, streetRow - 1, streetCol - 1);
        this.create(rowStart, streetCol + 1, streetRow - 1, colEnd);
        this.create(streetRow + 1, colStart, rowEnd, streetCol - 1);
        this.create(streetRow + 1, streetCol + 1, rowEnd, colEnd);
    }

    /**
     * Places rooms on the board, including Continental and Medical rooms.
     */
    public void placeRoom() {
        this.placeContinental();
        this.placeMedical();
       
    }
    
    /**
     * Places Continental rooms randomly on the board.
     */
    public void  placeContinental() {

        int rowaleat = random.nextInt(nbLigne );
        int colaleat = random.nextInt(nbColonne );
        if (this.carte[rowaleat][colaleat].canPlace()) {
            this.carte[rowaleat][colaleat] = new Continental(rowaleat,colaleat);}
        else {
            this.placeContinental();
        }
    }
    
    /**
     * Places Medical rooms randomly on the board.
     */
    public void placeMedical() {
        int rowaleat = random.nextInt(nbLigne );
        int colaleat = random.nextInt(nbColonne);
        if (this.carte[rowaleat][colaleat].canPlace()) {
            this.carte[rowaleat][colaleat] = new Medical(rowaleat,colaleat);}
        else {
            this.placeMedical();
        }
    }
    

    /**
     * Displays the board's grid representation.
     */
    public void afficherCarte(){
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {
                System.out.print(this.carte[i][j].getRepresentation());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Returns a string representation of the board.
     *
     * @return A string representation of the board.
     */
    public String toString(){
        String str = "";
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {
                str += this.carte[i][j].toString();
            }
        }
        return str;
    }

    /**
     * Finds and returns the cell with the highest noise level on the board.
     *
     * @return The cell with the highest noise level.
     */
    public Cell getMostNoisyCell(){
        Cell MostNoisyCell = this.carte[0][0];
        int noise= 0;
        for(int i = 0; i < this.nbLigne; i++) {
            for(int j = 0; j < this.nbColonne; j++) {
                if(noise < this.carte[i][j].getNoiseLevel()){
                    noise = this.carte[i][j].getNoiseLevel();
                    MostNoisyCell = this.carte[i][j];
                }
            }
        }
        return MostNoisyCell;
    }

    
    /**
     * Resets the noise level in all cells on the board.
     */
    public void resetNoiseInBoard(){
        for(int i = 0; i < this.nbLigne; i++) {
            for(int j = 0; j < this.nbColonne; j++) {
                this.carte[i][j].resetNoise();
            }
        }
    }

    /**
     * Displays the entire board including cells, doors, zombies, and survivors.
     */
    public void afficherCarteAll() {
        for (int i = 0; i < this.nbLigne; i++) {
            // Ligne horizontale supérieure
            for (int j = 0; j < this.nbColonne; j++) {
                System.out.print("+");
                
                if (this.carte[i][j].getNorthDoor().isOpen()) {
                    System.out.print("   ");
                } else {
                    System.out.print("---");
                }
            }
            System.out.println("+");
    
            // Contenu de la ligne
            for (int j = 0; j < this.nbColonne; j++) {

                
                if (this.carte[i][j].getWestDoor().isOpen()) {
                    System.out.print(" ");
                } else {
                    System.out.print(" |");
                }
                
    
                // Afficher le type de cellule (représentation)
                System.out.print(this.carte[i][j].getRepresentation());
                
                if (this.carte[i][j].getEastDoor().isOpen()) {
                    System.out.print("  ");
                } else {
                    System.out.print("|");
                }
                
            }
    
            System.out.println();
            
            for (int j = 0; j < this.nbColonne; j++) {
                System.out.print("+");
                
                if (this.carte[i][j].getSouthDoor().isOpen()) {
                    System.out.print("   ");
                } else {
                    System.out.print("---");
                }
            }
            System.out.println("+");
    
            // Afficher les zombies dans la cellule
            System.out.print("|");
            for (int j = 0; j < this.nbColonne; j++) {
                if (this.carte[i][j].getZombieInCell() != null) {
                    System.out.print("Z" + this.carte[i][j].getZombieInCell().size() + "  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("|");
    
            // Afficher les survivants dans la cellule
            System.out.print("|");
            for (int j = 0; j < this.nbColonne; j++) {
                if (this.carte[i][j].getSurvivorInCell() != null) {
                    System.out.print("S" + this.carte[i][j].getSurvivorInCell().size() + "  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("|");
    
            // Ligne horizontale inférieure
            for (int j = 0; j < this.nbColonne; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
    
            System.out.println(); // Ligne vide pour la clarté
        }
    }



    
}
