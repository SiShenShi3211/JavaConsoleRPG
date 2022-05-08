
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
		this.type = "consumable";
	}

	public int getUses()
	{
		return this.uses;
	}
	
	public void use()
	{
		//Make sure the item has uses left in order to use it
		if (this.uses > 0)
		{
			//Subtract uses
			this.uses--;
			// Make item do effect
			this.doEffect();
		}else
		{
			return;
		}
	}

	private void doEffect()
	{
		//First letter is always the status of consumable
		String effect = this.effect.substring(0, 1);
		int amount = (int)Integer.parseInt(this.effect.substring(1));

		switch (effect)
		{
			case "h":
				Personal1.player.heal(amount);
				break;
		}
	}

}
