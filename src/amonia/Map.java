package amonia;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.HashMap;

public class Map {
    Vec2 pos; 
    Tilemap tilemap;
    HashMap<String, Grid> levels;
    Grid currentLevel; 

 	public Map() throws IOException {
        tilemap = new Tilemap("sprites//map.png", 16);
        levels = Grid.getLevels(tilemap);
        currentLevel = levels.get("level_1");
	}

	public void Draw(Graphics2D g) {
		currentLevel.Draw(g);
	}
}
