package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.tool.Shield;


/**
 * The UseShield class represents an action of using a shield in the game.
 */
public class UseShield extends Action {

    /**
     * Constructor for the UseShield class.
     *
     * @param description the description of the action
     */
    public UseShield(String description) {
        super(description);
    }

    /**
     * This method represents the logic for using a shield in the game.
     * It takes an Actor object as a parameter and casts it to a Survivor object.
     * If the Survivor's tool in hand is an instance of Shield, it places the shield with a strength of 2.
     * It then removes the shield from the Survivor's actions.
     * If the Survivor's tool in hand is not a shield, it prints a message asking the player to select a shield.
     */
    @Override
    public void doIt(Actor actor) {
        Survivor s = (Survivor) actor;
        if (s.getToolInHand() instanceof Shield) {
            s.placeShield(2);
            s.removeAction(s.getToolInHand());
        }else{
            System.out.println("Please select a shield in your hand.");
        }
    }


    
}
