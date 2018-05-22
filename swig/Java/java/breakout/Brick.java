package breakout;

import java.awt.*;

public class Brick {

    int width, height;
    int x, y;

    boolean destroyed = false;

    Color color;

    public void paint(Graphics2D g) {
        if(!destroyed) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        if(destroyed) {
            return new Rectangle(-1, -1, 0, 0);
        }
        return new Rectangle(x, y, width, height);
    }
}
