package entities;

import helpers.Asset;
import helpers.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Background implements GameEntity {
    private static double WIDTH = 288;
    private static double HEIGHT = 512;
    Asset asset = new Asset("images/background.png", WIDTH , HEIGHT);
    List<Sprite> sprites;
    public Background(double screenWidth, double screenHeight,GraphicsContext gc){
        sprites = new ArrayList<>();
        double backgroundWidth = 0;
        do{
            Sprite background = new Sprite(asset, gc);
            if(screenHeight-112 < HEIGHT)
                background.resizeImage(WIDTH, HEIGHT);
            else
                background.resizeImage(WIDTH, screenHeight - 112);
            if(screenHeight > HEIGHT)
                background.setPos(backgroundWidth, 0);
            else
                background.setPos(backgroundWidth, screenHeight - HEIGHT);
            sprites.add(background);
            backgroundWidth+= WIDTH;
        }
        while(backgroundWidth < screenWidth + WIDTH);
    }

    @Override
    public void render() {
        for(Sprite sprite : sprites){
            sprite.render();
        }
    }

    @Override
    public void update(long now) {

    }
}
