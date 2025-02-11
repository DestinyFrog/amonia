package amonia;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.lang.Thread;

public class Game extends JFrame implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning = false;

    private Map map;
    private Player player;

    public Game() throws IOException {
        thread = new Thread(this);
        map = new Map();
        player = new Player();
    }

    public void Init() {
        Camera camera = new Camera(player);
        Camera.setCurretCamera(camera);

        addKeyListener(this);
        setTitle("Amonia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((int) Camera.currentCamera.transform.size.x, (int) Camera.currentCamera.transform.size.y);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.black);
        setResizable(false);
        setVisible(true);
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
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 120.0; // 60 times per second
        double delta = 0;
        requestFocus();
        while (isRunning) {
            long now = System.nanoTime();
            delta = delta + ((now - lastTime) / ns);
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            render();
        }
    }

    private void update() {
        player.Update();
        Camera.currentCamera.Update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, (int) Camera.currentCamera.transform.size.x, (int) Camera.currentCamera.transform.size.y);

        map.Draw(g);
        player.Draw(g);

        bs.show();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyChar = e.getKeyCode();
        player.input(keyChar);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyChar = e.getKeyCode();
        player.uninput(keyChar);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyCode();
        player.input(keyChar);
    }

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.Init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}