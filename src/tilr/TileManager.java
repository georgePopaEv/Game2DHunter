package tilr;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        loadMap("../res/maps/world01.txt");
    }

    public void loadMap(String filepath){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int row = 0; row<gp.maxWorldRow; row++){
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col=0;col < gp.maxWorldCol; col++){
                    mapTileNum[row][col] = Integer.parseInt(numbers[col]);
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/tiles/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/tiles/wall.png")));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/tiles/water.png")));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/tiles/earth.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/tiles/tree.png")));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/tiles/sand.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){


        for(int row = 0; row < gp.maxWorldRow; row++){
            for(int col = 0; col < gp.maxWorldCol; col++){

                int tilenum = mapTileNum[row][col];

                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tilenum].image, screenX, screenY,gp.tileSize,gp.tileSize, null);
                }

            }
        }
    }
}
