package inventory;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import core.Game_Tiled;
import objects.player.chest_Object;

public class Item {

	final Game_Tiled game;
	
	private String name;
	private String rarity; // "Legendary" , "Mythic" , "Rare" , "Common" ;

	private float hp_Boost, hp_Reduction ,
	attack_Boost, attack_Reduction, 
	speed_boost, speed_Reduction, 
	resistance_Boost, resistance_Reduction;
	
	private boolean alreadyFound;
	private boolean equipped;
	private boolean itsButtonInInventoryIsClicked;
	
	private Texture texture;
	
		
	private Item (final Game_Tiled game, Texture texture, String name, String rarity, float hp_boost, float hp_reduction, float attack_Boost, float attack_Reduction, 
			float speed_Boost, float speed_Reduction, float resistance_Boost, float resistance_Reduction) {
		
		this.game = game;
		this.texture = texture;
		this.name = name;
		this.rarity = rarity;
		this.hp_Boost = hp_boost;
		this.hp_Reduction = hp_reduction;
		this.attack_Boost = attack_Boost;
		this.attack_Reduction = attack_Reduction;
		this.speed_boost = speed_Boost;
		this.speed_Reduction = speed_Reduction;
		this.resistance_Boost = resistance_Boost;
		this.resistance_Reduction = resistance_Reduction;
	}


	public static void classify_Item(Item item) {

		if (item.rarity == "Legendary") {

			chest_Object.legendaryItems.add(item);
		}

		if (item.rarity == "Mythic") {

			chest_Object.mythicItems.add(item);
		}

		if (item.rarity == "Rare") {

			chest_Object.rareItems.add(item);
		}

		if (item.rarity == "Common") {

			chest_Object.commonItems.add(item);
		}

	}
	
	public static void fill_The_Chests(Game_Tiled game) {
		
		ArrayList<Item> AllItems = Game_Tiled.allItemsInTheGame;
		
		String legendaryRarity = "Legendary";
		String mythicRarity = "Mythic";
		String rareRarity = "Rare";
		String commonRarity = "Common";
		
		Texture IceShardOfTheScourgeTexture = new Texture (Gdx.files.internal("iceGem.png")); 
		Item IceShardOfTheScourge = new Item(game, IceShardOfTheScourgeTexture,"ICE SHARD OF THE RESTLESS SCOURGE", legendaryRarity, //Item name, rarity, texture
				20, 0, // hpBoost, hpReduction
				10, 0, // attackBoost, attackReduction
				0, 0, // speedBoost, speedReduction
				50, 0); // resistanceBoost, resistanceReduction
		AllItems.add(IceShardOfTheScourge);
		
		Texture FlameOfOlympusTexture = new Texture (Gdx.files.internal("flame.png")); 
		Item FlameOfOlympus = new Item(game, FlameOfOlympusTexture, "FLAME OF OLYMPUS", mythicRarity,
				0, 15, // hpBoost, hpReduction
				30, 0, // attackBoost, attackReduction
				0, 5, // speedBoost, speedReduction
				0, 20); // resistanceBoost, resistanceReduction
		AllItems.add(FlameOfOlympus);
		
		Texture tomeOfTheKirinTorTexture = new Texture (Gdx.files.internal("tome.png")); 
		Item tomeOfTheKirinTor = new Item(game, tomeOfTheKirinTorTexture,"TOME OF THE KIRIN TOR", rareRarity,
				0, 0, // hpBoost, hpReduction
				5, 0, // attackBoost, attackReduction
				0, 0, // speedBoost, speedReduction
				5, 0); // resistanceBoost, resistanceReduction
		AllItems.add(tomeOfTheKirinTor);
		
		Texture cinderOfGwynTexture = new Texture (Gdx.files.internal("cinder.png")); 
		Item cinderOfGwyn = new Item(game, cinderOfGwynTexture,"CINDER OF GWYN", commonRarity,
				5, 0, // hpBoost, hpReduction
				0, 0, // attackBoost, attackReduction
				5, 5, // speedBoost, speedReduction
				0, 0); // resistanceBoost, resistanceReduction
		AllItems.add(cinderOfGwyn);
		
		Texture bucket = new Texture (Gdx.files.internal("bucket.png")); 
		Item bucket1 = new Item(game, bucket,"SUPREEEEME BUCKET", commonRarity,
				5, 0, // hpBoost, hpReduction
				0, 0, // attackBoost, attackReduction
				5, 5, // speedBoost, speedReduction
				0, 0); // resistanceBoost, resistanceReduction
		AllItems.add(bucket1);
		
		
		
		
		for (Item item : AllItems) {
			classify_Item(item);
			
		}
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	

	public Texture getTexture() {
		return texture;
	}


	public String getRarity() {
		return rarity;
	}


	public void setRarity(String rarity) {
		this.rarity = rarity;
	}


	public float getHp_Boost() {
		return hp_Boost;
	}


	public void setHp_Boost(float hp_Boost) {
		this.hp_Boost = hp_Boost;
	}


	public float getHp_Reduction() {
		return hp_Reduction;
	}


	public void setHp_Reduction(float hp_Reduction) {
		this.hp_Reduction = hp_Reduction;
	}


	public float getAttack_Boost() {
		return attack_Boost;
	}


	public void setAttack_Boost(float attack_Boost) {
		this.attack_Boost = attack_Boost;
	}


	public float getAttack_Reduction() {
		return attack_Reduction;
	}


	public void setAttack_Reduction(float attack_Reduction) {
		this.attack_Reduction = attack_Reduction;
	}


	public float getSpeed_boost() {
		return speed_boost;
	}


	public void setSpeed_boost(float speed_boost) {
		this.speed_boost = speed_boost;
	}


	public float getSpeed_Reduction() {
		return speed_Reduction;
	}


	public void setSpeed_Reduction(float speed_Reduction) {
		this.speed_Reduction = speed_Reduction;
	}


	public float getResistance_Boost() {
		return resistance_Boost;
	}


	public void setResistance_Boost(float resistance_Boost) {
		this.resistance_Boost = resistance_Boost;
	}


	public float getResistance_Reduction() {
		return resistance_Reduction;
	}


	public void setResistance_Reduction(float resistance_Reduction) {
		this.resistance_Reduction = resistance_Reduction;
	}


	public boolean isAlreadyFound() {
		return alreadyFound;
	}


	public void setAlreadyFound(boolean alreadyFound) {
		this.alreadyFound = alreadyFound;
	}


	public boolean isEquipped() {
		return equipped;
	}


	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}


	public boolean isItsButtonInInventoryIsClicked() {
		return itsButtonInInventoryIsClicked;
	}


	public void setItsButtonInInventoryIsClicked(boolean itsButtonInInventoryIsClicked) {
		this.itsButtonInInventoryIsClicked = itsButtonInInventoryIsClicked;
	}
	
	

	
	


}
