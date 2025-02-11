package amonia;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Animator {
    public String currentAnimation;
    private HashMap<String, Animation> animations;

    public Animator(HashMap<String, Animation> animations, String firstAnimation) {
        this.animations = animations;
        this.currentAnimation = firstAnimation;
    }

    public void setAnimation(String newAnimation) {
        this.currentAnimation = newAnimation;
        this.animations.get(this.currentAnimation).Reset();
    }

    public BufferedImage Play() {
        this.animations.get(this.currentAnimation).Next();
        return this.animations.get(this.currentAnimation).getCurrentSprite();
    }
}
