package JavaApp;
import java.util.ArrayList;

//Each map should have a random generation, we can plan on adding different
//tile types on different biome types

public class Map {
  //Dimensions of map
    int width;
    int height;
    int level;
    int[][] tiles;
    ArrayList<RoomCluster> roomClusters;

    public Map()
    {
        this.width = (int)(Math.random() * 30) + 34;
        this.height = (int)(Math.random() * 30 )+ 14;
        this.level = 1;

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

        //After everything is placed lets generate room clusters
        this.roomClusters = new ArrayList<RoomCluster>();
        generateClusters();

        //Generate paths between rooms
        generateDoors();

        //Generate Door
        generateExit();

        //Finally, add walls to the outside of ground tiles connected to nothing
        generateWalls();

    }

    private void generateWalls()
    {
        //iterate through each tile on map  
        for(int i = 0; i < this.tiles.length; i++)
            {
                for (int j = 0; j < this.tiles[i].length; j++)
                {
                    // if tile is place replace adjacent tiles with walls
                    if (this.tiles[i][j] == 0)
                    {
                        // Top
                        if (this.tiles[i][Math.max(0, j - 1)] == 2)
                        {
                            this.tiles[i][Math.max(0, j - 1)] = 1;
                        }
                        // Under
                        if (this.tiles[i][Math.max(0, j + 1)] == 2)
                        {
                            this.tiles[i][Math.max(0, j + 1)] = 1;
                        }
                        // Left
                        if (this.tiles[Math.max(0, i - 1)][j] == 2)
                        {
                            this.tiles[Math.max(0, i - 1)][j] = 1;
                        }
                        // Right
                        if (this.tiles[Math.max(0, i + 1)][j] == 2)
                        {
                            this.tiles[Math.max(0, i + 1)][j] = 1;
                        }
                    }
                }
            } 
    }

    private void generateExit()
    {
        Vector2 selectedTile = new Vector2(0, 0);
        //Select random tile from last room
        for (int i = this.roomClusters.size() - 1; i >= 0; i--)
        {
            if (this.roomClusters.get(i).tiles.size() > 0 && (selectedTile.x == 0 && selectedTile.y == 0))
            {
                selectedTile = this.roomClusters.get(i).getRandomTileFromCluster();
                //After selecting tile then add door
                this.tiles[selectedTile.x][selectedTile.y] = 3;
            }         
        }  

    }

    private void generateDoors()
    {
        //Current implementation
        /* 
        1. Iterate through each cluster
        2. Pick how many doors (1-3), make sure index not beyond cluster length
        3. Draw line between random tile in current to selected cluster
        4. Repeat to forward (dont draw to backward rooms) only.
        */

        //1.

        for (int i = 0; i < this.roomClusters.size() - 1; i++)
        {
            //2.
            //Here we take 3 if there is enough otherwise less
            int maxPosDoors = Math.min(Math.max(1, i - this.roomClusters.size()), 2);

            //Select random number of max
            int doorAmount = (int)(Math.random() * maxPosDoors - 1) + 1;

            //3.
            for (int j = 1; j <= doorAmount; j++)
            {
                if (i + j < this.roomClusters.size())
                {
                    try
                    {
                        addLine(this.roomClusters.get(i).getRandomTileFromCluster(), this.roomClusters.get(i + j).getRandomTileFromCluster());
                    }catch(Exception e)
                    {
                       
                    }
                }
            }
        }
    }

    private void addLine(Vector2 start, Vector2 end)
    {
        //Go in horizontal + vertical in increments of one until end
        while (start.x != end.x || start.y != end.y)
        {
            //Draw on current selected tile
            
            if(start.x > end.x){start.x--;this.tiles[start.x][start.y] = 0;}
            if(start.y > end.y){start.y--;this.tiles[start.x][start.y] = 0;}
            if(start.x < end.x){start.x++;this.tiles[start.x][start.y] = 0;}
            if(start.y < end.y){start.y++;this.tiles[start.x][start.y] = 0;}
        }
    }

    private void generateClusters()
    {

        //search through map to find ground tile
        int clusterIndex = 0;
        for (int x = 0; x < this.tiles.length; x++){
            for (int y = 0; y < this.tiles[x].length; y++){
                //add blank cluster if our index is equal to the size
                if (clusterIndex >= this.roomClusters.size())
                {
                    this.roomClusters.add(new RoomCluster());
                }
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
    }

    //Selects tile within cluster and recursivly adds neighboring tiles
    private void addCluster(int x, int y, int clusterIndex)
    { 

        //Add current tile if not within cluster
        if (!this.roomClusters.get(clusterIndex).containsTile(x, y) && x >= 0 && x < this.width && y >= 0 && y < this.height && this.tiles[x][y] != 2)
        {
            this.tiles[x][y] = 0;
                
            this.roomClusters.get(clusterIndex).addTile(x, y);
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
