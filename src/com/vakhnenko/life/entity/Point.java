package com.vakhnenko.life.entity;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point shift(Point point) {
        return new Point(this.x + point.getX(), this.y + point.getY());
    }

    @Override
    public int compareTo(Point o) {
        return (x == o.x && y == o.y) ? 0 : 1;
    }
}
