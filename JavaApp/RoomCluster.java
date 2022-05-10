package JavaApp;

import java.util.ArrayList;
public class RoomCluster {
    //Will contain the tiles inside of a cluster
    ArrayList<Vector2> tiles;

    public RoomCluster()
    {
        this.tiles = new ArrayList<Vector2>();
    }

    //Checks to see if a vector 2 is contained in a cluster
    public boolean containsTile(int tileX, int tileY)
    {
        for (Vector2 tile : this.tiles)
        {
            if (tile.x == tileX && tile.y == tileY){
                return true;
            }
        }
        return false; 
    }

    public void addTile(int x, int y)
    {
        this.tiles.add(new Vector2(x, y));
    }
}
