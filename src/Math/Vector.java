package Math;

public class Vector {
    private double x, y, z;

    public Vector(){
        x = y = z = 0;
    }

    public Vector(double i){
        x = y = z = i;
    }

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void set(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length1D(){
        return Math.sqrt(x*x);
    }

    public double length2D(){
        return Math.sqrt(x*x + y*y);
    }

    public double length3D(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector add(double value){
        this.x += value;
        this.y += value;
        this.z += value;
        return this;
    }

    public Vector add(Vector vector){
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
        return this;
    }

    public Vector sub(Vector vector){
        this.x -= vector.getX();
        this.y -= vector.getY();
        this.z -= vector.getZ();
        return this;
    }

    public Vector sub(double value){
        this.x -= value;
        this.y -= value;
        this.z -= value;
        return this;
    }
}
