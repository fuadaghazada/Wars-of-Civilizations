package game_object.map;

/**
 *  This class create a tile map using tileset textures for the game levels
 *
 *  @authors:
 *      Fuad Aghazada
 *
 *
 *
 *  @version - 1.00
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class TileMap
{
    //Constants

    //Properties
    private float x;
    private float y;

    private int map[][];

    private int mapWidth;
    private int mapHeight;

    private ArrayList<Tile> tiles;

    /**
     *  Constructs a tile map
     */
    public TileMap (String fileName)
    {
        tiles = new ArrayList<>();
        this.loadMap(fileName);
    }

    private void loadMap(String fileName)
    {
        try
        {
            //for reading the fill which contains the tile types
            @SuppressWarnings("resource")
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            //width & height in string format taken from the text
            String mapWidthString  = bufferedReader.readLine();
            String mapHeightString = bufferedReader.readLine();

            //parsing to integer
            this.mapWidth  = Integer.parseInt(mapWidthString);
            this.mapHeight = Integer.parseInt(mapHeightString);

            //initializing 2D array for map tiles
            map = new int[mapHeight][mapWidth];

            //filling the array with the data from the map file
            for (int i = 0; i < mapHeight; i ++)
            {
                //splitting the tile data in a line
                String [] tokens = bufferedReader.readLine().split(" ");

                for(int k = 0 ; k <  tokens.length; k++)
                {
                    System.out.println(tokens[k]);
                }



                //filling process
                for (int j = 0; j < mapWidth; j++)
                {
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            // Generating the tiles in a list
            for(int i = 0; i < mapHeight; i++)
            {
                for(int j = 0; j < mapWidth; j++)
                {
                    if(map[i][j] != 0)
                    {
                        Tile tile = new Tile( x + j * Tile.getTileSize(), y + i * Tile.getTileSize());
                        tile.setType(map[i][j]);

                        tiles.add(tile);
                    }
                }
            }
        }
        catch (FileNotFoundException exception)
        {
            exception.printStackTrace();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     *  Updates the tile map
     */
    public void update()
    {
        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.get(i).update();
        }
    }

    /**
     *  Draws the tiled map using the data from the file
     */
    public void render(Graphics2D g2)
    {
        g2.setColor(Color.BLACK);

        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.get(i).render(g2);
        }
    }

    //ACCESS & MUTATE

    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getMapWidth()
    {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth)
    {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight()
    {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight)
    {
        this.mapHeight = mapHeight;
    }
}
