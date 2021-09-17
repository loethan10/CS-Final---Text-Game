//Ethan Lo, Final Project, 5/26/21
package FinalProject;

import java.util.Scanner;

public class Warrior { //Warrior is the parent class 
	protected int health; //health, duh
	protected int maxHealth; //maximum amount of health a player can have
	protected int strength; //strength, which is the multiplier to whatever number the dice roll
	protected String name; //name, duh
	protected String wizardType; //stores what type of wizard the player is (ice, life, etc)
	protected boolean isMob; //basically, is the warrior a player or a mob? this is important for the turn method
	protected int abilityNum; //how many times a special ability has been used in a game
	protected int roundCount; //counts how many more turns a special ability is active
	
	protected double resist; //the percentage of the attack damage that is negated
	protected int pierce; //how much of the target's resist will be subtracted
	protected int damage; //increases the attacker's damage by a certain percent
	
	protected boolean stunned; //boolean for if the player is stunned or not; if they are, they skip their turn and are unstunned
	protected boolean blade; //boolean for if the player already has a blade; if they already have one, they cannot have another
	protected boolean shield; //boolean for if the player already has a shield; if they already have one, they cannot have another
	protected boolean unlockedAbility; //boolean for if the player has already unlocked their special ability and can use it
	protected int dotRoundNum; //same as roundCount above, but for Fire Wizards' special ability, specifically (covered later)

	public Warrior (String name, String wizardType) { //Constructor for Players
		this.health = (int) ((Math.random() * 20) + 80);
		this.maxHealth = 100;
		this.strength = (int) ((Math.random() * 3) + 1);
		this.name = name;
		this.wizardType = wizardType;
		this.isMob = false;
		this.abilityNum = 0;
		this.roundCount = 0;
		
		this.resist = 0.0;
		this.pierce = 0;
		this.damage = 0;
		
		this.stunned = false;
		this.blade = false;
		this.shield = false;
		this.unlockedAbility = false;
		this.dotRoundNum = 0;
	}
	
	public Warrior (String name) { //Constructor for Mobs
		this.health = 70;
		this.maxHealth = 70;
		this.strength = 1;
		this.name = name;
		this.wizardType = "None";
		this.isMob = true;
		this.abilityNum = 0;
		this.roundCount = 0;
		
		this.resist = 0.0;
		this.pierce = 0;
		this.damage = 0;
		
		this.stunned = false;
		this.blade = false;
		this.shield = false;
		this.unlockedAbility = false;
		this.dotRoundNum = 0;
	}
	
	public int getStrength () { //getter for strength variable
		return this.strength;
	}
	
	public void setStrength (int strength) { //setter for strength variable
		this.strength = strength;
	}

	public int getHealth () { //getter for health variable
		return this.health;
	}
	
	public int getMaxHealth () { //getter for max health variable
		return this.maxHealth;
	}
	
	public String getName () { //getter for the player's name
		return this.name;
	}
	
	public String getWizardType () { //getter for the player's wizard type
		return this.wizardType;
	}
	
	public boolean getIsMob () { //getter for whether or not the player is a mob
		return this.isMob;
	}
	
	public void setIsMob () { //whenever i create an npc enemy, i call this method and make it a mob, so the code knows it's not the player themself
		this.isMob = true;
	}
	
	public double getResist () { //getter for the resist variable
		return this.resist;
	}

	public int getPierce () { //getter for the pierce variable
		return this.pierce;
	}
	
	public int getDamage () { //getter for the damage variable
		return this.damage;
	}
	
	public boolean getStun() { //getter for whether the player is stunned or not
		return this.stunned;
	}
	
	public void stunStatChange() { //i call this method to update the stun method; if the player is stunned, i un-stun them, and vice-versa
		if (this.stunned) {
			this.stunned = false;
			System.out.println("\n" + this.name + " is no longer stunned! (But they unfortunately had to skip their turn...)");
		} else {
			this.stunned = true;
			System.out.println(this.name + " is stunned!");
		}
	}
	
	public boolean getBlade() { //getter for whether the player has a blade or not
		return this.blade;
	}
	
	public void bladeChange() { //i call this method to give the player a blade (if they do not have one yet) or to take it away
		if (this.blade) {
			this.blade = false;
			System.out.println(this.name + " has used up their blade.");
		} else {
			this.blade = true;
			System.out.println(this.name + " now has a blade. Their next outgoing attack will increase by 40%.");
		}
	}
	
	public boolean getShield() { //getter for whether the player has a shield or not
		return this.shield;
	}
	
	public void shieldChange() { //i call this method to give the player a shield (if they do not have one yet) or to take it away
		if (this.shield) {
			this.shield = false;
			System.out.println(this.name + " has used up their shield.");
		} else {
			this.shield = true;
			System.out.println(this.name + " now has a shield. The next incoming attack will be reduced by 50%.");
		}
	}
	
	public boolean getUnlocked() { //getter for whether the player unlocked their special ability yet
		return this.unlockedAbility;
	}
	
	public void unlockIt() { //unlocks the player's special ability
		unlockedAbility = true;
		System.out.println("\nYou have unlocked your special ability! In combat, input \'D\' to activate it!\n");
	}
	
	public int getAbilityNum(){ //getter for how many times the ability was used within the battle
		return this.abilityNum;
	}
	
	public void abilityNumUpdate(){ //increases the amount of times an ability was used by 1; called whenever an ability is used
		this.abilityNum++;
	}
	
	public void abilityNumReset(){ //this is called whenever the battle ends to reset the count to 0, so the player can use their special ability the next battle
		this.abilityNum = 0;
	}
	
	public void setRoundCount(){ //sets the number of rounds a special ability is active to 4, so the special ability will activate for the next 4 rounds
		this.roundCount = 4;
	}
	
	public int getRoundCount(){ //getter for how many more turns a special ability is active
		return this.roundCount;
	}
	
	public int getDotNum() { //getter for Fire Wizards' 'dot' special ability 
		return this.dotRoundNum;
	}
	
	public void roundAbilityActivate(){ //this method is called every round... 
		
		if (this.getRoundCount() > 0) { //if the player's special ability lasts for more than 0 rounds, then we activate the ability 
			
			this.roundCount--;
			
			if (this.getWizardType().equals("Ice Wizard")) { //if the wizard is an Ice wizard, then their special ability must be the following: 
				System.out.println("");
				if (this.getShield() != true) { //essentially, if the Ice wizard does not already have a shield, it will give them one
					System.out.println("Special ability activates!");
					this.shieldChange();
				}
				System.out.println(this.getName() + "\'s special ability lasts " + this.getRoundCount() + " more round(s).");
				
			} else if (this.getWizardType().equals("Life Wizard")) { //if the wizard is a Life wizard, then their special ability must be the following: 
				System.out.println("");
				this.setHealth(15); //the Life wizard is healed 15 health every round their ability is active
				System.out.println("Special ability activates! " + this.getName() + " heals for 15 health. They now have " + this.getHealth() + " health.");
				System.out.println(this.getName() + "\'s special ability lasts " + this.getRoundCount() + " more round(s).");
				
			} else if (this.getWizardType().equals("Storm Wizard")) { //if the wizard is a Storm wizard, then their special ability must be the following: 
				System.out.println("");
				if (this.getBlade() != true) { //essentially, if the Storm wizard does not already have a blade, it will give them one
					System.out.println("Special ability activates!");
					this.bladeChange();
				}
				System.out.println(this.getName() + "\'s special ability lasts " + this.getRoundCount() + " more round(s).");
				
			} 
			
			
		}
		/*NOTE: unlike other wizards' special abilities, Fire wizards' special ability applies a damage-over-time on the opponent, so we have to check when the OPPONENT
		 has a special ability to trigger each round. for this reason, Fire wizards get a unique variable that is actually assigned to the opponent and checks if their
		 opponent still has rounds on the damage-over-time*/
		if (this.getDotNum() > 0) { 
			System.out.println("");
			this.dotRoundNum--;
			this.health -= 10;
			System.out.println("The damage-over-time damage deals 10 damage to " + this.getName() + ". The damage-over-time will last for " + getDotNum() + " more round(s).\n"
					+ this.getName() + " has " + this.getHealth() + " health left.");
		}
	}
	
	public void specialAbility(Warrior player2) { //this is the generic bad guys' special ability
		System.out.println("Healed " + this.getName() + " for 25 health!");
		this.setHealth(25);
		if (this.getBlade() != true) {
			this.bladeChange();
		}
		if (this.getShield() != true) {
			this.shieldChange();
		}
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
		}
	}
	
	public void setMaxMobHealth (int newHealth) { //For MOBS only! Changes their maximum health
		this.health = newHealth;
		this.maxHealth = newHealth;
	}
	
	public void takeDamage (int damage) { //Generic Attack Damage
		double tempResistAdd = 0;
		if (this.getShield()) { //if the target has a shield, it gets used up and adds 50% resist for this attack
			System.out.println(this.name + " has a shield! They resist 50% more for this attack!");
			this.shieldChange(); //removes the shield
			
			tempResistAdd = 0.5; //temporary increase to resist
		} 
		
		if ((this.resist + tempResistAdd) > 0) { //this if statement runs if the player has over 0 resist
			int absorb = (int) (damage * (resist + tempResistAdd));
			damage -= absorb; //now we take away damage depending on how high the resist is
			this.health -= damage; //and then we take away health
			System.out.println(this.getName() + " resists " + absorb + " hitpoints of damage! The attack does " + 
			damage + " hitpoints of damage. " + this.getName() + " has " + this.getHealth() + " health left!");
		} else { //this else statement runs if the player has no resist; they just take damage right away
			this.health -= damage; 
			if (this.health < 0) {
				this.health = 0;
			}
			System.out.println("The attack does " + damage + " hitpoints of damage. " + this.getName() + " has " + this.getHealth() + " health left!");
		}
	}
	
	public int drainDamage (int damage, int pierce) { //Death Wizards' Drain Attack Damage (unique because they get health back)
		double tempResistAdd = 0;
		if (this.getShield()) { //same concept as above
			System.out.println(this.name + " has a shield! They resist 50% more for this attack!");
			this.shieldChange();
			
			tempResistAdd = 0.5;
		} 
		
		double tots = resist + tempResistAdd - pierce;
		
		if ((this.resist + tempResistAdd) > 0) {
			if (pierce > 0) {
				if ((resist + tempResistAdd - pierce) < 0) {
					tots = 0;
				}
				
				System.out.println(this.getName() + "\'s resist was pierced through. They will now only resist " + tots + "% of the attack.");
			}
			
			int absorb = (int) (damage * (resist + tempResistAdd - pierce)); //remember how pierce removes some resist? we remove the resist here 
			if (absorb <= 0) { //the amount of damage absorbed cannot go lower than 0, so we just set it to 0 if it goes below
				absorb = 0;
			}
			damage -= absorb; //now we take away some damage based on how much was absorbed
			this.health -= damage;
			if (this.health < 0) {
				this.health = 0;
			}
			System.out.println(this.getName() + " resists " + absorb + " hitpoints of damage! The attack does " + 
			damage + " hitpoints of damage. " + this.getName() + " has " + this.getHealth() + " health left!");
			
			if (this.wizardType.equals("Life Wizard")) { 
				//IMPORTANT: if the target is a Life wizard (they disable Death wizards' healing ability), we return 0, which means the Death wizard heals back 0 health
				return 0;
			} else {
				return damage / 2; //how much Death Wizards will heal back (they heal back half of the damage they deal)
			}
			
		} else {
			this.health -= damage;
			if (this.health < 0) {
				this.health = 0;
			}
			System.out.println("The attack does " + damage + " hitpoints of damage. " + this.getName() + " has " + this.getHealth() + " health left!");
			
			if (this.wizardType.equals("Life Wizard")) {
				return 0;
			} else {
				return damage / 2; //how much Death Wizards will heal back
			}
		}
	}
	
	public void takeDamage (int damage, int pierce) { //Fire Wizards' Pierce Attack Damage
		int tempResistAdd = 0;
		if (this.getShield()) {
			System.out.println(this.name + " has a shield! They resist 50% more for this attack!");
			this.shieldChange();
			
			tempResistAdd = 50;
		} 
		double piercedResist = ((int) ((this.resist * 100) + tempResistAdd)) - pierce;
		
		if (this.resist > 0) {
			if (piercedResist < 0) {
				piercedResist = 0;
			} else {
				piercedResist = (double) piercedResist / 100;
			}
			double absorb = (damage * (piercedResist));
			damage = (int) (damage - absorb);
			System.out.printf("The " + this.name + " resisted some of the damage. However, the attacker's pierce negated"
					+ " some of the resist. \n" + this.name + " resisted %.2f hitpoints of damage!", absorb);
		}
		
		if (piercedResist == 0) { //this checks if the overall resist (with the pierce already calculated) is 0; if it is, Fire wizards can do x2 damage
			int critical = (int) ((Math.random() * 100) + 1);
			if (critical < pierce) {
				damage = damage * 2;
				
				if (this.resist > 0) {
					System.out.println("\nSince all of " + this.name + "'s resist was pierced through, the hit becomes a CRITICAL blow! "
							+ "The attack now does x2 damage!");
				} else {
					System.out.println("\nSince " + this.name + " had no resist, the hit becomes a CRITICAL blow! "
							+ "The attack now does x2 damage!");
				}
			}
		}
		this.health -= damage;
		if (this.health < 0) {
			this.health = 0;
		}
		System.out.println("The attack does " + damage + " hitpoints of damage. " + this.getName() + " has " + this.getHealth() + " health left!");
		
	}
	
	public void maxHealth () { //puts health at 100
		this.health = this.maxHealth;
	}
	
	public void incStrength (int addedStrength) { //increases strength
		this.strength += addedStrength;
		if (this.strength < 1) {
			this.strength = 1;
			System.out.print("\nNote: Players cannot have less than 1 strength!");
		}
	}
	
	public void attacks (Warrior victim) { //generic attack method
		int attackValue = this.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue; //total damage dealt is equal to whatever the dice rolled times the attacker's strength
		
		if (this.blade) { //if the attacker has a blade, then we increase their attack damage by 40% 
			if (this.getDamage() > 0) {
				damageDealt += damageDealt * 0.4;
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) + 40% (blade) = " + damageDealt);
				
				this.bladeChange();
			} else {
				damageDealt += damageDealt * 0.4;
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 40% (blade) = " + damageDealt);
				this.bladeChange();
			}
			
		} else {
			if (this.getDamage() > 0) {
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) = " + damageDealt);
			} else {

				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
				attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) = " + damageDealt);
			}
		}
		
		if (this.getPierce() > 0) { //if the attacker has some pierce, then we call the method that takes in pierce as a variable
			victim.takeDamage(damageDealt, this.getPierce());
		} else {
			victim.takeDamage(damageDealt);
		}
		
		if (victim.getHealth() < 0) { //if the victim's health dips below 0, we just set their health to 0
			victim.health = 0;
		}
		
	}
	
	public static void turn (Warrior player1, Warrior player2) { //the turn method, which lets the players take turns choosing what to do
		boolean chose = false;
		Scanner input = new Scanner(System.in);
		char choice1 = 'Z';
		
		if (player1.getIsMob()) { //see? remember when i said the mob boolean would be important? it determines which part of the if statement activates
			int randomChoose;
			
			while (!chose) { //basically, if the mob has not yet chosen what it wants to do, it will run the while loop until it does a valid move
				randomChoose = (int) ((Math.random() * 2) + 1);
				if (randomChoose <= 1) {
					System.out.println("\n" + player1.getName() + " decided to attack " + player2.getName() + "!");
					player1.attacks(player2);
					chose = true;
				} else if ((randomChoose > 1) && (randomChoose <= 2)) {
					if (!(player1.getBlade())) { //if it already has a blade, this will not run, and the while loop will run again
						System.out.println("\n" + player1.getName() + " decided to blade!");
						  player1.bladeChange();
						  chose = true;
					  } 
				} else if ((randomChoose > 2)) {
					if (!(player1.getShield())) { //if it already has a shield, this will not run, and the while loop will run again
						  System.out.println("\n" + player1.getName() + " decided to shield!");
						  player1.shieldChange();
						  chose = true;
					  }
				}
			}
			
		} else {
			if (player1.unlockedAbility) { //if the player has already unlocked their special ability, they will be able to activate it on their turn
				while (!chose) { //same concept as above; this runs until the player chooses a valid move
					System.out.print("\nIt is your turn! Would you like to (A) attack, (B) blade, (C) shield, or (D) use your special ability? ");
					choice1 = ((input.next()).toUpperCase()).charAt(0);
					
					if ((choice1 == 'A') || (choice1 == 'B') || (choice1 == 'C') || (choice1 == 'D')) { //runs based on the player's letter inputted
						
						switch(choice1) {
						  case 'A':
							  System.out.println(player1.getName() + " decided to attack " + player2.getName() + "!");
							  player1.attacks(player2);
							  chose = true;
							  break;
						  case 'B':
							  if (player1.getBlade()) {
								  System.out.println("Remember, you can only have one blade at a time! Choose again, please.");
							  } else {
								  System.out.println(player1.getName() + " decided to blade!");
								  player1.bladeChange();
								  chose = true;
							  }
							  break;
						  case 'C':
							  if (player1.getShield()) {
								  System.out.println("Remember, you can only have one shield at a time! Choose again, please.");
							  } else {
								  System.out.println(player1.getName() + " decided to shield!");
								  player1.shieldChange();
								  chose = true;
							  }
							  break;
						  case 'D':
							  if (player1.getAbilityNum() >= 3) {
								  System.out.println("Remember, you can only use your special ability 3 times per battle! Choose again, please.");
							  } else {
								  System.out.println(player1.getName() + " decided to use their " + player1.getWizardType() + " special ability!");
								  player1.specialAbility(player2);
								  player1.abilityNumUpdate();
								  chose = true;
							  }
							  break;
						  default:
						    System.out.println("ERROR ERROR ERROR ERROR (how embarrassing)");
						}
						
						
					} else {
						System.out.println("Invalid input! Please choose again.");
					}
				}
			} else { //if the player hasn't yet unlocked their special ability, this will run... it is the same as earlier, but without the special ability option
				while (!chose) {
					System.out.print("\nIt is your turn! Would you like to (A) attack, (B) blade, or (C) shield? ");
					choice1 = ((input.next()).toUpperCase()).charAt(0);
					
					if ((choice1 == 'A') || (choice1 == 'B') || (choice1 == 'C')) {
						
						switch(choice1) {
						  case 'A':
							  System.out.println(player1.getName() + " decided to attack " + player2.getName() + "!");
							  player1.attacks(player2);
							  chose = true;
							  break;
						  case 'B':
							  if (player1.getBlade()) {
								  System.out.println("Remember, you can only have one blade at a time! Choose again, please.");
							  } else {
								  System.out.println(player1.getName() + " decided to blade!");
								  player1.bladeChange();
								  chose = true;
							  }
							  break;
						  case 'C':
							  if (player1.getShield()) {
								  System.out.println("Remember, you can only have one shield at a time! Choose again, please.");
							  } else {
								  System.out.println(player1.getName() + " decided to shield!");
								  player1.shieldChange();
								  chose = true;
							  }
							  break;  
						  default:
						    System.out.println("ERROR ERROR ERROR ERROR (how embarrassing)");
						}
						
						
					} else {
						System.out.println("Invalid input! Please choose again.");
					}
				}
				
			}
		}
		
		
		
	}
	
	public static void generateLoot(Warrior player, Warrior creature) { //loot method, runs everytime an opponent is defeated
		Scanner input = new Scanner(System.in);
		int choice;
		
		Loot A = new Loot();
		Loot B = new Loot();
		Loot C = new Loot();
		Loot chosen;
		
		//Basically, below, three different loot are generated (with random names/stat increases), and the player has to choose which one they want
		System.out.println("\nUpon defeat, " + creature.getName() + " drops some items!");
		System.out.print("1) "); A.statement();
		System.out.print("2) "); B.statement();
		System.out.print("3) "); C.statement(); 
		System.out.print("Choose a number to keep/equip ONE of the gear pieces it dropped! ");
		choice = input.nextInt();
		
		if (choice == 1) {
			chosen = A;
		} else if (choice == 2) {
			chosen = B;
		} else {
			chosen = C;
		}
		
		//Basically, below, the code checks what stat the gear increases, then increases the stat it says it will increase
		if (chosen.getStatToInc().equals("strength")) {
			player.strength += chosen.getAmount();
		} else if (chosen.getStatToInc().equals("resist")) {
			player.resist += chosen.getAmount();
		} else if (chosen.getStatToInc().equals("max health")) {
			player.maxHealth += chosen.getAmount();
		} else if (chosen.getStatToInc().equals("pierce")) {
			player.pierce += chosen.getAmount();
		} else if (chosen.getStatToInc().equals("damage")) {
			player.damage += chosen.getAmount();
		}
		
		System.out.println("\nYou have successfully equipped the " + chosen.getName() + "! Here are your updated stats:");
		player.printStats();
	}
	
	public static void generateLoot(Warrior player) { //this is a loot method that runs specifically as a reward; runs without having to defeat an opponent
		
		Loot A = new Loot();
		Loot B = new Loot();
		double chosen;
		String chosenS;
		int added = 0;
		
		System.out.print("1) "); A.statement();
		System.out.print("2) "); B.statement();
		
		//Note: BOTH pieces of gear are given to the player, since this is a reward loot method (the previous loot method was for drops)
		while (added < 2) {
			if (added == 0) {
				chosen = A.getAmount();
				chosenS = A.getStatToInc();
			} else {
				chosen = B.getAmount();
				chosenS = B.getStatToInc();
			}
			
			if (chosenS.equals("strength")) {
				player.strength += chosen;
			} else if (chosenS.equals("resist")) {
				player.resist += chosen;
			} else if (chosenS.equals("max health")) {
				player.maxHealth += chosen;
			} else if (chosenS.equals("pierce")) {
				player.pierce += chosen;
			} else if (chosenS.equals("damage")) {
				player.damage += chosen;
			}
			
			added++;
		}
		
		System.out.println("\nYou have successfully equipped the " + A.getName() + " and the " + B.getName() + "! Here are your updated stats:");
		player.printStats();
	}
	
	public void printStats () { //generic print method (just print's the warrior's stats)
		System.out.println(this.name + "'s Stats || "
	+ "Health: " + this.health + "/" + this.getMaxHealth() + "\t| "
	+ "Strength: " + this.strength + "\t| " 
	+ "Resist: " + (this.resist * 100) + "%\t| " 
	+ "Pierce: " + this.pierce + "%\t| "
	+ "Damage boost: " + this.damage + "%");
	}
}



package FinalProject;

public class Thaumaturge extends Warrior {
	
	public Thaumaturge (String name) {
		super (name, "Ice Wizard");
		this.resist = ((int) ((Math.random() * 15) + 15)) * 0.01;
		this.health += 40;
		this.maxHealth = 140;
		if (this.getStrength() > 3) {
			this.strength = 3;
		}
	}
	
	public void maxHealth () { //puts health at 140
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Ice Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println("For four rounds, if " + this.getName() + " does not have a shield active, they will automatically get one!");
		if (this.getShield() != true) {
			this.shieldChange();
		}
		this.roundCount += 4;
	}
	
}



package FinalProject;

public class Pyromancer extends Warrior {
	
	public Pyromancer (String name) {
		super (name, "Fire Wizard");
		this.pierce = (int) ((Math.random() * 10) + 5);
		this.health += 20;
		this.maxHealth = 120;
		this.strength += 1;
	}
	
	public void maxHealth () { //puts health at 120
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Fire Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println("For five rounds, " + player2.getName() + " will be damaged for 10 health (for a total of 50)! The damage-over-time ignores all damage boosts, resist, and shield(s).");
		player2.dotRoundNum += 5;
	}
	
	public void attacks (Warrior victim) { //this is like the attack method we saw earlier, but with an added if/else branch checking if the opponent is an Ice Wizard
		int attackValue = this.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue;
		
		if (victim.wizardType.equals("Ice Wizard")) { //if the target is an Ice Wizard, the damage increases by a certain percent
			damageDealt += (damageDealt * 0.2);
			
			if (this.blade) {
				if (super.getDamage() > 0) {
					damageDealt += damageDealt * 0.4;
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Fire Wizards' damage bonus against Ice Wizards) + " + super.getDamage()
								+ "% (base damage bonus) + 40% (blade) " + "= " + damageDealt);
				} else {
					damageDealt += damageDealt * 0.4;
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Fire Wizards' damage bonus against Ice Wizards) "
								+ "+ 40% (blade) " + "= " + damageDealt);
				}
				
				this.bladeChange();
			} else {
				if (super.getDamage() > 0) {
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Fire Wizards' damage bonus against Ice Wizards) + " + super.getDamage()
									+ "% (base damage bonus) = " + damageDealt);
				} else {
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Fire Wizards' damage bonus against Ice Wizards) "
									+ "= " + damageDealt);
				}
				
			}
			 
			
		} else {
			
			if (this.blade) {
				if (super.getDamage() > 0) {

					damageDealt += damageDealt * 0.4;
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + super.getDamage() + "% (base damage bonus) + 40% (blade) " + "= " + damageDealt);
					this.bladeChange();
					
				} else {
					damageDealt += damageDealt * 0.4;
					
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 40% (blade) " + "= " + damageDealt);
					this.bladeChange();
				}
			} else {
				if (super.getDamage() > 0) {
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + super.getDamage() + "% (base damage bonus) = " + damageDealt);
				} else {
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) = " + damageDealt);
				}
			}
		}

		victim.takeDamage(damageDealt, this.pierce);
		
		if (victim.getHealth() < 0) {
			victim.health = 0;
		}
		
	}
	
}



package FinalProject;

public class Diviner extends Warrior {
	
	public Diviner (String name) {
		super (name, "Storm Wizard");
		this.damage = (int) ((Math.random() * 15) + 10);
		this.strength += 2;
		this.health -= 10;
		this.maxHealth = 90;
	}
	
	public void maxHealth () { //puts health at 90
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Storm Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println("For four rounds, if " + this.getName() + " does not have a blade active, they will automatically get one!");
		if (this.getBlade() != true) {
			this.bladeChange();
		}
		this.roundCount += 4;
	}
	
	public void attacks (Warrior victim) { //same generic attack method, BUT Storm wizards have a chance to fizzle, which prevents them from attacking
		int attackValue = super.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue;
		damageDealt += (int) (damageDealt * (0.01 * super.getDamage()));
		int fizzle = (int) ((Math.random() * 100) + 1);
		
		if (fizzle <= 15) { //basically, a random number is rolled; if it's less than or equal to 15, the attack does not go through
			System.out.println("The Storm Wizard fizzled! Nobody was hurt.");
		} else { 
			
			if (this.blade) {
				damageDealt += damageDealt * 0.4;
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " 
						+ (super.getDamage()) + "% (Storm Wizards' damage bonus) + 40% (blade) = " + damageDealt);
				this.bladeChange();
			} else {
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " 
						+ (super.getDamage()) + "% (Storm Wizards' damage bonus) = " + damageDealt);
			}
			
			if (this.getPierce() > 0) {
				victim.takeDamage(damageDealt, this.getPierce());
			} else {
				victim.takeDamage(damageDealt);
			}
					
			if (victim.getHealth() < 0) {
				victim.health = 0;
			}
		}
	}
}



package FinalProject;

public class Theurgist extends Warrior {

	public Theurgist (String name) {
		super (name, "Life Wizard");
		this.resist = ((int) ((Math.random() * 10) + 10)) * 0.01;
		this.health += 50;
		this.maxHealth = 160;
		if (this.getStrength() > 3) {
			this.strength = 3;
		}
	}
	
	public void maxHealth () { //puts health at 160
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Life Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println("For four rounds, if " + this.getName() + " will regain 15 health per round! (They also instantly heal 30 health right now!)");
		this.setHealth(30);
		System.out.println(this.getName() + " now has " + this.getHealth() + " health.");
		this.roundCount += 4;
	}
	
	public void attacks (Warrior victim) { //also generic attack method; like Fire wizards' attack method, it checks if the opponent is Death wizard and gives bonus damage
		int attackValue = this.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue;
		
		if (victim.wizardType.equals("Death Wizard")) {
			damageDealt += (damageDealt * 0.2);
			
			if (this.blade) {
				if (super.getDamage() > 0) {
					damageDealt += damageDealt * 0.4;
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Life Wizards' damage bonus against Death Wizards) + " + super.getDamage()
									+ "% (base damage bonus) + 40% (blade) = " + damageDealt);
					this.bladeChange();
				} else {
					damageDealt += damageDealt * 0.4;
					
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Life Wizards' damage bonus against Death Wizards) "
									+ "+ 40% (blade) = " + damageDealt);
				}
			} else {
				if (super.getDamage() > 0) {
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Life Wizards' damage bonus against Death Wizards) + " + super.getDamage()
									+ "% (base damage bonus) = " + damageDealt);
				} else {
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 20% (Life Wizards' damage bonus against Death Wizards) "
									+ "= " + damageDealt);
				}
				
			}
		} else {
			
			if (this.blade) {
				if (super.getDamage() > 0) {
					damageDealt += damageDealt * 0.4;
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
					
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + super.getDamage() + "% (base damage bonus) + 40% (blade) = " + damageDealt);
					this.bladeChange();
				} else {
					damageDealt += damageDealt * 0.4;
					System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
					

					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 40% (blade) = " + damageDealt);
					this.bladeChange();
				}
			} else { 
				if (super.getDamage() > 0) {
					damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + super.getDamage() + "% (base damage bonus) = " + damageDealt);
				} else {
					System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
							attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) = " + damageDealt);
				}
			}
		}
		
		if (this.getPierce() > 0) {
			victim.takeDamage(damageDealt, this.getPierce());
		} else {
			victim.takeDamage(damageDealt);
		}
		
		if (victim.getHealth() < 0) {
			victim.health = 0;
		}
		
	}

}



package FinalProject;

public class Necromancer extends Warrior {

	private boolean extraHeal;
	
	public Necromancer (String name) {
		super (name, "Death Wizard");
		this.health -= 10;
		this.maxHealth = 90;
		this.strength += 1;
		
		this.extraHeal = false;
	}
	
	public boolean getExtra() {
		return this.extraHeal;
	}
	
	public void changeExtra() {
		this.extraHeal = false;
	}
	
	public void maxHealth () { //puts health at 90
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Death Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println(this.getName() + "\'s next drain attack will heal back 150% more than usual!");
		this.extraHeal = true;
	}
	
	public void attacks (Warrior victim) { //same generic attack method, BUT heals back the player depending on how much damage they dealt
		int attackValue = this.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue;
		
		if (this.blade) {
			if (this.getDamage() > 0) {
				damageDealt += damageDealt * 0.4;
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) + 40% (blade) = " + damageDealt);
				
				this.bladeChange();
			} else {
				damageDealt += damageDealt * 0.4;
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 40% (blade) = " + damageDealt);
				this.bladeChange();
			}
			
		} else {
			if (this.getDamage() > 0) {
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) = " + damageDealt);
			} else {

				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
				attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) = " + damageDealt);
			}
		}

		int healBack = victim.drainDamage(damageDealt, this.getPierce());
		
		if (extraHeal) {
			healBack += (int) (healBack * 1.5);
			
			setHealth(healBack);
			System.out.println(this.getName() + " heals back 150% of the damage dealt because the attack was enchanced by a special ability! They heal back " + healBack + "\n"
					+ "hitpoints and now has " + this.health + " hitpoints.");
			
			this.extraHeal = false;
		} else if (victim.wizardType.equals("Life Wizard")) {
			System.out.println("Oh no! The drain attack does not heal " + this.getName() + " because "  + victim.getName() +  " is a Life Wizard. (Pro-tip: "
					+ "Life Wizards cancel out Death Wizards' heal-back effect, unless it is enchanced by a special ability!)");
		} else {
			setHealth(healBack);
			System.out.println(this.getName() + " heals back half of the damage dealt! They heal back " + healBack + " hitpoints "
					+ "and now has " + this.health + " hitpoints.");
		}
		
		
		if (victim.getHealth() < 0) {
			victim.health = 0;
		}
	}
}



package FinalProject;

public class Conjurer extends Warrior {

	public Conjurer (String name) {
		super (name, "Myth Wizard");
		this.health += 15;
		this.maxHealth = 115;
		this.strength += 1;
		this.resist = ((int) ((Math.random() * 10) + 5)) * 0.01;
	}
	
	public void maxHealth () { //puts health at 115
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Myth Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println("Removing " + player2.getName() + "\'s blade and shield, and stunning them...!");
		if (player2.getBlade()) {
			player2.bladeChange();
		}
		if (player2.getShield()) {
			player2.shieldChange();
		}
		if (player2.getStun() != true) {
			player2.stunStatChange();
		}
	}
	
	public void attacks (Warrior victim) { //same generic attack, BUT with a chance to stun the opponent
		int attackValue = this.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue;
		int stun = (int) ((Math.random() * 100) + 1); 
		
		if (this.blade) {
			if (this.getDamage() > 0) {
				damageDealt += damageDealt * 0.4;
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) + 40% (blade) = " + damageDealt);
				
				this.bladeChange();
			} else {
				damageDealt += damageDealt * 0.4;
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 40% (blade) = " + damageDealt);
				this.bladeChange();
			}
			
		} else {
			if (this.getDamage() > 0) {
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) = " + damageDealt);
			} else {

				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
				attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) = " + damageDealt);
			}
		}
		
		
		if (stun <= 25) { //the concept is the same as Storm wizards' fizzle; a random number is rolled, and if it's less than 26, the opponent is stunned
			System.out.println("Furthermore, " + this.getName() + " stunned the target! "  + victim.getName() +  " must skip their next turn!");
			victim.stunStatChange();
			
		}
		
		if (this.getPierce() > 0) {
			victim.takeDamage(damageDealt, this.getPierce());
		} else {
			victim.takeDamage(damageDealt);
		}
		
		if (victim.getHealth() < 0) {
			victim.health = 0;
		}
		
	}
}



package FinalProject;

public class Sorcerer extends Warrior {

	public Sorcerer (String name) {
		super (name, "Balance Wizard");
		this.health += 30;
		this.maxHealth = 130;
		this.resist = ((int) ((Math.random() * 10) + 5)) * 0.01;
		this.pierce = (int) ((Math.random() * 5) + 5);
	}
	
	public void maxHealth () { //puts health at 130
		this.health = this.getMaxHealth();
	}
	
	public void setHealth (int addedHealth) { //adds specific amount of health
		this.health += addedHealth;
		if (health > this.getMaxHealth()) {
			health = this.getMaxHealth();
			System.out.println("Balance Wizards cannot have more than " + this.getMaxHealth() + " health!");
		}
	}
	
	public void specialAbility(Warrior player2) { //when called, activates special ability
		System.out.println("Healed " + this.getName() + " for 50 health!");
		this.setHealth(50);
		if (this.getBlade() != true) {
			this.bladeChange();
		}
		if (this.getShield() != true) {
			this.shieldChange();
		}
		
		//Balance wizards' special ability involves PERMANENT stat increases, which we see below
		int random = (int) (Math.random() * 4) + 1;
		if (random <= 1) {
			this.resist += 0.02;
			System.out.println(this.getName() + " has permanently gained an additional 2% resist!");
		} else if (random <= 2) {
			this.damage += 0.02;
			System.out.println(this.getName() + " has permanently gained an additional 2% damage boost!");
		} else if (random <= 3) {
			this.maxHealth += 25;
			System.out.println(this.getName() + "\'s max health has permanently increased by 25!");
		} else {
			this.pierce += 1;
			System.out.println(this.getName() + " has permanently gained an additional 1% pierce!");
		}
	}
	
	
	public void attacks (Warrior victim) { //same generic attack, BUT (similar to Myth wizards) can stun opponent
		int attackValue = this.getStrength();
		int twoDiceRoll = ((int) ((Math.random() * 6) + 1)) + ((int) ((Math.random() * 6) + 1));
		int damageDealt = twoDiceRoll * attackValue;
		int stun = (int) ((Math.random() * 100) + 1);
		
		if (this.blade) {
			if (this.getDamage() > 0) {
				damageDealt += damageDealt * 0.4;
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) + 40% (blade) = " + damageDealt);
				
				this.bladeChange();
			} else {
				damageDealt += damageDealt * 0.4;
				System.out.println(this.name + " had a blade! The attack's overall damage is increased by 40%!");
				
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + 40% (blade) = " + damageDealt);
				this.bladeChange();
			}
			
		} else {
			if (this.getDamage() > 0) {
				damageDealt += (int) (damageDealt * (0.01 * this.getDamage()));
				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
						attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) + " + this.getDamage() + "% (damage boost) = " + damageDealt);
			} else {

				System.out.println("Two-dice roll value: " + twoDiceRoll + "\t| Total damage dealt: " + 
				attackValue + " (attacker's strength) x " + twoDiceRoll + " (dice) = " + damageDealt);
			}
		}
		
		if (stun <= 10) {
			System.out.println("Furthermore, " + this.getName() + " stunned the target! " + victim.getName() + " must skip their next turn!");
			victim.stunStatChange();
			
		} 
		
		victim.takeDamage(damageDealt, this.pierce);
		
		if (victim.getHealth() < 0) {
			victim.health = 0;
		}
		
	}
}


