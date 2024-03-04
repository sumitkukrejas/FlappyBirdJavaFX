package entities;

import helpers.Asset;
import helpers.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.List;

public class Bird implements GameEntity {

    private double BIRD_WIDTH = 56;
    private double BIRD_HEIGHT = 40;

    private Sprite birdSprite;
    private int currentIndex = 0;
    private long prevNow  = 0L;

    private Asset[] assets = {
            new Asset("images/bird1.png", BIRD_WIDTH, BIRD_HEIGHT),
            new Asset("images/bird2.png", BIRD_WIDTH, BIRD_HEIGHT),
            new Asset("images/bird3.png", BIRD_WIDTH, BIRD_HEIGHT)
    };

    public Bird(double screenWidth, double screenHeight, GraphicsContext gc) {
        birdSprite = new Sprite(assets[currentIndex], gc);
        birdSprite.setPosX(screenWidth / 2 - BIRD_WIDTH / 2);
        birdSprite.setPosY((screenHeight - 112) / 2);
        birdSprite.setVelY(-5);
    }

    @Override
    public void render() {
        birdSprite.renderSprite();
    }

    @Override
    public void update(long now) {
        if(now - prevNow > 100000000L){
            currentIndex++;
            prevNow = now;
        }
        if(currentIndex==3) currentIndex = 0;
        birdSprite.changeImage(assets[currentIndex]);
    }
}
