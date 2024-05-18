package zombicide.actor.zombie;


/**
 * The Malenia class represents a specific type of Zombie called Malenia.
 * Malenia extends the Zombie class and inherits its properties and behaviors.
 */
public class Malenia extends Zombie {


    /**
     * Constructs Malenia with default health and damage values.
     */
    public Malenia() {
        super(12, 6);
    }

    /**
     * Retrieves a string representation of the Malenia.
     * 
     * @return a string describing the Abomination zombie's health and damage
     */
    public String toString(){
        if(this.isDead()){
            return "Malenia is dead !";
        }
        return "Malenia has " + this.getHealth() + " HP and deals " + this.getDamage() + " damage";
    }
    
}
