package amonia;

import java.awt.Graphics2D;

public class Entity {
    public Transform transform;

    public void Update() {
        throw new Error("Update was not Implemented");
    }

    public void Draw(Graphics2D g) {
        throw new Error("Draw was not Implemented");
    }
}
