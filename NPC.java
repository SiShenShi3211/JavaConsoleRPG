public class NPC
{
	//Basic variables
	String name;
	Boolean controllable;
	int[] stats;
	int hp;
	int maxHp;
	int str;
	int dex;
	int def;
	int inte;
	int faith;
	boolean dead;
	int xPos;
	int yPos;
	String race;
	Weapon weapon;
	
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
		this.xPos = 4;
		this.yPos = 3;
		this.def = 2;
		this.race = "Human";
		this.weapon = new Weapon("fists", "meele", new int[] {0,0,0,0}, new int[] {0,0,0,0});
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
		this.weapon = new Weapon("claw", "meele", new int[] {0,0,0,0}, new int[] {0,0,0,0});
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
	
	
	//FUNCTION WHIH HANDLES PLAYER INPUT
		public void move(String input, int[][] map, NPC player)
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
				if (this.xPos < map[0].length - 1 && map[this.xPos + 1][this.yPos] == 0 )
				{
					//if player takes the space then attack player instead
					if (player.xPos == this.xPos + 1 && player.yPos == this.yPos)
					{
						
					}
					else
					{
						this.xPos += 1;
					}
					
				}		
				break;
				
				
				// FORWARD MOVEMENT
			case "w":
				//IF EMPTY MOVE FORWARD
				if (this.yPos > 0 && map[this.xPos][this.yPos - 1] == 0)
				{
					
					//if player takes the space then attack player instead
					if (player.xPos == this.xPos && player.yPos == this.yPos - 1)
					{
						
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
		
		
		
		//FUNCTION THAT ALLOWS PLAYER TO EQUIP WEAPONS IF REQ MET
		public void equipWeapon(Weapon weapon)
		{
			if (this.str >= weapon.strReq && this.dex >= weapon.dexReq && this.inte > weapon.intReq && this.faith > weapon.faithReq)
			{
				this.weapon = weapon;
			}
		}
	
	
	
}