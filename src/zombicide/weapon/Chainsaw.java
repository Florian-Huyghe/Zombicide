package zombicide.weapon;

/**
 * Represents a chainsaw weapon in the game.
 */
public class Chainsaw extends Weapon {
    
    /**
     * Constructor for the Chainsaw class.
     */
    public Chainsaw() {
        super("Chainsaw", 2, 5, 3, 0, 0, true);
    }

    /**
     * Provides a string representation of the chainsaw.
     *
     * @return A string describing the chainsaw.
     */
    public String toString(){
        return "It's a chainsaw. The number of dice throws is " + this.numberOfDiceThrows + ", the threshold is " 
        + this.threshold + ", the damage value is " + this.damageValue + ", and the min and max range are " + this.minRange + " and " + this.maxRange;
    }
}
