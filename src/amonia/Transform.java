package amonia;

public class Transform {
    public Vec2 pos;
    public Vec2 size;

    public Transform() {
        pos = new Vec2();
        size = new Vec2(16);
    }

    public Transform(double x, double y) {
        pos = new Vec2(x, y);
        size = new Vec2(16);
    }

    public Transform(double x, double y, double width, double height) {
        pos = new Vec2(x, y);
        size = new Vec2(width, height);
    }

    public static Vec2 Normalize(Transform transform) {
        return new Vec2(Camera.currentCamera.getNormalizedX() - transform.size.x/2 + transform.pos.x, Camera.currentCamera.getNormalizedY()  - transform.size.y/2 + transform.pos.y);
    }

    public Vec2 Normalize() {
        return new Vec2(Camera.currentCamera.getNormalizedX() - this.size.x/2 + this.pos.x, Camera.currentCamera.getNormalizedY() - this.size.y/2 + this.pos.y);
    }
}