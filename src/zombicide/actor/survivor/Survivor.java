package zombicide.actor.survivor;

import java.util.ArrayList;
import java.util.List;

import zombicide.action.Action;
import zombicide.action.Attack;
import zombicide.action.Deplacement;
import zombicide.action.LookAround;
import zombicide.action.MakeNoise;
import zombicide.action.OpenTheDoor;
import zombicide.action.Search;
import zombicide.action.TakeTool;
import zombicide.action.TakeToolsInHand;
import zombicide.action.TakeWeapon;
import zombicide.action.TakeWeaponInHand;
import zombicide.actor.Actor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.grid.cell.Cell;
import zombicide.listchooser.ListChooser;
import zombicide.tool.Tool;
import zombicide.weapon.Weapon;

/**
 * The Survivor class represents a survivor in the Zombicide game.
 * 
 * <p>A survivor has health points, action points, roles, weapons, tools, and available actions.
 * They can perform various actions such as attacking zombies, moving, searching, and using items.
 */
public class Survivor extends Actor {

    private Board grid; 
    private int health = 5; 
    private int actionPoints = 3; 
    private int level = 1; 
    private List<SurvivorRole> roles; 
    private List<Weapon> weapons; 
    private List<Tool> tools; 
    private List<Action> actions; 
    private Tool toolInHand = null; 
    private Weapon weaponInHand = null;
    private Cell cell;
    private String name;
    private int actionPointsMax = 3; 
    private boolean luckyRole = false; 
    
    /**
     * Constructs a Survivor object with a specified name.
     * 
     * @param name the name of the survivor
     */
    public Survivor(String name) {
        this.roles = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.initAction();
        this.name = name;
    }
    
    /**
     * Initializes the survivor with the game board and starting cell.
     * 
     * @param grid the game board
     * @param cell the starting cell of the survivor
     */
    public void initSurvivor(Board grid, Cell cell) {
        this.grid = grid;
        this.cell = cell;
    }

    /**
     * Initializes the available actions for the survivor.
     */
    private void initAction() {
        this.actions.add(new Deplacement("move"));
        this.actions.add(new Attack("Attack"));
        this.actions.add(new Search("Search"));
        this.actions.add(new LookAround("Look Around"));
        this.actions.add(new TakeToolsInHand("use tool"));
        this.actions.add(new TakeWeaponInHand("take weapon"));
        this.actions.add(new MakeNoise("make noise"));
        this.actions.add(new OpenTheDoor("open the door "));
        this.actions.add(new TakeWeapon("take weapon "));
        this.actions.add(new TakeTool("take tool "));
    }

    /**
     * Adds a tool action to the survivor's available actions.
     * 
     * @param t the tool to add
     */
    public void addAction(Tool t){
        this.actions.add(t.getAction());
    }

    /**
     * Removes a tool action from the survivor's available actions.
     * 
     * @param t the tool to remove
     */
    public void removeAction(Tool t){
        this.actions.remove(t.getAction());
    }

    /**
     * Decrements the survivor's action points.
     */
    public void decrementedActionPoints(){
        this.actionPoints--;
    }

    /**
     * Retrieves the game board.
     * 
     * @return the game board
     */
    public Board getGrid(){
        return this.grid;
    }

    /**
     * Retrieves the list of available actions for the survivor.
     * 
     * @return the list of available actions
     */
    public List<Action> getListAction(){
        return this.actions;
    }

    /**
     * Retrieves the tool currently in hand.
     * 
     * @return the tool in hand
     */
    public Tool getToolInHand(){
        return this.toolInHand;
    }

    /**
     * Sets the tool currently in hand.
     * 
     * @param t the tool to set as in hand
     */
    public void setToolInHand(Tool t){
        if(this.tools.contains(t)){
            this.toolInHand = t;
            this.tools.remove(t);
        }
    }

    /**
     * Retrieves the weapon currently in hand.
     * 
     * @return the weapon in hand
     */
    public Weapon getWeaponInHand(){
        return this.weaponInHand;
    }

    /**
     * Sets the weapon currently in hand.
     * 
     * @param w the weapon to set as in hand
     */
    public void setWeaponInHand(Weapon w){
        if(this.weapons.contains(w)){
            this.weaponInHand = w;
        }
    }

    /**
     * Retrieves the cell where the survivor is located.
     * 
     * @return the cell where the survivor is located
     */
    public Cell getCell(){
        return this.cell;
    }

    /**
     * Retrieves the name of the survivor.
     * 
     * @return the name of the survivor
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the cell where the survivor is located.
     * 
     * @param c the cell to set as the survivor's location
     */
    public void setCell(Cell c){
        this.cell.removeSurvivorInCell(this);
        c.setSurvivorInCell(this);
        this.cell = c;
    }

    /**
     * Retrieves the level of the survivor.
     * 
     * @return the level of the survivor
     */
    public int getLevel(){
        return this.level;
    }

    /**
     * Retrieves the maximum number of action points.
     * 
     * @return the maximum number of action points
     */
    public int getActionPointsMax(){
        return this.actionPointsMax;
    }

    /**
     * Resets the survivor's action points based on their level.
     */
    public void resetActionPoints(){
        if(this.level <3){
            this.actionPoints = 3;
        }else if(this.level <7 && this.level >=3){
            this.actionPoints = 4;
        }else if(this.level <11 && this.level >=7) {
            this.actionPoints = 5;
        }else{
            this.actionPoints =6;
        }
        if(this.luckyRole){
            this.actionPoints++;
        }
    }

    /**
     * Sets the survivor's level and adjusts the maximum number of action points if necessary.
     */
    public void setLevel(){
        this.level++;
        if (level==3 || level==7 || level==11){
            this.actionPointsMax++;
        }
    }

    /**
     * Retrieves the health points of the survivor.
     * 
     * @return the health points of the survivor
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Retrieves the action points of the survivor.
     * 
     * @return the action points of the survivor
     */
    public int getActionPoints(){
        return this.actionPoints;
    }

    /**
     * Retrieves the list of weapons carried by the survivor.
     * 
     * @return the list of weapons carried by the survivor
     */
    public List<Weapon> getWeapons(){
        return this.weapons;
    }

    /**
     * Retrieves the list of tools carried by the survivor.
     * 
     * @return the list of tools carried by the survivor
     */
    public  List<Tool> getTools(){
        return this.tools;
    }

    /**
     * Adds a weapon to the list of weapons carried by the survivor.
     * 
     * @param w the weapon to add
     */
    public void setWeapon(Weapon w){
        this.weapons.add(w);
    }

    /**
     * Adds a tool to the list of tools carried by the survivor.
     * 
     * @param t the tool to add
     */
    public void setTools(Tool t){
        this.tools.add(t);
        this.addAction(t);
    }

    /**
     * Retrieves the roles assigned to the survivor.
     * 
     * @return the roles assigned to the survivor
     */
    public List<SurvivorRole> getRole(){
        return this.roles;
    }

    /**
     * Removes a tool from the list of tools carried by the survivor.
     * 
     * @param t the tool to remove
     */
    public void  removeTools(Tool t){
        this.tools.remove(t);
    }

    /**
     * Adds a role to the survivor and adjusts the action points if necessary.
     *
     * @param role the role to be added
     */
    public void addRole(SurvivorRole role) {
        this.roles.add(role);
        if(role instanceof LuckyRole){
            this.actionPoints++;
            this.luckyRole = true;
        }
        if(role.getAvailableActions() != null){
            if(role instanceof FighterRole){
                this.actions.remove(1);
                this.actions.add(1, role.getAvailableActions());
            }else{
                this.actions.add(role.getAvailableActions());
            }
            
        }
    }

    /**
     * Receives damage and updates the health points accordingly.
     *
     * @param damage the amount of damage received
     */
    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println("Survivor " + name + " received  " + damage + " damage. Remaining health: " + this.health);
    }

    /**
     * Heals the survivor by increasing their health points.
     *
     * @param lifePoint the amount of health points to heal
     */
    public void healing(int lifePoint){
        if(this.health <= 4) this.health += lifePoint;
    }


    /**
     * Increases the health of the survivor by the specified amount.
     *
     * @param lifePoint the amount of health points to add to the survivor's health
     */
    public void placeShield(int lifePoint){
        this.health += lifePoint;
    }
    
    /**
     * Checks if the survivor is alive based on their current health points.
     *
     * @return true if the survivor's health is greater than 0, false otherwise
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Attacks a zombie and inflicts damage.
     *
     * @param damage the amount of damage to inflict
     * @param z the zombie to attack
     */
    public void attackZombie(int damage, Zombie z){
        z.receiveDamage(damage);
    }

    /**
     * Moves the survivor to a specified cell if the action is valid.
     *
     * @param c the cell to move to
     */
    public void moveIn(Cell c){
        if(this.canMoveIn(c)){
            this.setCell(c);
        }else{
            System.out.println("impossible action");
        }
    }

    /**
     * Checks if the survivor can move to a specified cell.
     *
     * @param c the cell to check
     * @return true if the survivor can move to the cell, false otherwise
     */
    public boolean canMoveIn(Cell c){
        if(this.getCell().getPositionI() != this.getGrid().getLigne() - 1 && c.getNorthDoor().isOpen()){
            return true;
        }else if(this.getCell().getPositionI() != 0 && c.getSouthDoor().isOpen()){
            return true;
        }else if(this.getCell().getPositionJ() != 0 && c.getEastDoor().isOpen()){
            return true;
        }else if(this.getCell().getPositionJ() != this.getGrid().getColonne() - 1 && c.getWestDoor().isOpen()){
            return true;
        }
        return false;
    }

    /**
     * Generates a string representation of the survivor.
     * 
     * @return a string describing the survivor's attributes
     */
    public String toString(){

        if(!this.isAlive()){
            return "Survivor " + name + " is dead...";
        }

        String str = "Survivor " + name + " have " + this.health + " hp, he have " + this.actionPoints + " action points | ";
        
        if(this.roles.size() > 0){
            str += "his role is ";
            for(int i = 0; i<roles.size();i++){
                str += roles.get(i).toString() + " ";
            }
            str += " | ";
        }

        if(this.weapons.size() > 0){
            str += " weapon : ";
            for(int i = 0; i<weapons.size();i++){
                str += weapons.get(i).toString() + " ";
            }
            str += " | ";
        }
        
        if (this.tools.size() > 0) {
            str += " tool : ";
            for(int i = 0; i<tools.size();i++){
                str += tools.get(i).toString();
            }
            str += " | ";
        }
        
        return str;
    }

    /**
     * Allows the survivor to take an action based on user input.
     * 
     * @param ilc the list chooser to facilitate user action selection
     */
    public void play(ListChooser<Action> ilc){
        System.out.println("------ " + name + " ------");

        Action chosenElement = ilc.choose("Which action do you choose ?", this.actions);
        System.out.println("You've chosen : " + chosenElement);
        if (chosenElement != null) {
            chosenElement.doIt(this);
        } else {
            this.actionPoints--;
        }
    }
}
