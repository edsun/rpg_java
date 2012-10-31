package kryonation.ipot.Entities;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class PlayerHUD extends Entity {
	protected Player player;
	private Image hud;
	private boolean drawStats;
	private Image manabar, healthbar;
	private Rectangle rect;
	private float scale = 0.7f;
	private float value1, value2;

	public PlayerHUD(float x, float y, Player player) {
		super(x, y);
		this.player = player;
		hud = ResourceManager.getImage("hud").getScaledCopy(scale);
		setGraphic(hud);
		value1=0.0f;
		value2=0.0f;

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		//healthbar draw x=26, y=17
		//healthbar draw x=26, y=45
		//manabar.draw(x+ 26*scale, y*scale+47*scale, , );
		g.setColor(Color.green);
		value1=player.getHealth();
		value2=player.maxHealth();
		g.fillRect(x+ 26*scale, y*scale+17*scale, (value1/value2)*100*scale, 16*scale);
		g.setColor(Color.blue);
		value1=player.getMana();
		value2=player.maxMana();
		g.fillRect(x+ 26*scale, y*scale+45*scale, (value1/value2)*100*scale, 16*scale);
		g.setColor(Color.white);
	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

	}
}
