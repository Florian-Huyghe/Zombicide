package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.cell.Cell;
import zombicide.grid.cell.Continental;
import zombicide.grid.cell.Medical;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.listchooser.RandomListChooser;

/**
 * The Deplacement class represents an action of movement performed by an actor in the game Zombicide.
 * This action allows survivors to move to neighboring cells and zombies to move towards the noisiest cell.
 */
public class Deplacement extends Action {

    /**
     * Constructor for the Deplacement class.
     *
     * @param description The description of the action.
     */
    public Deplacement(String description) {
        super(description);
    }

    /**
     * Method to execute the movement action.
     *
     * @param actor The actor performing the action.
     */
    @Override
    public void doIt(Actor actor) {
        if (actor instanceof Survivor) {
            // If the actor is a survivor
            Survivor s = (Survivor) actor;
            InteractiveListChooser<Cell> ilc = new InteractiveListChooser<Cell>();
            Cell chosenCell = ilc.choose("Which cell do you choose?", s.getCell().getNeighborCells());
            if (chosenCell != null) {
                if (chosenCell instanceof Medical) {
                    ((Medical) chosenCell).addPotion();
                }
                s.moveIn(chosenCell);
                s.decrementedActionPoints();
            }
        } else {
            // If the actor is a zombie
            Zombie z = (Zombie) actor;
            Cell mostNoisyCell = z.getGrid().getMostNoisyCell();
            
            if (mostNoisyCell.getNoiseLevel() == 0) {

                RandomListChooser<Cell> rlc = new RandomListChooser<Cell>();
                Cell chosenCell = rlc.choose("Which cell", z.getCell().getNeighborCells());
                if (chosenCell != null && !(chosenCell instanceof Continental)) {
                    z.moveIn(chosenCell);
                }

            } else {

                int posINoisyCell = mostNoisyCell.getPositionI();
                int posJNoisyCell = mostNoisyCell.getPositionJ();

                System.out.println(posINoisyCell + "  " + posJNoisyCell);

                int posIZombie = z.getCell().getPositionI();
                int posJZombie = z.getCell().getPositionJ();

                int difPosI = posINoisyCell - posIZombie;
                int difPosJ = posJNoisyCell - posJZombie;

                
                if (difPosJ > 0 && difPosI == 0) {
                    if (!(z.getGrid().getCells()[posIZombie][posJZombie + 1] instanceof Continental)) {
                        z.moveIn(z.getGrid().getCells()[posIZombie][posJZombie + 1]);
                    }
                } else if (difPosJ < 0 && difPosI == 0) {
                    if (!(z.getGrid().getCells()[posIZombie][posJZombie - 1] instanceof Continental)) {
                        z.moveIn(z.getGrid().getCells()[posIZombie][posJZombie - 1]);
                    }
                } else if (difPosI > 0) {
                    if (!(z.getGrid().getCells()[posIZombie + 1][posJZombie] instanceof Continental)) {
                        z.moveIn(z.getGrid().getCells()[posIZombie + 1][posJZombie]);
                    }
                } else {
                    if (!(z.getGrid().getCells()[posIZombie - 1][posJZombie] instanceof Continental)) {
                        z.moveIn(z.getGrid().getCells()[posIZombie - 1][posJZombie]);
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
    public String toString() {
        return "Deplacement";
    }
}
