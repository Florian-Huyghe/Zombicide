package zombicide.actor.zombie;

/**
 * Class representing an Abomination zombie.
 */
public class Abomination extends Zombie {
    
    /**
     * Constructs an Abomination zombie with default health and damage values.
     */
    public Abomination() {
        super(6, 3); 
    }

    /**
     * Retrieves a string representation of the Abomination zombie.
     * 
     * @return a string describing the Abomination zombie's health and damage
     */
    public String toString(){
        if(this.isDead()){
            return "This Abomination is dead";
        }
        return "The Abomination has " + this.getHealth() + " HP and deals " + this.getDamage() + " damage";
    }
}
