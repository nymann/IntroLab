package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {
    private Color color;

    public Bullet(float x, float y, float radians) {
        super();
        this.color = Color.YELLOW;
        this.x = x;
        this.y = y;
        this.radians = radians;
        this.speed = 200;
        dx = MathUtils.cos(this.radians) * speed;
        dy = MathUtils.sin(this.radians) * speed;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(this.color);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(this.x, this.y, 2);
        sr.end();
    }

    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;
    }
}
