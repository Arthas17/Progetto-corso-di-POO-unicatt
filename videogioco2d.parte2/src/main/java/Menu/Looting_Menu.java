package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

import core.Game_Tiled;
import inventory.Item;
import objects.player.chest_Object;

public class Looting_Menu implements Screen{

	private final Game_Tiled game;
	
	private Item itemFound;

	private OrthographicCamera camera;

	private Stage stage;

	private Skin chosenSkin;

	public Looting_Menu(final Game_Tiled game, Item itemFound) {

		this.game = game;
		this.itemFound = itemFound;

		this.stage = new Stage();

		this.chosenSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);


	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		Label label = new Label("You Found:  " + itemFound.getName() + "\n\n\n" 
				+ "Rarity:  " + itemFound.getRarity() + "\n\n"
				+ "Health Boost:  " + itemFound.getHp_Boost() + "\n\n"
				+ "Health Reduction:  " + itemFound.getHp_Reduction() + "\n\n"
				+ "Damage Boost:  " + itemFound.getAttack_Boost() + "\n\n"
				+ "Damage Reduction:  " + itemFound.getAttack_Reduction() + "\n\n"
				+ "Attack Speed Boost:  " + itemFound.getSpeed_boost() + "\n\n"
				+ "Attack Speed Reduction:  " + itemFound.getSpeed_Reduction() + "\n\n"
				+ "Resistance Boost:  " + itemFound.getResistance_Boost() + "\n\n"
				+ "Resistance Reduction:  " + itemFound.getResistance_Reduction() + "\n\n"
				, chosenSkin);

		label.setPosition(Gdx.graphics.getWidth()/2 - label.getWidth()/2, Gdx.graphics.getHeight()/2 - label.getHeight()/2);

		TextButton button = new TextButton("Back To Adventure!", chosenSkin);
		button.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.getGameScreen());
				stage.clear();
				
			}
			
		});
		
		stage.addActor(label);
		stage.addActor(button);

	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();

		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();

		stage.draw();
		
		game.getBatch().draw(itemFound.getTexture(), Gdx.graphics.getWidth() / 2 - 350 / 2 - 700, Gdx.graphics.getHeight() / 2  - 350/2,
				450, 450);



		game.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
