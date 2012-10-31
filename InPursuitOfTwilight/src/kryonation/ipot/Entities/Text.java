package kryonation.ipot.Entities;

import org.newdawn.slick.Font;

import it.randomtower.engine.entity.TextEntity;

public class Text extends TextEntity {

	public Text(float x, float y, Font font, String text) {
		super(x, y, font, text);

	}
	
	public void animateText(float x, float y){
		while(this.y < y)
		{
			this.y++;
		}
		this.removedFromWorld();
	}

}
