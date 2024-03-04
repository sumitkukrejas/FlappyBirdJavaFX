package entities;

import helpers.Asset;
import helpers.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Floor implements GameEntity {

    private int FLOOR_WIDTH = 336;
    private int FLOOR_HEIGHT = 112;
    Asset asset = new Asset("images/floor.png", FLOOR_WIDTH, FLOOR_HEIGHT);
    List<Sprite> floors = new ArrayList<>();
    public Floor(double screenWidth, double screenHeight, GraphicsContext gc) {
        int floorWidth = 0;
        while(floorWidth < screenWidth + FLOOR_WIDTH){
            Sprite floor = new Sprite(this.asset, gc);
            floor.setPosX(floorWidth);
            floor.setPosY(screenHeight - FLOOR_HEIGHT);
            floor.setVel(-2.5, 0);
            floors.add(floor);
            floorWidth += FLOOR_WIDTH;

        }
    }

    @Override
    public void render() {
        for(Sprite floorSprite : floors){
            floorSprite.render();
        }
    }

    @Override
    public void update(long now) {
        for(Sprite floor : floors){
            floor.update();
        }
    }
}
