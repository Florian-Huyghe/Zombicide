package test.zombicide;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import zombicide.grid.Board;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Walker;
import zombicide.actor.zombie.Zombie;

public class ZombieTest {
    
    @Test
    public void testAttack() {
        Survivor survivor = new Survivor("Rick");
        Zombie walker = new Walker();
        int initialHealth = survivor.getHealth();
        walker.attack(survivor);
        assertEquals(initialHealth - walker.getDamage(), survivor.getHealth());
    }

    @Test
    public void testGetHealth() {
        Zombie walker = new Walker();
        assertEquals(1, walker.getHealth());
    }

    @Test
    public void testGetDamage() {
        Zombie walker = new Walker();
        assertEquals(1, walker.getDamage());
    }

    @Test
    public void testIsDead() {
        Zombie walker = new Walker();
        assertFalse(walker.isDead());
        walker.receiveDamage(1);
        assertTrue(walker.isDead());
    }

    @Test
    public void testReceiveDamage() {
        Zombie walker = new Walker();
        int initialHealth = walker.getHealth();
        int damage = 1;
        walker.receiveDamage(damage);
        assertEquals(initialHealth - damage, walker.getHealth());
    }

    @Test
    public void testToString() {
        Zombie walker = new Walker();
        String expected = "The Walker has 1 HP and deals 1 damage";
        assertEquals(expected, walker.toString());
    }
    

     @Test 
    public void test_canMoveIn(){
        List<Zombie> listeZombies=new ArrayList<>();
        Zombie z1= new Runner();
        List<Survivor> listesSurvivors=new ArrayList<>();
        listeZombies.add(z1);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        assertTrue(z1.canMoveIn(board.getCells()[1][2]));
        assertFalse(z1.canMoveIn(board.getCells()[1][1]));
        z1.moveIn(board.getCells()[1][2]);
        assertEquals(z1.getCell(),board.getCells()[1][2]);

    }
}