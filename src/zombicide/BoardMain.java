package zombicide;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.survivor.FighterRole;
import zombicide.actor.survivor.LuckyRole;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.tool.FirstAidKit;
import zombicide.tool.Tool;
import zombicide.weapon.Pistol;
import zombicide.weapon.Rifle;
import zombicide.weapon.Weapon;

/**
 * This class demonstrates the usage of various elements in the Zombicide game.
 * It creates survivors, zombies, weapons, and tools, and showcases their interactions.
 */
public class BoardMain {

    /**
     * The main method that initializes and demonstrates the usage of various game elements.
     * @param args The command-line arguments. The first argument represents the number of rows on the game board,
     *             and the second argument represents the number of columns on the game board.
     */
    public static void main(String[] args) {

        Board board;

        List<Survivor> s = new ArrayList<>();
        List<Zombie> z=new ArrayList<>();

        // Creating survivors
        Survivor s1=new Survivor("florian");
        Survivor s2=new Survivor("hichem");

        s.add(s1);

        FighterRole f1=new FighterRole();
        LuckyRole l1=new LuckyRole();

        s1.addRole(f1);
        s2.addRole(l1);

        System.out.println(s1.toString());
        System.out.println(s2.toString());

        System.out.println("------------------------");

        // Creating zombies
        Zombie z1=new Abomination();
        Zombie z2= new Runner();

        z.add(z2);

        System.out.println(z1.toString());
        System.out.println(z2.toString());

        System.out.println("------------------------");

        // Creating weapons
        Weapon w1=new Pistol();
        Weapon w2= new Rifle();

        System.out.println(w1.toString());
        System.out.println(w2.toString());

        System.out.println("------------------------");

        // Creating tools
        Tool t1= new FirstAidKit();
        //Tool t2=new Map(board);

        System.out.println(t1.toString());
        //System.out.println(t2.toString());

        System.out.println("------------------------");

        // Equipping survivor with weapon and tools
        s1.setWeapon(w2);
        //s1.setTools(t2);

        System.out.println(s1.toString());

        System.out.println("-----------1-------------");

        try {
            // Creating and initializing the game board
            board = new Board(Integer.parseInt(args[0]), Integer.parseInt(args[1]), s, z);
            board.placeTool(0, 0, t1);
            board.placeWeapon(0, 0, w1);

            System.out.println(board.getCells()[2][2].toString());
            
        } catch (ArrayIndexOutOfBoundsException e) {
            // If command-line arguments are not provided, a default 5x5 game board is created
            board = new Board(5, 5, s, z);
            board.placeTool(0, 0, t1);
            board.placeWeapon(0, 0, w1);
            System.out.println(board.getCells()[2][2].toString());

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.println(board.getCells()[i][j].toString());
                }
            }

        }
        board.afficherCarte();
    }
}

