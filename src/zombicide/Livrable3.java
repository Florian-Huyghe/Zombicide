package zombicide;

import java.util.ArrayList;
import java.util.List;

import zombicide.action.Action;
import zombicide.actor.survivor.FighterRole;
import zombicide.actor.survivor.HealerRole;
import zombicide.actor.survivor.LuckyRole;
import zombicide.actor.survivor.ScavengerRole;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.listchooser.InteractiveListChooser;
import zombicide.tool.HealingVial;
import zombicide.tool.Tool;
import zombicide.weapon.Axe;
import zombicide.weapon.Weapon;

public class Livrable3 {
    public static void main(String[] args) {

        Board board;

        List<Survivor> s = new ArrayList<>();
        List<Zombie> z=new ArrayList<>();

        // Creating survivors
        Survivor s1=new Survivor("Flo");
        Survivor s2=new Survivor("Hichem");
        Survivor s3=new Survivor("Yass");
        Survivor s4=new Survivor("Mathys");


        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);

        FighterRole f1=new FighterRole();
        LuckyRole l1=new LuckyRole();
        HealerRole h1=new HealerRole();
        ScavengerRole sc1= new ScavengerRole();

        s1.addRole(f1);
        s2.addRole(l1);
        s3.addRole(h1);
        s4.addRole(sc1);

        // Creating zombies and add in to Liste of zombies
        Zombie z1= new Runner();
        Zombie z2= new Runner();
        Zombie z3= new Runner();
        Zombie z4= new Runner();

        z.add(z1);
        z.add(z2);
        z.add(z3);
        z.add(z4);




        // Creating weapons
        Weapon w1=new Axe();


        board = new Board(5, 5, s, z);

        System.out.println(board.getCells()[0][2].getDoors());
        System.out.println(board.getCells()[0][2].getSurvivorInCell().contains(s4));
        System.out.println(s4.getCell().getDoors());


        // Creating tools
        Tool t1= new HealingVial();  

     

        // Equipping survivor with weapon and tools
        s2.setWeapon(w1);
        s2.setWeaponInHand(w1);
        s3.setTools(t1);
        s3.setToolInHand(t1);
        s3.receiveDamage(1);

        board.afficherCarte();
        

        System.out.println("-----------1-------------");

        InteractiveListChooser<Action> ilc = new InteractiveListChooser<Action>();
        
        // Every survivors do an action
        s1.play(ilc);
        s2.play(ilc);
        s3.play(ilc);
        s4.play(ilc);

        z1.play();
        z2.play();
        z3.play();
        z4.play();

        board.afficherCarteAll();
        
        System.out.println(board.getCells()[0][2].getEastDoor() == board.getCells()[0][3].getWestDoor());

        System.out.println("--------------------------");

        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());
        System.out.println(s4.toString());
        

 
    }
    
}
