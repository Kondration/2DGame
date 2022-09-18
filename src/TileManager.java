import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWordCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/Map.txt");
    }
    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < gp.maxWorldRow; i++) {
                String line = br.readLine();
                for (int j = 0; j < gp.maxWordCol; j++) {
                    String partOfLine = String.valueOf(line.charAt(j));
                    mapTileNum[j][i] = Integer.parseInt(partOfLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("tiles/grass.jpg"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("tiles/wall.jpg"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("tiles/water.jpg"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("tiles/Tree.jpg"));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("tiles/Stone.jpg"));
            tiles[4].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.maxWordCol; i++) {
            for (int j = 0; j < gp.maxWorldRow; j++) {
                int worldX = i * gp.tileSize;
                int worldY = j * gp.tileSize;
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    g2.drawImage(tiles[mapTileNum[i][j]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
