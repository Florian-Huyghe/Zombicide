package zombicide.weapon;

/**
 * Represents a crowbar weapon in the game.
 */
public class Crowbar extends Weapon {

    /**
     * Constructor for the Crowbar class.
     */
    public Crowbar() {
        super("Crowbar", 1, 4, 1, 0, 0, false);
    }
    
    /**
     * Provides a string representation of the crowbar.
     *
     * @return A string describing the crowbar.
     */
    public String toString(){
        return "It's a crowbar. The number of dice throws is " + this.numberOfDiceThrows + ", the threshold is " 
        + this.threshold + ", the damage value is " + this.damageValue + ", and the min and max range are " + this.minRange + " and " + this.maxRange;
    }
}
