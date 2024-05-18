package zombicide.weapon;

/**
 * Represents a pistol weapon in the game.
 */
public class Pistol extends Weapon {

    /**
     * Constructor for the Pistol class.
     */
    public Pistol() {
        super("Pistol", 1, 4, 1, 0, 1, true);
    }

    /**
     * Provides a string representation of the pistol.
     *
     * @return A string describing the pistol.
     */
    public String toString(){
        return "It's a pistol. The number of dice throws is " + this.numberOfDiceThrows + ", the threshold is " 
        + this.threshold + ", the damage value is " + this.damageValue + ", and the min and max range are " + this.minRange + " and " + this.maxRange;
    }
}
