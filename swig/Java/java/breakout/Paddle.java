package breakout;

import java.awt.*;

public class Paddle extends Thread {

    final int WIDTH = 100;
    final int HEIGHT = 10;

    Breakout breakout;

    int x, y;

    public char direction = 's';

    public Paddle(Breakout breakout) {
        this.breakout = breakout;

        x = breakout.WIDTH / 2 - WIDTH / 2;
        y = breakout.HEIGHT - 50;
    }

    @Override
    public void run() {
        while(true) {
            move(direction);
            breakout.panel.repaint();
            try {
                sleep(2);
            }
            catch(InterruptedException ex) {}
        }
    }

    public void move(char direction) {
        if(direction == 'l' && x > 0) {
            x--;
        }
        if(direction == 'r' && x + WIDTH < breakout.WIDTH) {
            x++;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
