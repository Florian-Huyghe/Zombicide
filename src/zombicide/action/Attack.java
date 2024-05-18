package zombicide.action;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.cell.Cell;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.listchooser.RandomListChooser;
import zombicide.weapon.Chainsaw;
import zombicide.weapon.Pistol;
import zombicide.weapon.Weapon;

/**
 * The Attack class represents an action of attacking performed by an actor in the game Zombicide.
 * This action can be performed by a survivor against a zombie or by a zombie against a survivor.
 */
public class Attack extends Action {

    /**
     * Constructor for the Attack class.
     *
     * @param description The description of the action.
     */
    public Attack(String description) {
        super(description);
    }

    /**
     * Method to execute the attack action.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        if (actor instanceof Zombie) {
            // If the actor is a zombie
            Zombie z = (Zombie) actor;
            RandomListChooser<Survivor> ilc = new RandomListChooser<Survivor>();
            Survivor choseSurvivor = ilc.choose("random survivor", z.getCell().getSurvivorInCell());
            if (choseSurvivor != null) {
                z.attack(choseSurvivor);
                if (!choseSurvivor.isAlive()) {
                    choseSurvivor.getCell().removeSurvivorInCell(choseSurvivor);
                    choseSurvivor.getGrid().removeSurvivorInBoard(choseSurvivor);
                    System.out.println(choseSurvivor.getName() + " is dead");
                }
            }
            System.out.println("You've chosen : " + choseSurvivor);
        } else {
            // If the actor is a survivor
            Survivor s = (Survivor) actor;
            Weapon weapon = s.getWeaponInHand();
            if (weapon == null) {
                // If the survivor doesn't have a weapon in hand
                System.out.println("You don't have a weapon in your hands");
            } else if (weapon.getMaxRange() == 0) {
                // If the weapon has a range of 0 (melee attack)
                InteractiveListChooser<Zombie> ilc = new InteractiveListChooser<Zombie>();
                Zombie choseZombie = ilc.choose("Which Zombie", s.getCell().getZombieInCell());
                if (choseZombie != null) {
                    s.attackZombie(weapon.attack(), choseZombie);
                    s.decrementedActionPoints();
                    if (weapon instanceof Pistol || weapon instanceof Chainsaw) {
                        s.getCell().makeNoise();
                    }
                    if (choseZombie.isDead()) {
                        System.out.println("Zombie is dead");
                        s.setLevel();
                        choseZombie.getCell().removeZombieInCell(choseZombie);
                        choseZombie.getGrid().removeZombiesBoard(choseZombie);
                    } else {
                        System.out.println("Zombie still alive");
                    }
                } else {
                    System.out.println("You can't, there are no zombies in this cell");
                }
            } else if (weapon.getMaxRange() == 1) {
                // If the weapon has a range of 1
                List<Cell> lstCell = s.getCell().inRange1(s.getGrid().getCells());
                List<Zombie> lstZombie = new ArrayList<>();
                for (int i = 0; i < lstCell.size(); i++) {
                    List<Zombie> lstZomb = lstCell.get(i).getZombieInCell();
                    for (int j = 0; j < lstZomb.size(); j++) {
                        if (!lstZombie.contains(lstZomb.get(j))) {
                            lstZombie.add(lstZomb.get(j));
                        }
                    }
                }
                InteractiveListChooser<Zombie> ilc = new InteractiveListChooser<Zombie>();
                Zombie choseZombie = ilc.choose("Which Zombie", lstZombie);
                if (choseZombie != null) {
                    s.attackZombie(weapon.attack(), choseZombie);
                    s.decrementedActionPoints();
                    s.getCell().makeNoise();
                    if (choseZombie.isDead()) {
                        System.out.println("Zombie is dead");
                        s.setLevel();
                        choseZombie.getCell().removeZombieInCell(choseZombie);
                        choseZombie.getGrid().removeZombiesBoard(choseZombie);
                    } else {
                        System.out.println("Zombie still alive");
                    }
                } else {
                    System.out.println("You can't, there are no zombies in this cell");
                }
            } else {
                // If the weapon has a range greater than 1
                List<Cell> listCell = s.getCell().inRange3(s.getGrid().getCells());
                List<Zombie> listZombie = new ArrayList<>();
                for (int i = 0; i < listCell.size(); i++) {
                    List<Zombie> zombies = listCell.get(i).getZombieInCell();
                    for (int j = 0; j < zombies.size(); j++) {
                        if (!listZombie.contains(zombies.get(j))) {
                            listZombie.add(zombies.get(j));
                        }
                    }
                }
                InteractiveListChooser<Zombie> ilc = new InteractiveListChooser<Zombie>();
                Zombie choseZombie = ilc.choose("Which Zombie", listZombie);
                if (choseZombie != null) {
                    s.attackZombie(weapon.attack(), choseZombie);
                    s.decrementedActionPoints();
                    s.getCell().makeNoise();
                    if (choseZombie.isDead()) {
                        System.out.println("Zombie is dead");
                        s.setLevel();
                        choseZombie.getCell().removeZombieInCell(choseZombie);
                        choseZombie.getGrid().removeZombiesBoard(choseZombie);
                    } else {
                        System.out.println("Zombie still alive");
                    }
                } else {
                    System.out.println("You can't, there are no zombies in this cell");
                }
            }
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    public String toString() {
        return "Attack";
    }
}
