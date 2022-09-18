import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int FPS = 60;
    final int originalTitleSize = 16;
    final int scale = 3;
    final int tileSize = scale * originalTitleSize;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int maxWordCol = 94;
    final int maxWorldRow = 45;
    final int worldWidth = maxWordCol * tileSize;
    final int worldHeight = maxWorldRow * tileSize;
    public TileManager manager = new TileManager(this);
    KeyHandler handler = new KeyHandler();
    Thread gameThread;
    CollisionChecker cChecker = new CollisionChecker(this);
    Player player = new Player(this, handler);

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        addKeyListener(handler);
        setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime  = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        manager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
