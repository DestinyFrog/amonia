package amonia;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

public class Player extends Entity {
    public Physia physia;
    public Vec2 direction;
    private double speed = 1;
    private BufferedImage sprite;

    private Animator animator;

    private HashMap<String, Animation> getAnimations() {
        HashMap<String, Animation> animations = new HashMap<>();

        List<Vec2> idleSprites = new ArrayList<>();
        idleSprites.add(new Vec2(0, 0));
        Animation idleAnimation = new Animation(this.sprite, idleSprites, new Vec2(16, 16), 1200);
        animations.put("idle", idleAnimation);

        List<Vec2> walkSprites = new ArrayList<>();
        walkSprites.add(new Vec2(0, 0));
        walkSprites.add(new Vec2(1, 0));
        walkSprites.add(new Vec2(2, 0));
        Animation walkAnimation = new Animation(this.sprite, walkSprites, new Vec2(16, 16), 1200);
        animations.put("walk", walkAnimation);

        return animations;
    }

    public Player() throws IOException {
        direction = new Vec2(0, 0);
        transform = new Transform(0, 0, 16, 16);
        physia = new Physia(transform);

        File image = new File("sprites//player.png");
        sprite = ImageIO.read(image);

        animator = new Animator(getAnimations(), "idle");
    }

    @Override
    public void Update() {
        if (direction.x == 0 && direction.y == 0) {
            animator.setAnimation("idle");
        }

        this.physia.setVelocityX(direction.x * speed);
        this.physia.setVelocityY(direction.y * speed);
        physia.Move();
    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.green);
        Vec2 pos = transform.Normalize();
        g.drawImage(animator.Play(), (int) Math.round(pos.x), (int) Math.round(pos.y), null);
    }

    public void input(int c) {
        switch (c) {
            case 38: // KeyUp
                this.direction.y = -1;
                animator.setAnimation("walk");
                break;

            case 40: // KeyDown
                this.direction.y = 1;
                animator.setAnimation("walk");
                break;

            case 37: // KeyLeft
                this.direction.x = -1;
                animator.setAnimation("walk");

                break;

            case 39: // KeyRight
                this.direction.x = 1;
                animator.setAnimation("walk");
                break;
        }
    }

    public void uninput(int c) {
        switch (c) {
            case 38: // KeyUp
                this.direction.y = 0;
                break;

            case 40: // KeyDown
                this.direction.y = 0;
                break;

            case 37: // KeyLeft
                this.direction.x = 0;
                break;

            case 39: // KeyRight
                this.direction.x = 0;
                break;
        }
    }
}
