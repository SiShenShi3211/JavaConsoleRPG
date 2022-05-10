package JavaApp;
import java.util.ArrayList;

//Each map should have a random generation, we can plan on adding different
//tile types on different biome types

public class Map {
  //Dimensions of map
    int width;
    int height;
    int[][] tiles;
    RoomCluster[] roomClusters;

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
        this.width = (int)(Math.random() * 30) + 34;
        this.height = (int)(Math.random() * 30 )+ 14;
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

        //Place rectangles of each room
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

        //DEBUG: for right now just replace the center of room with a wall so we can see where each room center spawns
        for (int i = 0; i < rooms.length; i++)
        {
            this.tiles[rooms[i].position.x][rooms[i].position.y] = 1;
            //Personal1.player.addToLog("" + this.tiles[0][0], Personal1.turnNumber);
        }


        //After everything is placed lets generate room clusters
        this.roomClusters = new RoomCluster[rooms.length];
        generateClusters();

    }


    private void generateClusters()
    {
        //Init each cluster
        for(int i = 0 ; i < this.roomClusters.length; i++)
        {
            this.roomClusters[i] = new RoomCluster();
        }    

        //search through map to find ground tile
        int clusterIndex = 0;
        for (int x = 0; x < this.tiles.length; x++){
            for (int y = 0; y < this.tiles[x].length; y++){
                //if tile is not nothing
                if (this.tiles[x][y] != 2)
                {
                    //Check this if after because we dont want o waste computing power on titles that are blank
                    if (!tileInCluster(x,y))
                    {
                        //Function which adds a new cluster
                        addCluster(x, y, clusterIndex);
                        clusterIndex++;
                    }
                }

            }
        }

        //After clusters are generated log the length in console so we can see if its right
        Personal1.player.addToLog("cluster 1 size: " + this.roomClusters[0].tiles.size(), Personal1.turnNumber);

        //debug on first cluster
        for (Vector2 tile : this.roomClusters[0].tiles)
        {
            this.tiles[tile.x][tile.y] = 1;
        }
    }

    //Selects tile within cluster and recursivly adds neighboring tiles
    private void addCluster(int x, int y, int clusterIndex)
    {
        if (clusterIndex >= this.roomClusters.length)
        {
            Personal1.player.addToLog("cluster index: " + clusterIndex + " exceedes limit of " + this.roomClusters.length, Personal1.turnNumber);
            return;
        }
        //Add current tile if not within cluster
        if (!this.roomClusters[clusterIndex].containsTile(x, y) && x > 0 && x < this.width && y > 0 && y < this.height && this.tiles[x][y] != 2)
        {
            this.roomClusters[clusterIndex].addTile(x, y);
        }else {
            //Return if we see an existing tile so we dont go inf
            return;
        }

        //do this same function on every neighboring tile
        
        //top
        Vector2 topTile = new Vector2(x, Math.max(0, y - 1));
        addCluster(topTile.x, topTile.y, clusterIndex);

        //below
        Vector2 bottomTile = new Vector2(x, Math.min(this.height, y+1));
        addCluster(bottomTile.x, bottomTile.y, clusterIndex);

        Vector2 leftTile = new Vector2(Math.max(0, x - 1), y);
        addCluster(leftTile.x, leftTile.y, clusterIndex);

        Vector2 rightTile = new Vector2(Math.min(this.width, x + 1), y);
        addCluster(rightTile.x, rightTile.y, clusterIndex);
    }
    //Function which checks if tile is already in an existing cluster
    private boolean tileInCluster(int x, int y)
    {
        for (RoomCluster cluster : this.roomClusters)
        {
            if (cluster.containsTile(x,y))
            {
                return true;
            }
        }
        return false;
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

    /*
    How do we make doors for coonected rooms to outside rooms?
    
    Lets focus on merged rooms as a single entity each:
        - Search through map to find ground tile
        - Expand recursively on each connected tile, add coords to a RoomCluster class
        -Elminate each duplicate tile and we have made several merged rooms as one entity

        -Make lookup function for inCluster so we dont repeat this whole function and get a whole
        bunch of duplicate cluters
    
    If we have multiple entities we should focus on making sure they have a connection
        - select tiles that are closest to eachother from NEIGHBORING clusters (Dont want a web here)
        -After we select two tiles, put that in a function to draw a line to eachother
    
    */
    
}
