package Menu;

import java.awt.event.ItemEvent;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ScreenUtils;

import core.GameScreen;
import core.Game_Tiled;
import helper.TileMapHelper;
import inventory.Item;
import objects.player.Player;
import objects.player.chest_Object;

public class Save_Load_Menu implements Screen {

	private final Game_Tiled game;

	private OrthographicCamera camera;

	private Stage stage;


	private boolean isLoadScreen;
	private boolean isSaveScreen;

	private Skin chosenSkin;

	private float save_load_Menu_buttonWidth;
	private float save_load_Menu_buttonHeight;

	private float overWrite_Dialog_Width;
	private float overWrite_Dialog_Height;
	private float overWrite_Dialog_ButtonWidth;
	private float overWrite_Dialog_ButtonHeight;

	private String keyWord_button1;
	private String keyWord_button2;
	private String keyWord_button3;


	public Save_Load_Menu(final Game_Tiled game) {

		this.game = game;

		this.stage = new Stage();



		this.chosenSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

		this.save_load_Menu_buttonWidth = 160;
		this.save_load_Menu_buttonHeight = 80;

		this.overWrite_Dialog_Width = 400;
		this.overWrite_Dialog_Height = 200;
		this.overWrite_Dialog_ButtonWidth = 160;
		this.overWrite_Dialog_ButtonHeight = 80;

		this.keyWord_button1 = "whichPreferencesIsSavedInButton1";
		this.keyWord_button2 = "whichPreferencesIsSavedInButton2";
		this.keyWord_button3 = "whichPreferencesIsSavedInButton3";

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);



	}



	public void setLoadScreen(boolean isLoadScreen) {
		this.isLoadScreen = isLoadScreen;
	}



	public void setSaveScreen(boolean isSaveScreen) {
		this.isSaveScreen = isSaveScreen;
	}



	@Override
	public void show() {

		GameScreen gameScreen = game.getGameScreen();
		ArrayList<Preferences> listOfPreferences = gameScreen.getListOfPreferences();
		Preferences chosenPreferences = gameScreen.chosenPreferences_selector(); 

		Gdx.input.setInputProcessor(stage);


		TextButton button1 = new TextButton("Text Button",chosenSkin,"small");
		button1.setSize(save_load_Menu_buttonWidth, save_load_Menu_buttonHeight);
		if (isSaveScreen == true) {
			Label outputLabel1 = new Label("save1", chosenSkin);
			button1.setLabel(outputLabel1);

			try {

				if (listOfPreferences.get(Integer.parseInt(chosenPreferences.getString(keyWord_button1))).getBoolean("alreadySavedSomethingIn") == true) {

					button1.addListener(new ChangeListener() {

						@Override
						public void changed(ChangeEvent event, Actor actor) {


							Dialog overWrite_Dialog = create_OverWriteDialog(overWrite_Dialog_Width, overWrite_Dialog_Height, true, 0, 0, 0.2f, 1, "In questo salvataggio sono presenti dei dati desideri sovrascriverli?",chosenSkin);

							TextButton overWriteDialog_button1 = create_TextButton(overWrite_Dialog_ButtonWidth, overWrite_Dialog_ButtonHeight, "si'", chosenSkin, "small");

							overWriteDialog_button1.addListener(new ChangeListener() {

								@Override
								public void changed(ChangeEvent event, Actor actor) {

									stage.clear();
									overWrite_save(keyWord_button1);



								}
							});

							TextButton overWriteDialog_button2 = create_TextButton(80, 160, "no", chosenSkin, "small");
							overWriteDialog_button2.addListener(new ChangeListener() {

								@Override
								public void changed(ChangeEvent event, Actor actor) {
									stage.clear();
									game.setScreen(game.getSave_load_Menu());

								}
							});

							overWrite_Dialog.button(overWriteDialog_button1);
							overWrite_Dialog.button(overWriteDialog_button2);
							overWrite_Dialog.show(stage);

						}
					});
				}

			} catch (Exception e) {
				// TODO: handle exception

				button1.addListener(new ChangeListener() {

					@Override
					public void changed(ChangeEvent event, Actor actor) {

						save(keyWord_button1);					

					}
				});
			}
		}


		if (isLoadScreen == true) {
			Label outputLabel1 = new Label("load1", chosenSkin);
			button1.setLabel(outputLabel1);



			button1.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {

					load(keyWord_button1);
					stage.clear();

				}
			});
		}
		button1.setPosition((Gdx.graphics.getWidth()/2) - save_load_Menu_buttonWidth / 2 , (Gdx.graphics.getHeight() / 2) + 1.5f * save_load_Menu_buttonHeight + 7.5f);


		TextButton button2 = new TextButton("Text Button",chosenSkin,"small");
		button2.setSize(save_load_Menu_buttonWidth, save_load_Menu_buttonHeight);
		if (isSaveScreen == true) {
			Label outputLabel2 = new Label("save2", chosenSkin);
			button2.setLabel(outputLabel2);

			try {

				if (listOfPreferences.get(Integer.parseInt(chosenPreferences.getString(keyWord_button2))).getBoolean("alreadySavedSomethingIn") == true) {

					button2.addListener(new ChangeListener() {

						@Override
						public void changed(ChangeEvent event, Actor actor) {


							Dialog overWrite_Dialog = create_OverWriteDialog(overWrite_Dialog_Width, overWrite_Dialog_Height, true, 0, 0, 0.2f, 1, "In questo salvataggio sono presenti dei dati desideri sovrascriverli?",chosenSkin);

							TextButton overWriteDialog_button1 = create_TextButton(overWrite_Dialog_ButtonWidth, overWrite_Dialog_ButtonHeight, "si'", chosenSkin, "small");

							overWriteDialog_button1.addListener(new ChangeListener() {

								@Override
								public void changed(ChangeEvent event, Actor actor) {

									stage.clear();
									overWrite_save(keyWord_button2);

								}
							});

							TextButton overWriteDialog_button2 = create_TextButton(80, 160, "no", chosenSkin, "small");
							overWriteDialog_button2.addListener(new ChangeListener() {

								@Override
								public void changed(ChangeEvent event, Actor actor) {
									stage.clear();
									game.setScreen(game.getSave_load_Menu());

								}
							});

							overWrite_Dialog.button(overWriteDialog_button1);
							overWrite_Dialog.button(overWriteDialog_button2);
							overWrite_Dialog.show(stage);

						}
					});
				}

			} catch (Exception e) {
				// TODO: handle exception

				button2.addListener(new ChangeListener() {

					@Override
					public void changed(ChangeEvent event, Actor actor) {

						save(keyWord_button2);					

					}
				});
			}
		}


		if (isLoadScreen == true) {
			Label outputLabel2 = new Label("load2", chosenSkin);
			button2.setLabel(outputLabel2);



			button2.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {

					load(keyWord_button2);
					stage.clear();

				}
			});
		}
		button2.setPosition((Gdx.graphics.getWidth()/2) - (save_load_Menu_buttonWidth) / 2 , (Gdx.graphics.getHeight() / 2) + 0.5f * save_load_Menu_buttonHeight + 2.5f);

		TextButton button3 = new TextButton("Text Button",chosenSkin,"small");
		button3.setSize(save_load_Menu_buttonWidth, save_load_Menu_buttonHeight);
		if (isSaveScreen == true) {
			Label outputLabel3 = new Label("save3", chosenSkin);
			button3.setLabel(outputLabel3);

			try {

				if (listOfPreferences.get(Integer.parseInt(chosenPreferences.getString(keyWord_button3))).getBoolean("alreadySavedSomethingIn") == true) {

					button3.addListener(new ChangeListener() {

						@Override
						public void changed(ChangeEvent event, Actor actor) {


							Dialog overWrite_Dialog = create_OverWriteDialog(overWrite_Dialog_Width, overWrite_Dialog_Height, true, 0, 0, 0.2f, 1, "In questo salvataggio sono presenti dei dati desideri sovrascriverli?",chosenSkin);

							TextButton overWriteDialog_button1 = create_TextButton(overWrite_Dialog_ButtonWidth, overWrite_Dialog_ButtonHeight, "si'", chosenSkin, "small");

							overWriteDialog_button1.addListener(new ChangeListener() {

								@Override
								public void changed(ChangeEvent event, Actor actor) {

									stage.clear();
									overWrite_save(keyWord_button3);


								}
							});

							TextButton overWriteDialog_button2 = create_TextButton(80, 160, "no", chosenSkin, "small");
							overWriteDialog_button2.addListener(new ChangeListener() {

								@Override
								public void changed(ChangeEvent event, Actor actor) {
									stage.clear();
									game.setScreen(game.getSave_load_Menu());

								}
							});

							overWrite_Dialog.button(overWriteDialog_button1);
							overWrite_Dialog.button(overWriteDialog_button2);
							overWrite_Dialog.show(stage);

						}
					});
				}

			} catch (Exception e) {
				// TODO: handle exception

				button3.addListener(new ChangeListener() {

					@Override
					public void changed(ChangeEvent event, Actor actor) {

						save(keyWord_button3);					

					}
				});
			}
		}


		if (isLoadScreen == true) {
			Label outputLabel3 = new Label("load3", chosenSkin);
			button3.setLabel(outputLabel3);



			button3.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {

					load(keyWord_button3);
					stage.clear();

				}
			});
		}
		button3.setPosition((Gdx.graphics.getWidth()/2) - (save_load_Menu_buttonWidth) / 2 , (Gdx.graphics.getHeight() / 2) - 0.5f * save_load_Menu_buttonHeight - 2.5f);



		TextButton button4 = new TextButton("Text Button",chosenSkin,"small");
		button4.setSize(save_load_Menu_buttonWidth,save_load_Menu_buttonHeight);
		Label outputLabel4 = new Label("back", chosenSkin);
		button4.setLabel(outputLabel4);
		button4.setPosition((Gdx.graphics.getWidth()/2) - save_load_Menu_buttonWidth / 2 , ((Gdx.graphics.getHeight() / 2) - 1.5f * save_load_Menu_buttonHeight - 7.5f));
		button4.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				game.setScreen(game.getSaveMenuScreen());
				stage.clear();

			}
		});

		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);




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

	}


	public Dialog create_OverWriteDialog(float dialogWIdth, float dialogHeight, boolean isVisible , float r, float g, float b, float alpha, String text, Skin skin) {

		Dialog overWrite_Dialog = new Dialog("", skin);
		overWrite_Dialog.setSize(dialogWIdth, dialogHeight);
		overWrite_Dialog.setVisible(isVisible);
		overWrite_Dialog.setColor(r, g, b, alpha);
		overWrite_Dialog.text(text);

		return overWrite_Dialog;
	}

	public TextButton create_TextButton (float buttonWidth, float buttonHeight, String ButtonText,Skin buttonSkin, String styleName ) {

		TextButton button = new TextButton("Text Button",buttonSkin, styleName);
		button.setSize(buttonWidth, buttonHeight);
		Label outputLabel = new Label(ButtonText, buttonSkin);
		button.setLabel(outputLabel);



		return button;


	}


	public void overWrite_save(String keyWord) {


		GameScreen gameScreen = game.getGameScreen();
		ArrayList<Preferences> listOfPreferences = gameScreen.getListOfPreferences();
		Preferences chosenPreferences = gameScreen.chosenPreferences_selector();

		String keyword = chosenPreferences.getString(keyWord);
		String newPreferencesSavedIn = chosenPreferences.getString("Preference_Marker");
		for (int counter = 0; counter < gameScreen.getNumberOfOPreferences(); counter++) {
			listOfPreferences.get(counter).remove(keyWord);
			listOfPreferences.get(counter).flush();
			listOfPreferences.get(counter).putString(keyWord, newPreferencesSavedIn);
			listOfPreferences.get(counter).flush();
		}

		chosenPreferences.putBoolean("alreadySavedSomethingIn", true);
		chosenPreferences.flush();

		gameScreen.setChosenPreference(listOfPreferences.get((Integer.parseInt(keyword))));

		gameScreen.getChosenPreference().putBoolean("alreadySavedSomethingIn", false);
		gameScreen.getChosenPreference().flush();

		game.setScreen(game.getSave_load_Menu());
	}



	public void save (String keyWord) {

		GameScreen gameScreen = game.getGameScreen();
		ArrayList<Preferences> listOfPreferences = gameScreen.getListOfPreferences();
		Preferences chosenPreferences = gameScreen.chosenPreferences_selector();

		if (game.isIsTheGameLoaded() == false && game.isIsTheGameSaved() == false) {

			float body_PosX = gameScreen.getDefault_preferences().getFloat("body position x");
			chosenPreferences.putFloat("body position x", body_PosX);
			chosenPreferences.flush();

			float body_PosY = gameScreen.getDefault_preferences().getFloat("body position y");
			chosenPreferences.putFloat("body position y", body_PosY);
			chosenPreferences.flush();


			for (int counter = 0; counter < gameScreen.getAllChest().size(); counter++ ) {

				boolean chestSituation = gameScreen.getDefault_preferences().getBoolean("chest" + Integer.toString(counter));
				chosenPreferences.putBoolean("chest" + Integer.toString(counter), chestSituation);
				chosenPreferences.flush();

			}

			saveChestContent(chest_Object.commonItems, gameScreen.getDefault_preferences(), chosenPreferences);
			saveChestContent(chest_Object.rareItems, gameScreen.getDefault_preferences(), chosenPreferences);
			saveChestContent(chest_Object.mythicItems, gameScreen.getDefault_preferences(), chosenPreferences);
			saveChestContent(chest_Object.legendaryItems, gameScreen.getDefault_preferences(), chosenPreferences);

			for (int counter = 0; counter < Game_Tiled.itemsEquippedByThePlayer.size(); counter++) {
				String itemEquipped = gameScreen.getDefault_preferences().getString("equippedItem" + Integer.toString(counter));
				chosenPreferences.putString("equippedItem" + Integer.toString(counter), itemEquipped);
				chosenPreferences.flush();

			}

			float maxHealth = gameScreen.getDefault_preferences().getFloat("maxHealth");
			chosenPreferences.putFloat("maxHealth", maxHealth);
			chosenPreferences.flush();

			float health = gameScreen.getDefault_preferences().getFloat("health");
			chosenPreferences.putFloat("health", health);
			chosenPreferences.flush();



		}


		String whichPreferencesIsSavedInThisButton = chosenPreferences.getString("Preference_Marker");

		for (int counter = 0; counter < gameScreen.getNumberOfOPreferences(); counter++) {
			Preferences preferences = listOfPreferences.get(counter);
			preferences.putString(keyWord,whichPreferencesIsSavedInThisButton);
			preferences.flush();
		}

		chosenPreferences.putBoolean("alreadySavedSomethingIn", true);
		chosenPreferences.flush();

		Preferences newChosenPreferences = gameScreen.chosenPreferences_selector();
		gameScreen.setChosenPreference(newChosenPreferences);
		gameScreen.getChosenPreference().flush();

		game.setIsTheGameSaved(true);

	}



	public void saveChestContent(ArrayList<Item> ArrayListOfItemByRarity, Preferences preferences,Preferences chosenPreferences ) {


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

			boolean itemFoundOrNot = preferences.getBoolean(key + Integer.toString(counter));
			chosenPreferences.putBoolean(key + Integer.toString(counter), itemFoundOrNot);
			chosenPreferences.flush();
		}



	}

	public void load(String keyWord) {

		GameScreen gameScreen = game.getGameScreen();
		Preferences chosenPreferences = gameScreen.chosenPreferences_selector();
		ArrayList<Preferences> listOfPreferences = gameScreen.getListOfPreferences();



		String whichPreferencesIsSavedInThisButton = chosenPreferences.getString(keyWord);

		Preferences preferencesSelected = listOfPreferences.get(Integer.parseInt(whichPreferencesIsSavedInThisButton));

		Float playerX = preferencesSelected.getFloat("body position x");
		Float playerY = preferencesSelected.getFloat("body position y");

		game.setIsTheGameLoaded(true);

		TileMapHelper.mainCharSpawnX_Modified = playerX;
		TileMapHelper.mainCharSpawnY_Modified = playerY;

		TileMapHelper.chestLayout.clear();
		for (int counter = 0; counter < gameScreen.getAllChest().size(); counter++) {

			boolean thisChestLayout = preferencesSelected.getBoolean("chest" + Integer.toString(counter));
			TileMapHelper.chestLayout.add(thisChestLayout);


		}

		loadItems(chest_Object.commonItems, preferencesSelected);
		loadItems(chest_Object.rareItems, preferencesSelected);
		loadItems(chest_Object.mythicItems, preferencesSelected);
		loadItems(chest_Object.legendaryItems, preferencesSelected);

		int listSize = Game_Tiled.itemsEquippedByThePlayer.size();

		Game_Tiled.itemsEquippedByThePlayer.clear();


		for (int counter = 0; counter < listSize; counter++) {

			String itemEquipped = preferencesSelected.getString("equippedItem" + Integer.toString(counter));

			if (itemEquipped.equals("null") == true) {

				System.out.println("ciao");


				Game_Tiled.itemsEquippedByThePlayer.add(Game_Tiled.dummyItem);				

			}

			if (itemEquipped.equals("null") == false){

				for (Item itemInTheGame : Game_Tiled.allItemsInTheGame) {
					if (itemInTheGame.getName().equals(itemEquipped)) {
						Game_Tiled.itemsEquippedByThePlayer.add(itemInTheGame);

					}
				}
			}

		}




		gameScreen.cameraInitialSettings();

		game.setGameScreen(new GameScreen(game, game.getOrthographicCamera()));		

		game.setScreen(game.getGameScreen() );


	}

	public void loadItems (ArrayList<Item> ArrayListOfItemByRarity, Preferences chosenPreferences ) {

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

			boolean itemFoundOrNot = chosenPreferences.getBoolean(key + Integer.toString(counter));
			ArrayListOfItemByRarity.get(counter).setAlreadyFound(itemFoundOrNot);
		}


	}
}





