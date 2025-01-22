package JEngine.Math;

public class Transform {
    Vector position;
    Vector rotation;
    Vector scale;

    public Transform(){
        position = new Vector();
        rotation = new Vector();
        scale = new Vector(1);
    }

    public Transform(Vector position){
        this.position = position;
        rotation = new Vector();
        scale = new Vector(1);
    }

    public Transform(double x, double y, double z){
        this.position = new Vector(x, y, z);
        rotation = new Vector();
        scale = new Vector(1);
    }

    public Transform(double x, double y){
        this.position = new Vector(x, y, 0);
        rotation = new Vector();
        scale = new Vector(1);
    }

    public Vector position(){
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector rotation(){
        return rotation;
    }

    public void setRotation(Vector rotation) {
        this.rotation = rotation;
    }

    public Vector scale(){
        return scale;
    }

    public void setScale(Vector scale) {
        this.scale = scale;
    }
}
