package helper;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import helper.Costants;
import objects.player.chest_Object;
import objects.player.Player;
import objects.player.chest_Object;
import core.GameScreen;
import core.Game_Tiled;
import objects.player.Enemy;



public class TileMapHelper {

	final Game_Tiled game;
	public TiledMap tiledMap;
	private GameScreen gameScreen;
	public Body body;
	public float mainCharWidth, mainCharHeight;
	public float mainCharSpawnX_Default, mainCharSpawnY_Default;
	
	public float chestSpawnX_Default, chestSpawnY_Default;
	public float chestWidth, chestHeight;


	public static float mainCharSpawnX_Modified, mainCharSpawnY_Modified;

	public static ArrayList<Boolean> chestLayout = new ArrayList<Boolean>();



	public TileMapHelper (final Game_Tiled game, GameScreen gameScreen) {

		this.game = game;
		this.gameScreen = gameScreen;
		this.tiledMap = new TmxMapLoader().load("test2.tmx");

		parameters(tiledMap);
	}

	public void parameters(TiledMap tiledMap) {

		MapObjects mapObjects = tiledMap.getLayers().get("entities").getObjects();

		for (MapObject mapObject: mapObjects) {

			if (mapObject instanceof RectangleMapObject) {
				Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
				String rectangleName = mapObject.getName();

				if (rectangleName.equals("player")) {

					mainCharSpawnX_Default = rectangle.getX();
					mainCharSpawnY_Default = rectangle.getY();
					mainCharWidth = rectangle.getWidth();
					mainCharHeight = rectangle.getHeight();

				}

			}
		}





	}

	public OrthogonalTiledMapRenderer setupMap() {


		parseMapObjects(tiledMap.getLayers().get("entities").getObjects());
		return new OrthogonalTiledMapRenderer(tiledMap);
	}

	private void parseMapObjects(MapObjects mapObjects) {
		
		int chestCounter = 0;
		for (MapObject mapObject: mapObjects) {

			if (mapObject instanceof PolygonMapObject) {
				createStaticBody((PolygonMapObject) mapObject);

			}

			if (mapObject instanceof RectangleMapObject) {
				Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
				String rectangleName = mapObject.getName();

				if (rectangleName.equals("player")) {

					Float MainCharSpawnX = mainCharSpawnX_Default;
					Float MainCharSpawnY = mainCharSpawnY_Default;

					if (game.isIsTheGameLoaded() == true) {

						MainCharSpawnX = mainCharSpawnX_Modified;
						MainCharSpawnY = mainCharSpawnY_Modified;

					}


					body  = BodyHelperService.createBody(
							MainCharSpawnX + rectangle.getWidth() / 2, 
							MainCharSpawnY + rectangle.getHeight() / 2,
							mainCharWidth,
							mainCharHeight,
							false,
							gameScreen.getWorld());


					gameScreen.setPlayer(new Player(game, mainCharWidth, mainCharHeight, body));



				}
				
				
				if (rectangleName.equals("chest")) {
					
					float ChestSpawnX = rectangle.getX();
					float ChestSpawnY = rectangle.getY();
					float chestWidth = rectangle.getWidth();
					float chestHeight = rectangle.getHeight();


					Body chestBody  = BodyHelperService.createBody(
							ChestSpawnX + rectangle.getWidth() / 2, 
							ChestSpawnY + rectangle.getHeight() / 2,
							chestWidth,
							chestHeight,
							true,
							gameScreen.getWorld());
					
							chest_Object chest = new chest_Object(game, chestWidth, chestHeight, chestBody);
							chest.setChestWidth(chestWidth);
							chest.setChestHeight(chestHeight);
							chest.setChestSpawnX_Default(ChestSpawnX);
							chest.setChestSpawnY_Default(ChestSpawnY);
							if (game.isIsTheGameLoaded() == true) {
								chest.setLooted(chestLayout.get(chestCounter));
								chest.setChosenTexture();
								chestCounter++;
							}
							gameScreen.getAllChest().add(chest);

				}
				

				if (rectangleName.equals("enemy")) {

					float enemySpawnX = rectangle.getX();
					float enemySpawnY = rectangle.getY();
					float enemyWidth = rectangle.getWidth();
					float enemyHeight = rectangle.getHeight();


					Body enemyBody  = BodyHelperService.createBody(
							enemySpawnX + rectangle.getWidth() / 2, 
							enemySpawnY + rectangle.getHeight() / 2,
							enemyWidth,
							enemyHeight,
							true,
							gameScreen.getWorld());
					
							Enemy enemy = new Enemy (game, enemyWidth, enemyHeight, enemyBody);
							enemy.setEnemyWidth(enemyWidth);
							enemy.setEnemyHeight(enemyHeight);
							enemy.setEnemySpawnX_Default(enemySpawnX);
							enemy.setEnemySpawnY_Default(enemySpawnY);
							gameScreen.getAllEnemies().add(enemy);
				}
				

			}
		}
	}

	private void createStaticBody (PolygonMapObject polygonMapObject) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyDef.BodyType.StaticBody;
		body = gameScreen.getWorld().createBody(bodydef);
		Shape shape = createPolygonShape(polygonMapObject);
		body.createFixture(shape, 1000);	
		shape.dispose();


	}

	private Shape createPolygonShape (PolygonMapObject polygonMapObject) {

		float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];

		for(int i = 0; i < vertices.length / 2; i++) {

			Vector2 current = new Vector2(vertices[i * 2] / Costants.PPM , vertices[i*2 +1] / Costants.PPM);
			worldVertices[i] = current;

		}

		PolygonShape shape = new PolygonShape();
		shape.set(worldVertices);
		return shape;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public float getMainCharWidth() {
		return mainCharWidth;
	}

	public void setMainCharWidth(float mainCharWidth) {
		this.mainCharWidth = mainCharWidth;
	}

	public float getMainCharHeight() {
		return mainCharHeight;
	}

	public void setMainCharHeight(float mainCharHeight) {
		this.mainCharHeight = mainCharHeight;
	}

	public float getMainCharSpawnX_Default() {
		return mainCharSpawnX_Default;
	}

	public void setMainCharSpawnX_Default(float mainCharSpawnX_Default) {
		this.mainCharSpawnX_Default = mainCharSpawnX_Default;
	}

	public float getMainCharSpawnY_Default() {
		return mainCharSpawnY_Default;
	}

	public void setMainCharSpawnY_Default(float mainCharSpawnY_Default) {
		this.mainCharSpawnY_Default = mainCharSpawnY_Default;
	}

	public float getChestSpawnX_Default() {
		return chestSpawnX_Default;
	}

	public void setChestSpawnX_Default(float chestSpawnX_Default) {
		this.chestSpawnX_Default = chestSpawnX_Default;
	}

	public float getChestSpawnY_Default() {
		return chestSpawnY_Default;
	}

	public void setChestSpawnY_Default(float chestSpawnY_Default) {
		this.chestSpawnY_Default = chestSpawnY_Default;
	}

	public float getChestWidth() {
		return chestWidth;
	}

	public void setChestWidth(float chestWidth) {
		this.chestWidth = chestWidth;
	}

	public float getChestHeight() {
		return chestHeight;
	}

	public void setChestHeight(float chestHeight) {
		this.chestHeight = chestHeight;
	}

	

	public float getMainCharSpawnX_Modified() {
		return mainCharSpawnX_Modified;
	}

	public void setMainCharSpawnX_Modified(float mainCharSpawnX_Modified) {
		this.mainCharSpawnX_Modified = mainCharSpawnX_Modified;
	}

	public float getMainCharSpawnY_Modified() {
		return mainCharSpawnY_Modified;
	}

	public void setMainCharSpawnY_Modified(float mainCharSpawnY_Modified) {
		this.mainCharSpawnY_Modified = mainCharSpawnY_Modified;
	}

	public Game_Tiled getGame() {
		return game;
	}
	
	
}
