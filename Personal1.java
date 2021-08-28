//File created by William Stobaugh
/*Intent of application is to create a small scale RPG game
 * used to practice the concepts learned from AP Computer Science*/

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Personal1
{
	//Declare static variables, generally important
	public static int[][] map = new int[6][6];
	public static NPC[] entities = new NPC[10];
	public static NPC player = new NPC();
	public static int turnNumber = 0;
	public static void main(String[] args)
	{
		//Notes: NPC class is in order [controllable?, name, stats[hp, maxhp, str, dex], position[x,y]]
		
		//Notes: Weapon class is in order [Name, Type, req[str, dex, int, fth], damage[str, dex, int, fth] ]
		
		
		//Test weapon
		Weapon flamburg = new Weapon("Flamburg, Greatsword of Prince Herlock", "meele", new int[] {1,1, 1, 1}, new int[] {27, 0, 12,0} );
		player.equipWeapon(flamburg);
		//temporary wall testing 
		map[1][1] = 1; map[2][1] = 1; map[3][1] = 1;
		
		//temporary enemies
		entities[0] = new NPC(false, "Ghost", new int[]{3,3,2,6, 1}, new int[] {2,4});
		entities[2] = new NPC(false, "Goblin", new int[]{10,10,8,1, 4}, new int[] {1,3});
		entities[3] = new NPC(false, "Skeleton", new int[]{4,4,5,3, 3}, new int[] {0,2});
		
		//Player
		entities[1] = player;
		
		
		
		
		//Prompt user to start the game
		System.out.println("N: Hello Adventurer! Your name should be " + player.name + " if I am not mistaken!");
		System.out.println("N: We are in dire need of your help, we must save the princess!");
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you accept? (y/n)");
		String input = sc.nextLine();
		
		//if the player says no then quit
		if (input.equals("n"))
		{
			System.out.println("N: You vile creature, are you also aligned with the dark lord?!");
			System.out.println("GAME OVER");
		}
		
		//if player says yes then start game
		if (input.equals("y"))
		{
			System.out.println("N: Excellent, set fourth and lay blight upon the dark lord!");
			renderFrame();
		}
		
	}
	
	
	
	
	//RECURSIVE FUNCTION WHICH RENDERS FRAME
	public static void renderFrame()
	{
		//Star with 2 blocks of # for outline
		System.out.println("\n###########\n###########");
		
		//Increase turn #
		turnNumber++;
		
		// render map
		renderMap();
		
		// print player stats
		displayStats();
		
		
		//Get player input
				Scanner sc = new Scanner(System.in);
				System.out.println("Input action: ");
				String input = sc.nextLine();
				
				//Run player input
				handlePlayerInput(input);
		
		//End with 2 blocks of # for outline
		System.out.println("\n###########\n###########\n");
		
		
		
		//Make function recursive if player is still alive
		if (!player.dead)
		{
			//Clear screen
			clearScreen();
			
			//Handle enemy movement
			enemyMovement();
			
			//Make recursive
			renderFrame();
		}else
		{
			//Make game over screen
			printDeathScreen();
		}
		
	}
	
	
	
	
	
	// FUNCTION THAT HANDLES ATTACKING BETWEEN 2 ENTITIES.
	public static void attack(NPC attacker, NPC defender, String type)
	{
		//Str will handle the damage done
		//Dex will handle evasion / accuracy
		
		// calculate if hit or not (r > 1)
	float rand = (int)((float)(Math.random() * 100));
    int defenceValue = 1;
    
    
		
    //If hit, then take damage hp - (int)((float)(Math.random() * Str) / (float)(defender.def / defenceValue))
		if (rand > 10)
		{
			int damage = calculateDamage(attacker) / defenceValue;
			defender.takeDamage(damage);
			System.out.println(attacker.name + " dealt " + damage + " to " + defender.name);
			
			
		}
		else
		{
			System.out.println("Missed " + rand);
		}
		
		//If meele attack then also make defender retaliate (DO NOT RECURSE FOR THE LOVE OF GOD)
		if (type.equals("meele"))
		{
			int defDamage = calculateDamage(defender) / defenceValue;
			attacker.takeDamage(defDamage);
			System.out.println(defender.name + " dealt " + defDamage + " to " + attacker.name);
		}
		
		
	}
	
	
	
	
	
	//FUNCTION THAT CALCULATES THE DAMAGE OF AN ENTITIES ATTACK
	public static int calculateDamage(NPC entity)
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
	
	
	
	
	
	
	// FUNCTION THAT DISPLAYS THE STATS SECTION EVERY FRAME
	public static void displayStats()
	{
		
		
		//End with 2 blocks of # for outline
		System.out.println("###########\n###########\n");
		
		//print out stats
		System.out.print("HP: " + player.hp + "/" + player.maxHp + "\n");
		System.out.println("STR: " + player.str + "    DEX: " + player.dex);
		
		//Directions
		System.out.println("\nMove: wasd     Rest: r    Attack: move into enemy     Open Inventory: i");
		

	}
	
	
	
	
	//FUNCTION WHICH RENDERS THE MAP SECTION OF THE FRAME
	public static void renderMap()
	{
		//write logic to print player
		//print each layer and then \n at the end of the
		for (int y = 0; y < map[0].length; y++)
		{
			//for each tile
			for (int x = 0; x < map[0].length; x++)
			{
				
				//Check entity list if they meet there
				boolean occupied = false;
        //Check if entity is on tile
				for (NPC entity : entities)
				{
				//	System.out.print(entity.name);
					//RENDER ORDER > PLAYER > OTHER ENTITIES > TILE
					if (player.xPos == x && player.yPos == y)
					{
						System.out.print("P ");
            occupied = true;
						break;
					}else if (entity != null && entity.xPos == x && entity.yPos == y)
					{
						//if dead then print d otherwide print E
						if (entity.dead)
						{
							System.out.print("d ");
						}
						else
						{
							System.out.print("E ");
						}
						
						occupied = true;
						break;
					}
				}	

        //If no entity on tile then print
				if (!occupied)
        {
          tilePrintHandle(map[x][y]);
        }
					
				

			}
			//For each row skip a line
			System.out.print("\n");
		}
		
		
	}
	
	
	
	
	//FUNCTION WHICH HANDLES THE RENDERING OF TILEMAP
	public static void tilePrintHandle(int value)
	{
		//Switch statement for different possibilities
		switch (value)
		{
		//Empty tile
		case 0:
			System.out.print("  ");
			break;
		
		case 1:
			
			System.out.print("# ");
			break;
		}
	}
	
	
	public static void clearScreen() { 
	//Default clearing
    System.out.print("\033[H\033[2J"); 
    //For eclipse
    System.out.print("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n"); 
    System.out.flush();  
    
    
  }
	
	
	
	
	
	//FUNCTION THAT HANDLES ALL ENEMY MOVEMENT
	public static void enemyMovement()
	{
		int rand;
		//For every enemy have them move in a random direction
		//If walk into player then attack player
		for (NPC entity : entities)
		{
			//declare random number for action
			rand = (int) (Math.random()*5);
			
			//Use random number to decide which path to take
			if (entity != null && !entity.controllable && !entity.dead)
			{
				switch (rand)
				{
					//if zero then rest
					case 0:
						entity.heal((int)(entity.maxHp)/6);
					break;
					
					//if 1 go up
					case 1:
						entity.move("w", map, player);
					break;
					
					//if 2 go down
					case 2:
						entity.move("s", map, player);
					break;
					
					//if 3 go right
					case 3:
						entity.move("d", map, player);
					break;
					
					//if 4 go left
					case 4:
						entity.move("a", map, player);
					break;
					
					//Otherwise just rest (no idea why this would run)
					default:
						entity.heal((int)(entity.maxHp)/6);
					break;
				}
			}
			
		}
	}
	
	
	
	
	
	//FUNCTION WHIH HANDLES PLAYER INPUT
	public static void handlePlayerInput(String input)
	{
		//add switch statement
		switch (input)
		{
		// MOVEMENT
		
		// LEFT MOVEMENT
		case "a":
			// IF NOT WALL AND NOT OUT OF MAP
			if (player.xPos > 0 && map[player.xPos - 1][player.yPos] == 0)
			{
				//If checks to see if there is an entitiy, then it checks if it is dead or not
				if (entityAt(player.xPos - 1, player.yPos) && returnEntityAt(player.xPos - 1, player.yPos).dead == false)
				{
					//Attack enemy
					attack(player, returnEntityAt(player.xPos - 1, player.yPos), "meele");
				}
				else
				{
					player.xPos -= 1;
				}
				
			}
			break;
			
			
			// RIGHT MOVEMENT
		case "d":
			//IF EMPTY MOVE RIGHT
			if (player.xPos < map[0].length - 1 && map[player.xPos + 1][player.yPos] == 0 )
			{
				//Check for entity and if dead
				if (entityAt(player.xPos + 1, player.yPos) && returnEntityAt(player.xPos + 1, player.yPos).dead == false)
				{
					//Attack enemy
					attack(player, returnEntityAt(player.xPos + 1, player.yPos), "meele");
				}
				else
				{
					player.xPos += 1;
				}
				
			}		
			break;
			
			
			// FORWARD MOVEMENT
		case "w":
			//IF EMPTY MOVE FORWARD
			if (player.yPos > 0 && map[player.xPos][player.yPos - 1] == 0)
			{
				if (entityAt(player.xPos, player.yPos - 1) && returnEntityAt(player.xPos, player.yPos - 1).dead == false)
				{
					//Attack enemy
					attack(player, returnEntityAt(player.xPos, player.yPos - 1), "meele");
				}
				else
				{
					player.yPos -= 1;
				}
				
			}
			break;
			
			
			//DOWNWARD MOVEMENT
		case "s":
			
			if (player.yPos < map[0].length - 1 && map[player.xPos][player.yPos + 1] == 0)
			{
				if (entityAt(player.xPos, player.yPos + 1) && returnEntityAt(player.xPos, player.yPos + 1).dead == false)
				{
					//Attack enemy
					attack(player, returnEntityAt(player.xPos, player.yPos + 1), "meele");
				}
				else
				{
					player.yPos += 1;
				}
			}
			break;
			
			
			//REST
		case "r":
			player.heal(2);
			break;
			
			
			//Print inventory
		case "i":
			printInventory();
			break;
			
		}
	}
	
	
	
	public static void printInventory()
	{
		//Print contents of current inventory
		System.out.println("###########\n###########\n");
		
		System.out.println("Weapon: " + player.weapon.name);
		System.out.println("Head: ");
		System.out.println("Body: ");
		System.out.println("Leggings: ");
		System.out.println("Footware: ");
		
		
		//Give player options to do in inventory
		Scanner sc = new Scanner(System.in);
		System.out.println("Close inventory: c      Change weapon: v          Change armour:  b");
		String input = sc.nextLine();
	}
	
	
	
	//FUNCTION THAT RETURNS TRUE OR FALSE, CHECKING FOR A LOCATION FOR AN ENTITY
	public static boolean entityAt(int x, int y)
	{
		for (NPC entity : entities)
		{
			if (entity != null && entity.xPos == x && entity.yPos == y)
			{
				return true;
			}
		}
		return false;
	}
	
	//FUNCTION THAT RETURNS ENTITY AT POSITION
	public static NPC returnEntityAt(int x, int y)
	{
		for (NPC entity : entities)
		{
			if (entity.xPos == x && entity.yPos == y)
			{
				return entity;
			}
		}
		System.out.println("ERROR: ENTITY NOT FOUND, INSTANCE CREATED");
		return new NPC(false, "N/A", new int[]{0,0,0,0,0}, new int[] {0,0});
	}
	
	//Function which prints death screen
	public static void printDeathScreen()
	{
		System.out.println("**    **    ****      **    **");
		System.out.println(" **  **    **  **     **    **");
		System.out.println("   **      **  **      **  ** ");
		System.out.println("   **       ****        **** ");
		
		System.out.print("\n \n \n");
		
		System.out.println("*****       ****     *******    *****");
		System.out.println("**  **       **      **         **  **");
		System.out.println("**  **       **      *******    **  **");
		System.out.println("**  **       **      **         **  **");
		System.out.println("*****       ****     *******    ***** \n \n");
		
		System.out.println("Run lasted " + turnNumber + " turns.");
		
		
	}
}

