package com.carreath.JumpyHusky.FlyeyObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.carreath.JumpyHusky.Game;
import com.carreath.JumpyHusky.GameObject;
import com.carreath.JumpyHusky.ID;
import com.carreath.JumpyHusky.ImageLoader;
import com.carreath.JumpyHusky.Game.STATE;

public class Ground extends GameObject{

	private Player player;
	
	public Ground(Player player) {
		super(0, Game.HEIGHT - 141, ID.Ground);
		velX = 2;
	
		this.player = player;
	}

	@Override
	public void tick() {
		if(!Player.gameOver) {
			x-=velX;
			if(x <= -62)
				x = 0;
		}
		if(collision())
			Player.gameOver = true;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image ground=null;
		if(Game.gameState == STATE.Game)
			ground = ImageLoader.getLevel1Images()[0];
		
		g2d.drawImage(ground,x,y,2320, 141, null);
	} 

	public Rectangle getBounds() {
		return new Rectangle(0,y, 100,100);
	}
	public boolean collision() {
		boolean collides = false;
		
		if(player.getBounds().intersects(getBounds())) {
			collides = true;
		}
		
		return collides;
	}
	
}
