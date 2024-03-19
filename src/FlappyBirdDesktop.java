//--module-path ${PATH_JAVAFX} --add-modules javafx.controls,javafx.fxml
import entities.*;
import helpers.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.HashMap;
import java.util.Map;

public class FlappyBirdDesktop extends  Application{
    public static boolean gameStarted = false;
    public static boolean gameEnded = false;
    Font font = Font.loadFont("images/04b_19.ttf", 42);
    private double width = 450;
    private double height = 600;
    private double minWidth = 365;
    private double minHeight = 412;
    private double maxHeight = 502;
    private double maxWidth = 740;
    Bird bird;
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
        this.gc.clearRect(0 ,0, width, height);
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
        bird = new Bird(width, height, gc);
        gameEntities.put("bird", bird);
        gameEntities.put("floor", new Floor(width, height, gc));
        gameEntities.put("pipes", new Pipes(width, height, gc));
        gameEntities.put("title", new Title(width, height, gc));
        render();
    }

    private void setScene(Stage stage) {
        Canvas canvas = new Canvas();
        this.gc = canvas.getGraphicsContext2D();
        Pane pane = new Pane(canvas);
        Scene scene = new Scene(pane, width, height);
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
        setInputHandlers(scene);
        stage.setScene(scene);
        stage.show();
    }

    private void setInputHandlers(Scene scene) {
        scene.setOnKeyPressed(event->{
            if(event.getCode().equals(KeyCode.SPACE)){
                if(gameStarted){
                    bird.jumpHandler();
                    GameState.gameStarted = true;
                }
                else{
                    gameStarted = true;
                }
            }
            if(event.getCode().equals(KeyCode.ENTER)){
            }
        });

    }


    private void setStageProperties(Stage stage) {
        stage.getIcons().add(new Image("images/icon.png"));
        stage.setTitle("Flappy Bird");
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
    }

    private void startGameLoop(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateEntities(now);
                render();
            }
        };
        timer.start();
    }
}