package objects.player;



import java.awt.event.ItemEvent;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import Menu.Inventory_Menu;
import core.GameScreen;
import core.Game_Tiled;
import helper.Costants;
import inventory.Item;

public class Player extends GameEntity {

	private boolean showPlayer;
	
	
	private Texture player_healthbar;
	private Texture player_healthBarBackGround;
	
	private float max_Health;
	private float health;
	private float attack;
	private float attackSpeed;
	private float damegeReduction;
	

	private Inventory_Menu inventoryMenu;
	
	

	
	public Player(Game_Tiled game ,float width, float height, Body body) {
		super(game, width, height, body);

		
		this.speed = 10f;
		this.showPlayer = true;
		
		this.player_healthbar = new Texture(Gdx.files.internal("healthBar.png"));
		this.player_healthBarBackGround = new Texture(Gdx.files.internal("healthBarBackground.png"));
		
		if (game.isIsTheGameLoaded() == false) {
		this.max_Health = 20f;		
		this.health = 20f;
		}
		
		if (game.isIsTheGameLoaded() == true) {
			this.max_Health = game.getGameScreen().getChosenPreference().getFloat("maxHealth");		
			this.health = game.getGameScreen().getChosenPreference().getFloat("health");	
			}
		
		
		
		this.inventoryMenu = new Inventory_Menu(game);
		

		
		
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public void update() {
		// TODO Auto-generated method stub

		GameScreen gameScreen = game.getGameScreen();

		x = body.getPosition().x * Costants.PPM;
		gameScreen.chosenPreferences.putFloat("body position x", x);

		y = body.getPosition().y * Costants.PPM;
		gameScreen.chosenPreferences.putFloat("body position y", y);

		checkUserInput();
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub

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


		setShowPlayer(true);

		if (velX != 0 || velY != 0) {
			setShowPlayer(false);

		}


		body.setLinearVelocity(velX * speed, velY * speed); //allows the player to move with the setted speed
	}

	
	public void dealDamage(float damage) {
		
		health = Math.round( max_Health - damage);		
		
		
	}

	public void drawHealthBar () {
		
		game.getBatch().draw(player_healthBarBackGround, x - (5 * max_Health / 8) , y + 15, max_Health, 2);
		game.getBatch().draw(player_healthbar, x - ( 5 *max_Health / 8), y + 15, health, 2);
		
	}
	
	public void heal() {
		
		if (health != max_Health) {
			
			health = max_Health;
		}
		}
	
	public void notToHealed() {
		
		if (health >= max_Health) {
			
			health = max_Health;
		}
	}


	public float getSpeed() {
		
		return speed;
	}
	
	@Override
	public void setSpeed(float speed) {
		
		this.speed = speed;
	}
	
	public boolean isShowPlayer() {
		return showPlayer;
	}



	public void setShowPlayer(boolean showPlayer) {
		this.showPlayer = showPlayer;
	}


	public Texture getPlayer_healthbar() {
		return player_healthbar;
	}



	public Texture getPlayer_healthBarBackGround() {
		return player_healthBarBackGround;
	}



	public float getAttack() {
		return attack;
	}



	public void setAttack(float attack) {
		this.attack = attack;
	}



	public float getAttackSpeed() {
		return attackSpeed;
	}



	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}



	public float getDamegeReduction() {
		return damegeReduction;
	}



	public void setDamegeReduction(float damegeReduction) {
		this.damegeReduction = damegeReduction;
	}


	public Inventory_Menu getInventoryMenu() {
		return inventoryMenu;
	}

	public void setInventoryMenu(Inventory_Menu inventoryMenu) {
		this.inventoryMenu = inventoryMenu;
	}

	public float getMax_Health() {
		return max_Health;
	}

	public void setMax_Health(float max_Health) {
		this.max_Health = max_Health;
	}



	public float getHealth() {
		return health;
	}



	public void setHealth(float health) {
		this.health = health;
	}

	


	
	
	
	
	
	
	
	

}
