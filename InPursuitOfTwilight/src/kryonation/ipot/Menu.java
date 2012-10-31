package kryonation.ipot;


import it.randomtower.engine.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends World {
	
	private final static String MENU_TEXT1="Enter Game: [spacebar]";
	private final static String MENU_TEXT2="Exit Game:  [Esc]";
	public Menu(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);
		g.setFont(Main.ttfont);
		g.setColor(Color.white);
		g.drawString(MENU_TEXT1, getWidth()/3, 150);
		g.drawString(MENU_TEXT2, getWidth()/3, 200);
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_SPACE)) {

			// goto game world
			game.enterState(1);
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			// quit game
			System.exit(0);
		}
	}

}