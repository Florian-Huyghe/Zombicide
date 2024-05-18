package zombicide.action;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.cell.Cell;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.weapon.Chainsaw;
import zombicide.weapon.Pistol;
import zombicide.weapon.Weapon;

/**
 * The FightAction class represents an action of fighting performed by a survivor in the game Zombicide.
 * This action allows survivors to attack zombies within their weapon's range.
 */
public class FightAction extends Action {

    /**
     * Constructor for the FightAction class.
     *
     * @param description The description of the action.
     */
    public FightAction(String description) {
        super(description);
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Fighter Role Attack";
    }

    /**
     * Method to execute the fighting action.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        if (actor instanceof Survivor) {
            // If the actor is a survivor
            Survivor s = (Survivor) actor;
            Weapon weapon = s.getWeaponInHand();
            if (weapon == null) {
                // If the survivor doesn't have a weapon in hand
                System.out.println("You don't have a weapon in your hands");
            } else if (weapon.getMaxRange() == 0) {
                // If the weapon has a range of 0 (melee attack)
                InteractiveListChooser<Zombie> ilc = new InteractiveListChooser<Zombie>();
                Zombie chosenZombie = ilc.choose("Which Zombie", s.getCell().getZombieInCell());
                if (chosenZombie != null) {
                    s.attackZombie(weapon.attackFighterRole(), chosenZombie);
                    s.decrementedActionPoints();
                    if (weapon instanceof Pistol || weapon instanceof Chainsaw) {
                        s.getCell().makeNoise();
                    }
                    if (chosenZombie.isDead()) {
                        System.out.println("Zombie is dead");
                        s.setLevel();
                        chosenZombie.getCell().removeZombieInCell(chosenZombie);
                        chosenZombie.getGrid().removeZombiesBoard(chosenZombie);
                    } else {
                        System.out.println("Zombie still alive");
                    }
                } else {
                    System.out.println("You can't, there are no zombies in this cell");
                }
            } else if (weapon.getMaxRange() == 1) {
                // If the weapon has a range of 1
                List<Cell> neighborCells = s.getCell().getNeighborCells();
                List<Zombie> zombiesInRange = new ArrayList<>();
                neighborCells.add(s.getCell()); // Include the survivor's current cell
                for (Cell cell : neighborCells) {
                    zombiesInRange.addAll(cell.getZombieInCell());
                }
                InteractiveListChooser<Zombie> ilc = new InteractiveListChooser<Zombie>();
                Zombie chosenZombie = ilc.choose("Which Zombie", zombiesInRange);
                if (chosenZombie != null) {
                    s.attackZombie(weapon.attackFighterRole(), chosenZombie);
                    s.decrementedActionPoints();
                    s.getCell().makeNoise();
                    if (chosenZombie.isDead()) {
                        System.out.println("Zombie is dead");
                        s.setLevel();
                        chosenZombie.getCell().removeZombieInCell(chosenZombie);
                        chosenZombie.getGrid().removeZombiesBoard(chosenZombie);
                    } else {
                        System.out.println("Zombie still alive");
                    }
                } else {
                    System.out.println("You can't, there are no zombies in this cell");
                }
            } else {
                // If the weapon has a range greater than 1
                List<Cell> cellsInRange = s.getCell().inRange3(s.getGrid().getCells());
                List<Zombie> zombiesInRange = new ArrayList<>();
                for (Cell cell : cellsInRange) {
                    zombiesInRange.addAll(cell.getZombieInCell());
                }
                InteractiveListChooser<Zombie> ilc = new InteractiveListChooser<Zombie>();
                Zombie chosenZombie = ilc.choose("Which Zombie", zombiesInRange);
                if (chosenZombie != null) {
                    s.attackZombie(weapon.attackFighterRole(), chosenZombie);
                    s.decrementedActionPoints();
                    s.getCell().makeNoise();
                    if (chosenZombie.isDead()) {
                        System.out.println("Zombie is dead");
                        s.setLevel();
                        chosenZombie.getCell().removeZombieInCell(chosenZombie);
                        chosenZombie.getGrid().removeZombiesBoard(chosenZombie);
                    } else {
                        System.out.println("Zombie still alive");
                    }
                } else {
                    System.out.println("You can't, there are no zombies in this cell");
                }
            }
        }
    }
}
