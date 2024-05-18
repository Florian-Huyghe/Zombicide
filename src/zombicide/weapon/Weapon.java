package zombicide.weapon;

import java.util.Random;

/**
 * Abstract class representing a weapon.
 */
public abstract class Weapon {
    
    protected String name;
    protected int numberOfDiceThrows;
    protected int threshold;
    protected int damageValue;
    protected int minRange;
    protected int maxRange;
    protected boolean noise;

    /**
     * Constructor for the Weapon class.
     * 
     * @param name               The name of the weapon.
     * @param numberOfDiceThrows The number of dice throws for the weapon.
     * @param threshold          The threshold for a successful attack.
     * @param damageValue        The damage value of the weapon.
     * @param minRange           The minimum range of the weapon.
     * @param maxRange           The maximum range of the weapon.
     * @param noise              A boolean indicating whether the weapon makes noise.
     */
    public Weapon(String name, int numberOfDiceThrows, int threshold, int damageValue, int minRange, int maxRange, boolean noise) {
        this.name = name;
        this.numberOfDiceThrows = numberOfDiceThrows;
        this.threshold = threshold;
        this.damageValue = damageValue;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.noise = noise;
    }

    /**
     * Method to perform an attack with the weapon.
     * 
     * @return The total damage dealt by the attack.
     */
    public int attack() {
        int totalDamage = 0;
        Random random = new Random();

        for (int i = 0; i < this.numberOfDiceThrows; i++) {
            int diceResult = random.nextInt(6) + 1; 

            if (diceResult >= this.threshold) {
                totalDamage += this.damageValue;
            }
        }

        return totalDamage;
    }

    /**
     * Method to perform an attack with the weapon, considering the fighter role.
     * 
     * @return The total damage dealt by the attack.
     */
    public int attackFighterRole() {
        int totalDamage = 0;
        Random random = new Random();

        for (int i = 0; i < this.numberOfDiceThrows; i++) {
            int diceResult = random.nextInt(6) + 2; // Adding 1 to the dice result for the fighter role

            if (diceResult >= this.threshold) {
                totalDamage += this.damageValue;
            }
        }

        return totalDamage;
    }

    /**
     * Retrieves the name of the weapon.
     * 
     * @return The name of the weapon.
     */
    public String getName(){
        return  this.name;
    }

    /**
     * Retrieves the number of dice throws for the weapon.
     * 
     * @return The number of dice throws.
     */
    public int getNumberOfDiceThrows(){
        return this.numberOfDiceThrows;
    }

    /**
     * Retrieves the threshold for a successful attack.
     * 
     * @return The threshold for a successful attack.
     */
    public int getThreshold(){
        return this.threshold;
    }

    /**
     * Retrieves the damage value of the weapon.
     * 
     * @return The damage value of the weapon.
     */
    public int getDamageValue(){
        return this.damageValue;
    }

    /**
     * Retrieves the minimum range of the weapon.
     * 
     * @return The minimum range of the weapon.
     */
    public int getMinRange(){
        return this.minRange;
    }

    /**
     * Retrieves the maximum range of the weapon.
     * 
     * @return The maximum range of the weapon.
     */
    public int getMaxRange(){
        return this.maxRange;
    }

    /**
     * Checks if a target is within the range of the weapon.
     * 
     * @param distance The distance to the target.
     * @return True if the target is within range, false otherwise.
     */
    public boolean isInRange(int distance) {
        return distance >= this.minRange && distance <= this.maxRange;
    }
}
