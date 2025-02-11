package amonia;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private int currentSprite = 0;
    public List<BufferedImage> sprites;
    private boolean loop = true;
    public Vec2 unit;

    private int currentFrame = 0;
    private int frameRate = 60;

    private void setupSprites(BufferedImage sourceSprite, List<Vec2> pointsSprites) {
        for (Vec2 pointSprite : pointsSprites) {
            BufferedImage subSprite = sourceSprite.getSubimage((int) pointSprite.x * (int) this.unit.x,
                    (int) pointSprite.y * (int) this.unit.y, (int) this.unit.x, (int) this.unit.y);
            sprites.add(subSprite);
        }
    }

    public Animation(BufferedImage sourceSprite, List<Vec2> pointsSprites, Vec2 unit) {
        this.unit = unit;
        this.sprites = new ArrayList<>();
        this.setupSprites(sourceSprite, pointsSprites);
    }

    public Animation(BufferedImage sourceSprite, List<Vec2> pointsSprites, Vec2 unit, boolean loop) {
        this.loop = loop;
        this.unit = unit;
        this.sprites = new ArrayList<>();
        this.setupSprites(sourceSprite, pointsSprites);
    }

    public Animation(BufferedImage sourceSprite, List<Vec2> pointsSprites, Vec2 unit, int frameRate) {
        this.unit = unit;
        this.frameRate = frameRate;
        this.sprites = new ArrayList<>();
        this.setupSprites(sourceSprite, pointsSprites);
    }

    public Animation(BufferedImage sourceSprite, List<Vec2> pointsSprites, Vec2 unit, boolean loop, int frameRate) {
        this.loop = loop;
        this.unit = unit;
        this.frameRate = frameRate;
        this.sprites = new ArrayList<>();
        this.setupSprites(sourceSprite, pointsSprites);
    }

    public void Reset() {
        currentSprite = 0;
    }

    public void Next() {
        if (currentFrame >= frameRate) {
            currentFrame = 0;
            if (currentSprite < sprites.size() - 1) {
                currentSprite++;
            } else if (loop) {
                Reset();
            }
        } else {
            currentFrame++;
        }
    }

    public BufferedImage getCurrentSprite() {
        return sprites.get(currentSprite);
    }
}
