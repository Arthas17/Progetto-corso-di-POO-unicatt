package objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import core.Game_Tiled;

public abstract class GameEntity {
	
	final Game_Tiled game;
	protected float x, y, velX, velY, speed;
	protected float width,height;
	protected Body body;
	
	public GameEntity (final Game_Tiled game, float width, float height, Body body) {
		
		this.game = game;
		this.x = body.getPosition().x;
		this.y = body.getPosition().y;
		this.width = width;
		this.height = height;
		this.body = body;
		this.velX = 0;
		this.velY = 0;
		this.speed = 0;
		
	}
	
	
	
	
	public void setX(float x) {
		this.x = x;
	}




	public void setY(float y) {
		this.y = y;
	}

	
	



	public float getX() {
		return x;
	}




	public float getY() {
		return y;
	}


	

	public float getSpeed() {
		return speed;
	}




	public void setSpeed(float speed) {
		this.speed = speed;
	}




	public abstract void update();

	
	public abstract void render(SpriteBatch batch);


	public Body getBody() {
		return body;
	}


	public void setBody(Body body) {
		this.body = body;
	}
	
}
