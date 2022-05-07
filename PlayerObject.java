import java.util.ArrayList;
public class PlayerObject extends NPC {
	
	public ArrayList<Item> inventory;
	
	//The default NPC object is what our player can do
	public PlayerObject()
	{
		super();
		this.inventory = new ArrayList<Item>();
		
		//Adding these weapons to test the inventory system
		this.inventory.add(new Weapon("Test Weapon", "meele", new int[] {1,1, 1, 1}, new int[] {5, 0, 0,0} ));
		this.inventory.add(new Weapon("Test Weapon", "meele", new int[] {1,1, 1, 1}, new int[] {5, 0, 0,0} ));
		this.inventory.add(new Weapon("Test Weapon", "meele", new int[] {1,1, 1, 1}, new int[] {5, 0, 0,0} ));
		this.inventory.add(new Weapon("Test Weapon", "meele", new int[] {1,1, 1, 1}, new int[] {5, 0, 0,0} ));
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
					System.out.println(i + ": " + this.inventory.get(i).getName());
				}
			}
}
