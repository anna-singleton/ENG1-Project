package io.github.annabeths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Boat implements PhysicsObject {
	GameController controller;
    
	// GameObject stats
	float x;
	float y;
	float rotation;
	Sprite sprite = null;
	
	// Boat stats
	protected int HP;
	protected float speed;
	protected float turnSpeed;
	
    public Boat() {
        sprite = new Sprite(new Texture(Gdx.files.internal("mario/yanderedev.jpg")));
        sprite.setSize(50, 50);
        sprite.setOrigin(25, 25);
        
        sprite.setCenter(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }
	
	public abstract void Update(float delta);
	
	void Move(float delta, int multiplier) {
		// Convention: 0 degrees means the object is pointing right, positive angles are counter clockwise
		x += Math.cos(Math.toRadians(rotation)) * speed * delta * multiplier;
		y += Math.sin(Math.toRadians(rotation)) * speed * delta * multiplier;
		
		sprite.setPosition(x, y);
	}
	
	// Turn the boat, a positive multiplier will turn it anti-clockwise, negative clockwise
	void Turn(float delta, float multiplier) {
		rotation += turnSpeed * delta * multiplier;
		sprite.setRotation(rotation);
	}
	
	abstract void Shoot();
	
	// TODO
	boolean isColliding(PhysicsObject object) {
		return false;
	}
	
	abstract void Destroy();
	
	// Place the boat somewhere in global space, use this when spawning boats
	void SetPosition(float x, float y) {
		this.x = x;
		this.y = y;
		sprite.setPosition(x, y);
	}
}
