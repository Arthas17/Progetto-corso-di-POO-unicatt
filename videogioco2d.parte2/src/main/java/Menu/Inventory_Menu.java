package Menu;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import core.GameScreen;
import core.Game_Tiled;
import inventory.Item;
import objects.player.Player;
import objects.player.chest_Object;

public class Inventory_Menu implements Screen {	



	private final Game_Tiled game;

	private OrthographicCamera camera;

	private Stage stage;

	private Skin chosenSkin;

	private Texture skullFrame;
	private float skullFrameX, skullFrameY;
	private float skullFrameWidth, skullFrameHeight;

	private float equippedItemFrame_NorthX, equippedItemFrame_EastX, equippedItemFrame_SouthX,equippedItemFrame_WestX;
	private float equippedItemFrame_NorthY, equippedItemFrame_EastY, equippedItemFrame_SouthY,equippedItemFrame_WestY;

	private float equippedItemFrameWidth, equippedItemFrameHeight;

	private Texture mainChar;
	private float mainCharWidth, mainCharHeight;

	private Texture estus;
	private float estusX, estusY;
	private float estusWidth, estusHeight;

	ArrayList<CustomButton_InventoryMenu> customButtonsList;

	public Inventory_Menu (final Game_Tiled game) {

		this.game = game;



		this.stage = new Stage(new ScreenViewport());;

		this.chosenSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

		this.skullFrame = new Texture (Gdx.files.internal("skull frame.png"));
		this.skullFrameX = 300;
		this.skullFrameY = 450;
		this.skullFrameWidth = Gdx.graphics.getWidth()/4;
		this.skullFrameHeight = Gdx.graphics.getHeight() - skullFrameY;

		this.equippedItemFrameWidth = 150;
		this.equippedItemFrameHeight = 200;

		this.equippedItemFrame_NorthX = (skullFrameWidth / 2) - (equippedItemFrameWidth / 2) + skullFrameX;
		this.equippedItemFrame_NorthY = 200;
		this.equippedItemFrame_EastX = (skullFrameWidth / 2) - (equippedItemFrameWidth / 2) + skullFrameX + (equippedItemFrameWidth);
		this.equippedItemFrame_EastY = 200 - (200 / 2);
		this.equippedItemFrame_SouthX = equippedItemFrame_NorthX; 
		this.equippedItemFrame_SouthY = 0;
		this.equippedItemFrame_WestX = (skullFrameWidth / 2) - (equippedItemFrameWidth / 2) + skullFrameX - (equippedItemFrameWidth);
		this.equippedItemFrame_WestY = equippedItemFrame_EastY;


		this.mainChar = new Texture (Gdx.files.internal("mainCharInventory.png"));
		this.mainCharWidth = 250;
		this.mainCharHeight = 250;

		this.estus = new Texture (Gdx.files.internal("estus.png"));
		this.estusX = equippedItemFrame_EastX + 20; // "+20" is for a aesthetical effet there is no hidden logic behind
		this.estusY = equippedItemFrame_EastY + 40; //"+40" is for a aesthetical effet there is no hidden logic behind
		this.estusWidth = 110;
		this.estusHeight = 110;

		this.customButtonsList = new ArrayList<CustomButton_InventoryMenu>();


	}





	@Override
	public void show() {
		// TODO Auto-generated method stub

		Gdx.input.setInputProcessor(stage); //Start taking input from the ui 

		equippedItemsButton(skullFrame, equippedItemFrame_NorthX, equippedItemFrame_NorthY, equippedItemFrameWidth, equippedItemFrameHeight);
		equippedItemsButton(skullFrame, equippedItemFrame_SouthX, equippedItemFrame_SouthY, equippedItemFrameWidth, equippedItemFrameHeight);
		equippedItemsButton(skullFrame, equippedItemFrame_WestX, equippedItemFrame_WestY, equippedItemFrameWidth, equippedItemFrameHeight);


		drawItemsInTheInventory(chest_Object.commonItems, 1000, 900, 120, 110, 110);
		drawItemsInTheInventory(chest_Object.rareItems, 1000, 700, 120, 110, 110);
		drawItemsInTheInventory(chest_Object.mythicItems, 1000, 500, 120, 110, 110);
		drawItemsInTheInventory(chest_Object.legendaryItems, 1000, 300, 120, 110, 110);




	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();

		game.getBatch().setProjectionMatrix(camera.combined);

		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {

			stage.clear();

			game.setScreen(game.getGameScreen());
		}

		game.getBatch().begin();

		stage.draw();

		game.getBatch().draw(skullFrame, skullFrameX , skullFrameY, skullFrameWidth, skullFrameHeight);

		game.getBatch().draw(mainChar, skullFrameWidth / 2 - mainCharWidth / 2 + skullFrameX, skullFrameHeight + 10, 250, 250); // the "+10" is simply a visual feature, there is no logic behind it

		game.getBatch().draw(skullFrame, equippedItemFrame_EastX , equippedItemFrame_EastY, equippedItemFrameWidth, equippedItemFrameHeight);

		game.getBatch().draw(estus, equippedItemFrame_EastX + 20f, equippedItemFrame_EastY + 40f, 110, 110);



		if(Game_Tiled.itemsEquippedByThePlayer.get(0) != null) {
			game.getBatch().draw(Game_Tiled.itemsEquippedByThePlayer.get(0).getTexture(), equippedItemFrame_NorthX, equippedItemFrame_NorthY, equippedItemFrameWidth, equippedItemFrameHeight);
		}

		if(Game_Tiled.itemsEquippedByThePlayer.get(1) != null) {
			game.getBatch().draw(Game_Tiled.itemsEquippedByThePlayer.get(1).getTexture(), equippedItemFrame_WestX, equippedItemFrame_WestY, equippedItemFrameWidth, equippedItemFrameHeight);
		}


		if(Game_Tiled.itemsEquippedByThePlayer.get(2) != null) {
			game.getBatch().draw(Game_Tiled.itemsEquippedByThePlayer.get(2).getTexture(), equippedItemFrame_SouthX, equippedItemFrame_SouthY, equippedItemFrameWidth, equippedItemFrameHeight);
		}

		
		
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

	public void drawItemsInTheInventory(ArrayList<Item> itemsByRarity, float X_fromWhereStartDrawing, float Y_fromWhereStartDrawing, float visualSPaceBetweenItems, float itemsWidth, float itemsHeight) {

		float a = visualSPaceBetweenItems;
		int b = 0;
		boolean c = true;


		for (Item item: itemsByRarity) {

			if (item.isAlreadyFound() == true) {

				TextureRegionDrawable drawable = new TextureRegionDrawable(item.getTexture());
				TextureRegionDrawable drawable1 = new TextureRegionDrawable(item.getTexture());
				TextureRegionDrawable drawable2 = new TextureRegionDrawable(skullFrame);

				for (Item equippedItem: Game_Tiled.itemsEquippedByThePlayer) {
					if (equippedItem != null) {
						if (equippedItem.getName().equals(item.getName())) {
							drawable = new TextureRegionDrawable(skullFrame);
							drawable1 = new TextureRegionDrawable(skullFrame);
							drawable2 = new TextureRegionDrawable(item.getTexture());
							c = false;

						}
					}
				}

				CustomButton_InventoryMenu itemInInventory = new CustomButton_InventoryMenu(drawable, drawable1, drawable2);



				itemInInventory.setSize(itemsWidth, itemsHeight);

				itemInInventory.setPosition(X_fromWhereStartDrawing + b*(itemsWidth + a) , Y_fromWhereStartDrawing);


				if (c == true) {
					itemInInventory.addListener(new ChangeListener() {

						@Override
						public void changed(ChangeEvent event, Actor actor) {
							// TODO Auto-generated method stub


							if (itemInInventory.isChecked() == true) {
								


								for (int counter = 0; counter < Game_Tiled.itemsEquippedByThePlayer.size(); counter++) {
									if (Game_Tiled.itemsEquippedByThePlayer.get(counter) == null) {
										Game_Tiled.itemsEquippedByThePlayer.remove(counter);
										Game_Tiled.itemsEquippedByThePlayer.add(counter, item);
										break;
									}
								}
								
								item.setEquipped(true);

								statsModifier_AddItemEquipped(item);

							}
							if (itemInInventory.isChecked() == false) {
								


								for (int counter = 0; counter < Game_Tiled.itemsEquippedByThePlayer.size(); counter++) {
									if (Game_Tiled.itemsEquippedByThePlayer.get(counter) != null) {
										if(Game_Tiled.itemsEquippedByThePlayer.get(counter).getName().equals(item.getName())) {
											Game_Tiled.itemsEquippedByThePlayer.remove(counter);
											Game_Tiled.itemsEquippedByThePlayer.add(counter, Game_Tiled.dummyItem);
											break;
										}
									}

								}
								item.setEquipped(false);
								statsModifier_RemoveItemEquipped(item);
							}

						}

					});
				}

				if (c == false) {

					itemInInventory.addListener(new ChangeListener() {

						@Override
						public void changed(ChangeEvent event, Actor actor) {
							// TODO Auto-generated method stub


							if (itemInInventory.isChecked() == false) {
								

								for (int counter = 0; counter < Game_Tiled.itemsEquippedByThePlayer.size(); counter++) {
									if (Game_Tiled.itemsEquippedByThePlayer.get(counter) == null) {
										Game_Tiled.itemsEquippedByThePlayer.remove(counter);
										Game_Tiled.itemsEquippedByThePlayer.add(counter, item);
										break;
									}
								}
								item.setEquipped(true);
								statsModifier_AddItemEquipped(item);

							}
							if (itemInInventory.isChecked() == true) {
								

								for (int counter = 0; counter < Game_Tiled.itemsEquippedByThePlayer.size(); counter++) {
									if (Game_Tiled.itemsEquippedByThePlayer.get(counter) != null) {
										if(Game_Tiled.itemsEquippedByThePlayer.get(counter).getName().equals(item.getName())) {
											Game_Tiled.itemsEquippedByThePlayer.remove(counter);
											Game_Tiled.itemsEquippedByThePlayer.add(counter, Game_Tiled.dummyItem);
											break;
										}
										
										
									}




								}
								item.setEquipped(false);
								statsModifier_RemoveItemEquipped(item);
							}

						}

					});

				}

				customButtonsList.add(itemInInventory);
				stage.addActor(itemInInventory);
				b++;

			}
		}






	}

	public void equippedItemsButton (Texture texture, float x, float y, float width, float height) {

		TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
		TextureRegionDrawable drawable2 = new TextureRegionDrawable(estus);
		CustomButton_InventoryMenu button = new CustomButton_InventoryMenu(drawable, drawable,drawable2);
		button.setSize(width, height);
		button.setPosition(x, y);
		button.addListener(new InputListener() {

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				// TODO Auto-generated method stub
				super.enter(event, x, y, pointer, fromActor);

				for (CustomButton_InventoryMenu button1: customButtonsList) {

					boolean a = button1.isChecked();
					boolean b = !a;
					button1.setChecked(b);
				}

			}

		});

		stage.addActor(button);



	}


	public void statsModifier_AddItemEquipped(Item chosenItem) {

		GameScreen gameScreen = game.getGameScreen();
		Player player = gameScreen.getPlayer();


		float playerMaxHealth = player.getMax_Health();
		float playerAttack = player.getAttack();		
		float playerDamageReduction = player.getDamegeReduction();
		float playerAttackSpeed = player.getAttackSpeed();



		player.setMax_Health(playerMaxHealth + (chosenItem.getHp_Boost()) - (chosenItem.getHp_Reduction()));

		player.setAttack(playerAttack + (chosenItem.getAttack_Boost()) - (chosenItem.getAttack_Reduction()));

		player.setDamegeReduction(playerDamageReduction + (chosenItem.getResistance_Boost()) - (chosenItem.getResistance_Reduction()));

		player.setAttackSpeed(playerAttackSpeed + (chosenItem.getSpeed_boost()) - (chosenItem.getSpeed_Reduction()));


	}


	public void statsModifier_RemoveItemEquipped(Item chosenItem) {

		GameScreen gameScreen = game.getGameScreen();
		Player player = gameScreen.getPlayer();


		float playerMaxHealth = player.getMax_Health();
		float playerAttack = player.getAttack();		
		float playerDamageReduction = player.getDamegeReduction();
		float playerAttackSpeed = player.getAttackSpeed();



		player.setMax_Health(playerMaxHealth - (chosenItem.getHp_Boost()) + (chosenItem.getHp_Reduction()));

		player.setAttack(playerAttack - (chosenItem.getAttack_Boost()) + (chosenItem.getAttack_Reduction()));

		player.setDamegeReduction(playerDamageReduction - (chosenItem.getResistance_Boost()) + (chosenItem.getResistance_Reduction()));

		player.setAttackSpeed(playerAttackSpeed - (chosenItem.getSpeed_boost()) + (chosenItem.getSpeed_Reduction()));


	}



}
