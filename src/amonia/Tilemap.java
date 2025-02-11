package amonia;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Tilemap {
    private HashMap<Character, BufferedImage> tiles;
    public int unit;
    private String fileName;
    private String code;

    public Tilemap(String fileName, int unit) throws IOException {
        this.code = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.fileName = fileName;
        this.unit = unit;
        this.tiles = getTiles();
    }

    public Tilemap(String fileName, int unit, String code) throws IOException {
        this.code = code;
        this.fileName = fileName;
        this.unit = unit;
        this.tiles = getTiles();
    }

    private HashMap<Character, BufferedImage> getTiles() throws IOException {
		File imageMap = new File(fileName);
		BufferedImage bufferedImageMap = ImageIO.read(imageMap);

        int columns = bufferedImageMap.getWidth() / unit;
        int rows = bufferedImageMap.getHeight() / unit;

        HashMap<Character, BufferedImage> tilemap = new HashMap<Character, BufferedImage>();
        
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Character num = code.charAt(i * rows + j);
                BufferedImage tile = bufferedImageMap.getSubimage(unit*i, unit*j, unit, unit);
                tilemap.put(num, tile);
            }
        }
        
        return tilemap;
	}

    public BufferedImage getTile(Character key) {
        return tiles.get(key);
    }
}