package amonia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Grid {
    Transform transform;
    public String fileName;
    public String map;
    private Tilemap tilemap;

    public int columns = 0;
    public int rows = 0;

    public Grid(String fileName, Tilemap tilemap) throws IOException {
        this.fileName = fileName;
        this.tilemap = tilemap;
        this.map = readMap();
        int width = tilemap.unit * columns;
        int height = tilemap.unit * rows;
        this.transform = new Transform(0, 0, width, height);
    }

    private String readMap() throws IOException {
        FileReader map_file = new FileReader(this.fileName);
        BufferedReader reader = new BufferedReader(map_file);

        String text = "";
        String line = reader.readLine();

        columns = line.length();

        while (line != null) {
            rows++;
            text = text + line;
            line = reader.readLine();
        }

        reader.close();
        return text;
    }

    public static HashMap<String, Grid> getLevels(Tilemap tilemap) throws IOException {
        HashMap<String, Grid> levels = new HashMap<>();
        for (int i = 1; i <= 1; i++) {
            String levelName = "level_" + i;
            String levelPath = "levels//level_" + i + ".level";
            Grid levelGrid = new Grid(levelPath, tilemap);
            levels.put(levelName, levelGrid);
        }
        return levels;
    }

    public void Draw(Graphics2D g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Character emap = map.charAt(i * columns + j);
                if (emap != ' ') {
                    BufferedImage tile = tilemap.getTile(emap);
                    Vec2 normalized = transform.Normalize();
                    g.drawImage(tile, (int) Math.round(normalized.x + j * tilemap.unit), (int) Math.round(normalized.y + i * tilemap.unit), null);
                }
            }
        }
    }
}