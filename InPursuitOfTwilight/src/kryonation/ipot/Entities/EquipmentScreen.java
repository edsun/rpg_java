package kryonation.ipot.Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

public class EquipmentScreen extends Entity {

	protected Player player;
	private Image equipmentHUD;

	public EquipmentScreen(float x, float y, Player player) {
		super(x, y);
		this.player = player;
		equipmentHUD = ResourceManager.getImage("equipHUD").getScaledCopy(
				0.70f);
		setGraphic(equipmentHUD);
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		g.setColor(Color.white);
		

	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

	}

}
