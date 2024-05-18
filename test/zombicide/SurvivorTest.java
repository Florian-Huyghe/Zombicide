package test.zombicide;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import zombicide.action.Action;
import zombicide.actor.survivor.LuckyRole;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.grid.cell.Cell;
import zombicide.tool.HealingVial;
import zombicide.tool.Tool;

public class SurvivorTest {
    
    public  void main(String[] args) {
        testReceiveDamage();
        testIsAlive();
        testAttackZombie();
    }
    @Test
    public  void testReceiveDamage() {
        Survivor survivor = new Survivor("Rick");
        survivor.receiveDamage(3);
        assertEquals(survivor.getHealth(),2);
    }

    @Test 
    public void testHealing(){
        Survivor survivor = new Survivor("Rick");
        survivor.receiveDamage(3);
        survivor.healing(1);
        assertEquals(survivor.getHealth(),3);

    }


    @Test
    public  void testIsAlive() {
        Survivor survivor = new Survivor("Rick");
        assertTrue(survivor.isAlive());
        survivor.receiveDamage(5);
        assertFalse(survivor.isAlive());
    }

    @Test
    public  void testAttackZombie() {
        Survivor survivor = new Survivor("Rick");
        Zombie zombie = new Abomination();
        survivor.attackZombie(2, zombie);
        assertEquals(zombie.getHealth(),4);
    }

    @Test 
    public void testRoleSurvivor(){
        Survivor survivor = new Survivor("Rick");
        LuckyRole l1=new LuckyRole();
        survivor.addRole(l1);
        assertEquals(survivor.getActionPoints(),4);


    }

    @Test
    public void test_adds_10_actions_to_list() {
        Survivor survivor = new Survivor("Rick");
        assertEquals(10, survivor.getListAction().size());
    }

            
    @Test
    public void test_returns_tool_in_hand_if_exists() {
        Survivor survivor = new Survivor("Rick");
        Tool t1= new HealingVial(); 
        survivor.setTools(t1);
        survivor.setToolInHand(t1);
        Tool result = survivor.getToolInHand();
        assertEquals(t1, result);
    }
    

    @Test
     public void test_survivor_health_greater_than_zero() {
         Survivor survivor = new Survivor("John");
         survivor.receiveDamage(3);
         boolean result = survivor.isAlive();
         assertTrue(result);
    }
    @Test 
    public void test_canMoveIn(){
        List<Zombie> listeZombies=new ArrayList<>();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        assertTrue(survivor.canMoveIn(board.getCells()[1][2]));
        assertFalse(survivor.canMoveIn(board.getCells()[1][1]));
        survivor.moveIn(board.getCells()[1][2]);
        assertEquals(survivor.getCell(),board.getCells()[1][2]);

    }

    @Test
    public void test_decremented_action_points() {
        Survivor survivor = new Survivor("rick");
        survivor.decrementedActionPoints();
        assertEquals(2, survivor.getActionPoints());
    }

        // The method sets the actionPoints to 3 if the level is less than 3.
        @Test
        public void test_setActionPointsTo3IfLevelLessThan3() {
            Survivor survivor = new Survivor("John");
            survivor.setLevel();
            survivor.setLevel();
            assertEquals(4, survivor.getActionPointsMax());
        }

     @Test
    public void test_returns_grid() {
        List<Zombie> listeZombies=new ArrayList<>();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        Board result = survivor.getGrid();
        assertEquals(board,result);
    }
}
