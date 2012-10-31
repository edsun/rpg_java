package kryonation.ipot.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

public class InventoryScreen extends Entity {
	protected Player player;
	private Image inventoryHUD;


	public InventoryScreen(float x, float y, Player player) {
		super(x, y);
		this.player = player;
//		inventoryHUD = ResourceManager.getImage("inventoryHUD").getScaledCopy(
//				0.70f);
//		setGraphic(inventoryHUD);

	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);



	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);


	}


}
