package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import core.GameScreen;
import core.Game_Tiled;
import helper.Costants;

public class Enemy extends GameEntity{

	private float enemyWidth, enemyHeight;
	private float enemySpawnX_Default, enemySpawnY_Default;

	private Texture enemyTexture;
	private Texture chosenTexture;
	
	

	public Enemy(Game_Tiled game, float width, float height, Body body) {
		super(game, width, height, body);

		this.speed = 10f;

		this.enemyTexture = new Texture(Gdx.files.internal("lich king.png"));
		this.chosenTexture = enemyTexture;

	}


	public boolean inRange () {
		
		boolean inRange = false;
	
	float mainCharPosX = game.getGameScreen().getPlayer().x;
	float mainCharPosY = game.getGameScreen().getPlayer().y;
	
	if (Math.abs(enemySpawnX_Default - mainCharPosX) < 200 && Math.abs(enemySpawnY_Default - mainCharPosY) < 200 ) {
		
		inRange = true;
	}		
	
	return inRange;
		
		
	}

	

	@Override
	public void update() {
		// TODO Auto-generated method stub

		GameScreen gameScreen = game.getGameScreen();

		x = body.getPosition().x * Costants.PPM;


		y = body.getPosition().y * Costants.PPM;


		checkUserInput();
	}

	private void checkUserInput() {

		velX = 0;
		velY = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			velX = 0.2f;
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {

				velX = 0.35f;

			}

		}

		if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			velX = -0.2f;
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {

				velX = -0.35f;

			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
			velY = 0.2f;
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {

				velY = 0.35f;

			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			velY = -0.2f;
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {

				velY = -0.35f;

			}
		}


		/*setShowPlayer(true);

		if (velX != 0 || velY != 0) {
			setShowPlayer(false);

		}*/


		body.setLinearVelocity(velX * speed, velY * speed); //allows the player to move with the setted speed
	}

	public boolean inRangeForAttack(){

		boolean inRangeForAttack = false;

		float mainCharPosX = game.getGameScreen().getPlayer().x;
		float mainCharPosY = game.getGameScreen().getPlayer().y;

		if (Math.abs(enemySpawnX_Default - mainCharPosX) < 15 && Math.abs(enemySpawnY_Default - mainCharPosY) < 15 ) {

			inRangeForAttack = true;
		}		

		return inRangeForAttack;

	}



	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub

	}
	
	public Texture getChosenTexture() {
		return chosenTexture;
	}



	public float getEnemyWidth() {
		return enemyWidth;
	}



	public void setEnemyWidth(float enemyWidth) {
		this.enemyWidth = enemyWidth;
	}



	public float getEnemyHeight() {
		return enemyHeight;
	}



	public void setEnemyHeight(float enemyHeight) {
		this.enemyHeight = enemyHeight;
	}



	public float getEnemySpawnX_Default() {
		return enemySpawnX_Default;
	}



	public void setEnemySpawnX_Default(float enemySpawnX_Default) {
		this.enemySpawnX_Default = enemySpawnX_Default;
	}



	public float getEnemySpawnY_Default() {
		return enemySpawnY_Default;
	}



	public void setEnemySpawnY_Default(float enemySpawnY_Default) {
		this.enemySpawnY_Default = enemySpawnY_Default;
	}


	

}
