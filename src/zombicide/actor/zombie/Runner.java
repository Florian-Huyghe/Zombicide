package zombicide.actor.zombie;

/**
 * Class representing a Runner zombie.
 */
public class Runner extends Zombie {
    
    /**
     * Constructs a Runner zombie with default health and damage values.
     */
    public Runner() {
        super(2, 1);
    }

    /**
     * Retrieves a string representation of the Runner zombie.
     * 
     * @return a string describing the Runner zombie's health and damage
     */
    public String toString(){
        if(this.isDead()){
            return "This Runner is dead";
        }
        return "The Runner has " + this.getHealth() + " HP and deals " + this.getDamage() + " damage";
    }
}
