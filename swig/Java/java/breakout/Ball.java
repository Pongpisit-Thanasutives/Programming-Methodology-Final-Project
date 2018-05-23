package breakout;

import java.awt.*;

public class Ball extends Thread {

    final int DIAMETER = 20;

    Breakout breakout;

    int x, y;

    boolean going_up = true;
    boolean going_right = false;

    public Ball(Breakout breakout) {
        this.breakout = breakout;

        x = breakout.WIDTH / 2 - DIAMETER / 2;
        y = breakout.HEIGHT - 50 - DIAMETER;
    }

    @Override
    public void run() {
        while(true) {
            move();
            breakout.panel.repaint();
            try {
                sleep(5);
            }
            catch(InterruptedException ex) {}
        }
    }

    public void move() {
        if(going_right) {
            if(x + DIAMETER >= breakout.WIDTH) {
                going_right = false;
            }
        }
        else {
            if(x <= 0) {
                going_right = true;
            }
        }

        if(going_up) {
            if(y == 0) {
                going_up = false;
            }
            else {
                for(Brick[] bricks_row : breakout.panel.wall.bricks) {
                    for(Brick brick : bricks_row) {
                        if(getBounds().intersects(brick.getBounds())) {
                            going_up = false;
                            brick.destroyed = true;
                            breakout.score();
                            breakout.checkWall();
                        }
                    }
                }
            }
        }
        else {
            if(y + DIAMETER == breakout.HEIGHT) {
                breakout.restart();
            }
            else {
                if(getBounds().intersects(breakout.panel.paddle.getBounds())) {
                    going_up = true;
                }
                else {
                    for(Brick[] bricks_row : breakout.panel.wall.bricks) {
                        for(Brick brick : bricks_row) {
                            if(getBounds().intersects(brick.getBounds())) {
                                going_up = true;
                                brick.destroyed = true;
                                breakout.score();
                                breakout.checkWall();
                            }
                        }
                    }
                }
            }
        }

        if(going_right) x++;
        else x--;

        if(going_up) y--;
        else y++;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
