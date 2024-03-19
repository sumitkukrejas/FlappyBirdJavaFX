package entities;

import helpers.Asset;
import helpers.GameState;
import helpers.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
public class Pipes implements GameEntity{
    private static double PIPE_GAP = 364;
    private static double PIPE_START_DISTANCE = 200;
    private static double PIPE_WIDTH = 62;
    private static double PIPE_HEIGHT = 2000;
    Asset assetUp = new Asset("images/up_pipe.png", PIPE_WIDTH, PIPE_HEIGHT);
    Asset assetDown = new Asset("images/down_pipe.png", PIPE_WIDTH, PIPE_HEIGHT);
    List<Sprite> spritesUp = new ArrayList<>();
    List<Sprite> spritesDown = new ArrayList<>();
    private double screenWidth;
    private double screenHeight;
    private GraphicsContext gc;
    public Pipes(double screenWidth, double screenHeight , GraphicsContext gc){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gc = gc;
        Sprite[] pipes = createPipes(screenWidth + PIPE_START_DISTANCE);
        GameState.activePipes = pipes;
        spritesUp.add(pipes[0]);
        spritesDown.add(pipes[1]);
    }

    private Sprite[] createPipes(double posX) {
        double usableHeight = screenHeight - PIPE_GAP;
        double random = Math.ceil(Math.random() * usableHeight+1);

        Sprite pipeUp = new Sprite(assetUp, this.gc);
        pipeUp.setVel(-2.5, 0);
        pipeUp.setPos(posX, 206 + random);

        Sprite pipeDown = new Sprite(assetDown, this.gc);
        pipeDown.setVel(-2.5, 0);
        pipeDown.setPos(posX, -1954+random);
        return new Sprite[] {pipeUp, pipeDown};
    }
    @Override
    public void render() {


        if(GameState.gameStarted){
            makePipesInfinite();
            for(int i = 0 ; i< spritesUp.size() ; i++){
                if(spritesUp.get(i).getPosX()>screenWidth/2){
                    GameState.activePipes[0] = spritesUp.get(i);
                    GameState.activePipes[1] = spritesDown.get(i);
                    Rectangle2D rectup = spritesUp.get(i).getSize();
                    break;
                }
            }
        }
        else if(GameState.gameEnded){
            for(Sprite sprite : spritesDown){
                sprite.setVel(0,0);
            }
            for(Sprite sprite: spritesUp){
                sprite.setVel(0,0);
            }
        }
        for(Sprite pipeUp : spritesUp) pipeUp.render();
        for(Sprite pipeDown : spritesDown) pipeDown.render();
    }

    private void makePipesInfinite() {
        if(spritesUp.get(spritesUp.size()-1).getPosX()<screenWidth){
            Sprite[] newPipes = createPipes(spritesDown.get(spritesDown.size()-1).getPosX()+260);
            spritesUp.add(newPipes[0]);
            spritesDown.add(newPipes[1]);
        }
        if(spritesUp.get(0).getPosX()<-PIPE_WIDTH){
            spritesUp.remove(0);
            spritesDown.remove(0);
        }
    }

    @Override
    public void update(long now) {
        if(GameState.gameStarted){
            for(Sprite spriteUp : spritesUp) spriteUp.update();
            for(Sprite spriteDown : spritesDown) spriteDown.update();
        }
    }
}
