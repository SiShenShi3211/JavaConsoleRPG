package JavaApp;
//File created by William Stobaugh
/*Intent of application is to create a small scale RPG game
 * used to practice the concepts learned from AP Computer Science*/

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Personal1
{
	//Declare static variables, generally important
	//Make map random
	public static Map gameMap = new Map();
	public static int[][] map;
	


	public static NPC[] entities = new NPC[25];
	public static PlayerObject player = new PlayerObject();
	public static int turnNumber = 0;
	public static void main(String[] args)
	{
		//Generate the map
		generateMap();

		
		//Test weapon
		Weapon flamburg = new Weapon("Flamburg, Greatsword of Prince Herlock", "meele", new int[] {1,1, 1, 1}, new int[] {27, 0, 12,0} );
		flamburg.pickUp();
		flamburg.setDescription("Sword of the great prince Herlock, unlikely to have made it's way down here...");
		
		//Test weapon pickup
		Consumable testItem = new Consumable("Health Potion", "Looks like Kool-aid doesn't probably taste like it", "h4", 2);
		testItem.pickUp();
		
		//temporary enemies
		//entities[0] = new NPC(false, "ghost", new int[]{3,3,2,6, 1}, new int[] {12,8});
		//entities[2] = new NPC(false, "Goblin", new int[]{10,10,8,1, 4}, new int[] {0,3});
		//entities[3] = new NPC(false, "Skeleton", new int[]{4,4,5,3, 3}, new int[] {4,10});
		
		//Player
		entities[1] = player;
		
		
		
		//Prompt user to start the game
		ScenePrefabs.printTitleScreen();

		System.out.print("\033[48;2;0;0;0m");
		System.out.println("N: Hello Adventurer! Your name should be " + ScenePrefabs.colorText(ScenePrefabs.BLUE, player.name) + " if I am not mistaken!");
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
		else if (input.equals("y"))
		{
			System.out.println("N: Excellent, set fourth and lay blight upon the dark lord!");
			// wait(3);
			// System.out.print("N: Little did our hero know that they were doomed.");
			// wait(1);
			// System.out.print(".");
			// wait(1);
			// System.out.print(".");
			// wait(1);
			renderFrame();
		} else 
		{
			System.exit(1);
		}
		
	}
	
	
	
	
	//RECURSIVE FUNCTION WHICH RENDERS FRAME
	public static void renderFrame()
	{
		//Top wall
		System.out.println("\n-------------------------------------------------------------");
		
		//Increase turn #
		turnNumber++;
		
		//Print turn # and log
		System.out.println("Turn " + turnNumber + " |||  Floor " + gameMap.level);
		player.printLog();
		
		//print log
		
		//Bottom Wall
		for (int i = 0; i <gameMap.tiles.length; i++ )
		{
			System.out.print("??????");
		}
		System.out.println();
		
		
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
		System.out.println("\n-------------------------------------------------------------\n-------------------------------------------------------------\n");
		
		
		
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
			ScenePrefabs.printDeathScreen();
		}
		
	}
	
	
	
	// FUNCTION THAT DISPLAYS THE STATS SECTION EVERY FRAME
	public static void displayStats()
	{
		
		
		//Padding with top wall
		for (int i = 0; i <gameMap.tiles.length; i++ )
		{
			System.out.print("??????");
		}
		System.out.println();
		
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
			for (int x = 0; x < map.length; x++)
			{
				//set color of console (0,0,0 == r,g,b)
				System.out.print("\033[48;2;0;0;0m");
				//Check entity list if they meet there
				boolean occupied = false;
        //Check if entity is on tile
				for (NPC entity : entities)
				{
				//	System.out.print(entity.name);
					//RENDER ORDER > PLAYER > OTHER ENTITIES > TILE
					if (player.xPos == x && player.yPos == y)
					{
						System.out.print(ScenePrefabs.colorText(ScenePrefabs.BLUE,"P "));
            occupied = true;
						break;
					}else if (entity != null && entity.xPos == x && entity.yPos == y)
					{
						//if dead then print d otherwise print E
						if (entity.dead)
						{
							System.out.print(ScenePrefabs.colorText(ScenePrefabs.BLACK,"d "));
						}
						else
						{
							System.out.print(ScenePrefabs.colorText(ScenePrefabs.RED, (entity.name.substring(0,1) + " ")));;
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
			System.out.print("$ \n");
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
			System.out.print("??? ");
			break;
		
		case 1:
			
			System.out.print(ScenePrefabs.colorText(ScenePrefabs.WHITE, "??? "));
			break;

		case 2:
		System.out.print("  ");
			break;
		
		case 3:
		System.out.print(ScenePrefabs.colorText(ScenePrefabs.YELLOW, "D "));
			break;
		
		case 4:
		System.out.print("L ");
			break;

		default:
		System.out.print("? ");
			break;
		}
	}
	
	
	public static void clearScreen() { 
	//Default clearing
    System.out.print("\033[H\033[2J"); 
    //For eclipse
    System.out.print("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n"); 
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
			if (player.xPos > 0 && map[player.xPos - 1][player.yPos] != 1)
			{

				//If checks to see if there is an entitiy, then it checks if it is dead or not
				if (entityAt(player.xPos - 1, player.yPos) && returnEntityAt(player.xPos - 1, player.yPos).dead == false)
				{
					//Attack enemy
					player.attack(returnEntityAt(player.xPos - 1, player.yPos), player);
				}
				else
				{
					//First check if we are changing to a diff dungeon
					checkDungeonClearTile(map[player.xPos - 1][player.yPos]);
					player.xPos -= 1;
				}
				
			}
			break;
			
			
			// RIGHT MOVEMENT
		case "d":
			//IF EMPTY MOVE RIGHT
			if (player.xPos < map.length - 1 && map[player.xPos + 1][player.yPos] != 1 )
			{

				//Check for entity and if dead
				if (entityAt(player.xPos + 1, player.yPos) && returnEntityAt(player.xPos + 1, player.yPos).dead == false)
				{
					//Attack enemy
					player.attack(returnEntityAt(player.xPos + 1, player.yPos), player);
				}
				else
				{
					//First check if we are changing to a diff dungeon
					checkDungeonClearTile(map[player.xPos + 1][player.yPos]);
					player.xPos += 1;
				}
				
			}		
			break;
			
			
			// FORWARD MOVEMENT
		case "w":
			//IF EMPTY MOVE FORWARD
			if (player.yPos > 0 && map[player.xPos][player.yPos - 1] != 1)
			{

				if (entityAt(player.xPos, player.yPos - 1) && returnEntityAt(player.xPos, player.yPos - 1).dead == false)
				{
					//Attack enemy
					player.attack(returnEntityAt(player.xPos, player.yPos - 1), player);
				}
				else
				{
					//First check if we are changing to a diff dungeon
					checkDungeonClearTile(map[player.xPos][player.yPos - 1]);
					player.yPos -= 1;
				}
				
			}
			break;
			
			
			//DOWNWARD MOVEMENT
		case "s":
			
			if (player.yPos < map[0].length - 1 && map[player.xPos][player.yPos + 1] != 1)
			{


				if (entityAt(player.xPos, player.yPos + 1) && returnEntityAt(player.xPos, player.yPos + 1).dead == false)
				{
					//Attack enemy
					player.attack(returnEntityAt(player.xPos, player.yPos + 1), player);
				}
				else
				{
					//check if we are changing to a diff dungeon
					checkDungeonClearTile(map[player.xPos][player.yPos + 1]);
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

		case "q":
			System.exit(0);
			break;

		case "g": //DEBUG ONLY
			generateMap();
			break;
		
		default:
			player.addToLog("Invalid input", turnNumber);
			
		}
	}

	//If tile is of door type then we want to generate a new dungeon
	public static void checkDungeonClearTile(int type)
	{
		if (type == 3)
		{
			//Generate new dungeon
			generateMap();
		}
	}
	
	public static void generateMap()
	{
		//Generate map
		gameMap.generateDungeon();
		map = gameMap.tiles;

		//Increment level
		gameMap.level++;

		//Spawn in new entities	
		generateEntities();	

		//Spawn player in first room
		Vector2 spawnTile = gameMap.roomClusters.get(0).getRandomTileFromCluster();
		player.xPos = spawnTile.x; player.yPos = spawnTile.y;
	}
	
	public static void generateEntities()
	{
		//Clear entities
		for (int i = 0; i < entities.length; i++)
		{
			entities[i] = new NPC(false, "ghost", new int[]{3,3,2,6, 1}, new int[] {1,8});
		}

		//Add player entity
		entities[0] = player;

		//Add random entities in the map
		String[] nameArray = {"Ghost", "Goblin", "Rat", "Eagle", "Skeleton", "Beast", "Bat", "Corrupt Monk", "Slime", "Witch", "Cyclops"};
		int spawnCurrent = 0;
		for (int enemyIndex = 0; enemyIndex < entities.length; enemyIndex++)
		{
			//Add random amount of enemies in each room
			for (int i = 0; i < gameMap.roomClusters.size(); i++)
			{
				spawnCurrent = 0;
				if (gameMap.roomClusters.get(i).tiles.size() > 10)
				{
					try 
					{
						//For each cluster, spawn monster every 11 tiles
						for (int j = 0; j < gameMap.roomClusters.get(i).tiles.size() / 20 && enemyIndex < entities.length & spawnCurrent <= 3; j++)
						{
							//Spawn monster in random location

							//Generate monster stats
							String monsterName = (nameArray[(int)(Math.random() * (nameArray.length + 1))]);
							int monsterHealth = (int)(Math.random() * (20)) + 3;
							int monsterStrengthDex = (int)(Math.random() * (15)) + 3;
							
							//Spawn monster with random place
							Vector2 monsterPos = gameMap.roomClusters.get(i).getRandomTileFromCluster();
							entities[enemyIndex] = new NPC(false, monsterName, new int[]{monsterHealth,monsterHealth,monsterStrengthDex,monsterStrengthDex, 1}, new int[] {monsterPos.x,monsterPos.y});

							//Increment entity counter so we dont go past limit
							enemyIndex++;
							spawnCurrent++;
						}
					} catch (Exception e)
					{

					}
				}
				
			}
		}
	}
	
	public static void printInventory()
	{
		//Print contents of current inventory
		System.out.println("-------------------------------------------------------------\n-------------------------------------------------------------\n");
		
		System.out.println("(0)Weapon: " + player.weapon.name + " || " + player.weapon.description);
		System.out.println("(1)Head: ");
		System.out.println("(2)Body: ");
		System.out.println("(3)Leggings: ");
		System.out.println("(4)Footware: ");
		System.out.println("(5)Accessory: ");
		System.out.println("-------------------------------------------------------------");
		player.printInventory();
		
		
		//Give player options to do in inventory
		Scanner sc = new Scanner(System.in);
		System.out.println("Close inventory: c    Use Item: v       Change gear: b");
		String input = sc.nextLine();
		
		//Switch case for input possibilities
		switch(input)
		{
		
			//Exit inventory
			case "c":
			
			return;
			
			//Open item inventory
			case "v":
				//player.addToLog("OPENED CONSUMABLE INVENTORY", turnNumber);
				System.out.println("Please select index of item: ");
				int consumeIndex = sc.nextInt();
				player.handleConsume(consumeIndex);
			break;
			
			//Open gear inventory
			case "b":
				//player.addToLog("OPENED GEAR INVENTORY", turnNumber);
				//Ask player to select a number
				System.out.println("Please select index of item: ");
				int itemIndex = sc.nextInt();
				player.handleEquip(itemIndex);
			break;
			
			//If not good input exit function
			default:
				//player.addToLog("FAULTY INPUT FOR INVENTORY", turnNumber);
			break;
		}
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


	//function that handles waiting in seconds
	public static void wait(int time)
	{
		try{Thread.sleep(time * 1000);} 
		catch(InterruptedException ex){Thread.currentThread().interrupt();}
	}
	
	
}

