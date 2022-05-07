public class ScenePrefabs {
    //This class will be used to store static functions that contain large amounts of text art. 

    // Declaring RESET so that we can reset the color
    public static final String RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String BLACK = "\u001B[30m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";


    //Function which prints death screen
	public static void printTitleScreen()
	{
		System.out.println( YELLOW + " ________  ________           ________  ________  ________        ___  _______   ________ _________  " + RESET);
		System.out.println( YELLOW + "|\\   ____\\|\\   ____\\         |\\   __  \\|\\   __  \\|\\   __  \\      |\\  \\|\\  ___ \\ |\\   ____\\\\___   ___\\ " + RESET);
		System.out.println( YELLOW + "\\ \\  \\___|\\ \\  \\___|_        \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\     \\ \\  \\ \\   __/|\\ \\  \\___\\|___ \\  \\_| " + RESET);
		System.out.println( YELLOW + " \\ \\  \\    \\ \\_____  \\        \\ \\   ____\\ \\   _  _\\ \\  \\\\\\  \\  __ \\ \\  \\ \\  \\_|/_\\ \\  \\       \\ \\  \\  " + RESET);
		System.out.println( YELLOW + "  \\ \\  \\____\\|____|\\  \\        \\ \\  \\___|\\ \\  \\\\  \\\\ \\  \\\\\\  \\|\\  \\\\_\\  \\ \\  \\_|\\ \\ \\  \\____   \\ \\  \\ " + RESET);
		System.out.println( YELLOW + "   \\ \\_______\\____\\_\\  \\        \\ \\__\\    \\ \\__\\\\ _\\\\ \\_______\\ \\________\\ \\_______\\ \\_______\\  \\ \\__\\ " + RESET);
		System.out.println( YELLOW + "    \\|_______|\\_________\\        \\|__|     \\|__|\\|__|\\|_______|\\|________|\\|_______|\\|_______|   \\|__|" + RESET);
		System.out.println( YELLOW + "             \\|_________|                                                                             " + RESET);
		
		
	}
    
    
    
    
    //Function which prints death screen
	public static void printDeathScreen()
	{
        int turnNumber = Personal1.turnNumber;
		System.out.println( RED + "  ___    ___ ________  ___  ___          ________  ___  _______   ________              ________     " + RESET);
		System.out.println( RED + " |\\  \\  /  /|\\   __  \\|\\  \\|\\  \\        |\\   ___ \\|\\  \\|\\  ___ \\ |\\   ___ \\         ___|\\   ____\\    " + RESET);
		System.out.println( RED + " \\ \\  \\/  / | \\  \\|\\  \\ \\  \\\\\\  \\       \\ \\  \\_|\\ \\ \\  \\ \\   __/|\\ \\  \\_|\\ \\       |\\__\\ \\  \\___|    " + RESET);
		System.out.println( RED + "  \\ \\    / / \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\ \\\\ \\ \\  \\ \\  \\_|/_\\ \\  \\ \\\\ \\      \\|__|\\ \\  \\       " + RESET);
		System.out.println( RED + "   \\/  /  /   \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\_\\\\ \\ \\  \\ \\  \\_|\\ \\ \\  \\_\\\\ \\         __\\ \\  \\____  " + RESET);
		System.out.println( RED + " __/  / /      \\ \\_______\\ \\_______\\       \\ \\_______\\ \\__\\ \\_______\\ \\_______\\       |\\__\\ \\_______\\" + RESET);
		System.out.println( RED + "|\\___/ /        \\|_______|\\|_______|        \\|_______|\\|__|\\|_______|\\|_______|       \\|__|\\|_______|" + RESET);
		System.out.println( RED + "\\|___|/                                                                                              " + RESET);
		
		System.out.println( BLUE + "Run lasted " + turnNumber + " turns." + RESET);
		
		
	}


    public static void colorPrintln(String color, String text)
    {
        System.out.println(color + text + RESET);
    }

    public static void colorPrint(String color, String text)
    {
        System.out.print(color + text + RESET);
    }

    public static String colorText(String color, String text)
    {
        return(color + text + RESET);
    }
}
