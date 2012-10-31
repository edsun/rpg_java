package kryonation.ipot;

import it.randomtower.engine.ResourceManager;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	private static boolean resourcesInitd;
	public static TrueTypeFont ttfont;

	public Main(String title) {
		super(title);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		initResources();
		// add worlds
		addState(new Menu(0, container));
		addState(new Game(1, container));
	}

	public static void initResources() throws SlickException {
		resourcesInitd = false;
		try {
			
			//load image, sound and other project resources from an xml document
			ResourceManager.loadResources("res/resources.xml");
			
			//create a custom font from file
			Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File(
					"res/emulogic.ttf"));
			Font fontBase = fontRaw.deriveFont(8f);
			ttfont = new TrueTypeFont(fontBase, false);
			resourcesInitd = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("/res/emulogic.ttf"
					+ " not loaded.  Using serif font.");
			ttfont = new TrueTypeFont(new Font("serif", Font.PLAIN, 24), true);
		}

	}

	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Main(
					"Unknown RPG"));
			container.setDisplayMode(640, 480, false);
			container.setTargetFrameRate(60);
			container.start();
			System.out.println(resourcesInitd);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}