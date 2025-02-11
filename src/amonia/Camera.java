package amonia;

public class Camera {
    public static Camera currentCamera;
    public Transform transform;
    private Entity entityToFollow = null;

    private Vec2 defaultSize = new Vec2(400, 400); 

    public static void setCurretCamera(Camera camera) {
        Camera.currentCamera = camera;
    }

    public Camera() {
        this.transform = new Transform(0, 0, defaultSize.x, defaultSize.y);
    }

    public Camera(Entity entityToFollow) {
        this.transform = new Transform(0, 0, defaultSize.x, defaultSize.y);
        this.entityToFollow = entityToFollow;
    }

    public void Update() {
        if (this.entityToFollow != null) {
            this.transform.pos.x = this.entityToFollow.transform.pos.x;
            this.transform.pos.y = this.entityToFollow.transform.pos.y;
        }
    }

    public int getNormalizedX() {
        return ((int) this.transform.pos.x * -1) + ((int) this.transform.size.x*2);
    }

    public int getNormalizedY() {
        return ((int) this.transform.pos.y * -1) + ((int) this.transform.size.y*2);
    }
}
