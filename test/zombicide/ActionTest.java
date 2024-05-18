import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import zombicide.actor.Actor;
import zombicide.grid.Board;
import zombicide.action.HealPotion;
import zombicide.action.MakeNoise;
import zombicide.action.UseMap;
import zombicide.action.UseShield;
import zombicide.actor.survivor.*;
import zombicide.tool.HealingVial;
import zombicide.tool.Map;
import zombicide.tool.Shield;
import zombicide.tool.Tool;
import zombicide.actor.zombie.*;


public class ActionTest {


    
    @Test
    public  void testHealPotionWithHealingVial() {
        Survivor survivor = new Survivor("Alice");
        Tool heal=new HealingVial();
        survivor.setTools(heal);
        survivor.setToolInHand(heal); 
        Zombie z1= new Runner();
        z1.attack(survivor);
        HealPotion healPotion = new HealPotion("Heal survivor");
        healPotion.doIt(survivor);
        assertEquals(5,survivor.getHealth());
        
    }

    @Test 
    public void testUseMap(){
        List<Zombie> listeZombies=new ArrayList<>();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        Tool map=new Map(board);
        survivor.setTools(map);
        survivor.setToolInHand(map); 
        UseMap useMap= new UseMap("Use Map");
        useMap.doIt(survivor);
        assertEquals(2,survivor.getActionPoints());
        assertEquals(board.getCells()[2][2],board.getMostNoisyCell());
    }

    @Test 
    public void testUseShield(){
        List<Zombie> listeZombies=new ArrayList<>();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        Tool shield=new Shield();
        survivor.setTools(shield);
        survivor.setToolInHand(shield);
        UseShield useShield=new UseShield("Use Shield");
        useShield.doIt(survivor);
        assertEquals(7,survivor.getHealth());
    }

    @Test 
    public void testMakeNoise(){
        List<Zombie> listeZombies=new ArrayList<>();
        Survivor survivor = new Survivor("Rick");
        List<Survivor> listesSurvivors=new ArrayList<>();
        listesSurvivors.add(survivor);
        Board board = new Board(5, 5,listesSurvivors, listeZombies);
        MakeNoise makeNoise=new MakeNoise("make Noise");
        makeNoise.doIt(survivor);
        assertEquals(board.getCells()[2][2],board.getMostNoisyCell());

    }
}
