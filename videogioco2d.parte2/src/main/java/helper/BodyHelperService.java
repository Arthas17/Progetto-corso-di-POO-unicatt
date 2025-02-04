package helper;

import javax.swing.text.StyleConstants.CharacterConstants;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import helper.Costants;

public class BodyHelperService {

	public static Body createBody(float x, float y,float width, float height, boolean isStatic, World world) {
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x / Costants.PPM, y / Costants.PPM);
		bodyDef.fixedRotation = true;
		Body body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2/Costants.PPM, height/2/Costants.PPM);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0;
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
		
		
		
	}
	
}
