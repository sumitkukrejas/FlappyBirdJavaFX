import entities.Background;
import entities.Bird;
import entities.GameEntity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FlappyBirdDesktop extends  Application{
    private double width = 450;

    private double height = 600;
    private double minWidth = 365;
    private double minHeight = 412;

    private double maxHeight = 502;
    private double maxWidth = 740;

    private Scene scene;
    private GraphicsContext gc;
    private AnimationTimer timer;

    private Map<String, GameEntity> gameEntities = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        setStageProperties(stage);
        setScene(stage);
        initRender();
        startGameLoop();
        stage.show();
    }

    private void render(){
        for(GameEntity gameEntity : gameEntities.values()){
            gameEntity.render();
        }
    }

    private void updateEntities(long now){
        for(GameEntity gameEntity : gameEntities.values()){
            gameEntity.update(now);
        }
    }
    private void initRender() {
        gameEntities.put("background" , new Background(width, height, gc));
        gameEntities.put("bird", new Bird(width, height, gc));
        render();
    }


    private void setHandlers(){
//        this.scene.onMouseClickedProperty().addListener(()->{
//            gameEntities.get("bird").set
//        });
    }
    private void setScene(Stage stage) {
        Canvas canvas = new Canvas();
        this.gc = canvas.getGraphicsContext2D();
        Pane pane = new Pane(canvas);
        Scene scene = new Scene(pane, 840, 500);
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.heightProperty().addListener((obs, oldVal , newVal)->{
            this.height = newVal.doubleValue();
            initRender();
        });

        canvas.widthProperty().addListener((obs, oldVal, newVal)->{
            this.width = newVal.doubleValue();
            initRender();
        });
        stage.setScene(scene);
        stage.show();
    }

    private void setStageProperties(Stage stage) {
        stage.getIcons().add(new Image("images/icon.png"));
        stage.setTitle("Flappy Bird");
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
//        stage.setMaxHeight(maxHeight);
//        stage.setMaxWidth(maxWidth);
    }

    private void startGameLoop(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateEntities(l);
                render();
            }
        };
        timer.start();
    }
}