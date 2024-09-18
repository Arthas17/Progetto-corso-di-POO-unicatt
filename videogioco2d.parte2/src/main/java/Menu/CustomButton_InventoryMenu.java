package Menu;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class CustomButton_InventoryMenu extends ImageButton {
	
	private boolean isClicked;

	public CustomButton_InventoryMenu(Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
		super(imageUp, imageDown, imageChecked);
		// TODO Auto-generated constructor stub
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
 

}
