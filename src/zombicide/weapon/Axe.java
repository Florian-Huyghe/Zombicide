package zombicide.weapon;

/**
 * Represents an axe weapon in the game.
 */
public class Axe extends Weapon {
    
    /**
     * Constructor for the Axe class.
     */
    public Axe() {
        super("Axe", 1, 4, 2, 0, 0, false);
    }

    /**
     * Provides a string representation of the axe.
     *
     * @return A string describing the axe.
     */
    public String toString(){
        return "It's an axe. The number of dice throws is " + this.numberOfDiceThrows + ", the threshold is " 
        + this.threshold + ", the damage value is " + this.damageValue + ", and the min and max range are " + this.minRange + " and " + this.maxRange;
    }
}
