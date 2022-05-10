package JavaApp;

public class Room {
    //A room should have a vector 2 center
    Vector2 position;
    Rectangle[] rectangles;
    //A room should have a series of rectangles to make a shape
    public Room(int maxX, int maxY)
    {

        //X = center + offset which wont cause out of bounds within range of 6
        int x = (maxX/2) + ((int)(Math.random()  * (maxX - 9)) - (maxX/2));
        int y = (maxY/2) + ((int)(Math.random()  * (maxY - 9)) - (maxY/2));
        this.position = new Vector2(x, y);

        //add rectangles to make interesting areas
        this.rectangles = new Rectangle[(int)(Math.random() * 3) + 1];
        for (int i = 0; i < this.rectangles.length; i++)
        {
            this.rectangles[i] = new Rectangle();
        }
        
    }
}
