package core;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import Menu.SaveMenuScreen;
import helper.Costants;
import helper.TileMapHelper;
import inventory.Item;
import objects.player.chest_Object;
import objects.player.Enemy;
import objects.player.Player;


import com.badlogic.gdx.math.Vector3;
import core.Animator;
import core.Game_Tiled;
;

public class GameScreen extends ScreenAdapter {

	public Game_Tiled game;
	public GameScreen gameScreen;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer box2dDebugRenderer;
	public OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
	public TileMapHelper tileMapHelper;

	public Preferences default_preferences;
	public Preferences preferences_0;
	public Preferences preferences_1;
	public Preferences preferences_2;
	public Preferences preferences_3;

	public  ArrayList<Preferences> listOfPreferences;
	public int numberOfOPreferences;
	public Preferences chosenPreferences;



	//game objects
	public Player player;
	private ArrayList<chest_Object> allChest;
	private ArrayList<Enemy> allEnemies;



	public GameScreen(Game_Tiled game, OrthographicCamera camera) {


		this.game = game;


		this.default_preferences = Gdx.app.getPreferences("default");


		this.preferences_0 = Gdx.app.getPreferences("0");
		try {
			if (preferences_0.getBoolean("alreadySavedSomethingIn") == false) {
				preferences_0.putString("Preference_Marker", "0");
				preferences_0.putBoolean("alreadySavedSomethingIn", false);
			}
		} catch (Exception e) {

			preferences_0.putString("Preference_Marker", "0");
			preferences_0.putBoolean("alreadySavedSomethingIn", false);
		}

		this.preferences_1 = Gdx.app.getPreferences("1");
		try {
			if (preferences_1.getBoolean("alreadySavedSomethingIn") == false) {
				preferences_1.putString("Preference_Marker", "1");
				preferences_1.putBoolean("alreadySavedSomethingIn", false);
			}
		} catch (Exception e) {

			preferences_1.putString("Preference_Marker", "1");
			preferences_1.putBoolean("alreadySavedSomethingIn", false);
		}

		this.preferences_2 = Gdx.app.getPreferences("2");
		try {
			if (preferences_2.getBoolean("alreadySavedSomethingIn") == false) {
				preferences_2.putString("Preference_Marker", "2");
				preferences_2.putBoolean("alreadySavedSomethingIn", false);
			}
		} catch (Exception e) {

			preferences_2.putString("Preference_Marker", "2");
			preferences_2.putBoolean("alreadySavedSomethingIn", false);
		}

		this.preferences_3 = Gdx.app.getPreferences("3");
		try {
			if (preferences_3.getBoolean("alreadySavedSomethingIn") == false) {
				preferences_3.putString("Preference_Marker", "3");
				preferences_3.putBoolean("alreadySavedSomethingIn", false);
			}
		} catch (Exception e) {

			preferences_3.putString("Preference_Marker", "3");
			preferences_3.putBoolean("alreadySavedSomethingIn", false);
		}

		this.listOfPreferences = createListOfPreferences();
		this.numberOfOPreferences = listOfPreferences.size();
		
		

		if (game.isIsTheGameLoaded() == true) {

			chosenPreferences = chosenPreferences_selector();
		}

		else {

			chosenPreferences = default_preferences;
		}

		
		
		
		this.allChest = new ArrayList<chest_Object>();
		this.allEnemies = new ArrayList<Enemy>();


		this.camera = camera;
		camera.zoom = 0.2f;
		this.batch = game.getBatch();
		this.world = new World(new Vector2(0,0), false);
		this.box2dDebugRenderer = new Box2DDebugRenderer(false, false, false, false, false, false);
		this.tileMapHelper = new TileMapHelper(game, this);
		this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();


		cameraInitialSettings();


	} 

	public ArrayList<Preferences> createListOfPreferences(){

		ArrayList<Preferences> listOfPrefereces = new ArrayList<Preferences>();
		listOfPrefereces.add(preferences_0);
		listOfPrefereces.add(preferences_1);
		listOfPrefereces.add(preferences_2);
		listOfPrefereces.add(preferences_3);

		return listOfPrefereces;		
	}
	
	

	public Preferences chosenPreferences_selector() {

		Preferences chosenPreferences = null;


		for (int counter = 0; counter < numberOfOPreferences; counter++) {
			if (listOfPreferences.get(counter).getBoolean("alreadySavedSomethingIn") == false){
				chosenPreferences = listOfPreferences.get(counter);
				break;
			}

		}

		return chosenPreferences;

	}



	@Override
	public void render (float delta) {


		this.update();

		handleInput();



		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Animator animator = game.getAnimator();

		animator.stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		this.orthogonalTiledMapRenderer.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
			Gdx.graphics.setWindowedMode(960, 640);


		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
			Monitor primary = Lwjgl3ApplicationConfiguration.getPrimaryMonitor();
			DisplayMode desktopMode = Lwjgl3ApplicationConfiguration.getDisplayMode(primary);
			Gdx.graphics.setFullscreenMode(desktopMode);


		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {


			game.setScreen(game.getSaveMenuScreen());
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			/*player.updateInventoryMenu();*/
			game.setScreen(player.getInventoryMenu());


		}

		
		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			/*player.updateInventoryMenu();*/
			player.heal();


		}


		batch.begin();



		for (chest_Object chestObject : allChest) {
			batch.draw(chestObject.getChosenTexture(), (Math.round(chestObject.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (chestObject.getChestWidth() / 2),
					(Math.round(chestObject.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (chestObject.getChestHeight() / 2), chestObject.getChestWidth(), chestObject.getChestHeight());

		}

		for (Enemy enemy: allEnemies) {

			batch.draw(enemy.getChosenTexture(), (Math.round(enemy.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (enemy.getEnemyWidth() / 2),
					(Math.round(enemy.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (enemy.getEnemyHeight() / 2), enemy.getEnemyWidth(), enemy.getEnemyHeight());

			boolean inRange= enemy.inRange();
			
			if (inRange == true && Gdx.input.isKeyPressed(Input.Keys.L))
			game.setScreen(game.getYouDied_Menu());

		}

		for (chest_Object chestObject : allChest) {
			if (chestObject.isLooted() == false) {
				if (chestObject.inRangeForLoot() == true) {

					if (Gdx.input.isKeyJustPressed(Input.Keys.L)){

						chestObject.chestOpening();
					}
				}

			}

		}



		draw_MAIN_CHARACTER();

		
		player.drawHealthBar();
		player.notToHealed();
		healthTracker();
		
		chestTracker();
		

		itemsTracker(chest_Object.commonItems);
		itemsTracker(chest_Object.rareItems);
		itemsTracker(chest_Object.mythicItems);
		itemsTracker(chest_Object.legendaryItems);
		
		equippedItemsTracker();
		
		batch.end();
		box2dDebugRenderer.render(world, camera.combined.scl(Costants.PPM));


	}

	private void update() {

		world.step(1/60f, 6, 2);
		cameraUpdate();	
		batch.setProjectionMatrix(camera.combined);		

		orthogonalTiledMapRenderer.setView(camera);

		player.update();

		default_preferences.flush();
		preferences_0.flush();
		preferences_1.flush();
		preferences_2.flush();
		preferences_3.flush();

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}


	}
	
	public void healthTracker() {
		
		chosenPreferences.putFloat("maxHealth", player.getMax_Health());
		chosenPreferences.flush();
		chosenPreferences.putFloat("health", player.getHealth());
		chosenPreferences.flush();
	}

	public void chestTracker () {

		int counter = 0;
		for (chest_Object chestObject: allChest) {

			String chestkey = "chest" + Integer.toString(counter);
			chosenPreferences.putBoolean(chestkey, chestObject.isLooted());
			chosenPreferences.flush();
			counter++;
		}
	}

	public void itemsTracker(ArrayList<Item> ArrayListOfItemByRarity) {

		String key = "";

		if (ArrayListOfItemByRarity == chest_Object.commonItems) {

			key = "Common";
		}

		if (ArrayListOfItemByRarity == chest_Object.rareItems) {

			key = "Rare";
		}
		if (ArrayListOfItemByRarity == chest_Object.mythicItems) {

			key = "Mythic";
		}
		if (ArrayListOfItemByRarity == chest_Object.legendaryItems) {

			key = "Legendary";
		}


		for (int counter = 0; counter < ArrayListOfItemByRarity.size(); counter++ ) {

			chosenPreferences.putBoolean(key + Integer.toString(counter), ArrayListOfItemByRarity.get(counter).isAlreadyFound());
			chosenPreferences.flush();
		}
	}

	public void equippedItemsTracker() {

		for (int counter = 0; counter < Game_Tiled.itemsEquippedByThePlayer.size(); counter++) {

			if (Game_Tiled.itemsEquippedByThePlayer.get(counter) != null) {

				chosenPreferences.putString("equippedItem" + Integer.toString(counter), Game_Tiled.itemsEquippedByThePlayer.get(counter).getName());
			}

			if (Game_Tiled.itemsEquippedByThePlayer.get(counter) == null) {

				chosenPreferences.putString("equippedItem" + Integer.toString(counter), "null");
			}

		}


	}



	public void cameraInitialSettings() {

		Vector3 position = camera.position;
		
		if (game.isIsTheGameLoaded() == false) {

			position.x = Math.round(tileMapHelper.getMainCharSpawnX_Default());
			position.y = Math.round(tileMapHelper.getMainCharSpawnY_Default());

		}

		position.x = Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f;
		position.y = Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f;


	}

	public void cameraUpdate() {

		Vector3 position = camera.position;

		if (Math.abs(position.x - player.getX()) > 350 || Math.abs(position.y - player.getY()) > 200) {

			position.x = Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f;
			position.y = Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f;

			camera.position.set(position);
		}


		camera.update();


	}

	private void handleInput() {


		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			camera.zoom += 0.02;
			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			camera.zoom -= 0.02;
			
		}

		camera.zoom = MathUtils.clamp(camera.zoom, 0.3f, 0.5f);

	}

	
	
	
	public void draw_MAIN_CHARACTER() {

		Animator animator = game.getAnimator();
		
		
		TextureRegion steady_MainChar = animator.getChosen_Steady_Animation().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion up_Walk = animator.getWalkAnimationUp().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion up_Right_Walk = animator.getWalkAnimationUp_Right().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion right_Walk = animator.getWalkAnimationRight().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion down_Right_Walk = animator.getWalkAnimationDown_Right().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion down_Walk = animator.getWalkAnimationDown().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion down_Left_Walk = animator.getWalkAnimationDown_Left().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion left_Walk = animator.getWalkAnimationLeft().getKeyFrame(animator.getStateTime(), true);
		
		TextureRegion up_Left_Walk = animator.getWalkAnimationUp_Left().getKeyFrame(animator.getStateTime(), true);

		
		
		if (player.isShowPlayer() == true) {

			batch.draw(steady_MainChar , (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
					(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight());

		}

		
		if ((Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) || 
				(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) ||
				(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D)) || 
				(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {

			player.getBody().setLinearVelocity(0, 0);

			batch.draw(steady_MainChar , (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
					(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight());

			animator.setMovement_error(true);

		}


		if (animator.isMovement_error() == false) {


			if ((Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W)) ||
					(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.UP))) {

				batch.draw(up_Right_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
						(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)

				animator.setChosen_Steady_Animation(animator.getUp_Right_SteadyPosition());

				animator.setDiagonalWalk(true);
			}
			

			if ((Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.W)) || 
					(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP))) {

				batch.draw(up_Left_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
						(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)

				animator.setChosen_Steady_Animation(animator.getUp_left_Steady_Position());

				animator.setDiagonalWalk(true);
			}
			

			if ((Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.S)) ||
					(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN))) {

				batch.draw(down_Right_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
						(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)


				animator.setChosen_Steady_Animation(animator.getDown_Right_Steady_Position());

				animator.setDiagonalWalk(true);
			}
			

			if ((Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.S)) ||
					(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN))) {

				batch.draw(down_Left_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
						(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)


				animator.setChosen_Steady_Animation(animator.getDownLeft_Steady_Position());

				animator.setDiagonalWalk(true);
			}
			

			if (animator.isDiagonalWalk() == false) {



				if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {


					batch.draw(up_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
							(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)

					animator.setChosen_Steady_Animation(animator.getUp_Steady_Position());

				}


				if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {



					batch.draw(down_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
							(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)

					animator.setChosen_Steady_Animation(animator.getDown_Steady_Position());

				}


				if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {



					batch.draw(right_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
							(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)

					animator.setChosen_Steady_Animation(animator.getRight_Steady_Position());

				}


				if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {



					batch.draw(left_Walk,  (Math.round(player.getBody().getPosition().x * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharWidth() / 2),
							(Math.round(player.getBody().getPosition().y * Costants.PPM * 10) / 10f) - (tileMapHelper.getMainCharHeight() / 2), tileMapHelper.getMainCharWidth(), tileMapHelper.getMainCharHeight()); // Draw current frame at (50, 50)

					animator.setChosen_Steady_Animation(animator.getLeft_Steady_Position());

				}
			}
		}


		animator.setDiagonalWalk(false);
		animator.setMovement_error(false);



	}



	@Override
	public void dispose() {
		// dispose of all the native resources

		batch.dispose();




	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}


	public void setPlayer(Player player) {

		this.player = player;

	}

	public ArrayList<Preferences> getListOfPreferences() {
		return listOfPreferences;
	}

	public void setListOfPreferences(ArrayList<Preferences> listOfPreferences) {
		this.listOfPreferences = listOfPreferences;
	}

	public int getNumberOfOPreferences() {
		return numberOfOPreferences;
	}

	public void setNumberOfOPreferences(int numberOfOPreferences) {
		this.numberOfOPreferences = numberOfOPreferences;
	}

	public Preferences getChosenPreference() {
		return chosenPreferences;
	}

	public void setChosenPreference(Preferences chosenPreference) {
		this.chosenPreferences = chosenPreference;
	}

	public Preferences getDefault_preferences() {
		return default_preferences;
	}

	public void setDefault_preferences(Preferences default_preferences) {
		this.default_preferences = default_preferences;
	}

	public TileMapHelper getTileMapHelper() {
		return tileMapHelper;
	}

	public void setTileMapHelper(TileMapHelper tileMapHelper) {
		this.tileMapHelper = tileMapHelper;
	}

	public OrthogonalTiledMapRenderer getOrthogonalTiledMapRenderer() {
		return orthogonalTiledMapRenderer;
	}

	public void setOrthogonalTiledMapRenderer(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer) {
		this.orthogonalTiledMapRenderer = orthogonalTiledMapRenderer;
	}

	public ArrayList<chest_Object> getAllChest() {
		return allChest;
	}

	public void setAllChest(ArrayList<chest_Object> allChest) {
		this.allChest = allChest;
	}

	public Player getPlayer() {
		return player;
	}


	public ArrayList<Enemy> getAllEnemies() {
		return allEnemies;
	}












}
