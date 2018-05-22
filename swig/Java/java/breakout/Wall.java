package breakout;

import java.awt.*;

public class Wall {

    int rows, columns;

    Brick[][] bricks;

    public Wall(Breakout breakout, int rows, int columns) {
        int i, j;
        int brick_width = breakout.WIDTH / columns;
        final int brick_height = 20;

        this.rows = rows;
        this.columns = columns;

        bricks = new Brick[rows][columns];

        for(i = 0; i < rows; i++) {
            Color color = new Color((int) (Math.random() * 0x1000000));

            for(j = 0; j < columns; j++) {
                bricks[i][j] = new Brick();

                bricks[i][j].color = color;

                bricks[i][j].width = brick_width;
                bricks[i][j].height = brick_height;

                bricks[i][j].x = brick_width * j;
                bricks[i][j].y = brick_height * i + 50;
            }
        }
    }

    public boolean isCompletelyBroken() {
        int i, j;

        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                if(!bricks[i][j].destroyed) {
                    return false;
                }
            }
        }

        return true;
    }

    public void paint(Graphics2D g) {
        int i, j;

        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                bricks[i][j].paint(g);
            }
        }
    }
}
