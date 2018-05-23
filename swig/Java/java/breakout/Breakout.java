package breakout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Breakout {

    public final int WIDTH = 640;
    public final int HEIGHT = 480;

    public JFrame frame;
    public Panel panel;

    public int score = 0;
    public int highestScore = 0;
    public boolean isJustFinishRestarting = false;

    public Breakout() {        
        frame = new JFrame("Score 0");
        panel = new Panel(this);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Your highest score is " + highestScore,
                        "Exit", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    System.exit(0);
                  
                }
            }

            public void windowOpened(WindowEvent e){
                JOptionPane.showMessageDialog(null, "Use your voice to move the paddle");
            }
        });
        frame.setResizable(false);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.ball.start();
        panel.paddle.start();
    }

    public void score() {
        score++;
        if (score > highestScore) highestScore = score;
        frame.setTitle("Score " + score);
    }

    public void decreaseScore(){
        if (score > 0) score--;
        frame.setTitle("Score " + score);
    }

    public void checkWall() {
        if(panel.wall.isCompletelyBroken()) {
            restart();
        }
    }

    public void restart() {
        panel.restart_game.setText("Restarted in 3");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {}
        panel.restart_game.setText("Restarted in 2");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {}
        panel.restart_game.setText("Restarted in 1");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {}
        
        panel.restart_game.setText("Break the bricks!");

        panel.ball.x = WIDTH / 2 - panel.ball.DIAMETER / 2;
        panel.ball.y = HEIGHT - 50 - panel.ball.DIAMETER;

        panel.ball.going_up = true;
        panel.ball.going_right = false;

        panel.paddle.x = WIDTH / 2 - panel.paddle.WIDTH / 2;
        panel.paddle.direction = 's';

        for(Brick[] bricks_row : panel.wall.bricks) {
            for(Brick brick : bricks_row) {
                brick.destroyed = false;
            }
        }

        score = 0;
        isJustFinishRestarting = true;
        frame.setTitle("Score " + score);
    }
}
