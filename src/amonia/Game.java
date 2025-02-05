package amonia;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.lang.Thread;

public class Game extends JFrame implements Runnable {
	private Thread thread;
	private boolean isRunning = false;
	private Graphics2D g;

	private Map map;

	public Game() {
		thread = new Thread(this);
		try {
			map = new Map();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Init() {
		setSize(map.getWidth(), map.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Amonia");
		setLocationRelativeTo(null);
		setBackground(Color.black);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
		g = (Graphics2D) getGraphics();
		start();
	}

	public synchronized void start() {
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		map.Draw(g);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.Init();
	}
}