//Ethan Lo, Final Project, 5/26/21
package FinalProject;

import java.util.Locale;
import java.lang.Math;

import java.util.Scanner;

public class Client {
	public static void main (String[] args) {
		
		String name; //Holds the player's name 
		int wizardTypeNum = 0; //Determines what wizard type the player will be
		Warrior player1 = null; //Creates a generic warrior object named player that we will assign a type to later
		Scanner input = new Scanner(System.in); //Scanner (duh)
		
		System.out.print("Hello! Welcome to this wizarding RPG-like game, titled \'Wizarding RPG-like Game!\' Weird name, right? (You\'re totally not dreaming or anything...)"
				+ "\nTo start, what is your name? ");
		name = input.nextLine(); //This stores the player's name into the 'name' variable
		
		//The statements below just explain the different wizards' playstyles
		System.out.println("Now, what type of wizard would you like to be, " + name + "? Each type of wizard has their own unique playstyle!\n");
		
		System.out.println("1) Ice Wizards specialize in outlasting their opponents, and do not generally do not focus on attacking.\n "
				+ "They have some of the lowest damage output, but have the most health and resist, allowing them to negate some damage that they take.\n");
		
		System.out.println("2) Fire Wizards specialize in calculated attacks. They have some of the highest strength values,  and specialize in piercing through\n"
				+ " enemies' resist. They can even critical, dealing x2 damage! Fire Wizards have an extra damage boost against Ice Wizards.\n");
		
		System.out.println("3) Storm Wizards specialize in pure damage; they have the highest damage out of all Wizards, and are the only class with an extra damage-increasing stat-\n"
				+ " \'damage\'- which adds a certain percentage to their attacks. However, their attacks may not go through and instead fizzle.\n");
		
		System.out.println("4) Life Wizards specialize in healing; while Life Wizards do not have very high damage, they have the most health out of all wizard \n"
				+ "types. Life Wizards do extra damage to Death Wizards, and are immune to \'drain\' attacks.\n");
		
		System.out.println("5) Death Wizards specialize in \'drain\' attacks and have relatively high damage outputs (but low max health). Their attacks focus on dealing damage, \n"
				+ "then healing back half of the damage dealt to themselves. However, when facing Life Wizards, Death Wizards do not have the ability to heal themselves.\n");
		
		System.out.println("6) Myth Wizards specialize in manipulation. They can stun their opponents when attacking, and are immune to stuns themselves. \n"
				+ "Myth Wizards have moderate resist, health, and strength, but can eventually remove their opponents' support (blades and shields).\n");
		
		System.out.println("7) Balance Wizards, unlike all the other wizards, DO NOT specialize in any one thing! They have moderate stats: relatively high health, \n"
				+ "some resist, moderate strength, and a little bit of pierce.\n");
		
		System.out.println("8) \"SURPRISE ME!\" (Choose this option and we will choose a wizard-type for you!)\n");
		
		System.out.print("What type of wizard would you like to be? Enter the number that corresponds with the wizard above: ");
		wizardTypeNum = input.nextInt();
		
		if ((wizardTypeNum >= 8) || (wizardTypeNum <= 0)) { //If the player chooses a number that IS NOT 1-7, it chooses a random type for them
			wizardTypeNum = (int) (Math.random() * 6) + 1;
		} 
		
		player1 = determineWiz(wizardTypeNum, name); //Finally, we set the warrior object called player to a subclass type by calling a method
		
		String companion = "lost"; //We can ignore this for now...
		
		//The following variables essentially determine whether or not the game continues by checking if they turn true (they turn true if the player wins a round)
		boolean contTwo = false; 
		boolean contThree = false;
		boolean contFour = false;
		boolean contFive = false;
		boolean contSix = false; 
		
		player1.printStats(); 
		
		if(LvlOne.one(player1) == false) { //first level
			System.out.println("\n\n~ ~ ~ ~ ~ GAME OVER ~ ~ ~ ~ ~"); //If the player loses (which we will know if the level method returns false), this statement is printed
		} else {
			contTwo = true; //We make contTwo true, which means the next if statement (aka the next level) will run, since the player won
		}
		
		if (contTwo) {
			if (LvlTwo.two(player1) == false) { //second level
				System.out.println("\n\n~ ~ ~ ~ ~ GAME OVER ~ ~ ~ ~ ~");
			} else {
				contThree = true;
			}
		}
		
		if (contThree) {
			companion = LvlThree.three(player1); 
			/*Basically, unlike the other level methods, instead of returning a boolean, the third level returns a String; that string is either 'lost' or the name of 
			a companion. The companion name returned depends on the player's choices. We want to carry that name over to the next method, so we have to store it*/
			if(companion.equals("lost")) { //third level
				System.out.println("\n\n~ ~ ~ ~ ~ GAME OVER ~ ~ ~ ~ ~"); //If no companion name is returned, but rather 'lost' is returned, this statement is printed
			} else {
				contFour = true;
			}
		}
		
		if (contFour) {
			if (LvlFour.four(player1, companion) == false) { //fourth level
				System.out.println("\n\n~ ~ ~ ~ ~ GAME OVER ~ ~ ~ ~ ~");
			} else {
				contFive = true;
			}
		}
		
		/*
		if (contFive) {
			if (LvlFive.five(player1) == false) { //fifth level
				System.out.println("\n\n~ ~ ~ ~ ~ GAME OVER ~ ~ ~ ~ ~");
			} else {
				contSix = true;
			}
		}

		if (contSix) {
			if (LvlSix.six(player1) == false) { //sixth level
				System.out.println("\n\n~ ~ ~ ~ ~ GAME OVER ~ ~ ~ ~ ~");
			} 
		} 
		*/
		
	}
	
	public static Warrior determineWiz(int wizardTypeNum, String name) { //This is the method we called earlier, which assigned a subclass to the warrior object
		Warrior player1;
		
		if (wizardTypeNum == 1) {
			
			System.out.println("\nYou are an Ice Wizard! They specialize in outlasting their opponents, akin to immovable sheets of Arctic Ice and the\n"
					+ "powerful forces of katabatic winds which do not stray from their path. Ice Wizards generally do not focus on\n"
					+ "attack-oriented strategies, as they have some of the lowest damage outputs compared to their other wizard counterparts.\n"
					+ "However, they have the most resist, which allows them to negate some damage that they take, and very high health,\n"
					+ "allowing them to tank huge amounts of damage.\n");
			
			 return player1 = new Thaumaturge(name); //Here, we return an object initialized as a Thaumaturge, which is a subclass of warrior 
		} else if (wizardTypeNum == 2) {
			
			System.out.println("\nYou are a Fire Wizard! They specialize in strong, calculated attacks, which, with enough precision, can take down even the\n"
					+ "most formidable foes; their heat can melt down seemingly-impenetrable ice fortresses. Fire wizards, unlike Ice Wizards,\n"
					+ "focus on attacking, but are resilient enough to stay alive. They have some of the highest strength values, and specialize\n"
					+ "in piercing through enemies' resist, and if the enemy is unprotected with no resist at all, they can make a calculated \n"
					+ "critical strike dealing double damage! Fire Wizards have an extra damage boost against Ice Wizards, as they can easily melt them down.\n");
			
			 return player1 = new Pyromancer(name);
		} else if (wizardTypeNum == 3) {
			
			System.out.println("\nYou are a Storm Wizard! They specialize in chaotic, violent winds; their unpredictable and volatile nature can prove to be insane\n"
					+ "in terms of damage. With thundering bolts that can jolt anyone to their knees and gusting hurricanes that can blow \n"
					+ "just about anything away, Storm Wizards have the highest damage out of all Wizards. They are even the sole class with an extra\n"
					+ "damage-increasing stat- \'damage\'- which adds a certain percentage to their attacks. However, as a glass-cannon, they are \n"
					+ "more fragile than other classes, and their aforementioned unpredictability means their attacks may not go through and instead fizzle.\n");
			
			return player1 = new Diviner(name);
		} else if (wizardTypeNum == 4) {
			
			System.out.println("\nYou are a Life Wizard! They specialize in the gift of, well, life. With neigh-omnipotent Mother Nature on their side, Life Wizards\n"
					+ "can call upon powerful forces to help keep them alive long after the opponent has been defeated. While Life Wizards do not have very\n"
					+ "high damage, due to their insistence on all lives being precious, Life Wizards have the most direct access to healing, and are the most powerful\n"
					+ "at it. They have the most health out of all wizard types. Constant healing can easily allow them to outlast any opponent. Because of Mother Nature's \n"
					+ "extreme hatred for Thanatos, the lord of death, all Life Wizards were blessed to do extra damage to Death Wizards; they are also immune to drain attacks.\n");
			
			return player1 = new Theurgist(name);
		} else if (wizardTypeNum == 5) {
			
			System.out.println("\nYou are a Death Wizard! They specialize in manipulating the all-powerful gift of life. They can control forces that are otherwise inaccessible for \n"
					+ "mere common mortals. Death Wizards seek to hinder life, so they have moderately high damage outputs, but relatively low max hitpoints. \n"
					+ "With their ability to manipulate people's souls and wills, Death Wizards can steal health from their opponents. Their attacks\n"
					+ "focus on dealing damage, then healing back half of the damage dealt to themselves. Ironically, Death Wizards have some of the easiest ways of staying\n"
					+ "alive. (However, when facing Life Wizards, Death Wizards do not have the ability to heal themselves back up.)\n");
			
			return player1 = new Necromancer(name);
		} else if (wizardTypeNum == 6) {
			
			System.out.println("\nYou are a Myth Wizard! They specialize in all things legendary; with the past and legendary tall-tales of heroes on their side, Myth Wizards\n "
					+ "can manipulate the fabric of time to a certain extent. Myth Wizards have a chance of stunning their opponents when attacking, as their greatness can\n"
					+ "either blind or leave their opponents gasping in awe; Myth Wizards themselves are immune to stuns. Myth Wizards have moderate resist, health, and strength,\n"
					+ "but can learn the ability to directly remove their opponents' support (blades and shields).\n");
			
			return player1 = new Conjurer(name);
		} else if (wizardTypeNum == 7) {
			
			System.out.println("\nYou are a Balance Wizard! Unlike all the other wizards, they DO NOT specialize in any one thing! As their school of magic suggests,\n"
					+ "they have the most balanced stats out of all wizards, and have a little bit of everything. While they are the most mediocre, they have\n"
					+ "the most knowledge of their opponents and are incredibly versatile. With the ability to switch up their strategies, Balance Wizards can\n"
					+ "adapt to their opponents. They have moderate stats, but have the most amount of stats available to them, in comparison to other wizards.\n"
					+ "Balance Wizards have relatively high health, some resist, moderate strength, a little bit of pierce, small amounts of healing, and have a \n"
					+ "tiny chance of stunning their opponent. They truly are Jacks-of-all-Trades!\n");
			
			return player1 = new Sorcerer(name);
		} else {
			return player1 = new Warrior(name, "Error"); 
		}
	}
	
}


