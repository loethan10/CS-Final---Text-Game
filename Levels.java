//Ethan Lo, Final Project, 5/26/21
package FinalProject;
import java.util.Locale;
import java.util.Scanner;

public class Levels {
	
	public Levels () {
		
	}
	
	public static Warrior anotherRound(Warrior player1, Warrior player2) { 
		boolean player1Alive = (player1.getHealth() > 0);
		boolean player2Alive = (player2.getHealth() > 0);
		
		while (player1Alive && player2Alive) { //this runs every round until one of the two players are defeated

			player1.roundAbilityActivate();
			
			if (player1Alive && (player1.getStun() != true)) { //checks if the player is stunned
				Warrior.turn(player1, player2);
			} else if (player1.getStun() == true) { //if the player is stunned, then they skip their turn, then are un-stunned
				player1.stunStatChange();
			}
			
			player1Alive = (player1.getHealth() > 0);
			player2Alive = (player2.getHealth() > 0);
			
			if (player2Alive) {
				player2.roundAbilityActivate();
			}
			
			player2Alive = (player2.getHealth() > 0);
			
			if (player2Alive && (player2.getStun() != true)) {
				Warrior.turn(player2, player1);
			} else if (player2.getStun() == true) {
				player2.stunStatChange();
			}
		}
		
		if (player1Alive) { //If player 1 is last player standing
			player1.abilityNumReset();
			return player1;
		} else { //If player 2 is last player standing
			player2.abilityNumReset();
			return player2;
		}
	}
	
	//ATTACK METHOD
	public static void attackPerson(Warrior attacker, Warrior defender) {
			System.out.println("\n" + attacker.getName() + " attacks "+ defender.getName() + ", who currently has " 
		+ defender.getHealth() + " health!");
			
			attacker.attacks(defender);
			System.out.println(defender.getName() + " has " + defender.getHealth() + " health left.");
			
			if (defender.getHealth() == 0) {
				System.out.println("******"+ defender.getName() + " IS DEFEATED!******");
			} else {
				//Every time a player attacks and the game hasn't ended, they have a chance of finding apples or amulets
				//magicItems(attacker);
			}
	}
}



package FinalProject;
import java.util.Locale;
import java.util.Scanner;

public class LvlOne extends Levels {
	
	public LvlOne () {
		super();
	}
	
	/*NOTE: most of the level methods are the same, so I'll just explain once, or I'll sound like a broken record
	  The method returns true if the player wins, false if the player loses. In the main method, that will determine whether or not to run the next level*/
	public static boolean one(Warrior player) {
		Scanner input = new Scanner(System.in); //Scanner, duh
		int choice1;
		
		System.out.println("\nNow that you've chosen your wizard type, it's time to figure out where in the world you are. What? Who am I? Don't worry about that--");
		System.out.println("\t\"Hello!!!... Yes, you, I'm talking to you!\" a Fire Elf shouts at you from a distance.");
		System.out.print("Rude, that guy is interrupting me... Anyways, you can choose to respond with three different responses:\n"
				+ "1) \"Who, me?\"\n"
				+ "2) \"You interrupted the nice, handsome guy I was talking to.\"\n"
				+ "3) *Silence*\n"
				+ "Type a number 1-3 of the dialogue option you'd like to respond with. Throughout the game, choosing different options will affect your gameplay, so be wise! ");
		choice1 = input.nextInt(); //stores player's choice
		
		if (choice1 == 1){ //depending on the player's choice, the dialogue changes
			System.out.println("\tThe Fire Elf rolls his eyes at you. \"Not much up there, huh?\" he asks.");
		} else if (choice1 == 2) {
			System.out.println("\tThe Fire Elf looks around in confusion. \"What guy?\" he asks.");
		} else if (choice1 == 3) {
			System.out.println("\tThe Fire Elf yells again, \"Excuse me, how rude! Don't just ignore me!\"");
		} else {
			System.out.println("Oh? Trying to be all smart with me, eh? Well, not typing a number between 1-3 will be treated as responding in silence.");
		}
		
		System.out.println("\t\"You\'re not from around here, are you?\" the Fire Elf sighs. \"I shall introduce myself, then; my name is Frosty Dwarf!... What's that?\n"
				+ "\tYou find my name weird in contrast to my species? Look at you! You're named something weird, and you're a human.\" Frosty Dwarf says in disgust.");
		System.out.print("Suddenly, thunder rumbles up ahead. Hmm... could've sworn it wasn't storming earlier.\n"
				+ "1) \"Where did the storm come from?\"\n"
				+ "2) \"Your name is really weird.\"\n"
				+ "3) *Silence*\n"
				+ "Type a number 1-3 of the dialogue option you'd like to respond with. ");
		choice1 = input.nextInt();
		
		if (choice1 == 1){
			System.out.println("\tFrosty Dwarf looks up at the sky. \"The weather likes to change eratically around here.\"");
		} else if (choice1 == 2) {
			System.out.println("\tFrosty Dwarf frowns. \"You really aren't very nice, are you?\"");
		} else {
			System.out.println("Nothing? Random storm appears and there's nothing you'd like to say? Okay....");
		}
		
		System.out.println("\tFrosty Dwarf turns around and points to a building. \"Ever since Lord Far Quad took over the robot factory, our town has\n"
				+ "\tbeen ravaged and attacked. No one in the village knows why Lord Far Quad wanted to attack, but, alas, he did. After taking over the robot factory,\n"
				+ "\the made horrible evil robots that took over the rest of the village. It's kind of obvious what happened from there.\"");
		System.out.print("I'm no expert in stories, but isn't this the part where you offer to help... (especially since you're a wizard)?\n"
				+ "1) \"I can help! I've got " + player.getWizardType() + " magic!\"\n"
				+ "2) \"Take me to the factory. I'll see what I can do...\"\n"
				+ "3) *Silence*\n"
				+ "Type a number 1-3 of the dialogue option you'd like to respond with. ");
		choice1 = input.nextInt();
		
		if (choice1 == 1){
			System.out.println("\tFrost Dwarf's eyes light up. \"You can!? Oh wow! Thank you so very much!\" He starts walking off. Perhaps you should follow.");
		} else if (choice1 == 2) {
			System.out.println("\tFrosty Dwarf cheers and says, \"HOORAY! Follow me!\"");
		} else {
			System.out.println("\t\"If it's not too much trouble... can you help us?\" Frosty Dwarf asks. He turns and starts walking to the factory.");
		}
		
		System.out.println("Inside the robot factory, you spot a short figure seated inside an office overlooking the entire factory. That's probably Lord Far Quad.");
		System.out.println("\t\"I AM LORD FAR QUAD! Who are you measly intruders!? Robot guard, break them for me!\" Lord Far Quad roars from up above.");
		System.out.println("Uh oh. Time for your first fight. Don't worry, I'll help you!\n");
		
		Warrior creature = new Warrior("Battle-Breaker Bot Guard"); //creates an opponent using warrior class
		player.printStats(); //prints player's stats
		System.out.println("\tvs.");
		creature.printStats(); //prints enemy's stats
		
		System.out.println("\nBattles are turned-based, which means you and the opponent take turns choosing what to do. When it's your turn, you have\n"
				+ "the ability to either blade, shield, or attack. Blading increases your next attack's damage by 40%, while shielding decreases the next\n"
				+ "attack you take by 50%! However, you can only have one of each at a time, so you won't be able to use more if you've already got a blade\n"
				+ "or shield unused.");
		System.out.println("\t\"Not to be that guy, but are you okay, man? Who are you talking to?\" Frosty Dwarf asks, inquisitively.");
		System.out.println("Ignore him. Now, since you're going first, go ahead and type either (A), (B), or (C) to choose what to do.");
		System.out.println("\n" + (anotherRound(player, creature)).getName() + " wins the battle!"); //this basically makes them start fighting
		
		if (player.getHealth() <= 0) { //if the player has 0 health, they lose, and it's game over
			return false;
		}
		
		System.out.println("\t\"Wow! Great job, " + player.getName() + "! Those Battle-Breaker Bot Guards (we call them breaker-bots for short) have been running amuck for\n"
				+ "\tfar too long. It's about time someone's done something about them!\" Frosty Dwarf exclaims happily.");
		System.out.println("Yes, I agree with this weird Ice-Fire Elf-Dwarf creature thing.... What?... What do you mean \'You said you\'d help me?\' I did! I told you\n"
				+ "how to use your letter keys to fight... Yea... Yea... Moving on! Whenever a battle ends, you get loot, which you can use to make your stats better.\n"
				+ "You can only choose one of the items, though.... Why?... Because! Hahaha!");
		 
		Warrior.generateLoot(player, creature); //if the player won, the code continues, and loot is generated
		
		System.out.println("\n\t\"You have great fashion choice! It looks good on you,\" Frosty Dwarf says. \"But, not to be that elf, shouldn\'t we go find Lord Far Quad now?\n"
				+ "\tI heard he\'s making a run for it, especially since you\'ve blasted right through his guards! Oh joyous day! FREEDOM IS NEAR!\"");
		System.out.println("The weird elf-dwarf thing is right. Go after Lord Far Quad. He\'s not very strong without his guards... you've got the hang of this now, right?\n"
				+ "Greeeeaaatt!... I'll... uh... I have some narrator stuff to do! Be right backkk.");
		
		return true;
	}
	
}


package FinalProject;

import java.util.Scanner;

public class LvlTwo extends Levels {
	
	public LvlTwo () {
		super();
	}
	
	public static boolean two(Warrior player) {
		Scanner input = new Scanner(System.in);
		int choice1;
		
		player.unlockIt();
		
		System.out.println("\t\"Follow me, young wizard,\" Frosty Dwarf says as he motions you through the back doors of the factory. \"Right there!\" he exclaims, pointing at\n"
			+ "\t a fleeing Lord Far Quad from afar. \"Go get him, savior wizard!\"");
		System.out.println("\t\"What!? How did you catch up so quickly!? Nevermind that, I'm taking you down!\" Lord Far Quad rages.\n");
		
		if (player.getHealth() <= 70) {
			System.out.println("\t\"Hold on! Your health looks dangerously low. Let me heal you up with 70 health real quick!\" Frosty Dwarf says.\n");
			player.setHealth(70);
		}
		
		Warrior creature = new Warrior("Lord Far Quad");
		creature.setMaxMobHealth(125);
		creature.incStrength(1);
		
		player.printStats();
		System.out.println("\tvs.");
		creature.printStats();
		
		System.out.println("\n" + (anotherRound(player, creature)).getName() + " wins the battle!");
		
		if (player.getHealth() <= 0) {
			return false;
		} 
		
		Warrior.generateLoot(player, creature);
		
		System.out.println("\n\t\"What!? Impossible! You haven\'t seen the last of me! My master Tay Tay will surely teach you a lesson!\" Lord Far Quad cries as he scuttles away in defeat.");
		System.out.println("\tFrosty Dwarf jumps up and down in excitement. \"HOORAY! Our savior! You've finally liberated us from that tyrant! Come to the village, we must repay your"
				+ "\n\tkindness!\" Frosty Dwarf runs off into the distance.");
		System.out.println("Perhaps you should follow him... Huh? Yes, I\'m back! I was... uh... well, busy. But great job on defeating Lord Far Quad! Oh yes, he said something about \'Tay Tay?\'"
				+ "\nHmm... I'm not an expert in lore, but that's probably a bad person and a big problem in the future. But ignore that for right now, since we should go follow that weird dwarf thing!");
		
		return true;
	}
}



package FinalProject;

import java.util.Scanner;

public class LvlThree extends Levels{
	
	public LvlThree () {
		super();
	}
	//NOTE: Unlike the other level methods, this returns a String, which is either 'lost' or the name of an NPC
	public static String three(Warrior player) {
		Scanner input = new Scanner(System.in);
		int choice1;
		boolean nice;
		
		System.out.println("You arrive at a nice, pretty village. The citizens- dwarves, elves, trolls, and fairies- are all in epic celebration. From afar, Frosty Dwarf\n"
				+ "is engaged in conversation with another local. He probably went around telling everyone that you saved them because as soon as someone spots you, they shout out your"
				+ "\nname.");
		System.out.println("\t\"OH MY GOSH! Is that " + player.getName() + "!?\" a fairy screeches out in excitement. The village erupts in a frenzy, as everyone swarms you.");
		System.out.println("\t\"Our savior! Please! Accept our gifts!\" citizens all around you shout.");

		Warrior.generateLoot(player);
		
		System.out.println("\nWhat a nice town. Oh, look. Someone\'s walking towards you. They don\'t look happy. Unfortunately, they\'re probably here to tell you some bad news.");
		System.out.print("\t\"You\'re the guy everyone\'s parading around for?\" the serious trolls huffs. \"Hmph. You don\'t look much like a savior to me. I do not have a say in this,\n"
				+ "\tthough. Our town mayor is looking for you. He says it is urgent.\"\n"
		+ "1) \"You\'re not very nice... And what\'s in it for me?\"\n"
		+ "2) \"I AM a savior. I\'ll prove it to you! Take me to your mayor!\"\n"
		+ "3) *Silence*\n"
		+ "Type a number 1-3 of the dialogue option you'd like to respond with. ");
		choice1 = input.nextInt();

		if (choice1 == 1){
			System.out.println("\tThe troll huffs again. \"Kindness doesn\'t really get you anywhere,\" he says before walking off. You run up to catch up to him.");
			nice = false;
		} else if (choice1 == 2) {
			System.out.println("\tThe troll shrugs. \"You\'ve got a good attitude, though, that is admirable,\" he says. He motions you to follow him.");
			nice = true;
		} else {
			System.out.println("\tThe troll turns around and starts walking. Go follow him!");
			nice = false;
		}
		
		System.out.println("\t\"The name\'s Lumber Troll. I know, my parents gave me SUCH a unique name,\" Lumber Troll sighs.");
		System.out.println("You and Lumber Troll enter the Mayor\'s Place. It\'s empty, and a direct contrast to the party going on outside. Where in the world is the mayor? Something\n"
				+ "about this doesn\'t seem right... Wait! Look! There\'s something up ahead!");
		System.out.println("\t\"Mayor, sir?\" Lumber Troll asks as he approaches the suspicious-looking entity.");
		System.out.println("The entity suddenly turns around, bares its ugly fangs, and hisses. I knew it looked suspicious. Wait, is that the mayor!? Yikes.");
		System.out.println("\t\"Sir!?\" Lumber Troll says in shock as he stumbles backwards.");
		System.out.println("\t\"Foolish creatures!\" the ex-mayor bellows. \"The old mayor is gone! Tay Tay has blessed me with magical Death powers! I am now... JOHN MAYOR!\"");
		System.out.println("Hello. Narrator here. Just wanted to tell you good luck because you might need it. Unlike the other two fights you fought, this guy has a school of magic,\n"
				+ "like you! Which means he's got special abilities and stuff like that. How fun! Since he's a Death Wizard, you better watch out for his heals! (Also, remember how I told you\n"
				+ "that your choices affect the story? Yea... I hope you were nice to Lumber Troll, that'd make this easier...)\n");
		
		if (player.getHealth() <= 90) {
			System.out.println("Also, sorry for bothering you so much, but your health is kind of low! I'm gonna patch you up with 90 more health real quick! Say, \'Thank you, narrator!\'\n");
			player.setHealth(90);
		}
		
		Warrior creature = new Necromancer("John Mayor");
		creature.setMaxMobHealth(165);
		creature.setStrength(2);
		creature.setIsMob();
		
		player.printStats();
		System.out.println("\tvs.");
		creature.printStats();
		
		if (nice) {
			creature.setHealth(-25);
			System.out.println("\n\t\"" + player.getName() + " you are a valiant hero! I will fight alongside you! KIAAAA!\" Lumber Troll yells as he charges at John Mayor.");
			System.out.println("\t\"Gah!\" John Mayor exclaims as he is thwacked by Lumber Troll. \"Stay away, foolish troll!\" Lumber Troll is thrashed aside, and is knocked out,\n"
					+ "\t but not before dealing 25 damage! John Mayor now only has " + creature.getHealth() + " health left!");
		} else {
			System.out.println("\n\t\"" + player.getName() + " prove your worth.\" Lumber Troll says as he sulks into the shadows, forcing you to deal with the threat alone.");
			System.out.println("Yea, narrator here, again. Maybe if you were nicer to him he would\'ve helped... ah well! To the battle we go!");
		}
		
		System.out.println("\n" + (anotherRound(player, creature)).getName() + " wins the battle!");
		
		if (player.getHealth() <= 0) {
			return "lost";
		} 
		
		System.out.println("\nRight after you cast your final spell, the ex-mayor stumbles backwards and collapses on the floor.");

		Warrior.generateLoot(player, creature);
		
		if (nice) {
			System.out.println("\n\tLumber Troll stirs awake and says, \"You did it, " + player.getName() + "!\" He hobbles over to the ex-mayor.");
		} else {
			System.out.println("\n\tLumber Troll pats you on the back and walks to the ex-mayor. \"You have proved your worth and gained my respect. Well done, " + player.getName() + "!\"");
		}
		
		System.out.print("\t\"He\'s still has evil magic within him! What do we do?\" Lumber Troll asks you.\n"
		+ "1) \"I can try and extract the magic out of him, but it\'ll be dangerous and may hurt you as collateral damage...\"\n"
		+ "2) \"There\'s nothing we can do, I'm sorry, but he\'s gone...\"\n"
		+ "3) *Silence*\n"
		+ "Type a number 1-3 of the dialogue option you'd like to respond with. Warning, your choice here will affect the story (duh)... ");
		choice1 = input.nextInt();

		if (choice1 == 1){
			System.out.println("\t\"Just do it! It will be for the good of our proud village,\" Lumber Troll says valiantly.");
			System.out.println("You tell him to step aside, and after he does, you begin a strange ritual. Indeed, it is very strange, since the words just seem to come to you, despite "
					+ "\nnever having done this before. Just then, a blast of dark energy shoots through the room, and you black out.");
			return "Mayor Jack";
		} else {
			System.out.println("\t\"I have failed you, Mayor,\" Lumber Troll mourns.");
			System.out.println("Suddenly, the ex-mayor\'s body starts to convulse and emit a strong heat. You shout for Lumber Troll to get back, and pull him away. You both run out \n"
					+ "of the building just as the building explodes. Dark magic is the only explanation for what just happened. Then, you pass out.");
			return "Lumber Troll";
		} 

	}
}



package FinalProject;

import java.util.Scanner;

public class LvlFour extends Levels{
	
	public LvlFour () {
		super();
	}
	//Note: this level method takes in a player name AND the name of whichever NPC survived the last level
	public static boolean four(Warrior player, String companion) {
		Scanner input = new Scanner(System.in);
		int choice1;
		
		if (companion.equals("Lumber Troll")) {
			System.out.println("\nWhen you wake up you look to your left and see that " + companion + " had survived the explosion... the mayor didn\'t make it...");
			System.out.println("\t\"Nice to see you awake,\" Lumber Troll says.");
		} else {
			System.out.println("When you wake up you look to your left and see that " + companion + " had survived the explosion... Lumber Troll didn\'t make it...");
			System.out.println("\t\"Nice to see you awake,\" the Mayor says, now fully healed. \"You are a hero to this village. Also, please, call me Mayor Jack.\"");
		}
		
		System.out.println("You are displeased by the events that had unfolded. You failed to save someone! That has never happened before... Just then, the sky darkens again.\n"
				+ "Hmm... I honestly could have sworn you'd already beaten everything... Oh no... it\'s General Drone!");
		
		System.out.println("\t\"My master Tay Tay sent me to end you, once and for all!\" General Drone screeched.");
		
		if (companion.equals("Lumber Troll")) {
			System.out.println("\t\"Quick, " + player.getName() + "!\" says Lumber Troll. \"If you defeat that thing, maybe we\'ll finally be able to find out where Tay Tay\'s lair is!\"");
			player.strength++; 
			System.out.println("\t\"Also, here\'s some strength to help you out!\" Lumber Troll says.\n");
		} else {
			System.out.println("\t\"Quick, " + player.getName() + "!\" says Mayor Jack. \"If you defeat that thing, maybe we\'ll finally be able to find out where Tay Tay\'s lair is!\"");
			player.pierce += 10; 
			System.out.println("\t\"Also, here\'s some pierce to help you out!\" Mayor Jack says.\n");
		}
		
		if (player.getHealth() <= 75) {
			System.out.println("Your health looks gnarly. Let me heal you up a bit. Again, say, \'Thank you, narrator!\' It makes me happy!\n");
			player.setHealth(150);
		}
		
		Warrior creature = new Thaumaturge("General Drone");
		creature.setMaxMobHealth(200);
		creature.setStrength(2);
		creature.setIsMob();
		
		player.printStats();
		System.out.println("\tvs.");
		creature.printStats();
		
		System.out.println("\n" + (anotherRound(player, creature)).getName() + " wins the battle!");
		
		if (player.getHealth() <= 0) {
			return false;
		} 
		
		Warrior.generateLoot(player, creature);
		
		System.out.println("\n\t\"Impossible! How was I defeated!?\" screeched General Drone.");
		if (companion.equals("Lumber Troll")) {
			System.out.println("\t\"Now, you will tell us where Tay Tay lies!\" Lumber Troll demands.");
		} else {
			System.out.println("\t\"Now, you will tell us where Tay Tay lies!\" Mayor Jack demands.");
		}
		
		System.out.println("\t\"No! Never-- okay, fine. She doesn\'t pay me enough, anyways. Up the mountains! Now, leave me beeeee!\" General Drone screamed as she flew off.");
		
		return true;
		
	}

}



package FinalProject;

import java.util.Scanner;

public class LvlFive extends Levels{
	
	public LvlFive () {
		super();
	}
	
	public static boolean five(Warrior player) {
		Scanner input = new Scanner(System.in);
		int choice1;
		
		System.out.println("...");
		
		if (player.getHealth() <= 70) {
			System.out.println("...");
			player.setHealth(70);
		}
		
		Warrior creature = new Warrior("...");
		creature.setMaxMobHealth(125);
		creature.incStrength(1);
		
		player.printStats();
		System.out.println("\tvs.");
		creature.printStats();
		
		System.out.println("\n" + (anotherRound(player, creature)).getName() + " wins the battle!");
		
		if (player.getHealth() <= 0) {
			return false;
		} 
		
		Warrior.generateLoot(player, creature);

		return true;
	}
}



package FinalProject;

import java.util.Scanner;

public class LvlSix extends Levels{
	
	public LvlSix () {
		super();
	}
	
	public static boolean six(Warrior player) {
		Scanner input = new Scanner(System.in);
		int choice1;
		
		System.out.println("...");
		
		if (player.getHealth() <= 70) {
			System.out.println("...");
			player.setHealth(70);
		}
		
		Warrior creature = new Warrior("...");
		creature.setMaxMobHealth(125);
		creature.incStrength(1);
		
		player.printStats();
		System.out.println("\tvs.");
		creature.printStats();
		
		System.out.println("\n" + (anotherRound(player, creature)).getName() + " wins the battle!");
		
		if (player.getHealth() <= 0) {
			return false;
		} 
		
		Warrior.generateLoot(player, creature);

		return true;
	}
}


