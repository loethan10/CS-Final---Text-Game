//Ethan Lo, Final Project, 5/26/21
package FinalProject;

public class Loot {
	
	protected String name;
	protected String statToInc;
	protected double amountInc;
	
	public Loot () {
		this.statToInc = FindStat();
		this.amountInc = IncStat(statToInc);
		this.name = initialize();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStatToInc() {
		return this.statToInc;
	}
	
	public double getAmount() {
		return this.amountInc;
	}
	
	public void statement() {
		if (this.getStatToInc().equals("resist")) {
			System.out.println("The " + this.getName() + " gives you an additional " + (this.getAmount() * 100) + "% resist!");
		} else if (this.getStatToInc().equals("damage")) {
			System.out.println("The " + this.getName() + " gives you an additional " + (int) (this.getAmount()) + "% permanent damage boost!");
		} else if (this.getStatToInc().equals("pierce")) {
			System.out.println("The " + this.getName() + " gives you " + (int) (this.getAmount()) + "% additional pierce!");
		} else {
			int num = (int) this.getAmount();
			System.out.println("The " + this.getName() + " increases your " +  this.getStatToInc() + " by " + num + "!");
		}
	}
	
	public String FindStat() {
		int random = (int) (Math.random() * 5) + 1;
		
		if (random <= 1) {
			return "strength";
		} else if (random <= 2) {
			return "resist";
		} else if (random <= 3) {
			return "max health";
		} else if (random <= 4) {
			return "pierce";
		} else {
			return "damage";
		} 
	}
	
	public double IncStat(String incStat) {
		if (this.getStatToInc().equals("strength")) {
			int random = (int) (Math.random() * 2) + 1;
			return random;
		} else if (this.getStatToInc().equals("resist")) {
			double random = ((int) ((Math.random() * 15) + 5)) * 0.01;
			return random;
		} else if (this.getStatToInc().equals("max health")) {
			int random = (int) (Math.random() * 20) + 20;
			return random;
		} else if (this.getStatToInc().equals("pierce")) {
			int random = (int) ((Math.random() * 5) + 5);
			return random;
		} else if (this.getStatToInc().equals("damage")) {
			int random = (int) ((Math.random() * 5) + 5);
			return random;
		}
		
		return 0.0;
	}
	
	public String initialize() {
		String lootName = "";
		int random = (int) (Math.random() * 19) + 1;
		int random2 = (int) (Math.random() * 2) + 1;
		int random3 = (int) (Math.random() * 7) + 1;
		
		if (random3 <= 1) {
			if (random2 <= 1) {
				lootName += "Hat of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Hat";
			}
		} else if (random3 <= 2) {
			if (random2 <= 1) {
				lootName += "Robes of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Robes";
			}
		} else if (random3 <= 3) {
			if (random2 <= 1) {
				lootName += "Boots of ";
				lootName += lastName(random);
			} else {
 				lootName += firstName(random);
				lootName += " Boots";
			}
		} else if (random3 <= 4) {
			if (random2 <= 1) {
				lootName += "Wand of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Wand";
			}
		} else if (random3 <= 5) {
			if (random2 <= 1) {
				lootName += "Athame of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Athame";
			}
		} else if (random3 <= 6) {
			if (random2 <= 1) {
				lootName += "Amulet of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Amulet";
			}
		} else if (random3 <= 7) {
			if (random2 <= 1) {
				lootName += "Ring of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Ring";
			}
		} else if (random3 <= 8) {
			if (random2 <= 1) {
				lootName += "Deck of ";
				lootName += lastName(random);
			} else {
				lootName += firstName(random);
				lootName += " Deck";
			}
		}
		
		
		return lootName;
	}
	
	public String firstName(int num) {
		if (num <= 1) {
			return "Valiant Solider's";
		} else if (num <= 2) {
			return "Righteous";
		} else if (num <= 3) {
			return "Silver-lined";
		} else if (num <= 4) {
			return "Heroes\'";
		} else if (num <= 5) {
			return "Prodigious";
		} else if (num <= 6) {
			return "Mayor\'s Right Hand\'s";
		} else if (num <= 7) {
			return "Presidental";
		} else if (num <= 8) {
			return "Exquistely Blue";
		} else if (num <= 9) {
			return "Not-very-disappointing";
		} else if (num <= 10) {
			return "Spirit-Summoner\'s Legendary";
		} else if (num <= 11) {
			return "Idealistic";
		} else if (num <= 12) {
			return "Splendiferous";
		} else if (num <= 13) {
			return "Very Supercalifragilisticexpialidocious";
		} else if (num <= 14) {
			return "Martyr\'s";
		} else if (num <= 15) {
			return "Fantastic";
		} else if (num <= 16) {
			return "Magician\'s";
		} else if (num <= 17) {
			return "Master Wizard\'s";
		} else if (num <= 18) {
			return "Rich Person\'s";
		} else if (num <= 19) {
			return "College Graduate\'s";
		} else {
			return "Grand ol' Biker\'s";
		}
	}
	
	public String lastName(int num) {
		if (num <= 1) {
			return "Resilience";
		} else if (num <= 2) {
			return "Power";
		} else if (num <= 3) {
			return "Might";
		} else if (num <= 4) {
			return "Amazing Goodwill and Luck";
		} else if (num <= 5) {
			return "Untold Secrets";
		} else if (num <= 6) {
			return "Winter\'s Fury";
		} else if (num <= 7) {
			return "Summer\'s Bane";
		} else if (num <= 8) {
			return "Chaos";
		} else if (num <= 9) {
			return "Unending Hunger";
		} else if (num <= 10) {
			return "Valiant Souls\'";
		} else if (num <= 11) {
			return "Kings";
		} else if (num <= 12) {
			return "Knights";
		} else if (num <= 13) {
			return "Jack\'s Grace";
		} else if (num <= 14) {
			return "Force";
		} else if (num <= 15) {
			return "Wolves";
		} else if (num <= 16) {
			return "the Red Sun";
		} else if (num <= 17) {
			return "Eternal Flames";
		} else if (num <= 18) {
			return "Unyielding Wrath";
		} else if (num <= 19) {
			return "Amazingness";
		} else {
			return "Coders Making Cool Names";
		}
	}
	
}


