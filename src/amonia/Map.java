package amonia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Map {
	public int columns = 15;
	public int rows = 17;
	public int unit = 16;

	public BufferedImage[] tiles;

	public int[][] map =
	{
		{2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
		{1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 1},
		{3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7},
		{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
	};

	private void readMap() {
		try {
			File map_file = new File(".\\levels\\level1.tl");
			Scanner scanner = new Scanner(map_file);

			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Map() throws IOException {
		File imageMap = new File(".\\sprites\\map.png");
		BufferedImage bufferedImageMap = ImageIO.read(imageMap);

		tiles = new BufferedImage[] {
			bufferedImageMap.getSubimage(0, 0, unit, unit),
			bufferedImageMap.getSubimage(0, unit, unit, unit),
			bufferedImageMap.getSubimage(unit, 0, unit, unit),
			bufferedImageMap.getSubimage(unit, unit, unit, unit),
			bufferedImageMap.getSubimage(unit*2, 0, unit, unit),
			bufferedImageMap.getSubimage(unit*2, unit, unit, unit),
			bufferedImageMap.getSubimage(unit*3, 0, unit, unit),
			bufferedImageMap.getSubimage(unit*3, unit, unit, unit)
		};
	}

	public int getWidth() {
		return columns * unit;
	}

	public int getHeight() {
		return rows * unit;
	}

	public void Draw(Graphics2D g) {
		int emap;

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				emap = map[j][i];
				g.drawImage(this.tiles[emap], i*unit, j*unit, null);
			}
		}
	}
}
