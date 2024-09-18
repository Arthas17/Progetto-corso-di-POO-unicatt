package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ScreenUtils;

import core.Game_Tiled;

public class SaveMenuScreen implements Screen{

	private final Game_Tiled game;

	private OrthographicCamera camera;

	private Stage stage;

	private Skin chosenSkin;

	private float saveMenuScreen_buttonWidth, saveMenuScreen_buttonHeight;

	public SaveMenuScreen(final Game_Tiled game) {
		this.game = game;
		this.stage = new Stage();
		
		this.chosenSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

		this.saveMenuScreen_buttonWidth = 160;
		this.saveMenuScreen_buttonHeight = 80;


		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

	}

	@Override
	public void show() {

		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		TextButton saveMenu_button1 = create_TextButton(saveMenuScreen_buttonWidth, saveMenuScreen_buttonHeight, 
				(Gdx.graphics.getWidth()/2) - saveMenuScreen_buttonWidth / 2 , (Gdx.graphics.getHeight() / 2) + 0.5f * saveMenuScreen_buttonHeight + 5,
				"RESUME", chosenSkin, "small");
		saveMenu_button1.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				game.setScreen(game.getGameScreen());
				stage.clear();

			}
		});

		TextButton saveMenu_button2 = create_TextButton(saveMenuScreen_buttonWidth, saveMenuScreen_buttonHeight, 
				(Gdx.graphics.getWidth()/2) - saveMenuScreen_buttonWidth / 2 , (Gdx.graphics.getHeight() / 2) - 0.5f * saveMenuScreen_buttonHeight ,
				"SAVE", chosenSkin, "small");
		saveMenu_button2.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getSave_load_Menu().setSaveScreen(true);
				game.getSave_load_Menu().setLoadScreen(false);
				game.setScreen(game.getSave_load_Menu());
				stage.clear();

			}
		});

		TextButton saveMenu_button3 = create_TextButton(saveMenuScreen_buttonWidth, saveMenuScreen_buttonHeight, 
				(Gdx.graphics.getWidth()/2) - saveMenuScreen_buttonWidth / 2 , (Gdx.graphics.getHeight() / 2) - 1.5f * saveMenuScreen_buttonHeight - 5,
				"LOAD", chosenSkin, "small");
		saveMenu_button3.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getSave_load_Menu().setSaveScreen(false);
				game.getSave_load_Menu().setLoadScreen(true);
				game.setScreen(game.getSave_load_Menu());
				stage.clear();

			}
		});

		stage.addActor(saveMenu_button1);
		stage.addActor(saveMenu_button2);
		stage.addActor(saveMenu_button3);

	}



	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		stage.draw();

		game.getBatch().end();

	}

	public TextButton create_TextButton (float buttonWidth, float buttonHeight, float position_X, float position_Y ,String ButtonText,Skin buttonSkin, String styleName ) {

		TextButton button = new TextButton("Text Button",buttonSkin, styleName);
		button.setSize(buttonWidth, buttonHeight);
		Label outputLabel = new Label(ButtonText, buttonSkin);
		button.setLabel(outputLabel);
		button.setPosition(position_X, position_Y);


		return button;


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
