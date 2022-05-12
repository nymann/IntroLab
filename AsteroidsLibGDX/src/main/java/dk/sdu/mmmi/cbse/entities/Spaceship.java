package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;
import java.util.List;

public class Spaceship extends SpaceObject {

    private boolean left;
    private boolean right;
    private boolean up;

    protected float maxSpeed = 300;
    protected float acceleration = 200;
    protected float deceleration = 10;

    private List<Bullet> bullets = new ArrayList<>();

    protected Color color = Color.WHITE;

    public Spaceship() {

        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;

        shapeX = new float[4];
        shapeY = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;
    }

    private void setShape() {
        shapeX[0] = x + MathUtils.cos(radians) * 8;
        shapeY[0] = y + MathUtils.sin(radians) * 8;

        shapeX[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapeY[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapeX[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapeY[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapeX[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapeY[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void shoot() {
        if(bullets.size() == 20) {
            return;
        }
        bullets.add(new Bullet(x, y,  radians));
    }

    public void update(float dt) {

        // turning
        if (left) {
            radians += rotationSpeed * dt;
        } else if (right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (up) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        bullets.removeIf(SpaceObject::isOutsideWindow);
        for (Bullet bullet: bullets) {
           bullet.update(dt);
        }

        // screen wrap
        wrap();
    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(this.color);

        sr.begin(ShapeType.Line);

        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {
            sr.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
        }

        sr.end();
        for (Bullet bullet : bullets) {
            bullet.draw(sr);
        }

    }

}
