
public class Consumable extends Item {

	
	/*Every parameter should have the following 
	 * "First letter of action (H = heal) (D = Damage)"
	 *  "Anything after (substring(1)) should contain an amount
	 * */
	String effect;
	int uses;
	
	//Consumables should also have an omUse() effect
	public Consumable(String name, String desc, String effect, int uses)
	{
		this.name = name;
		this.description = desc;
		this.effect = effect;
		this.uses = uses;
	}
	
	public void use()
	{
		if (this.uses > 0)
		{
			
		}
		//Get rid of this item
	}

}
