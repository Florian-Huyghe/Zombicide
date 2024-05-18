package test.zombicide;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.grid.cell.Cell;
import zombicide.grid.cell.Continental;
import zombicide.grid.cell.Room;
import zombicide.grid.cell.door.Door;
import zombicide.weapon.Axe;
import zombicide.weapon.Rifle;
import zombicide.weapon.Weapon;
import zombicide.tool.*;


public class CellTest {

    @Test
    public void testSetSurvivorInCell() {
        Cell cell = new Continental(1,1);
        Survivor survivor = new Survivor("Rick");
        cell.setSurvivorInCell(survivor);
        assertTrue(cell.getSurvivorInCell().contains(survivor));
    }

    @Test
    public void testRemoveSurvivorInCell() {
        Cell cell = new Continental(1,1);
        Survivor survivor = new Survivor("Rick");
        cell.setSurvivorInCell(survivor);
        cell.removeSurvivorInCell(survivor);
        assertFalse(cell.getSurvivorInCell().contains(survivor));
    }
    @Test
    public void test_all_doors_closed() {
        Cell cell = new Room(0, 0);
        Door northDoor = new Door();
        Door southDoor = new Door();
        Door eastDoor = new Door();
        Door westDoor = new Door();
       
        cell.setNorthDoor(northDoor);
        cell.setSouthDoor(southDoor);
        cell.setEastDoor(eastDoor);
        cell.setWestDoor(westDoor);
       
        List<Door> expectedDoors = new ArrayList<>();
        expectedDoors.add(northDoor);
        expectedDoors.add(southDoor);
        expectedDoors.add(eastDoor);
        expectedDoors.add(westDoor);
        List<Door> actualDoors = cell.getDoors();
        assertEquals(expectedDoors, actualDoors);
       }

   
    @Test
    public void test_returns_neighbor_cells() {
        Cell cell = new Room(0, 0);
        Cell northCell = new Room(0, 1);
        Cell southCell = new Room(0, -1);
        Cell westCell = new Room(-1, 0);
        Cell eastCell = new Room(1, 0);
        cell.setNorthCell(northCell);
        cell.setSouthCell(southCell);
        cell.setWestCell(westCell);
        cell.setEastCell(eastCell);


        List<Cell> neighborCells = cell.getNeighborCells();

        assertEquals(4, neighborCells.size());
        assertTrue(neighborCells.contains(northCell));
        assertTrue(neighborCells.contains(southCell));
        assertTrue(neighborCells.contains(westCell));
        assertTrue(neighborCells.contains(eastCell));
    }

        public void test_current_cell_and_neighbors_within_boundaries() {
            Cell[][] grid = new Cell[3][3];
            Cell cell = new Room(1, 1);
            grid[1][1] = cell;
            grid[0][1] = new Room(0, 1);
            grid[2][1] = new Room(2, 1);
            grid[1][0] = new Room(1, 0);
            grid[1][2] = new Room(1, 2);
    
            List<Cell> expected = new ArrayList<>();
            expected.add(cell);
            expected.add(grid[0][1]);
            expected.add(grid[2][1]);
            expected.add(grid[1][0]);
            expected.add(grid[1][2]);
    
            List<Cell> result = cell.inRange1(grid);
    
            assertEquals(expected, result);
        }


        @Test
        public void test_worksForOneByOneGrid() {
            Cell[][] grid = new Cell[1][1];
            Cell cell = new Room(0, 0);
            grid[0][0] = cell;
            List<Cell> result = cell.inRange3(grid);
            assertEquals(1, result.size());
            assertTrue(result.contains(cell));
        }


    @Test
    public void test_empty_list_when_no_weapons_present() {
        Cell cell = new Room(0, 0);
        List<Weapon> weapons = cell.getWeaponInCell();
        assertNotNull(weapons);
        assertTrue(weapons.isEmpty());
    }
    
        
    @Test
    public void test_add_weapon_to_empty_cell() {
        Cell cell = new Room(0, 0);
        Weapon weapon = new Axe();
        
        cell.setWeaponInCell(weapon);
        
        List<Weapon> weapons = cell.getWeaponInCell();
        assertEquals(1, weapons.size());
        assertTrue(weapons.contains(weapon));
        }

    @Test
    public void test_remove_weapon_from_cell_with_one_weapon() {
        Cell cell = new Room(0, 0);
        Weapon weapon = new Axe();
        cell.setWeaponInCell(weapon);
        cell.removeWeaponInCell(weapon);
        assertTrue(cell.getWeaponInCell().isEmpty());

        }
    


    @Test
    public void test_with_all_doors_and_occupants() {
        Cell cell = new Room(0, 0);
        Door northDoor = new Door();
        Door southDoor = new Door();
        Door eastDoor = new Door();
        Door westDoor = new Door();
        cell.setNorthDoor(northDoor);
        cell.setSouthDoor(southDoor);
        cell.setEastDoor(eastDoor);
        cell.setWestDoor(westDoor);

        Survivor survivor1 = new Survivor("rick");
        Survivor survivor2 = new Survivor("jean");
        cell.setSurvivorInCell(survivor1);
        cell.setSurvivorInCell(survivor2);

        Zombie zombie1 = new Runner();
        Zombie zombie2 = new Runner();
        cell.setZombieInCell(zombie1);
        cell.setZombieInCell(zombie2);

        Weapon weapon1 = new Axe();
        Weapon weapon2 = new Rifle();
        cell.setWeaponInCell(weapon1);
        cell.setWeaponInCell(weapon2);

        Tool tool1 = new FirstAidKit();
        Tool tool2 = new MasterKey();
        cell.setToolsInCell(tool1);
        cell.setToolsInCell(tool2);

        String expected = " Cell : North door: " + northDoor.toString() + " East door : "+ eastDoor.toString() + " West door : " + westDoor.toString() +" South door :"+  southDoor.toString() + " | \n ";
        expected += "Survivor :\n";
        expected += survivor1.toString() + "\n";
        expected += survivor2.toString() + "\n";
        expected += " | ";
        expected += "Zombie :\n";
        expected += zombie1.toString() + "\n";
        expected += zombie2.toString() + "\n";
        expected += " | ";
        expected += "Weapons : \n";
        expected += weapon1.toString() + "\n";
        expected += weapon2.toString() + "\n";
        expected += " | ";
        expected += "Tools : \n";
        expected += tool1.toString() + "\n";
        expected += tool2.toString() + "\n";

        assertEquals(expected, cell.toString1());
    }


}





