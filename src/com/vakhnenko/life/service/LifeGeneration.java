package com.vakhnenko.life.service;

import com.vakhnenko.life.entity.Life;
import com.vakhnenko.life.entity.Point;

import java.util.Arrays;
import java.util.HashSet;

import static com.vakhnenko.life.entity.Constants.START_POPULATION;

public class LifeGeneration {
    HashSet<Point> aroundSet = new HashSet<>(Arrays.asList(
            new Point(-1, -1), new Point(1, 1),
            new Point(0, -1), new Point(0, 1),
            new Point(-1, 0), new Point(1, 0),
            new Point(1, -1), new Point(-1, 1)));

    HashSet<Point> newGenerationSet;
    private Life life;


    public LifeGeneration(Life life) {
        this.life = life;
    }

    private Point getStartingPoint() {
        int x = 1 + (int) (Math.random() * ((life.getWidth() - 2)));
        int y = 1 + (int) (Math.random() * ((life.getHeight() - 2)));
        return new Point(x, y);
    }

    private HashSet<Point> getSecondsPoint(Point start) {
        HashSet<Point> result = new HashSet();

        for (Point point : aroundSet) {
            result.add(start.shift(point));
        }
        return result;
    }

    public void population() {
        for (int i = 0; i < START_POPULATION; i++) {
            Point startingPoint = getStartingPoint();
            HashSet<Point> secondPointsSet = getSecondsPoint(startingPoint);

            life.setPointLive(startingPoint);
            life.setPointsLife(secondPointsSet);
        }
    }

    private HashSet<Point> getAroundPoints(Point point) {
        HashSet<Point> resultSet = new HashSet<>();

        for (Point around : aroundSet) {
            Point temp = point.shift(around);
            if (!isNewGenerationPoint(temp)) {
                if ((temp.getX() >= 0 && temp.getX() < life.getWidth()) &&
                        (temp.getY() >= 0 && temp.getY() < life.getHeight())) {
                    resultSet.add(temp);
                }
            }
        }
        return resultSet;
    }

    private boolean isNewGenerationPoint(Point point) {
        for (Point newGeneration : newGenerationSet) {
            if (newGeneration.compareTo(point) == 0) {
                return true;
            }
        }
        return false;
    }

    private int getLifeAmount(HashSet<Point> points) {
        int result = 0;
        for (Point point : points) {
            if (life.isLive(point)) {
                result++;
            }
        }
        return result;
    }

    public void update() {
        int lifeAmount;
        HashSet<Point> aroundSet;
        newGenerationSet = new HashSet<>();

        for (int y = 0; y < life.getHeight(); y++) {
            for (int x = 0; x < life.getWidth(); x++) {
                aroundSet = getAroundPoints(new Point(x, y));
                lifeAmount = getLifeAmount(aroundSet);
                Point newPoint = new Point(x, y);

                if (life.isLive(newPoint)) {
                    if (!isNewGenerationPoint(newPoint) && lifeAmount < 2 || lifeAmount > 3) {
                        life.setPointDead(newPoint);
                    }
                } else {
                    if (lifeAmount == 3) {
                        life.setPointLive(newPoint);
                        newGenerationSet.add(newPoint);
                    }
                }
            }
        }
    }
}
