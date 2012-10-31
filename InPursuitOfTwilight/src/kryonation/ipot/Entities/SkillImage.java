package kryonation.ipot.Entities;

import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillImage extends Entity {
	
	
	private Image scaledIcon;

	public SkillImage(float x, float y) {
		super(x, y);

		
//		scaledIcon = skillIcons.getSubImage(32, 544, 32, 32).getScaledCopy(1.4f);
//		imageArea = new Rectangle(x, y, scaledIcon.getWidth(),
//				scaledIcon.getHeight());
//		System.out.println("imageArea:" +imageArea.getX()+", "+imageArea.getY()+ "size:"+imageArea.getHeight()+", "+imageArea.getWidth()+", "+"Max x:"+imageArea.getMaxX()+", "+"Min x:"+imageArea.getMinX()+", "+"Max y:"+imageArea.getMaxY()+", "+"Min y:"+imageArea.getMinY());
		setGraphic(SkillIcons.skillIconSet.get((((int)(Math.random()*SkillIcons.skillIconSet.size())))+""));
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		

	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

	
	}

}
