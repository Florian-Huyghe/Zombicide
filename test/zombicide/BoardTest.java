package test.zombicide;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import zombicide.grid.Board;
import zombicide.grid.cell.Cell;
import zombicide.actor.survivor.Survivor;
import zombicide.tool.HealingVial;
import zombicide.tool.Map;
import zombicide.tool.Tool;
import zombicide.weapon.Axe;
import zombicide.weapon.Weapon;
import zombicide.actor.zombie.*;


public class BoardTest {

    @Test 
    public void test_init_Board(){
        List<Zombie> listeZombies=new ArrayList<>();
        Zombie z1= new Runner();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listeZombies.add(z1);
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        assertEquals(z1.getCell(),board.getCells()[0][2]);
        assertEquals(survivor.getCell(),board.getCells()[2][2]);
        assertFalse(board.getCells()[1][1].isStreet());
        assertTrue(board.getCells()[0][2].getSouthDoor().isOpen());

    }
    @Test
    public void test_survivors_levels_greater_than_30_and_no_zombies_or_survivors_left() {
        List<Zombie> listeZombies=new ArrayList<>();
        Zombie z1= new Runner();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listeZombies.add(z1);
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        for (int i= 0; i<30;i++){
            survivor.setLevel();
        }
        assertTrue(board.endGame());
        }

    @Test 
    public void test_add_Zombie(){
        List<Zombie> listeZombies=new ArrayList<>();
        Zombie z1= new Runner();
        Zombie z2= new Runner();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listeZombies.add(z1);
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        board.addZombie(board.getCells()[2][2], z2);
        assertEquals(board.getCells()[2][2],z2.getCell());
        }


    @Test 
    public void test_place_Survivor(){
        List<Zombie> listeZombies=new ArrayList<>();
        Zombie z1= new Runner();
        Survivor survivor = new Survivor("Rick");
        Survivor survivor2 = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();

        listeZombies.add(z1);
        listesSurvivors.add(survivor);
        listesSurvivors.add(survivor2);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        board.placeSurvivor(1,2);
        assertEquals(2,board.getCells()[2][2].getSurvivorInCell().size());
        }


    @Test 
    public void test_place_Zombie(){
        List<Zombie> listeZombies=new ArrayList<>();
        Zombie z1= new Runner();
        Zombie z2=new Runner();
        Survivor survivor = new Survivor("Rick");
        Survivor survivor2 = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();

        listeZombies.add(z1);
        listesSurvivors.add(survivor);
        listesSurvivors.add(survivor2);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        board.placeZombie(0, 0, board.getLigne()-1, board.getColonne()-1, board.getFirstStreetRow(), board.getFirstStreetCol());
        assertEquals(2,board.getCells()[0][2].getZombieInCell().size());
        }

    @Test
    public void test_place_tool_valid_cell() {
        List<Zombie> listeZombies=new ArrayList<>();
        List<Survivor> listesSurvivors=new ArrayList<>();
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        Tool t1= new HealingVial(); 
        List<Tool> listeTool=new ArrayList<>(); 
        listeTool.add(t1);
        board.placeTool(2, 2, t1);
        assertEquals(listeTool, board.getCells()[2][2].getToolsInCell());
    }

    @Test
    public void test_place_weapon_in_room_cell() {
        List<Zombie> listeZombies=new ArrayList<>();
        List<Survivor> listesSurvivors=new ArrayList<>();
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        Weapon w1=new Axe();
        List<Weapon> listeWeapons=new ArrayList<>();
        listeWeapons.add(w1);
        board.placeWeapon(2, 2, w1);
        assertEquals(listeWeapons, board.getCells()[2][2].getWeaponInCell());

    }

    @Test
    public void test_returns_0_when_no_survivors_on_board() {
        List<Zombie> listeZombies=new ArrayList<>();
        List<Survivor> listesSurvivors=new ArrayList<>();
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        int result = board.getAllSurvivorsLevel();
        assertEquals(0, result);
        Survivor survivor = new Survivor("Rick");
        listesSurvivors.add(survivor);
        Board board1 = new Board(5, 5,listesSurvivors, listeZombies);
        result = board1.getAllSurvivorsLevel();
        assertEquals(1, result);
        }
    
    @Test
        public void test_MostNoisyCell(){
        List<Zombie> listeZombies=new ArrayList<>();
        List<Survivor> listesSurvivors=new ArrayList<>();
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        board.getCells()[2][2].makeNoise();
        Cell mostNoisyCell = board.getMostNoisyCell();
        assertEquals(board.getCells()[2][2], mostNoisyCell);

    }
    @Test
    public void test_ResetNoisyCell(){
    List<Zombie> listeZombies=new ArrayList<>();
    List<Survivor> listesSurvivors=new ArrayList<>();
    Board board = new Board(5, 5,listesSurvivors, listeZombies);
    board.getCells()[2][2].makeNoise();
    Cell mostNoisyCell = board.getMostNoisyCell();
    assertEquals(board.getCells()[2][2], mostNoisyCell);
    assertEquals(board.getCells()[2][2].getNoiseLevel(),1);
    board.getCells()[2][2].resetNoise();
    assertEquals(board.getCells()[2][2].getNoiseLevel(),0);

    }

}



