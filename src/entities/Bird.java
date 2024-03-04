package entities;

import helpers.Asset;
import helpers.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bird implements GameEntity {

    private double BIRD_WIDTH = 56;
    private double BIRD_HEIGHT = 40;
    private Sprite bird;
    private int currentIndex = 0;
    private long prevNow  = 0L;

    private Asset[] assets = {
            new Asset("images/bird1.png", BIRD_WIDTH, BIRD_HEIGHT),
            new Asset("images/bird2.png", BIRD_WIDTH, BIRD_HEIGHT),
            new Asset("images/bird3.png", BIRD_WIDTH, BIRD_HEIGHT)
    };

    public Bird(double screenWidth, double screenHeight, GraphicsContext gc) {
        bird = new Sprite(assets[currentIndex], gc);
        bird.setPosX(screenWidth / 2 - BIRD_WIDTH / 2);
        bird.setPosY((screenHeight - 112) / 2);
        bird.setVel(0,0);
    }

    @Override
    public void render() {
        bird.render();
    }

    @Override
    public void update(long now) {
        if(now - prevNow > 90000000){
            currentIndex++;
            prevNow = now;
        }
        if(currentIndex==3) currentIndex = 0;
        bird.changeImage(assets[currentIndex]);
        bird.update();
    }
    public void jumpHandler(){
        bird.setVelY(-8);
    }
}
