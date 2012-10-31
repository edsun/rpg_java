package kryonation.ipot;

import java.awt.Font;

import javax.swing.JOptionPane;

import kryonation.ipot.Entities.InventoryScreen;
import kryonation.ipot.Entities.LevelMap;
import kryonation.ipot.Entities.Player;
import kryonation.ipot.Entities.SkillHUD;
import kryonation.ipot.Entities.SkillIcons;
import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.World;
import it.randomtower.engine.entity.TextEntity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends World {
	public static final int ID = 1;
	private TrueTypeFont font;
	private LevelMap map;
	private TextEntity playerName;
	private String characterName;
	protected Player player;
	private boolean mouseDown = false;
	private boolean mouseUp = true;

	public Game(int id, GameContainer container) throws SlickException {
		super(id, container);

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);
		g.drawString(characterName , getWidth()/2-((characterName.length()*3)-2), getHeight()/2-18);
		//g.drawString("X: "+container.getInput().getMouseX()+" Y: "+container.getInput().getMouseY(), 150, 150);
		g.setColor(Color.black);
		g.drawString(container.getFPS()+ " fps", 525, 10);
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		
		//load icons for skills
		SkillIcons.load();
		// set font
		font = Main.ttfont;
		characterName = JOptionPane
				.showInputDialog("Enter the name of your character");
		player = new Player(getWidth() / 2, getHeight() / 2, characterName);
		map = new LevelMap(0, 0, player);

		
		
		changePlayerName(characterName);
//		playerName = new TextEntity(player.x - characterName.length() * 2,
//				player.y - 12, font, characterName);

		add(map);
		add(player);
//		add(playerName);
		defineControls();

	}

	private void changePlayerName(String name) {
		characterName = name;
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(container, sbg, delta);
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			// goto menu world
			sbg.enterState(0);
		}
		
		/*
		 * Testing skill drag and drop on skill bar
		 */
		float xp = (float)input.getMouseX();
		float yp = (float)input.getMouseY();
//		System.out.println("xp:"+xp+" yp:"+yp);
//		
//		if(xp >= player.skillHud.skillImage[0].x && xp <= player.skillHud.skillImage[0].x +32 && yp >= player.skillHud.skillImage[0].y && yp <= player.skillHud.skillImage[0].y +32){
//			
//			float curX = player.skillHud.skillImage[0].x, curY = player.skillHud.skillImage[0].y;
//			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
//				mouseDown = true;
//				mouseUp = false;
//			}
//				//move icon with cursor while clicked and dragged
//				if(mouseDown){
//					player.skillHud.skillImage[0].setPosition(new Vector2f(xp,yp));
//				}

//			//return the icon to its original place if it does not end up in the second slot
//			if(player.skillHud.skillImage[0].x < player.skillHud.skillImage[1].x &&player.skillHud.skillImage[0].x > player.skillHud.skillImage[1].x +32 
//			  && player.skillHud.skillImage[0].y < player.skillHud.skillImage[1].y &&player.skillHud.skillImage[0].y > player.skillHud.skillImage[1].y +32){
//				player.skillHud.skillImage[0].x = curX;
//				player.skillHud.skillImage[0].y = curY;
//			}
//		}
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(xp >= player.skillHud.skillImage[0].x && xp <= player.skillHud.skillImage[0].x +32 && yp >= player.skillHud.skillImage[0].y && yp <= player.skillHud.skillImage[0].y +32)
				System.out.println("You clicked on the first icon!");
			if(xp >= player.skillHud.skillImage[1].x && xp <= player.skillHud.skillImage[1].x +32 && yp >= player.skillHud.skillImage[1].y && yp <= player.skillHud.skillImage[1].y +32)
				System.out.println("You clicked on the second icon!");
			if(xp >= player.skillHud.skillImage[2].x && xp <= player.skillHud.skillImage[2].x +32 && yp >= player.skillHud.skillImage[2].y && yp <= player.skillHud.skillImage[2].y +32)
				System.out.println("You clicked on the third icon!");
			if(xp >= player.skillHud.skillImage[3].x && xp <= player.skillHud.skillImage[3].x +32 && yp >= player.skillHud.skillImage[3].y && yp <= player.skillHud.skillImage[3].y +32)
				System.out.println("You clicked on the fourth icon!");
			if(xp >= player.skillHud.skillImage[4].x && xp <= player.skillHud.skillImage[4].x +32 && yp >= player.skillHud.skillImage[4].y && yp <= player.skillHud.skillImage[4].y +32)
				System.out.println("You clicked on the fifth icon!");
			if(xp >= player.skillHud.skillImage[5].x && xp <= player.skillHud.skillImage[5].x +32 && yp >= player.skillHud.skillImage[5].y && yp <= player.skillHud.skillImage[5].y +32)
				System.out.println("You clicked on the sixth icon!");
			if(xp >= player.skillHud.skillImage[6].x && xp <= player.skillHud.skillImage[6].x +32 && yp >= player.skillHud.skillImage[6].y && yp <= player.skillHud.skillImage[6].y +32)
				System.out.println("You clicked on the seventh icon!");
			if(xp >= player.skillHud.skillImage[7].x && xp <= player.skillHud.skillImage[7].x +32 && yp >= player.skillHud.skillImage[7].y && yp <= player.skillHud.skillImage[7].y +32)
				System.out.println("You clicked on the eighth icon!");
		}
		
//		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
//			if (xp <=199 && xp >=155 && yp>=425 && yp<=469) {
//				
//			}
//		}

		

	}

	private void defineControls() {

	}

}