package core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import helper.Costants;

import objects.player.Player;
import objects.player.GameEntity;

public class Animator {
	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS_WALKANIMATION = 15, FRAME_ROWS_WALKANIMATION = 8;
	private static final int FRAME_COLS_STEADY = 1, FRAME_ROWS_STEADY = 8;
	
	public boolean diagonalWalk;
	public boolean movement_error;
	

	// Objects used
	private Animation<TextureRegion> walkAnimationUp; 
	private Animation<TextureRegion> walkAnimationUp_Right;
	private Animation<TextureRegion> walkAnimationRight; 
	private Animation<TextureRegion> walkAnimationDown_Right;
	private Animation<TextureRegion> walkAnimationDown; 
	private Animation<TextureRegion> walkAnimationDown_Left;
	private Animation<TextureRegion> walkAnimationLeft; 
	private Animation<TextureRegion> walkAnimationUp_Left;
	
	private Animation<TextureRegion> up_Steady_Position;
	private Animation<TextureRegion> up_Right_SteadyPosition;
	private Animation<TextureRegion> right_Steady_Position;
	private Animation<TextureRegion> down_Right_Steady_Position;
	private Animation<TextureRegion> down_Steady_Position;
	private Animation<TextureRegion> downLeft_Steady_Position;
	private Animation<TextureRegion> left_Steady_Position;
	private Animation<TextureRegion> up_left_Steady_Position;

	private TextureRegion[] upWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];		
	private TextureRegion[] up_RightWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];
	private TextureRegion[] rightWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];
	private TextureRegion[] down_RightWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];
	private TextureRegion[] downWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];
	private TextureRegion[] downLeftWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION]; 
	private TextureRegion[] leftWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];		
	private TextureRegion[] up_leftWalkFrame = new TextureRegion[FRAME_COLS_WALKANIMATION];
	
	private TextureRegion[] up_Steady = new TextureRegion[FRAME_COLS_STEADY];		
	private TextureRegion[] up_Right_Steady = new TextureRegion[FRAME_COLS_STEADY];
	private TextureRegion[] right_Steady = new TextureRegion[FRAME_COLS_STEADY];
	private TextureRegion[] down_Right_Steady = new TextureRegion[FRAME_COLS_STEADY];
	private TextureRegion[] down_Steady = new TextureRegion[FRAME_COLS_STEADY];
	private TextureRegion[] downLeft_Steady = new TextureRegion[FRAME_COLS_STEADY]; 
	private TextureRegion[] left_Steady = new TextureRegion[FRAME_COLS_STEADY];		
	private TextureRegion[] up_left_Steady = new TextureRegion[FRAME_COLS_STEADY];



	public ArrayList<TextureRegion[]> arrayOfTextureRegions_forWalkSheet;
	public ArrayList<TextureRegion[]> arrayOfTextureRegions_forSteady;
	
	public Animation<TextureRegion> chosen_Steady_Animation;
	
	

	// A variable for tracking elapsed time for the animation
	public float stateTime = 0f;

	public Animator () {

		this.diagonalWalk = false;
		this.movement_error = false;
		
		this.arrayOfTextureRegions_forWalkSheet = createArrayOfTextures_forWalkSheet();
		this.arrayOfTextureRegions_forSteady = createArrayOfTextures_forSteadyPosition();
		createAnimation(FRAME_COLS_WALKANIMATION, FRAME_ROWS_WALKANIMATION,"all_movements.png", arrayOfTextureRegions_forWalkSheet);
		createAnimation(FRAME_COLS_STEADY, FRAME_ROWS_STEADY, "steady.png", arrayOfTextureRegions_forSteady);
		

		walkAnimationUp = new Animation<TextureRegion>(0.2f, upWalkFrame);
		walkAnimationUp_Right = new Animation<TextureRegion>(0.2f, up_RightWalkFrame);
		walkAnimationRight = new Animation<TextureRegion>(0.2f, rightWalkFrame);
		walkAnimationDown_Right = new Animation<TextureRegion>(0.2f, down_RightWalkFrame);
		walkAnimationDown = new Animation<TextureRegion>(0.2f, downWalkFrame);
		walkAnimationDown_Left = new Animation<TextureRegion>(0.2f, downLeftWalkFrame);
		walkAnimationLeft = new Animation<TextureRegion>(0.2f, leftWalkFrame);
		walkAnimationUp_Left = new Animation<TextureRegion>(0.2f, up_leftWalkFrame);
		
		up_Steady_Position = new Animation<TextureRegion>(0.2f, up_Steady);
		up_Right_SteadyPosition = new Animation<TextureRegion>(0.2f, up_Right_Steady);
		right_Steady_Position = new Animation<TextureRegion>(0.2f, right_Steady);
		down_Right_Steady_Position = new Animation<TextureRegion>(0.2f, down_Right_Steady);
		down_Steady_Position = new Animation<TextureRegion>(0.2f, down_Steady);
		downLeft_Steady_Position = new Animation<TextureRegion>(0.2f, downLeft_Steady);
		left_Steady_Position = new Animation<TextureRegion>(0.2f, left_Steady);
		up_left_Steady_Position = new Animation<TextureRegion>(0.2f, up_left_Steady);
		
		
		this.chosen_Steady_Animation = up_Steady_Position;

	}

	public ArrayList<TextureRegion[]> createArrayOfTextures_forWalkSheet (){
		ArrayList<TextureRegion[]> arrayOfTextureRegions = new ArrayList<TextureRegion[]>();
		arrayOfTextureRegions.add(upWalkFrame);
		arrayOfTextureRegions.add(up_RightWalkFrame);
		arrayOfTextureRegions.add(rightWalkFrame);
		arrayOfTextureRegions.add(down_RightWalkFrame);
		arrayOfTextureRegions.add(downWalkFrame);
		arrayOfTextureRegions.add(downLeftWalkFrame);
		arrayOfTextureRegions.add(leftWalkFrame);
		arrayOfTextureRegions.add(up_leftWalkFrame);

		return arrayOfTextureRegions;



	}
	
	public ArrayList<TextureRegion[]> createArrayOfTextures_forSteadyPosition (){
		ArrayList<TextureRegion[]> arrayOfTextureRegions = new ArrayList<TextureRegion[]>();
		arrayOfTextureRegions.add(up_Steady);
		arrayOfTextureRegions.add(up_Right_Steady);
		arrayOfTextureRegions.add(right_Steady);
		arrayOfTextureRegions.add(down_Right_Steady);
		arrayOfTextureRegions.add(down_Steady);
		arrayOfTextureRegions.add(downLeft_Steady);
		arrayOfTextureRegions.add(left_Steady);
		arrayOfTextureRegions.add(up_left_Steady);

		return arrayOfTextureRegions;



	}





	public void createAnimation(int frame_Cols, int frame_Rows, String file_string ,ArrayList<TextureRegion[]> array) {

		// Load the sprite sheet as a Texture
		Texture walkSheet = new Texture(Gdx.files.internal(file_string));

		// Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / frame_Cols,
				walkSheet.getHeight() / frame_Rows);

		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.

		/*TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];*/


		for (int i = 0; i < frame_Rows; i++) {
			for (int j = 0; j < frame_Cols; j++) {
				array.get(i)[j] = tmp[i][j];





			}
		}



	}

	public boolean isDiagonalWalk() {
		return diagonalWalk;
	}

	public void setDiagonalWalk(boolean diagonalWalk) {
		this.diagonalWalk = diagonalWalk;
	}

	public boolean isMovement_error() {
		return movement_error;
	}

	public void setMovement_error(boolean movement_error) {
		this.movement_error = movement_error;
	}

	public Animation<TextureRegion> getWalkAnimationUp() {
		return walkAnimationUp;
	}

	public void setWalkAnimationUp(Animation<TextureRegion> walkAnimationUp) {
		this.walkAnimationUp = walkAnimationUp;
	}

	public Animation<TextureRegion> getWalkAnimationUp_Right() {
		return walkAnimationUp_Right;
	}

	public void setWalkAnimationUp_Right(Animation<TextureRegion> walkAnimationUp_Right) {
		this.walkAnimationUp_Right = walkAnimationUp_Right;
	}

	public Animation<TextureRegion> getWalkAnimationRight() {
		return walkAnimationRight;
	}

	public void setWalkAnimationRight(Animation<TextureRegion> walkAnimationRight) {
		this.walkAnimationRight = walkAnimationRight;
	}

	public Animation<TextureRegion> getWalkAnimationDown_Right() {
		return walkAnimationDown_Right;
	}

	public void setWalkAnimationDown_Right(Animation<TextureRegion> walkAnimationDown_Right) {
		this.walkAnimationDown_Right = walkAnimationDown_Right;
	}

	public Animation<TextureRegion> getWalkAnimationDown() {
		return walkAnimationDown;
	}

	public void setWalkAnimationDown(Animation<TextureRegion> walkAnimationDown) {
		this.walkAnimationDown = walkAnimationDown;
	}

	public Animation<TextureRegion> getWalkAnimationDown_Left() {
		return walkAnimationDown_Left;
	}

	public void setWalkAnimationDown_Left(Animation<TextureRegion> walkAnimationDown_Left) {
		this.walkAnimationDown_Left = walkAnimationDown_Left;
	}

	public Animation<TextureRegion> getWalkAnimationLeft() {
		return walkAnimationLeft;
	}

	public void setWalkAnimationLeft(Animation<TextureRegion> walkAnimationLeft) {
		this.walkAnimationLeft = walkAnimationLeft;
	}

	public Animation<TextureRegion> getWalkAnimationUp_Left() {
		return walkAnimationUp_Left;
	}

	public void setWalkAnimationUp_Left(Animation<TextureRegion> walkAnimationUp_Left) {
		this.walkAnimationUp_Left = walkAnimationUp_Left;
	}

	public Animation<TextureRegion> getUp_Steady_Position() {
		return up_Steady_Position;
	}

	public void setUp_Steady_Position(Animation<TextureRegion> up_Steady_Position) {
		this.up_Steady_Position = up_Steady_Position;
	}

	public TextureRegion[] getUpWalkFrame() {
		return upWalkFrame;
	}

	public void setUpWalkFrame(TextureRegion[] upWalkFrame) {
		this.upWalkFrame = upWalkFrame;
	}

	public TextureRegion[] getUp_RightWalkFrame() {
		return up_RightWalkFrame;
	}

	public void setUp_RightWalkFrame(TextureRegion[] up_RightWalkFrame) {
		this.up_RightWalkFrame = up_RightWalkFrame;
	}

	public TextureRegion[] getRightWalkFrame() {
		return rightWalkFrame;
	}

	public void setRightWalkFrame(TextureRegion[] rightWalkFrame) {
		this.rightWalkFrame = rightWalkFrame;
	}

	public TextureRegion[] getDown_RightWalkFrame() {
		return down_RightWalkFrame;
	}

	public void setDown_RightWalkFrame(TextureRegion[] down_RightWalkFrame) {
		this.down_RightWalkFrame = down_RightWalkFrame;
	}

	public TextureRegion[] getDownWalkFrame() {
		return downWalkFrame;
	}

	public void setDownWalkFrame(TextureRegion[] downWalkFrame) {
		this.downWalkFrame = downWalkFrame;
	}

	public TextureRegion[] getDownLeftWalkFrame() {
		return downLeftWalkFrame;
	}

	public void setDownLeftWalkFrame(TextureRegion[] downLeftWalkFrame) {
		this.downLeftWalkFrame = downLeftWalkFrame;
	}

	public TextureRegion[] getLeftWalkFrame() {
		return leftWalkFrame;
	}

	public void setLeftWalkFrame(TextureRegion[] leftWalkFrame) {
		this.leftWalkFrame = leftWalkFrame;
	}

	public TextureRegion[] getUp_leftWalkFrame() {
		return up_leftWalkFrame;
	}

	public void setUp_leftWalkFrame(TextureRegion[] up_leftWalkFrame) {
		this.up_leftWalkFrame = up_leftWalkFrame;
	}

	public TextureRegion[] getUp_Steady() {
		return up_Steady;
	}

	public void setUp_Steady(TextureRegion[] up_Steady) {
		this.up_Steady = up_Steady;
	}

	public TextureRegion[] getUp_Right_Steady() {
		return up_Right_Steady;
	}

	public void setUp_Right_Steady(TextureRegion[] up_Right_Steady) {
		this.up_Right_Steady = up_Right_Steady;
	}

	public TextureRegion[] getRight_Steady() {
		return right_Steady;
	}

	public void setRight_Steady(TextureRegion[] right_Steady) {
		this.right_Steady = right_Steady;
	}

	public TextureRegion[] getDown_Right_Steady() {
		return down_Right_Steady;
	}

	public void setDown_Right_Steady(TextureRegion[] down_Right_Steady) {
		this.down_Right_Steady = down_Right_Steady;
	}

	public TextureRegion[] getDown_Steady() {
		return down_Steady;
	}

	public void setDown_Steady(TextureRegion[] down_Steady) {
		this.down_Steady = down_Steady;
	}

	public TextureRegion[] getDownLeft_Steady() {
		return downLeft_Steady;
	}

	public void setDownLeft_Steady(TextureRegion[] downLeft_Steady) {
		this.downLeft_Steady = downLeft_Steady;
	}

	public TextureRegion[] getLeft_Steady() {
		return left_Steady;
	}

	public void setLeft_Steady(TextureRegion[] left_Steady) {
		this.left_Steady = left_Steady;
	}

	public TextureRegion[] getUp_left_Steady() {
		return up_left_Steady;
	}

	public void setUp_left_Steady(TextureRegion[] up_left_Steady) {
		this.up_left_Steady = up_left_Steady;
	}

	public ArrayList<TextureRegion[]> getArrayOfTextureRegions_forWalkSheet() {
		return arrayOfTextureRegions_forWalkSheet;
	}

	public void setArrayOfTextureRegions_forWalkSheet(ArrayList<TextureRegion[]> arrayOfTextureRegions_forWalkSheet) {
		this.arrayOfTextureRegions_forWalkSheet = arrayOfTextureRegions_forWalkSheet;
	}

	public ArrayList<TextureRegion[]> getArrayOfTextureRegions_forSteady() {
		return arrayOfTextureRegions_forSteady;
	}

	public void setArrayOfTextureRegions_forSteady(ArrayList<TextureRegion[]> arrayOfTextureRegions_forSteady) {
		this.arrayOfTextureRegions_forSteady = arrayOfTextureRegions_forSteady;
	}


	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public Animation<TextureRegion> getChosen_Steady_Animation() {
		return chosen_Steady_Animation;
	}

	public void setChosen_Steady_Animation(Animation<TextureRegion> chosen_Steady_Animation) {
		this.chosen_Steady_Animation = chosen_Steady_Animation;
	}

	public Animation<TextureRegion> getUp_Right_SteadyPosition() {
		return up_Right_SteadyPosition;
	}

	public void setUp_Right_SteadyPosition(Animation<TextureRegion> up_Right_SteadyPosition) {
		this.up_Right_SteadyPosition = up_Right_SteadyPosition;
	}

	public Animation<TextureRegion> getRight_Steady_Position() {
		return right_Steady_Position;
	}

	public void setRight_Steady_Position(Animation<TextureRegion> right_Steady_Position) {
		this.right_Steady_Position = right_Steady_Position;
	}

	public Animation<TextureRegion> getDown_Right_Steady_Position() {
		return down_Right_Steady_Position;
	}

	public void setDown_Right_Steady_Position(Animation<TextureRegion> down_Right_Steady_Position) {
		this.down_Right_Steady_Position = down_Right_Steady_Position;
	}

	public Animation<TextureRegion> getDown_Steady_Position() {
		return down_Steady_Position;
	}

	public void setDown_Steady_Position(Animation<TextureRegion> down_Steady_Position) {
		this.down_Steady_Position = down_Steady_Position;
	}

	public Animation<TextureRegion> getDownLeft_Steady_Position() {
		return downLeft_Steady_Position;
	}

	public void setDownLeft_Steady_Position(Animation<TextureRegion> downLeft_Steady_Position) {
		this.downLeft_Steady_Position = downLeft_Steady_Position;
	}

	public Animation<TextureRegion> getLeft_Steady_Position() {
		return left_Steady_Position;
	}

	public void setLeft_Steady_Position(Animation<TextureRegion> left_Steady_Position) {
		this.left_Steady_Position = left_Steady_Position;
	}

	public Animation<TextureRegion> getUp_left_Steady_Position() {
		return up_left_Steady_Position;
	}

	public void setUp_left_Steady_Position(Animation<TextureRegion> up_left_Steady_Position) {
		this.up_left_Steady_Position = up_left_Steady_Position;
	}
	
	


}
