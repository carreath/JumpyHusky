package com.carreath.JumpyHusky;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import com.carreath.JumpyHusky.FlyeyObjects.Player;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i=0; i<object.size();i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for(int i=object.size()-1; i>=0;i--) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic sans", 50, 40));
		g.drawString("Score: " + Player.score, 30, Game.HEIGHT - 65);
	}
	public void clearObjects() {
		object = new LinkedList<GameObject>();
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
