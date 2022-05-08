public class ScenePrefabs {
    //This class will be used to store static functions that contain large amounts of text art. 

    // Declaring RESET + BLACK_BACKGROUND so that we can reset the color
    public static final String RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String BLACK = "\u001B[30m";
    public static final String BLACK_BACKGROUND = "\033[48;2;0;0;0m";  // BLACK
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\033[0;37m";   // WHITE


    //Function which prints death screen
	public static void printTitleScreen()
	{
		System.out.println( YELLOW + BLACK_BACKGROUND + " ________  ________           ________  ________  ________        ___  _______   ________ _________  " + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + "|\\   ____\\|\\   ____\\         |\\   __  \\|\\   __  \\|\\   __  \\      |\\  \\|\\  ___ \\ |\\   ____\\\\___   ___\\ " + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + "\\ \\  \\___|\\ \\  \\___|_        \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\     \\ \\  \\ \\   __/|\\ \\  \\___\\|___ \\  \\_| " + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + " \\ \\  \\    \\ \\_____  \\        \\ \\   ____\\ \\   _  _\\ \\  \\\\\\  \\  __ \\ \\  \\ \\  \\_|/_\\ \\  \\       \\ \\  \\  " + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + "  \\ \\  \\____\\|____|\\  \\        \\ \\  \\___|\\ \\  \\\\  \\\\ \\  \\\\\\  \\|\\  \\\\_\\  \\ \\  \\_|\\ \\ \\  \\____   \\ \\  \\ " + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + "   \\ \\_______\\____\\_\\  \\        \\ \\__\\    \\ \\__\\\\ _\\\\ \\_______\\ \\________\\ \\_______\\ \\_______\\  \\ \\__\\ " + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + "    \\|_______|\\_________\\        \\|__|     \\|__|\\|__|\\|_______|\\|________|\\|_______|\\|_______|   \\|__|" + RESET + BLACK_BACKGROUND);
		System.out.println( YELLOW + BLACK_BACKGROUND + "             \\|_________|                                                                             " + RESET + BLACK_BACKGROUND);
		
		
	}
    
    
    
    
    //Function which prints death screen
	public static void printDeathScreen()
	{
        int turnNumber = Personal1.turnNumber;
		System.out.println( RED + BLACK_BACKGROUND + "  ___    ___ ________  ___  ___          ________  ___  _______   ________              ________     " + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + " |\\  \\  /  /|\\   __  \\|\\  \\|\\  \\        |\\   ___ \\|\\  \\|\\  ___ \\ |\\   ___ \\         ___|\\   ____\\    " + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + " \\ \\  \\/  / | \\  \\|\\  \\ \\  \\\\\\  \\       \\ \\  \\_|\\ \\ \\  \\ \\   __/|\\ \\  \\_|\\ \\       |\\__\\ \\  \\___|    " + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + "  \\ \\    / / \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\ \\\\ \\ \\  \\ \\  \\_|/_\\ \\  \\ \\\\ \\      \\|__|\\ \\  \\       " + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + "   \\/  /  /   \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\_\\\\ \\ \\  \\ \\  \\_|\\ \\ \\  \\_\\\\ \\         __\\ \\  \\____  " + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + " __/  / /      \\ \\_______\\ \\_______\\       \\ \\_______\\ \\__\\ \\_______\\ \\_______\\       |\\__\\ \\_______\\" + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + "|\\___/ /        \\|_______|\\|_______|        \\|_______|\\|__|\\|_______|\\|_______|       \\|__|\\|_______|" + RESET + BLACK_BACKGROUND);
		System.out.println( RED + BLACK_BACKGROUND + "\\|___|/                                                                                              " + RESET + BLACK_BACKGROUND);
		
		System.out.println( BLUE + BLACK_BACKGROUND + "Run lasted " + turnNumber + " turns." + RESET + BLACK_BACKGROUND);
		
		
	}

    


    public static void colorPrintln(String color, String text)
    {
        System.out.println(color + BLACK_BACKGROUND + text + RESET + BLACK_BACKGROUND);
    }

    public static void colorPrint(String color, String text)
    {
        System.out.print(color + BLACK_BACKGROUND + text + RESET + BLACK_BACKGROUND);
    }

    public static String colorText(String color, String text)
    {
        return(color + BLACK_BACKGROUND + text + RESET + BLACK_BACKGROUND);
    }
}
