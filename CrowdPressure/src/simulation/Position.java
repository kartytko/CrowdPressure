package simulation;

public class Position {
    private float x_;
    private float y_;

    public Position(float x, float y){
        x_ = x;
        y_ = y;
    }

    public float getX_() {
        return x_;
    }

    public void setX_(float x_) {
        this.x_ = x_;
    }

    public float getY_() {
        return y_;
    }

    public void setY_(float y_) {
        this.y_ = y_;
    }
}
