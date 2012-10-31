package kryonation.ipot.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

public class LevelMap extends Entity {
	public final static String MAP = "Map";
	public static float SPEED = 0.125f;
	private TiledMap map;
	private Player player;
	
	public LevelMap(float x, float y, Player player) {
		super(x, y);
		this.player = player;
		map = ResourceManager.getMap("map1");
		define("up", Input.KEY_W);
		define("down", Input.KEY_S);
		define("left", Input.KEY_A);
		define("right", Input.KEY_D);
		
		addType(MAP);
	}
	
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		int xX=(int) (x+0.0f), yY=(int) (y+0.0f);
		map.render(xX,yY);
		

	}
	
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
		
		if (check("up") && y < 241.75) {
			y+= SPEED *delta;
		}
		if (check("down")&& y > -1780.2) {
			y-= SPEED *delta;
		}
		if (check("left") && x < 323.7) {
			x+= SPEED *delta;
		}
		if (check("right") && x > -1705) {
			x-= SPEED *delta;
		}
	}
}
