package com.vakhnenko.life.draw;

import com.vakhnenko.life.entity.Life;
import com.vakhnenko.life.entity.Point;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

import static com.vakhnenko.life.entity.Constants.*;

public class LifeDraw extends JPanel {
    private Point startingPoint;
    private HashSet<Point> secondPointsSet;

    private Life life;
    private JFrame frame;

    public LifeDraw(Life life) {
        this.life = life;

        frame = new JFrame("Life");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addToFrame(frame.getContentPane());

        frame.setBounds(0, 0, life.getWidth() * POINT_LIFE_SIZE, life.getHeight() * POINT_LIFE_SIZE);
        frame.setVisible(true);
    }

    public void addToFrame(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(this);
    }

    private void drawLife(Graphics g, Color c, int x, int y) {
        int drawX = x * POINT_LIFE_SIZE;
        int drawY = y * POINT_LIFE_SIZE;

        g.setColor(AROUND_COLOR);
        g.fillRect(drawX, drawY, POINT_LIFE_SIZE, POINT_LIFE_SIZE);
        g.setColor(c);
        g.fillRect(drawX + 1, drawY + 1, POINT_LIFE_SIZE - 2, POINT_LIFE_SIZE - 2);
    }

    public void paintComponent(Graphics g) {
        int width = life.getWidth();
        int height = life.getHeight();
        Color color;

        super.paintComponent(g);
        g.setColor(TABLE_COLOR);
        g.fillRect(0, 0, width * POINT_LIFE_SIZE, height * POINT_LIFE_SIZE);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (life.isLive(new Point(x, y))) {
                    color = LIFE_COLOR;
                } else {
                    color = TABLE_COLOR;
                }
                drawLife(g, color, x, y);
            }
        }
        requestFocus();
    }
}

