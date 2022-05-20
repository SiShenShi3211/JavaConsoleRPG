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

        //Sort tiles from High to low X
        selectionSortTilesByX();
        
    }


    private void selectionSortTilesByX()
    {
        int n = this.tiles.size();
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
            {
                if (this.tiles.get(j).x < this.tiles.get(minIndex).x)
                {
                    minIndex = j;
                }
            }
                    
 
            // Swap the found minimum element with the first
            // element
            Vector2 temp = this.tiles.get(minIndex);
            this.tiles.set(minIndex, this.tiles.get(i));
            this.tiles.set(i, temp);
        }
    }

    public Vector2 getRandomTileFromCluster()
    {
        return this.tiles.get((int)(Math.random() * this.tiles.size()));
    }
}
