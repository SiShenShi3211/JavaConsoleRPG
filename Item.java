public abstract class Item implements ItemInteract {
	//We want this class to be abstract because I don't want any objects to be assigned its type
	//Things every item should have:
	String name;
	String description;
	String type;
	boolean canPickUp;
	
	/*What is an Item:
	 * 
	 * It should be anything the player can pick up from the ground
	 * Should also be anthing the player can use
	 * 
	 * */
	
	
	
	public void setDescription(String text)
	{
		this.description = text;
	}	
	public String getDescription()
	{
		return this.description;
	}
	public String getName()
	{
		return this.name;
	}
	public String getType()
	{
		return this.type;
	}

	
	
	
	//When the pickup function is called then we want to add this object into the player inventory
	public void pickUp() {
		Personal1.player.inventory.add(this);
	}
	
	//TODO: Add a way to get rid of an item

}
