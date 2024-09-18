package objects.player;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import Menu.Looting_Menu;
import core.GameScreen;
import core.Game_Tiled;
import helper.Costants;
import inventory.Item;

public class chest_Object extends GameEntity{
	
	final Game_Tiled game;
	protected float x, y, velX, velY, speed;
	protected float width,height;
	protected Body body;
	
	private float chestWidth, chestHeight;
	private float chestSpawnX_Default, chestSpawnY_Default;
	
	private Texture closed_Chest;
	private Texture opened_Chest;
	private Texture looted_Chest;
	
	private Texture chosenTexture;
	
	public static ArrayList<Item> commonItems = new ArrayList<Item>();;
	public static ArrayList<Item> rareItems = new ArrayList<Item>();;
	public static ArrayList<Item> mythicItems = new ArrayList<Item>();;
	public static ArrayList<Item> legendaryItems = new ArrayList<Item>();;
	
	private boolean opened;
	private boolean looted;
	
	
	
	public chest_Object (Game_Tiled game ,float width, float height, Body body) {
		
		super(game, width, height, body);
		
		
		this.game = game;
		this.width = width;
		this.height = height;
		this.body = body;
		
		this.closed_Chest = new Texture (Gdx.files.internal("chest.png"));
		this.opened_Chest = new Texture (Gdx.files.internal("opened_chest.png"));
		this.looted_Chest = new Texture (Gdx.files.internal("looted_chest.png"));
		
		
		this.chosenTexture = closed_Chest;
		
		
	}
	
	public void setChosenTexture() {
		
		if (isLooted() == true) {
			this.chosenTexture = looted_Chest;
			
		}
		
	}
	
	public boolean inRangeForLoot(){
		
		boolean inRangeForLoot = false;
		
		float mainCharPosX = game.getGameScreen().getPlayer().x;
		float mainCharPosY = game.getGameScreen().getPlayer().y;
		
		if (Math.abs(chestSpawnX_Default - mainCharPosX) < 15 && Math.abs(chestSpawnY_Default - mainCharPosY) < 15 ) {
			
			inRangeForLoot = true;
		}		
		
		return inRangeForLoot;
		
	}
	
	public void chestOpening() {
					
				chosenTexture = opened_Chest;
				
				Timer timer = new Timer();
				timer.scheduleTask(new Task() {
					
					@Override
					public void run() {
						
						Item itemFound = looting();
						
						Looting_Menu lootingMenu = new Looting_Menu(game, itemFound);
						
						game.setScreen(lootingMenu);
					}
				}
						
					, 2);
				
				
			
	}
	
			
			

	
	public Item looting() {
		
		Item chosenItem = null;
		
		int randomNumber = getRandomIntegerBetweenRange(0, 100);
		
		
		
		if (randomNumber < 60) {
			
			int randomItem = getRandomIntegerBetweenRange(0, commonItems.size() - 1);
			chosenItem = commonItems.get(randomItem);
		
			
			int counter = 0;
			while (chosenItem.isAlreadyFound() == true && counter == 0) {
				int randomItem1 = getRandomIntegerBetweenRange(0, commonItems.size() - 1);
				Item chosenItem1 = commonItems.get(randomItem1);
				if (chosenItem1.isAlreadyFound() == false) {
					chosenItem = chosenItem1;
					counter++;
				}
				
			}
			
			
			
			
		}
		
		if ( randomNumber >= 60 && randomNumber < 90 ) {
			
			int randomItem = getRandomIntegerBetweenRange(0, rareItems.size() - 1);
			chosenItem = rareItems.get(randomItem);
			
			int counter = 0;
			if (chosenItem.isAlreadyFound() == true && counter == 0) {
				int randomItem1 = getRandomIntegerBetweenRange(0, rareItems.size() - 1);
				Item chosenItem1 = rareItems.get(randomItem1);
				if (chosenItem1.isAlreadyFound() == false) {
					chosenItem = chosenItem1;
					counter++;
				}
				
			}
			
			
			
						
		}
		
		if ( randomNumber >= 90 && randomNumber < 98 ) {
			
			int randomItem = getRandomIntegerBetweenRange(0, mythicItems.size() - 1);
			chosenItem = mythicItems.get(randomItem);
			
			int counter = 0;
			if (chosenItem.isAlreadyFound() == true && counter == 0) {
				int randomItem1 = getRandomIntegerBetweenRange(0, mythicItems.size() - 1);
				Item chosenItem1 = mythicItems.get(randomItem1);
				if (chosenItem1.isAlreadyFound() == false) {
					chosenItem = chosenItem1;
					counter++;
				}
				
			}
			
			
		}
		
		if (randomNumber >= 98 ) {
			
			int randomItem = getRandomIntegerBetweenRange(0, legendaryItems.size() - 1);
			chosenItem = legendaryItems.get(randomItem);
			
			int counter = 0;
			if (chosenItem.isAlreadyFound() == true && counter == 0) {
				int randomItem1 = getRandomIntegerBetweenRange(0, legendaryItems.size() - 1);
				Item chosenItem1 = legendaryItems.get(randomItem1);
				if (chosenItem1.isAlreadyFound() == false) {
					chosenItem = chosenItem1;
					counter++;
				}
				
			}
			
			
		}
		
		this.setChosenTexture(looted_Chest);
		this.setLooted(true);
		
		chosenItem.setAlreadyFound(true);
			
		
		return chosenItem;
			

		
	}
	
	public int getRandomIntegerBetweenRange(int min, int max) {
		
		Double d = (Math.random()*((max - min) + 1)) + min;
		return d.intValue();
	}
	

	
	
	
	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isLooted() {
		return looted;
	}

	public void setLooted(boolean looted) {
		this.looted = looted;
	}

	public Texture getChosenTexture() {
		return chosenTexture;
	}

	public void setChosenTexture(Texture chosenTexture) {
		this.chosenTexture = chosenTexture;
	}

	public Texture getClosed_Chest() {
		return closed_Chest;
	}




	public void setClosed_Chest(Texture closed_Chest) {
		this.closed_Chest = closed_Chest;
	}
	
	




	public Texture getOpened_Chest() {
		return opened_Chest;
	}




	public void setOpened_Chest(Texture opened_Chest) {
		this.opened_Chest = opened_Chest;
	}




	public Texture getLooted_Chest() {
		return looted_Chest;
	}




	public void setLooted_Chest(Texture looted_Chest) {
		this.looted_Chest = looted_Chest;
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
	
	




	public Body getBody() {
		return body;
	}

	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	

}
