package zombicide.actor.zombie;

/**
 * Class representing a Walker zombie.
 */
public class Walker extends Zombie {
    
    /**
     * Constructs a Walker zombie with default health and damage values.
     */
    public Walker() {
        super(1, 1);
    }

    /**
     * Retrieves a string representation of the Walker zombie.
     * 
     * @return a string describing the Walker zombie's health and damage
     */
    public String toString(){
        if(this.isDead()){
            return "This Walker is dead";
        }
        return "The Walker has " + this.getHealth() + " HP and deals " + this.getDamage() + " damage";
    }
}
