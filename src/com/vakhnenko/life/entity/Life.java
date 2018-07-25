package com.vakhnenko.life.entity;

import java.util.HashSet;

import static com.vakhnenko.life.entity.Constants.*;

public class Life {
    private int width = MIN_FIELD_WIDTH + (int) (Math.random() * ((MAX_FIELD_WIDTH - MIN_FIELD_WIDTH) + 1));
    private int height = MIN_FIELD_HEIGHT + (int) (Math.random() * ((MAX_FIELD_HEIGHT - MIN_FIELD_HEIGHT) + 1));
    private boolean[][] life = new boolean[width][height];

    public Life() {
    }

    public boolean[][] getLife() {
        return life;
    }

    public boolean[][] getLifeClone() {
        return life.clone();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPointLive(Point point) {
        life[point.getX()][point.getY()] = true;
    }

    public void setPointDead(Point point) {
        life[point.getX()][point.getY()] = false;
    }

    public void setPointsLife(HashSet<Point> points) {
        for (Point point : points) {
            setPointLive(point);
        }
    }

    public boolean isLive(Point point) {
        return life[point.getX()][point.getY()];
    }
}
