package helpers;

public class Asset {
    private String path;
    private double width;
    private double height;

    public Asset(String path, double width, double height){
        this.path = path;
        this.width  = width;
        this.height = height;
    }

    public String getPath(){
        return this.path;
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }
}
