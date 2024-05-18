package zombicide.game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.listchooser.util.Input;
import zombicide.tool.FirstAidKit;
import zombicide.tool.HealingVial;
import zombicide.tool.InfraredGoggles;
import zombicide.tool.Map;
import zombicide.tool.MasterKey;
import zombicide.tool.Shield;
import zombicide.weapon.Axe;
import zombicide.weapon.Chainsaw;
import zombicide.weapon.Crowbar;
import zombicide.weapon.Pistol;
import zombicide.weapon.Rifle;
import zombicide.weapon.Weapon;
import zombicide.action.Action;
import zombicide.actor.survivor.FighterRole;
import zombicide.actor.survivor.HealerRole;
import zombicide.actor.survivor.LuckyRole;
import zombicide.actor.survivor.ScavengerRole;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Brute;
import zombicide.actor.zombie.Malenia;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Walker;
import zombicide.actor.zombie.Zombie;
import zombicide.grid.Board;
import zombicide.listchooser.InteractiveListChooser;


/**
 * Class representing the game logic and flow of Zombicide.
*/
public class Game {

	/**
     * Starts a game of Zombicide with the specified grid size.
     *
     * @param nbLigne   the number of rows in the grid
     * @param nbColonne the number of columns in the grid
     */
	public void startGame(){

		System.out.println("\n");
		System.out.println("You will face zombies,\nEradicate them all or reach level 30 to escape ! \n");

		List<String> mode=new ArrayList<>();
		mode.add("Easy");
		mode.add("Normal");
		mode.add("Hard");
		mode.add("Level DarkSouls");

		InteractiveListChooser<String> ilcc = new InteractiveListChooser<String>();
		String chosenMode = ilcc.choose("Which difficult you choose ?",mode);
		if ( chosenMode== null) {
			chosenMode="Normal";
		}

		int toolDropRate;
		int weaponDropRate;

		if (chosenMode=="Easy"){
			toolDropRate = 2;
			weaponDropRate = 2;
		}
		else if (chosenMode=="Normal"){
			toolDropRate = 2;
			weaponDropRate = 3;
		}
		else if (chosenMode=="Hard"){
			toolDropRate = 3;
			weaponDropRate = 4;
		}
		else {
			toolDropRate = 4;
			weaponDropRate = 5;
		}

		
		System.out.println("Choose the size of the map\n ");

		int ligne = 5;
        int colonne = 5;

        try {

            System.out.print("Number of lines ( minimum 5 ) :  ");
            ligne = Input.readInt();
			if(ligne<5) ligne = 5;

            System.out.print("Number of columns ( minimum 5 ) :  ");
            colonne = Input.readInt();
			if(colonne<5) colonne = 5;

        } catch (java.io.IOException e) {
            System.err.println("Error.");
        }

		


		Random random = new Random();
		
		List<Survivor> s = new ArrayList<>();
        List<Zombie> z =new ArrayList<>();
		
		

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

		Weapon w1=new Pistol();
		Weapon w2=new Pistol();
		Weapon w3=new Pistol();
		Weapon w4=new Pistol();

		s1.setWeapon(w1);
		s2.setWeapon(w2);
		s3.setWeapon(w3);
		s4.setWeapon(w4);

		s1.setWeaponInHand(w1);
		s2.setWeaponInHand(w2);
		s3.setWeaponInHand(w3);
		s4.setWeaponInHand(w4);


        Zombie z1= new Abomination();
        Zombie z2= new Brute();
        Zombie z3= new Runner();
        Zombie z4= new Walker();
		Zombie malenia = new Malenia();

        z.add(z1);
        z.add(z2);
        z.add(z3);
        z.add(z4);

		
		
		
		Board board = new Board(ligne, colonne, s, z);

		for(int i = 0; i<board.getLigne();i++){
			for(int j = 0; j<board.getColonne();j++){
				 if(!(s1.getGrid().getCells()[i][j].isStreet())){
					int toolSpawner = random.nextInt(toolDropRate);
					int weaponSpawner = random.nextInt(weaponDropRate);

					if(toolSpawner == 0){
						int toolSelect = random.nextInt(6);
						if(toolSelect == 0){
							board.placeTool(i, j, new FirstAidKit());
						}else if(toolSelect == 1){
							board.placeTool(i, j, new HealingVial());
						}else if(toolSelect == 2){
							board.placeTool(i, j, new InfraredGoggles());
						}else if(toolSelect == 3){
							board.placeTool(i, j, new Map(board));
						}else if(toolSelect == 4){
							board.placeTool(i, j, new MasterKey());
						}else if(toolSelect == 5){
							board.placeTool(i, j, new Shield());
						}
					}

					if(weaponSpawner == 0){
						int weaponSelect = random.nextInt(5);
						if(weaponSelect == 0){
							board.placeWeapon(i, j, new Axe());
						}else if(weaponSelect == 1){
							board.placeWeapon(i, j, new Chainsaw());
						}else if(weaponSelect == 2){
							board.placeWeapon(i, j, new Crowbar());
						}else if(weaponSelect == 3){
							board.placeWeapon(i, j, new Rifle());
						}else if(weaponSelect == 4){
							board.placeWeapon(i, j, new Pistol());
						}
					}
				}
			}
		}


		board.afficherCarte();

		System.out.println("--------------------------");

		InteractiveListChooser<Action> ilc = new InteractiveListChooser<Action>();

		while(0<s1.getActionPoints()){
			s1.play(ilc);
		}

        while(0<s2.getActionPoints()){
			s2.play(ilc);
		}

        while(0<s3.getActionPoints()){
			s3.play(ilc);
		}

        while(0<s4.getActionPoints()){
			s4.play(ilc);
		}

		for(int i =0; i<board.getZombieInBoard().size();i++){
			if(board.getZombieInBoard().get(i).getCell() != null){
				board.getZombieInBoard().get(i).play();
			}
			
		}


		s1.resetActionPoints();
		s2.resetActionPoints();
		s3.resetActionPoints();
		s4.resetActionPoints();


        board.afficherCarteAll();

		System.out.println("--------------------------");

        System.out.println(s1.toString());
		System.out.println("--------------------------");
        System.out.println(s2.toString());
		System.out.println("--------------------------");
        System.out.println(s3.toString());
		System.out.println("--------------------------");
        System.out.println(s4.toString());
		System.out.println("--------------------------");
		
		board.resetNoiseInBoard();


		int maleniaSpawnerIndicator = 4;
		

		while(!board.endGame()){

			if(chosenMode== "Level DarkSoul"){
				if(maleniaSpawnerIndicator>0){
					System.out.println("Malenia will appear in " + maleniaSpawnerIndicator + " turns");
					maleniaSpawnerIndicator--;
				}else{
					if(maleniaSpawnerIndicator == 0){
						System.out.println("Malenia is here ! \u001B[31mBECAREFUL\u001B[0m !!!");
						board.addZombie(board.getCells()[board.getFirstStreetRow()][board.getFirstStreetCol()], malenia);
						maleniaSpawnerIndicator--;
					}
					if(malenia.isDead()){
						System.out.println("Malenia is dead, Well done !\n you are the king of Soulslike");
						break;
					}
				}
			}


			int totalLevel=0;
			if (s1.isAlive()){
				totalLevel+=s1.getLevel();
			}
			if (s2.isAlive()){
				totalLevel+=s2.getLevel();
			}
			if (s3.isAlive()){
				totalLevel+=s3.getLevel();
			}
			if (s4.isAlive()){
				totalLevel+=s4.getLevel();
			}

			if(totalLevel>29){
				System.out.println("Your team has reached level 30, you are saved this time.");
			}else{
				System.out.println("Team Level : " + totalLevel);
			}


			int nbZombie=0;
			if (chosenMode=="Easy"){
				nbZombie=totalLevel/4;
			}
			else if (chosenMode=="Normal"){
				nbZombie=totalLevel/3;
			}
			else if (chosenMode=="Hard"){
				nbZombie=totalLevel/2;
			}
			else {
				nbZombie=totalLevel*2 -totalLevel/2;
			}
			
			List<Zombie> newZombie = new ArrayList<>();

            for(int j=0;j<nbZombie;j++){

                int rdn=random.nextInt(4)+1;

                if (rdn==1 ){

                    Brute brute=new Brute();
					newZombie.add(brute);


                }else if(rdn==2){

                    Runner runner=new Runner();
                    newZombie.add(runner);

                }else if (rdn==3 ){

                    Abomination abomination=new Abomination();
                    newZombie.add(abomination);

                }else {

                    Walker walker=new Walker();
                    newZombie.add(walker);

                }
                                
            }
			
			board.initZombies(newZombie);
			board.placeNewZombie(0, 0, board.getLigne()-1, board.getColonne()-1, board.getFirstStreetRow(), board.getFirstStreetCol(), newZombie);

			if(s1.isAlive()){
				
				int i = 0;
				while(i<s1.getActionPoints()){
					s1.play(ilc);
				}
				s1.resetActionPoints();
			}


			if(s2.isAlive()){
				
				int i = 0;
				while(i<s2.getActionPoints()){
					s2.play(ilc);
				
				}
				s2.resetActionPoints();
			}


			if(s3.isAlive()){
				
				int i = 0;
				while(i<s3.getActionPoints()){
					s3.play(ilc);
				
				}
				s3.resetActionPoints();
			}

			if(s4.isAlive()){
				
				int i = 0;
				while(i<s4.getActionPoints()){
					s4.play(ilc);
			
				}
				s4.resetActionPoints();
			}


			for(int i =0; i<board.getZombieInBoard().size();i++){
				if(board.getZombieInBoard().get(i).getCell() != null){
					if(board.getZombieInBoard().get(i) instanceof Malenia){
						board.getZombieInBoard().get(i).play();
						board.getZombieInBoard().get(i).play();
					}
					board.getZombieInBoard().get(i).play();

				}
				
			}

			board.resetNoiseInBoard();
			board.afficherCarteAll();

			System.out.println("--------------------------");

			
			if(board.getSurvivorInBoard().size()>0){
				System.out.println("Survivors still alive : ");
				for(int i = 0; i<board.getSurvivorInBoard().size(); i++){
					System.out.println(board.getSurvivorInBoard().get(i).toString()); 
					System.out.println("--------------------------");
				}
			}else{
				System.out.println("Zombies won...");
			}

			if(board.getZombieInBoard().size() == 0){
				System.out.println("You have cleaned the city of zombies");
			}

			
			
		}
	}
}
