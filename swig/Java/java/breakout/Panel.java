package breakout;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Panel extends JPanel {

    public Wall wall;
    public Ball ball;
    public Paddle paddle;

    public String chosenOption = "voice";
    JButton optButton = new JButton("Switch mode");

    JLabel restart_game = new JLabel("Break the bricks!");

    public Panel(Breakout breakout) {
        setFocusable(true);
        
        wall = new Wall(breakout, 5, 8);
        ball = new Ball(breakout);
        paddle = new Paddle(breakout);

        restart_game.setFont(new Font("Serif", Font.BOLD, 20));
        add(restart_game);

        optButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chosenOption.equals("voice")) chosenOption = "keyboard";
                else chosenOption = "voice";
            }
        });
        optButton.setFocusable(false);
        add(optButton);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    paddle.direction = 'l';
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    paddle.direction = 'r';
                }
                if (chosenOption.equals("voice")) breakout.decreaseScore();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                paddle.direction = 's';
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paint(g);

        wall.paint(g2d);
        ball.paint(g2d);
        paddle.paint(g2d);
    }
}
