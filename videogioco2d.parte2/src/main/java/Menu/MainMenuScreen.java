package Menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ScreenUtils;

import core.GameScreen;
import core.Game_Tiled;

public class MainMenuScreen implements Screen {
	
	
	

	final Game_Tiled game;

	OrthographicCamera camera;
	
	public Stage stage;
	
	Skin chosenSkin;

	private float mainMenuScreen_buttonWidth, mainMenuScreen_buttonHeight;
	
	public MainMenuScreen(final Game_Tiled game) {
		
		this.game = game;
		this.stage = new Stage();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		
		this.mainMenuScreen_buttonWidth = 160;
		this.mainMenuScreen_buttonHeight = 80;
		
		chosenSkin = new Skin(Gdx.files.internal("glassy-ui.json"));
	}

	@Override
	public void show() {
		
		
		Gdx.input.setInputProcessor(stage);
			
		
		TextButton start_Button = create_TextButton(mainMenuScreen_buttonWidth, mainMenuScreen_buttonHeight,
				(Gdx.graphics.getWidth() / 2) - (mainMenuScreen_buttonWidth / 2), (Gdx.graphics.getHeight() / 2) + 5,
				"START", chosenSkin, "small");
		start_Button.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.getGameScreen());
				stage.clear();
				
			}
		});
		
		TextButton load_button = create_TextButton(mainMenuScreen_buttonWidth, mainMenuScreen_buttonHeight,
				(Gdx.graphics.getWidth()/2) - (mainMenuScreen_buttonWidth / 2), (Gdx.graphics.getHeight() / 2) - (mainMenuScreen_buttonHeight) - 5, 
				"LOAD", chosenSkin, "small");		
		load_button.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getSave_load_Menu().setLoadScreen(true);
				game.getSave_load_Menu().setSaveScreen(false);
				/*Game_Tiled.save_load_Menu.show();*/
				
				game.setScreen(game.getSave_load_Menu());
				stage.clear();
				
			}
		});
		
		stage.addActor(start_Button);
		stage.addActor(load_button);
		

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

		this.dispose();
	}
	
	public TextButton create_TextButton (float buttonWidth, float buttonHeight, float position_X, float position_Y ,String ButtonText,Skin buttonSkin, String styleName ) {
		
		TextButton button = new TextButton("Text Button",buttonSkin, styleName);
		button.setSize(buttonWidth, buttonHeight);
		Label outputLabel = new Label(ButtonText, buttonSkin);
		button.setLabel(outputLabel);
		button.setPosition(position_X, position_Y);
		
		
		return button;
		
		
	}

}
