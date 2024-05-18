package zombicide.weapon;

/**
 * Represents a rifle weapon in the game.
 */
public class Rifle extends Weapon {

    /**
     * Constructor for the Rifle class.
     */
    public Rifle() {
        super("Rifle", 2, 4, 1, 1, 3, true);
    }

    /**
     * Provides a string representation of the rifle.
     *
     * @return A string describing the rifle.
     */
    public String toString(){
        return "It's a rifle. The number of dice throws is " + this.numberOfDiceThrows + ", the threshold is " 
        + this.threshold + ", the damage value is " + this.damageValue + ", and the min and max range are " + this.minRange + " and " + this.maxRange;
    }
}
