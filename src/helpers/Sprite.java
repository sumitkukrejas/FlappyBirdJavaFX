package helpers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private String path;
    private double width;
    private double height;
    GraphicsContext gc;
    private double posX, posY, velX, velY;
    Image image;
    public Sprite(Asset asset, GraphicsContext gc){
        this.gc = gc;
        this.path = asset.getPath();
        this.height = asset.getHeight();
        this.width = asset.getWidth();
        this.image = new Image(this.path, this.width, this.height, false, false);
    }

    public void changeImage(Asset asset){
        this.width = asset.getWidth();
        this.height = asset.getHeight();
        this.path = asset.getPath();
        this.image = new Image(asset.getPath(), asset.getWidth(), asset.getHeight(), false, false);
    }


    public void resizeImage(double width, double height){
        this.width = width;
        this.height = height;
        this.image = new Image(this.path, width, height, false, false);
    }

    public void setPos(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }
    public void render(){
        this.gc.drawImage(this.getImage(), this.posX, this.posY);
    }

    public void update(){
        this.posX += velX;
        this.posY += velY;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void setVel(double velX , double velY){
        this.velX = velX;
        this.velY = velY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
