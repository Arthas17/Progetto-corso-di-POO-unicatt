package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import core.Game_Tiled;

public class youDied_Menu implements Screen{
	
	private final Game_Tiled game;

	private OrthographicCamera camera;
	
	private SpriteBatch batch;
	
	private Texture youDied;
	
	private float youDiedWidth, youDiedHeight;
	
	public youDied_Menu(final Game_Tiled game) {
		 
		this.game = game;
		
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		this.batch = new SpriteBatch();
		
		this.youDied = new Texture(Gdx.files.internal("youDied.png"));
		this.youDiedWidth = 848;
		this.youDiedHeight = 84;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			/*player.updateInventoryMenu();*/
			Gdx.app.exit();

		}

		
		batch.begin();
		batch.draw(youDied, Gdx.graphics.getWidth() / 2 - youDiedWidth / 2, Gdx.graphics.getHeight() / 2 - youDiedHeight / 2, youDiedWidth, youDiedHeight);
		batch.end();
		
		

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
		
		batch.dispose();
	}

}
