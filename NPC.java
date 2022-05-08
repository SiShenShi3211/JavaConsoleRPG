import java.util.ArrayList;

public class NPC
{
	//Setting variables
	Integer logMaxCount = 3;
	
	//Basic variables
	String name; boolean controllable;
	int[] stats; int hp;
	int maxHp; int str;
	int dex; int def;
	int inte; int faith;
	boolean dead;
	int xPos; int yPos;
	ArrayList<String> log; ArrayList<Integer> logIndexs;
	String race; Weapon weapon;
	
	// If blank then generate basic player
	public NPC()
	{
		this.controllable = true;
		this.name = "William";
		this.hp = 15;
		this.maxHp = 15;
		this.str = 10;
		this.dex = 5;
		this.inte = 4;
		this.faith = 2;
		this.dead = false;
		this.xPos = 20;
		this.yPos = 5;
		this.def = 2;
		this.race = "Human";
		this.weapon = new Weapon("fists", "meele", new int[] {0,0,0,0}, new int[] {0,0,0,0});
		this.log = new ArrayList<String>();
		this.logIndexs = new ArrayList<Integer>();
	}
	
	
	
	
	//Constructor will include if controllable, stats, and name
	public NPC(Boolean controllable, String name, int[] stats, int[] pos)
	{
		this.controllable = controllable;
		this.name = name;
		this.hp = stats[0];
		this.maxHp = stats[1];
		this.str = stats[2];
		this.dex = stats[3];
		this.def = stats[4];
		this.xPos = pos[0];
		this.yPos = pos[1];
		this.dead = false;
		this.weapon = new Weapon("claw", "meele", new int[] {0,0,0,0}, new int[] {2,0,0,0});
	}
	
	
	//Function which can control damage taken
	public void takeDamage(int dmgValue)
	{
		this.hp -= dmgValue;
		
		//If hp hits 0 or below then die
		if (this.hp <= 0)
		{
			this.hp = 0;
			this.dead = true;
		}
		
	}
	
	//FUnction to control healing
	public void heal(int healValue)
	{
		this.hp += healValue;
		
		//Just make sure he dont go past the cap
		if (this.hp > this.maxHp)
		{
			this.hp = this.maxHp;
		}
	}

	
	
	
	
	//FUNCTION FOR NPC MOVE
		public void move(String input, int[][] map, PlayerObject player)
		{
			//add switch statement
			switch (input)
			{
			// MOVEMENT
			
			// LEFT MOVEMENT
			case "a":
				// IF NOT WALL AND NOT OUT OF MAP
				if (this.xPos > 0 && map[this.xPos - 1][this.yPos] == 0)
				{
					//if player takes the space then attack player instead
					if (player.xPos == this.xPos - 1 && player.yPos == this.yPos)
					{
						this.attack(player, player);
					}
					else
					{
						this.xPos -= 1;
					}			
				}
				break;
				
				
				// RIGHT MOVEMENT
			case "d":
				//IF EMPTY MOVE RIGHT
				if (this.xPos < map.length - 1 && map[this.xPos + 1][this.yPos] == 0 )
				{
					//if player takes the space then attack player instead
					if (player.xPos == this.xPos + 1 && player.yPos == this.yPos)
					{
						this.attack(player, player);
					}
					else
					{
						this.xPos += 1;
					}
					
				}
				
				
				// FORWARD MOVEMENT
			case "w":
				//IF EMPTY MOVE FORWARD
				if (this.yPos > 0 && map[this.xPos][this.yPos - 1] == 0)
				{
					
					//if player takes the space then attack player instead
					if (player.xPos == this.xPos && player.yPos == this.yPos - 1)
					{
						this.attack(player, player);
					}
					else
					{
						this.yPos -= 1;
					}
				}
				break;
				
				
				//DOWNWARD MOVEMENT
			case "s":
				
				if (this.yPos < map[0].length - 1 && map[this.xPos][this.yPos + 1] == 0)
				{
					//if player takes the space then attack player instead
					if (player.xPos == this.xPos && player.yPos == this.yPos + 1)
					{
						this.attack(player, player);
					}
					else
					{
						this.yPos += 1;
					}
				}
				break;
				
				//If given faulty input then throw error
				default:
					System.out.println("WARNING: NPC " + this.name + " GIVEN FAULTY MOVMENT COMMAND");
				break;
				
			}
		}
		
		
		public void attack(NPC defender, PlayerObject player)
		{
			//Str will handle the damage done
			//Dex will handle evasion / accuracy
			
			// calculate if hit or not (r > 1)
		float rand = (int)((float)(Math.random() * 100));
	    int defenceValue = 1;
	    String output;
	    
	    
			
	    //If hit, then take damage hp - (int)((float)(Math.random() * Str) / (float)(defender.def / defenceValue))
			if (rand > 10)
			{
				int damage = calculateDamage(this) / defenceValue;
				defender.takeDamage(damage);
				System.out.println(this.name + " dealt " + damage + " to " + defender.name);
				output = (this.name + " dealt " + damage + " to " + defender.name);
				player.addToLog(output, Personal1.turnNumber);
				
				
			}
			else
			{
				output = ("Missed " + rand);
				player.addToLog(output, Personal1.turnNumber);
			}
			
			//If meele attack then also make defender retaliate (DO NOT RECURSE FOR THE LOVE OF GOD)
			if (this.weapon.type.equals("meele"))
			{
				int defDamage = calculateDamage(defender) / defenceValue;
				this.takeDamage(defDamage);
				output = (defender.name + " dealt " + defDamage + " to " + this.name);
				player.addToLog(output, Personal1.turnNumber);
			}
			
			
		}
		
		
		
		
		//FUNCTION THAT CALCULATES THE DAMAGE OF AN ENTITIES ATTACK
		private static int calculateDamage(NPC entity)
		{
			//Inital damage is based on stats
			int damage = (int)((float)(Math.random() * entity.str));
			int extraDamage = 0;
			
			//If NPC has a weapon (it should) then add extra damage
			if (entity.weapon != null)
			{
				//For the time being weapon damage will be calculated as combination of the different stats
				extraDamage += entity.weapon.pDamage;
				extraDamage += entity.weapon.rDamage;
				extraDamage += entity.weapon.mDamage;
				extraDamage += entity.weapon.fDamage;
			}
			
			damage += extraDamage;
			
			return damage;
		}
		
		
		
		//FUNCTION THAT ALLOWS PLAYER TO EQUIP WEAPONS IF REQ MET
		public void equipWeapon(Weapon weapon)
		{
			if (this.str >= weapon.strReq && this.dex >= weapon.dexReq && this.inte > weapon.intReq && this.faith > weapon.faithReq)
			{
				this.weapon = weapon;
			}
		}
		
	
	
	
}