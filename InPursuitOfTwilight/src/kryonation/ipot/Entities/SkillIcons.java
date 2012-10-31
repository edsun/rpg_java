package kryonation.ipot.Entities;

import it.randomtower.engine.ResourceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class SkillIcons {
	public static int HEIGHT = 32;
	public static int WIDTH =  32;
	public static SpriteSheet skillIconSheet;
	public static ArrayList<Image> iconList;
	public static Map<String, Image> skillIconSet;
	public static int numSkills;
	
	public SkillIcons() {
		
	}
	
	/*
	 * Change this implementation to use a hashmap key value pair eg. Map<String, Image> skillIconSet = new HashMap<String, Image>(); set spritesheet data with xml file so 
	 * the key can be created with a name delegated to images through the xml reference file. Pull the information together into a set and create a HashMap for easy
	 * search and retrieval of images based on the name key.
	 */
	public static void load(){
		iconList = new ArrayList<Image>();
		skillIconSet = new HashMap<String, Image>();
		skillIconSheet = ResourceManager.getSpriteSheet("skillIcons");
		numSkills =0;
		for(int i =0; i < skillIconSheet.getWidth();i+=32){
			for(int j =0; j < skillIconSheet.getHeight();j+=32){
				
				skillIconSet.put((numSkills)+"", skillIconSheet.getSubImage(j, i, HEIGHT, WIDTH).getScaledCopy(1.4f));
				numSkills++; 
			}
			
		}
		System.out.println("Number of skill icons loaded.... " +numSkills);
	}

}
