package zombicide.actor.zombie;

/**
 * Class representing a Brute zombie.
 */
public class Brute extends Zombie {
    
    /**
     * Constructs a Brute zombie with default health and damage values.
     */
    public Brute() {
        super(4, 2);
    }

    /**
     * Retrieves a string representation of the Brute zombie.
     * 
     * @return a string describing the Brute zombie's health and damage
     */
    public String toString(){
        if(this.isDead()){
            return "This Brute is dead";
        }
        return "The Brute has " + this.getHealth() + " HP and deals " + this.getDamage() + " damage";
    }
}
