package zombicide.actor.zombie;

import zombicide.action.Action;
import zombicide.action.Attack;
import zombicide.action.Deplacement;
import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.grid.Board;
import zombicide.grid.cell.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a Zombie.
 */
public abstract class Zombie extends Actor {
    protected int health;
    protected int damage;
    protected Cell cell;
    protected Board grid;
    protected List<Action> actions;

    /**
     * Constructs a Zombie with specified health and damage.
     *
     * @param health the health points of the zombie
     * @param damage the damage points inflicted by the zombie
     */
    public Zombie(int health, int damage) {
        this.health = health;
        this.damage = damage;
        this.actions = new ArrayList<>();
        this.initAction();
    }

    /**
     * Initializes the list of available actions for the zombie.
     */
    private void initAction() {
        this.actions.add(new Deplacement("move"));
        this.actions.add(new Attack("Attack"));
    }

    /**
     * Initializes the zombie with the given grid and cell.
     *
     * @param grid the game board grid
     * @param cell the cell where the zombie is located
     */
    public void initZombie(Board grid, Cell cell) {
        this.grid = grid;
        this.cell = cell;
    }

    /**
     * Retrieves the game board grid.
     *
     * @return the game board grid
     */
    public Board getGrid() {
        return this.grid;
    }

    /**
     * Retrieves the cell where the zombie is located.
     *
     * @return the cell where the zombie is located
     */
    public Cell getCell() {
        return this.cell;
    }

    /**
     * Sets the cell where the zombie is located.
     *
     * @param c the cell to set
     */
    public void setCell(Cell c) {
        this.cell.removeZombieInCell(this);
        c.setZombieInCell(this);
        this.cell = c;
    }

    /**
     * Inflicts damage to a survivor.
     *
     * @param survivor the survivor to attack
     */
    public void attack(Survivor survivor) {
        survivor.receiveDamage(this.damage);
    }

    /**
     * Receives damage from a survivor or another source.
     *
     * @param n the amount of damage to receive
     */
    public void receiveDamage(int n) {
        this.health -= n;
    }

    /**
     * Retrieves the health points of the zombie.
     *
     * @return the health points of the zombie
     */
    public int getHealth() {
        return health;
    }

    /**
     * Retrieves the damage points inflicted by the zombie.
     *
     * @return the damage points inflicted by the zombie
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Checks if the zombie is dead.
     *
     * @return true if the zombie is dead, false otherwise
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Moves the zombie to the specified cell.
     *
     * @param c the cell to move to
     */
    public void moveIn(Cell c) {
        if (this.canMoveIn(c)) {
            this.setCell(c);
        } else {
            System.out.println("Impossible action");
        }
    }

    /**
     * Checks if the zombie can move to the specified cell.
     *
     * @param c the cell to move to
     * @return true if the zombie can move to the cell, false otherwise
     */
    public boolean canMoveIn(Cell c) {
        if (this.getCell().getPositionI() != this.getGrid().getLigne() - 1 && c.getNorthDoor().isOpen()) {
            return true;
        } else if (this.getCell().getPositionI() != 0 && c.getSouthDoor().isOpen()) {
            return true;
        } else if (this.getCell().getPositionJ() != 0 && c.getEastDoor().isOpen()) {
            return true;
        } else if (this.getCell().getPositionJ() != this.getGrid().getColonne() - 1 && c.getWestDoor().isOpen()) {
            return true;
        }
        return false;
    }

    /**
     * Executes the zombie's action.
     */
    public void play() {
        if (this.getCell().getSurvivorInCell().size() == 0) {
            this.actions.get(0).doIt(this);
        } else {
            this.actions.get(1).doIt(this);
        }
    }
}
