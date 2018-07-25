package com.vakhnenko.life.service;

import com.vakhnenko.life.draw.LifeDraw;
import com.vakhnenko.life.entity.Life;

import static com.vakhnenko.life.entity.Constants.SLEEP_TIME;

public class LifeService {
    private Life life;
    private LifeDraw lifeDraw;
    private LifeGeneration lifeGeneration;

    public LifeService() {
        life = new Life();
        lifeDraw = new LifeDraw(life);
        lifeGeneration = new LifeGeneration(life);
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        lifeGeneration.population();

        while (true) {
            lifeGeneration.update();
            lifeDraw.repaint();
            sleep();
        }
    }
}
