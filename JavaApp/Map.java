package JavaApp;
import java.util.ArrayList;

//Each map should have a random generation, we can plan on adding different
//tile types on different biome types

public class Map {
  //Dimensions of map
    int width;
    int height;
    int[][] tiles;

    public Map()
    {
        this.width = (int)(Math.random() * 30) + 34;
        this.height = (int)(Math.random() * 30 )+ 14;

        //Operate knowing I screwed up the XY for the map way earlier
        this.tiles = new int[this.width][this.height];
    }

    /*KEY
    0: ground
    1: wall
    2: nothing (blank)
     */
    public void generateDungeon() 
    { 

        //Every time we generate a dungeon clear the old everything
        this.tiles = new int[this.width][this.height];
        //every tile should be empty
        for(int i = 0; i < this.tiles.length; i++)
        {
            for (int j = 0; j < this.tiles[i].length; j++)
            {
                this.tiles[i][j] = 2;
            }
        }


        //Get # of rooms and where their middle should be


        //# of rooms should scale on the size
        int roomCount = Math.max(((this.width * this.height) / 160), 3);

        //Make a room class, should be made of different rectanges
        Room[] rooms = new Room[roomCount];

        //Assign values to room
        for (int i = 0; i < rooms.length; i++)
        {
           rooms[i] = new Room(this.width, this.height);
        }

        //DEBUG: for right now just replace the center of room with a wall
        for (int i = 0; i < rooms.length; i++)
        {
            this.tiles[rooms[i].position.x][rooms[i].position.y] = 1;
            //Personal1.player.addToLog("" + this.tiles[0][0], Personal1.turnNumber);
        }

        //DEBUG Place point of each rectangle
        for (int i = 0; i < rooms.length; i++)
        {
            for (int j = 0; j < rooms[i].rectangles.length; j++)
            {
                int rectX = Math.max((rooms[i].position.x + rooms[i].rectangles[j].position.x), 0);
                int rectY = Math.max((rooms[i].position.y + rooms[i].rectangles[j].position.y), 0);

                //This functions adds a rect of ground to the map
                addRect(rectX, rectY, rooms[i].rectangles[j].width, rooms[i].rectangles[j].height);
                //this.tiles[rectX][rectY] = 2;
            }
        }
    }

    //Add ground to map with rect
    private void addRect(int x, int y, int width, int height)
    {
        //i = x
        for (int i = 0; i < width; i++)
        {
            //j = y
            for (int j = 0; j < height; j++)
            {
                //make sure room tile in bounds
                if (x + i >= 0 && x + i < this.width && y + j >= 0 && y + j < this.height)
                {
                    this.tiles[x + i][y + j] = 0;
                }
                
            }
        }
    }
    
}
