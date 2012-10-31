package kryonation.ipot.Entities;

import java.awt.Font;

import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;
import it.randomtower.engine.entity.TextEntity;

import javax.swing.JOptionPane;

import kryonation.ipot.Game;
import kryonation.ipot.Main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

public class Player extends Entity {
	private DamageText damageText;
	protected PlayerHUD hud;
	public SkillHUD skillHud;
	protected EquipmentScreen equipScreen;
	public final static float SCALE = 1.75f;
	private int[] duration = { 200, 200 };
	
	private Image standU, standL, standR, standD, walkU1, walkU2, walkL1,
			walkL2, walkR1, walkR2, walkD1, walkD2;
	public int exp, lvl;
	private String characterName;
	private int health;
	private int mana;
	private int maxHealth;
	private int maxMana;
	private int timeSinceLastRender, rechargeTimer;
	private int skillCost;
	private Image attackUp1;
	private Image attackUp2;
	private Image attackDown1;
	private Image attackDown2;
	private Image attackLeft1;
	private Image attackRight1;
	private Image attackLeft2;
	private Image attackRight2;
	private SpriteSheet swordSprite;
	private boolean upPressed, downPressed, leftPressed, rightPressed,attackPressed;
	private boolean equipDisplayed;
	private boolean renderattackDown;
	private boolean renderattackLeft;
	private boolean renderattackRight;
	private boolean renderattackUp;
	private boolean attackUpDir;
	private boolean attackDownDir;
	private boolean attackLeftDir;
	private boolean attackRightDir;
	private Animation attackUpAnim;
	private Animation attackDownAnim;
	private Animation attackLeftAnim;
	private Animation attackRightAnim;

	public Player(float x, float y, String name) throws SlickException {
		super(x, y);

		// player stats
		characterName =  name;
		maxHealth = health = 100;
		maxMana = mana = 50;
		lvl = 1;
		exp = 0;
		skillCost = 10;
		// load inventory screen
		equipScreen = new EquipmentScreen(380, 0, this);
		// load player hud
		hud = new PlayerHUD(0, 0, this);
		skillHud = new SkillHUD(150,425,this);

		// load images from resources.xml
		swordSprite = ResourceManager.getSpriteSheet("sword_anim");
		attackUp1 = swordSprite.getSubImage(64, 32, 32, 32)
				.getScaledCopy(SCALE);
		attackUp2 = swordSprite.getSubImage(64, 0, 32, 32).getScaledCopy(SCALE);
		attackDown1 = swordSprite.getSubImage(96, 32, 32, 32).getScaledCopy(
				SCALE);
		attackDown2 = swordSprite.getSubImage(96, 0, 32, 32).getScaledCopy(
				SCALE);
		attackLeft1 = swordSprite.getSubImage(32, 32, 32, 32).getScaledCopy(
				SCALE);
		attackLeft2 = swordSprite.getSubImage(32, 0, 32, 32).getScaledCopy(
				SCALE);
		attackRight1 = swordSprite.getSubImage(0, 32, 32, 32).getScaledCopy(
				SCALE);
		attackRight2 = swordSprite.getSubImage(0, 0, 32, 32).getScaledCopy(
				SCALE);
		standU = ResourceManager.getImage("standUp").getScaledCopy(SCALE);
		standL = ResourceManager.getImage("standLeft").getScaledCopy(SCALE);
		standR = ResourceManager.getImage("standRight").getScaledCopy(SCALE);
		standD = ResourceManager.getImage("standDown").getScaledCopy(SCALE);
		walkU1 = ResourceManager.getImage("walkUp1").getScaledCopy(SCALE);
		walkU2 = ResourceManager.getImage("walkUp2").getScaledCopy(SCALE);
		walkL1 = ResourceManager.getImage("walkLeft1").getScaledCopy(SCALE);
		walkL2 = ResourceManager.getImage("walkLeft2").getScaledCopy(SCALE);
		walkR1 = ResourceManager.getImage("walkRight1").getScaledCopy(SCALE);
		walkR2 = ResourceManager.getImage("walkRight2").getScaledCopy(SCALE);
		walkD1 = ResourceManager.getImage("walkDown1").getScaledCopy(SCALE);
		walkD2 = ResourceManager.getImage("walkDown2").getScaledCopy(SCALE);

		// create image arrays
		Image[] upStationary = { standU, standU }, downStationary = { standD,
				standD }, leftStationary = { standL, standL }, rightStationary = {
				standR, standR }, up = { walkU1, walkU2 }, down = { walkD1,
				walkD2 }, left = { walkL1, walkL2 }, right = { walkR1, walkR2 }, attackUp = {
				attackUp1, attackUp2 }, attackDown = { attackDown1, attackDown2 }, attackLeft = {
				attackLeft1, attackLeft2 }, attackRight = { attackRight1,
				attackRight2 };

		// set current image
		setGraphic(standD);

		// set type and collision
		addType(Entity.PLAYER);
		setHitBox(0, 0, 32, 32);

		// define animations
		addAnimation("stillUp", new Animation(upStationary, duration, false));
		addAnimation("stillDown",
				new Animation(downStationary, duration, false));
		addAnimation("stillLeft",
				new Animation(leftStationary, duration, false));
		addAnimation("stillRight", new Animation(rightStationary, duration,
				false));
		addAnimation("moveUp", new Animation(up, duration, false));
		addAnimation("moveDown", new Animation(down, duration, false));
		addAnimation("moveLeft", new Animation(left, duration, false));
		addAnimation("moveRight", new Animation(right, duration, false));

		attackUpAnim = new Animation(attackUp, duration, false);
		attackDownAnim = new Animation(attackDown, duration, false);
		attackLeftAnim = new Animation(attackLeft, duration, false);
		attackRightAnim = new Animation(attackRight, duration, false);

		// set default attack animation to none
		setAttackAnim("");

		System.out.println(renderattackDown + "" + renderattackLeft + ""
				+ renderattackRight + "" + renderattackUp);

		// set attack Directions to default
		attackUpDir = false;
		attackDownDir = false;
		attackLeftDir = false;
		attackRightDir = false;

		// bind controls to keys
		define("attack", Input.KEY_Q);
		define("up", Input.KEY_W);
		define("down", Input.KEY_S);
		define("left", Input.KEY_A);
		define("right", Input.KEY_D);
		define("inventory", Input.KEY_I);
		define("equip", Input.KEY_E);

		// check keys pressed
		equipDisplayed = false;
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		attackPressed = false;

		// check render time && recharge timer
		rechargeTimer = 100;
		timeSinceLastRender = 10;

		// players damage text
		damageText = new DamageText(x, y, Main.ttfont, "");
	}

	public void setCharacterName(String name) {
		characterName = name;
	}

	public void addAnimation(String animName, Animation animation) {
		boolean firstAnim = animations.isEmpty();
		animations.put(animName, animation);

		if (firstAnim) {
			setAnim(animName);
		}
	}

	public void setAnim(String animName) {
		if (!animations.containsKey(animName)) {
			throw new IllegalArgumentException("No animation for " + animName);
		}
		currentAnim = animName;
		Animation currentAnimation = animations.get(currentAnim);
		width = currentAnimation.getWidth();
		height = currentAnimation.getHeight();
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		g.drawString(characterName, 0, 0);
		if (check("attack")) {

			if (renderattackUp && timeSinceLastRender < 7) {
				// attackUpAnim.draw(x+5, y+5)
				g.drawAnimation(attackUpAnim, x-13, y-40);
				System.out.println("Attacking..Up");
			}
			if (renderattackDown && timeSinceLastRender < 7) {
				// attackDownAnim.draw(x+5, y-5);
				g.drawAnimation(attackDownAnim, x-13, y+10);
				System.out.println("Attacking..Down");
			}
			if (renderattackLeft && timeSinceLastRender < 7) {
				// attackLeftAnim.draw(x, y-5);
				System.out.println("Attacking..Left");
				g.drawAnimation(attackLeftAnim, x-37, y-10);
			}
			if (renderattackRight && timeSinceLastRender < 7) {
				// attackRightAnim.draw(x+10, y-5);
				System.out.println("Attacking..Right");
				g.drawAnimation(attackRightAnim, x + 7, y - 10);
			}
		}
		// setAttackAnim("");

		hud.render(container, g);
		skillHud.render(container, g);
		if (equipDisplayed) {
			equipScreen.render(container, g);
//			System.out.println(characterName);
			g.drawString(characterName+" Lvl: "+lvl, equipScreen.x +50, equipScreen.y +45);
		}
	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		if (mana < maxMana && rechargeTimer > 99) {
			rechargeTimer = 0;
			mana++;
		}
		if (check("attack") && timeSinceLastRender > 9) {
			
			if(mana > skillCost){
				timeSinceLastRender = 0;
				rechargeTimer = 0;
				renderattackUp = renderattackDown = renderattackLeft = renderattackRight = false;
				if (attackUpDir) {
					renderattackUp = true;
	
				}
				if (attackDownDir) {
					renderattackDown = true;
	
				}
				if (attackLeftDir) {
					renderattackLeft = true;
	
				}
				if (attackRightDir) {
					renderattackRight = true;
	
				}
				setMana(mana - skillCost);
			}
			else
			{
				System.out.println("Insufficient Mana...");
			}
		}
		
		// System.out.println(timeSinceLastRender);
		if (check("equip") && timeSinceLastRender > 9) {
			timeSinceLastRender = 0;
			if (!equipDisplayed) {
				equipDisplayed = true;
				System.out.println("Displaying Equipment Screen...");
			} else {
				equipDisplayed = false;
				System.out.println("Hiding Equipment Screen...");
			}
		}
		if (check("up") || check("down") || check("left") || check("right")) {

			clearKeyPresses();
			attackUpDir = attackDownDir = attackLeftDir = attackRightDir = false;
			if (check("up")) {
				upPressed = true;
				attackUpDir = true;
				setAnim("moveUp");
				System.out.println("Moving Up");
			}
			if (check("down")) {
				downPressed = true;
				attackDownDir = true;
				setAnim("moveDown");
				System.out.println("Moving Down");
			}
			if (check("left")) {
				leftPressed = true;
				attackLeftDir = true;
				setAnim("moveLeft");
				System.out.println("Moving Left");
			}
			if (check("right")) {
				rightPressed = true;
				attackRightDir = true;
				setAnim("moveRight");
				System.out.println("Moving Right");
			}
		} else {
			if (upPressed) {
				setAnim("stillUp");
				clearKeyPresses();

			}
			if (downPressed) {
				setAnim("stillDown");
				clearKeyPresses();

			}
			if (leftPressed) {
				setAnim("stillLeft");
				clearKeyPresses();

			}
			if (rightPressed) {
				setAnim("stillRight");
				clearKeyPresses();

			}
		}

		if (timeSinceLastRender > 10)
			timeSinceLastRender = 10;

		if (rechargeTimer > 100)
			rechargeTimer = 100;
		// System.out.println(rechargeTimer);
		timeSinceLastRender++;
		rechargeTimer++;
	}

	private void setAttackAnim(String string) {

		if (string.equals("attackUp")) {
			renderattackUp = true;
			renderattackDown = false;
			renderattackLeft = false;
			renderattackRight = false;
		}
		if (string.equals("attackDown")) {
			renderattackUp = false;
			renderattackDown = true;
			renderattackLeft = false;
			renderattackRight = false;
		}
		if (string.equals("attackLeft")) {
			renderattackUp = false;
			renderattackDown = false;
			renderattackLeft = true;
			renderattackRight = false;
		}
		if (string.equals("attackRight")) {
			renderattackUp = false;
			renderattackDown = false;
			renderattackLeft = false;
			renderattackRight = true;
		}
		if (!(string.equals("attackRight") && (string.equals("attackLeft") && (string
				.equals("attackUp") && (string.equals("attackDown")))))) {
			renderattackUp = renderattackDown = renderattackLeft = renderattackRight = false;
		}
	}

	private void clearKeyPresses() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		attackPressed = false;
	}

	public void spawn(float x, float y) {

	}

	public void die() {

	}

	public void levelUp() {
		lvl++;
	}

	public int getHealth() {

		return health;
	}

	public int getMana() {

		return mana;
	}

	public int maxHealth() {

		return maxHealth;
	}

	public int maxMana() {

		return maxMana;
	}

	public void setHealth(int amount) {
		if (amount < 0)
			health = 0;
		else
			health = amount;
	}

	public void setMana(int amount) {
		if (amount < 0)
			mana = 0;
		else
			mana = amount;
	}

	/**
	 * @return the characterName
	 */
	public String getCharacterName() {
		return characterName;
	}

}
