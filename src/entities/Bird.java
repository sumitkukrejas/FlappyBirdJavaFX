package entities;

import helpers.Asset;
import helpers.GameState;
import helpers.Sprite;
import javafx.scene.canvas.GraphicsContext;
public class Bird implements GameEntity {

    private double BIRD_WIDTH = 56;
    private double BIRD_HEIGHT = 40;
    private Sprite bird;
    private int currentIndex = 0;
    private long prevNow  = 0L;
    private float termianlVel = 10;
    private double screenHeight;
    private double screenWidth;
    private Asset[] assets = {
            new Asset("images/bird1.png", BIRD_WIDTH, BIRD_HEIGHT),
            new Asset("images/bird2.png", BIRD_WIDTH, BIRD_HEIGHT),
            new Asset("images/bird3.png", BIRD_WIDTH, BIRD_HEIGHT)
    };

    public Bird(double screenWidth, double screenHeight, GraphicsContext gc) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
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
        if(!GameState.gameStarted && !GameState.gameEnded){
            //bird floating
            bird.setVel(0,Math.sin((double)now/90000000));
        }
        else if(GameState.gameEnded){
            //game stops
            birdFallDown();
        }
        else if(GameState.gameStarted){
            updateBirdPlaying();
            if(bird.getPosY() + BIRD_HEIGHT > screenHeight-135 ||GameState.activePipes[0].intersects(this.bird) || GameState.activePipes[1].intersects(this.bird)){
                GameState.gameStarted = false;
                GameState.gameEnded = true;
            }
        }
        updateAsset(now);
    }

    private void birdFallDown() {
        bird.setVelY(8);
        if(bird.getPosY()>=screenHeight-BIRD_HEIGHT-112){
            bird.setVel(0,0);
        }
    }

    private void updateBirdPlaying() {
        double vel = bird.getVelY();

        if(vel>termianlVel)
            bird.setVelY(vel+ .2);
        else
            bird.setVelY(vel+ .44);
    }

    private void updateAsset(long now) {
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
