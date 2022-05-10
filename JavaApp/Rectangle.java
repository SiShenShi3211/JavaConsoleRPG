package JavaApp;

public class Rectangle {
    Vector2 position; //Position offset of top left corner
    int width;
    int height;

    public Rectangle()
    {
        //random offset from center, has to be on the left and right of point
        int x = ((int)(-(Math.random() * 4) - 3));
        int y = ((int)(-(Math.random() * 4) - 3));


        //width and height = touching center + (>||<) same length opposite
        this.width = -(x * 2) + ((int)(Math.random() * 5) - 2);
        this.height = -(y * 2) + ((int)(Math.random() * 5) - 2);

        this.position = new Vector2(x, y);


        //Random size, should be big enough to touch center
        //this.width = 
    }
}
