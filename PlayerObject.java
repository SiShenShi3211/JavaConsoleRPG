import java.util.ArrayList;
import java.util.Scanner;
public class PlayerObject extends NPC {
	
	public ArrayList<Item> inventory;
	
	//The default NPC object is what our player can do
	public PlayerObject()
	{
		super();
		this.inventory = new ArrayList<Item>();
		
		//Adding these weapons to test the inventory system
		this.inventory.add(new Weapon("Rusted Sword", "meele", new int[] {1,1, 1, 1}, new int[] {2, 0, 0,0} ));
	}
	
	
	//TODO: move player functions here from NPC class
	//well be using a log to make sure we can print text on multiple frames
			public void addToLog(String text, Integer time) {
				
				//Firstly add what we have here
				this.log.add(text);
				this.logIndexs.add(time);
				
				//Next remove any logs that are go outside max count
				Integer removeCount = this.log.size() - logMaxCount;
				if (removeCount > 0)
				{
					//Since more logs than max
					for (int i = this.log.size() - 1; i >= 0; i--)
					{
						if (i <= removeCount)
						{
							//remove from both arraylists
							this.log.remove(i);
							this.logIndexs.remove(i);
							
						}
					}
				}
				
			}
			
			public void printLog()
			{
				//Go through each item and list its description + age
				for (int i = 0; i < this.log.size() && i < this.logIndexs.size(); i++)
				{
					Integer age = (Personal1.turnNumber - this.logIndexs.get(i));
					System.out.println(this.log.get(i) + " |--| " + age);
				}
			}
			
			public void printInventory() {
				
				for (int i = 0; i < this.inventory.size(); i++) {
					String typeName = this.inventory.get(i).getType();


					//Give each item type a different color
					switch (this.inventory.get(i).getType())
					{
						case "weapon":
							typeName = ScenePrefabs.colorText(ScenePrefabs.RED, ("weapon"));
							break;
						case "armour":
							typeName = ScenePrefabs.colorText(ScenePrefabs.BLUE, ("armour"));
							break;
						case "consumable":
							Consumable consumable = (Consumable)this.inventory.get(i);
							typeName = ScenePrefabs.colorText(ScenePrefabs.GREEN, ("consumable (" + (consumable.getUses()) + ")"));
							break;

					}


					System.out.println(ScenePrefabs.colorText(ScenePrefabs.YELLOW, (i + ": "))+ this.inventory.get(i).getName() + " $ " + typeName);
				}
			}

			//Function to equip a new weapon
			public void handleEquip(int index)
			{
				//If out of bounds warn player and return
				if (index < 0 || index > this.inventory.size() - 1)
				{
					this.addToLog("Invalid Gear Index", Personal1.turnNumber);
					return;
				}

				Item selectedItem = this.inventory.get(index);

				//check for type of item, must be "weapon"
				if (!selectedItem.type.equals("weapon") && !selectedItem.type.equals("armour"))
				{
					this.addToLog("Selected item is not equipable", Personal1.turnNumber);
					return;
				}

				//At this point we can replace the item we are holding with whatever

				//Weapon implementation
				if (selectedItem.type.equals("weapon"))
				{
					//if we are currently holding fists just equip the weapon
					if (this.weapon.name.equals("fists"))
					{
						this.equipWeapon((Weapon) selectedItem);
						this.inventory.remove(index);
						return;
					}

					//otherwise replace the weapon we have
					this.inventory.set(index, this.weapon);
					this.weapon = (Weapon) selectedItem;
					return;

				}

				//Armour implementation
			}


			public void handleConsume(int index)
			{
				//If out of bounds warn player and return
				if (index < 0 || index > this.inventory.size() - 1)
				{
					this.addToLog("Invalid Item Index", Personal1.turnNumber);
					return;
				}

				Item selectedItem = this.inventory.get(index);

				//check for type of item, must be "weapon"
				if (!selectedItem.type.equals("consumable"))
				{
					this.addToLog("Selected item is not a consumable", Personal1.turnNumber);
					return;
				}

				//If all checks are ok then use item
				Consumable consumable = (Consumable) selectedItem;
				Scanner sc = new Scanner(System.in);
				String input;

				if (consumable.getUses() > 0)
				{
						//Make sure the player wants it
					System.out.println("Are you sure you want to use");
					System.out.println(ScenePrefabs.colorText(ScenePrefabs.BLUE, consumable.name + " || " + consumable.description));
					System.out.print("(Y/n): ");

					input = sc.nextLine();

					//Use if player is certain
					if (input.equals("y") || input.equals("Y") || input.equals(""))
						consumable.use();
				}else
				{

					//if no more uses offer to remove the item from the player's inventory
					System.out.println(ScenePrefabs.colorText(ScenePrefabs.RED, consumable.name + " has no more uses!"));
					System.out.println("Drop item?");
					System.out.print("(Y/n): ");

					input = sc.nextLine();

					//Use if player is certain
					if (input.equals("y") || input.equals("Y") || input.equals(""))
						Personal1.player.inventory.remove(index);
				}
				
			}
}
