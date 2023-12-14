// TetrisView.java

package view;

import common.Observer;
import model.TetrisModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisView extends JFrame implements Observer {
    private TetrisModel model;

    private JPanel gamePanel;
    private Timer timer;

    public TetrisView(TetrisModel model) {
        this.model = model;
        this.model.addObserver(this);

        gamePanel = new TetrisGamePanel(model);
        this.add(gamePanel);

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.moveDown();
            }
        });
        timer.start();

        this.setSize(400, 600);
        this.setTitle("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void update() {
        gamePanel.repaint();
    }
}
