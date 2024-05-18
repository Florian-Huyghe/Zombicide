package zombicide.action;

import zombicide.listchooser.InteractiveListChooser;
import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Brute;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Walker;
import zombicide.grid.cell.Cell;
import zombicide.grid.cell.Continental;
import zombicide.grid.cell.door.Door;

import java.util.List;
import java.util.Random;

/**
 * The OpenTheDoor class represents an action of opening a door in the game Zombicide.
 * This action allows survivors to open doors, potentially revealing zombies behind them.
 */
public class OpenTheDoor extends Action {
    private Random random;

    /**
     * Constructor for the OpenTheDoor class.
     *
     * @param description The description of the action.
     */
    public OpenTheDoor(String description) {
        super(description);
        this.random = new Random();
    }

    /**
     * Method to execute the action of opening a door.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        InteractiveListChooser<Door> ilc = new InteractiveListChooser<Door>();
        Door chosenDoor = ilc.choose("Which door do you choose?", s.getCell().getDoors());
        if (chosenDoor != null) {
            if (s.getWeaponInHand() != null) {
                chosenDoor.openTheDoor();
                s.decrementedActionPoints();
                s.getCell().makeNoise();

                List<Cell> neighborCells = s.getCell().getNeighborCells();

                for (int i = 0; i < neighborCells.size(); i++) {
                    if ((neighborCells.get(i).getNorthDoor() == chosenDoor ||
                            neighborCells.get(i).getSouthDoor() == chosenDoor ||
                            neighborCells.get(i).getEastDoor() == chosenDoor ||
                            neighborCells.get(i).getWestDoor() == chosenDoor) &&
                            !(neighborCells.get(i) instanceof Continental)) {

                        int nbZombie = random.nextInt(3) + 1;
                        int zombAuth = 0;

                        for (int j = 0; j < nbZombie; j++) {
                            int rdn = random.nextInt(4) + 1;
                            if (rdn == 1 && zombAuth == 0) {
                                Brute brute = new Brute();
                                s.getGrid().addZombie(neighborCells.get(i), brute);
                                zombAuth = 1;
                            } else if (rdn == 2) {
                                Runner runner = new Runner();
                                s.getGrid().addZombie(neighborCells.get(i), runner);
                            } else if (rdn == 3 && zombAuth == 0) {
                                Abomination abomination = new Abomination();
                                s.getGrid().addZombie(neighborCells.get(i), abomination);
                                zombAuth = 1;
                            } else {
                                Walker walker = new Walker();
                                s.getGrid().addZombie(neighborCells.get(i), walker);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Override of the toString method to return the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String toString() {
        return "Open the door";
    }
}
