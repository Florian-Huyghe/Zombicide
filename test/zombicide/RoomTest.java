package test.zombicide;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.grid.cell.Cell;
import zombicide.grid.cell.Room;
import zombicide.grid.cell.door.*;

public class RoomTest {

        @Test
        public void testGetRepresentation() {
            List<Zombie> listeZombies=new ArrayList<>();
            List<Survivor> listesSurvivors=new ArrayList<>();
            Board board = new Board(5, 5,listesSurvivors, listeZombies);
            Cell room=new Room(0, 0);
                assertEquals("R", room.getRepresentation());
        }
    
        @Test
        public void testCanPlace() {
            List<Zombie> listeZombies=new ArrayList<>();
            List<Survivor> listesSurvivors=new ArrayList<>();
            Board board = new Board(5, 5,listesSurvivors, listeZombies);
            assertTrue(board.getCells()[0][0].canPlace());
        
    }
    }
