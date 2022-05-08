
public class Weapon extends Item {
	//Weapon variables
	int[] req; //Requirements go in order : Str -> dex -> int -> faith
	int[] damageType; //Damages go in order : physical, ranged, magical, faith
	
	
	
	//Sub variables not put in as direct variables
	int strReq; int dexReq; int intReq; int faithReq;
	int pDamage; int rDamage; int mDamage; int fDamage;
	
	public Weapon(String name, String type, int[] req, int[] damageType)
	{
		//Basic attributes
		this.name = name; this.type = type;
		this.canPickUp = true;
		this.description = "";
		this.type = "weapon";
		
		//State attributes
		this.pDamage = damageType[0]; this.rDamage = damageType[1]; 
		this.mDamage = damageType[2]; this.fDamage = damageType[3];  
		
		//Requirement attributes
		this.strReq = req[0]; this.dexReq = req[1];
		this.intReq = req[2]; this.faithReq = req[3];
		
		
	}
	
	
	//When the player interacts with this we need to add it to their inventory
	public void mapInteract()
	{
		//Add implementation
	}
	
	
	
}
