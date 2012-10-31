package kryonation.ipot.Entities;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SkillHUD extends Entity {
	public static final int SKILL_BAR_SIZE = 8;
	public int[] skillImagePos;
	protected Player player;
	private Image hud;
	public SkillImage[] skillImage;
	private Rectangle imageArea;

	public SkillHUD(float x, float y, Player player) {
		super(x, y);
		this.player = player;

		hud = ResourceManager.getImage("skillhud").getScaledCopy(0.7f);
		skillImagePos = new int[SKILL_BAR_SIZE];
		skillImage = new SkillImage[SKILL_BAR_SIZE];
		
		for(int i =0; i<skillImagePos.length; i++)
			skillImagePos[i] = i*45;
		
		for(int i=0;i<skillImage.length;i++)
			skillImage[i] = new SkillImage(x+skillImagePos[i],y);

		setGraphic(hud);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		
		for(int i =0; i<skillImage.length;i++){
			
			if(!(skillImage[i] == null)){
				skillImage[i].render(gc, g);
				g.drawString((i+1)+"", skillImage[i].x+31, skillImage[i].y+1);
			}
			
		}
		
		

	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
//		Input input = container.getInput();
//		float xp = (float)input.getMouseX();
//		float yp = (float)input.getMouseY();
//		System.out.println("xp:"+xp+" yp:"+yp);
//		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
//		if(xp >= skillImage[0].x && xp <= skillImage[0].x +32 && yp >= skillImage[0].y && yp <= skillImage[0].y +32)
//			System.out.println("You clicked on the first icon!");
//		if(xp >= skillImage[1].x && xp <= skillImage[1].x +32 && yp >= skillImage[1].y && yp <= skillImage[1].y +32)
//			System.out.println("You clicked on the second icon!");
//		if(xp >= skillImage[2].x && xp <= skillImage[2].x +32 && yp >= skillImage[2].y && yp <= skillImage[2].y +32)
//			System.out.println("You clicked on the third icon!");
//		if(xp >= skillImage[3].x && xp <= skillImage[3].x +32 && yp >= skillImage[3].y && yp <= skillImage[3].y +32)
//			System.out.println("You clicked on the fourth icon!");
//		if(xp >= skillImage[4].x && xp <= skillImage[4].x +32 && yp >= skillImage[4].y && yp <= skillImage[4].y +32)
//			System.out.println("You clicked on the fifth icon!");
//		if(xp >= skillImage[5].x && xp <= skillImage[5].x +32 && yp >= skillImage[5].y && yp <= skillImage[5].y +32)
//			System.out.println("You clicked on the sixth icon!");
//		if(xp >= skillImage[6].x && xp <= skillImage[6].x +32 && yp >= skillImage[6].y && yp <= skillImage[6].y +32)
//			System.out.println("You clicked on the seventh icon!");
//		if(xp >= skillImage[7].x && xp <= skillImage[7].x +32 && yp >= skillImage[7].y && yp <= skillImage[7].y +32)
//			System.out.println("You clicked on the eighth icon!");
//	}
		
	}
	
	public void rearrangeSkillBar(SkillImage skill1, SkillImage skill2, float skill1PositionX, float skill1PositionY,float skill2PositionX,float skill2PositionY){
		
	}

}
