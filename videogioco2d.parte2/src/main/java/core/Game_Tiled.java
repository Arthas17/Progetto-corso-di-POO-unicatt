package core;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Menu.Looting_Menu;
import Menu.MainMenuScreen;
import Menu.SaveMenuScreen;
import Menu.Save_Load_Menu;
import Menu.youDied_Menu;
import helper.TileMapHelper;
import inventory.Item;
import objects.player.chest_Object;


public class Game_Tiled extends Game{

	public static Game_Tiled INSTANCE;
	private Animator animator;
	private int widthscreen, heightscreen;
	private OrthographicCamera orthographicCamera;
	private SpriteBatch batch;
	public BitmapFont font;

	private GameScreen gameScreen;
	private MainMenuScreen mainMenuScreen;
	private SaveMenuScreen saveMenuScreen;
	private Save_Load_Menu save_load_Menu;
	private Looting_Menu looting_Menu;
	private youDied_Menu youDied_Menu;

	private boolean IsTheGameLoaded = false;
	private boolean IsTheGameSaved = false;



	public static ArrayList<Item> allItemsInTheGame = new ArrayList<Item>();

	public static ArrayList<Item> itemsEquippedByThePlayer = new ArrayList<Item>();
	public static Item dummyItem;


	public Game_Tiled() {
		INSTANCE = this;


	}


	@Override
	public void create() {


		this.widthscreen = Gdx.graphics.getWidth();
		this.heightscreen = Gdx.graphics.getHeight();
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, widthscreen, heightscreen);
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();


		this.animator = new Animator();


		Item.fill_The_Chests(INSTANCE);

		itemsEquippedByThePlayer.add(dummyItem);
		itemsEquippedByThePlayer.add(dummyItem);
		itemsEquippedByThePlayer.add(dummyItem);



		gameScreen = new GameScreen(this, orthographicCamera);
		mainMenuScreen = new MainMenuScreen(INSTANCE);		
		save_load_Menu = new Save_Load_Menu(INSTANCE);		
		saveMenuScreen = new SaveMenuScreen(INSTANCE);
		youDied_Menu = new youDied_Menu(INSTANCE);

		setScreen(mainMenuScreen);

	}


	public Animator getAnimator() {
		return animator;
	}


	public void setAnimator(Animator animator) {
		this.animator = animator;
	}


	public GameScreen getGameScreen() {
		return gameScreen;
	}


	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}


	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}


	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}


	public SaveMenuScreen getSaveMenuScreen() {
		return saveMenuScreen;
	}


	public void setSaveMenuScreen(SaveMenuScreen saveMenuScreen) {
		this.saveMenuScreen = saveMenuScreen;
	}


	public Save_Load_Menu getSave_load_Menu() {
		return save_load_Menu;
	}


	public void setSave_load_Menu(Save_Load_Menu save_load_Menu) {
		this.save_load_Menu = save_load_Menu;
	}


	public boolean isIsTheGameLoaded() {
		return IsTheGameLoaded;
	}


	public void setIsTheGameLoaded(boolean isTheGameLoaded) {
		IsTheGameLoaded = isTheGameLoaded;
	}


	public boolean isIsTheGameSaved() {
		return IsTheGameSaved;
	}


	public void setIsTheGameSaved(boolean isTheGameSaved) {
		IsTheGameSaved = isTheGameSaved;
	}


	public SpriteBatch getBatch() {
		return batch;
	}


	public Looting_Menu getLooting_Menu() {
		return looting_Menu;
	}


	public youDied_Menu getYouDied_Menu() {
		return youDied_Menu;
	}


	public void setYouDied_Menu(youDied_Menu youDied_Menu) {
		this.youDied_Menu = youDied_Menu;
	}


	public OrthographicCamera getOrthographicCamera() {
		return orthographicCamera;
	}


	public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
		this.orthographicCamera = orthographicCamera;
	}

	








}
