package entities;

import helpers.Asset;
import helpers.GameState;
import helpers.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Title implements GameEntity{
    private static double TITLE_WIDTH = 300;
    private static double TITLE_HEIGHT = 80;
    Asset asset = new Asset("images/title.png", TITLE_WIDTH, TITLE_HEIGHT);

    Sprite title;
    public Title(double screenWidth, double screenHeight, GraphicsContext gc){
        title = new Sprite(asset, gc);
        title.setPosX(screenWidth/2 - TITLE_WIDTH/2);
        title.setPosY(screenHeight/8);
    }


    @Override
    public void render() {
        if(!GameState.gameStarted && !GameState.gameEnded){
            title.render();
        }
    }

    @Override
    public void update(long now) {

    }
}
