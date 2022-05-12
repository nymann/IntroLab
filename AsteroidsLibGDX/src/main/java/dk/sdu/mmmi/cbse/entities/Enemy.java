package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class Enemy extends Spaceship {
    private Random random;
    public Enemy() {
        super();
        this.color = Color.RED;
        this.random = new Random();
    }

    public void setDirections() {
        this.setUp(random.nextBoolean());
        this.setLeft(random.nextBoolean());
        this.setRight(random.nextBoolean());
    }
}
