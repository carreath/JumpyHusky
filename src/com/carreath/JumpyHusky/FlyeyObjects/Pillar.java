package com.carreath.JumpyHusky.FlyeyObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.carreath.JumpyHusky.Game;
import com.carreath.JumpyHusky.GameObject;
import com.carreath.JumpyHusky.ID;
import com.carreath.JumpyHusky.ImageLoader;

public class Pillar extends GameObject {
	private static final Random rand = new Random();
	
	private static final int WIDTH = 100;
	public static final int X_SEPARATION = 350;
	public static final int Y_SEPARATION = 200;
	private Pillar lastPillar;
	private Player player;
	
	private int y2;
	private boolean scored = false;

	private Rectangle topRect;
	private Rectangle bottomRect;
	public Pillar(int x, int y, ID id, Pillar lastPillar, Player player) {
		super(x, y, id);

		velX = -2;
		this.lastPillar = lastPillar;
		
		this.player = player;
		
		resetPillar();
	}

	@Override
	public void tick() {
		if(!Player.gameOver)
			x += velX;

		if(collision())
			Player.gameOver = true;
		
		if(x < velX - WIDTH) {
			resetPillar();
		}
	}

	private void resetPillar() {
		if(lastPillar == null) 
			x = Game.WIDTH + 100;
		else
			x = lastPillar.x + X_SEPARATION;
		y = (rand.nextInt(42) + 8) * (Game.HEIGHT / 100);
		y2 = y + Y_SEPARATION;
		
		topRect = new Rectangle(x, y - ImageLoader.getLevel1Images()[3].getHeight()/2 - 100,
					WIDTH, ImageLoader.getLevel1Images()[3].getHeight()/2);
		bottomRect = new Rectangle(x, y2 + 100, WIDTH, 
					ImageLoader.getLevel1Images()[3].getHeight()/2);
		new Rectangle(x + WIDTH, y, 1, y2);
		
		scored = false;
	}
	
	@Override
	public void render(Graphics g) {
		BufferedImage[] a = ImageLoader.getLevel1Images();

		g.drawImage(a[3], x, topRect.y, topRect.width, topRect.height, null);
		g.drawImage(a[3], x, bottomRect.y, bottomRect.width, bottomRect.height, null);
		
		g.drawImage(a[2], x, y - 100, topRect.width, 100, null);
		g.drawImage(a[1], x, bottomRect.y - 100, bottomRect.width, 100, null);

		/** Debugging HitBoxes
		g.setColor(Color.WHITE);
		g.drawRect(getBounds1().x,getBounds1().y,getBounds1().width,getBounds1().height);
		g.drawRect(getBounds2().x,getBounds2().y,getBounds2().width,getBounds2().height);
		g.drawRect(getBounds3().x,getBounds3().y,getBounds3().width,getBounds3().height);
		g.drawRect(getBounds4().x,getBounds4().y,getBounds4().width,getBounds4().height);
		**/
	}
	
	public boolean collision() {
		boolean collided = false;
		
		if(player.getBounds().intersects(getBounds1()) ||
				player.getBounds().intersects(getBounds2()) ||
				player.getBounds().intersects(getBounds3()) ||
				player.getBounds().intersects(getBounds4())) {
			collided = true;
		}

		if(player.getBounds().intersects(getScoreBounds()) && !scored){
			scored = true;
			Player.scored();
		}
		
		return collided;
	}
	
	public Rectangle getBounds1() {
		return new Rectangle(x, topRect.y + 75, topRect.width, topRect.height);	
	}
	public Rectangle getBounds2() {
		return new Rectangle(x, bottomRect.y - 75, bottomRect.width, bottomRect.height);
	}
	public Rectangle getBounds3() {
		return new Rectangle(x + topRect.width/6, y - 100, topRect.width - topRect.width/3, 90);			
	}
	public Rectangle getBounds4() {
		return new Rectangle(x + topRect.width/6, bottomRect.y - 90, 2*topRect.width/3, 95);	
	}
	public Rectangle getScoreBounds() {
		return new Rectangle(x + topRect.width, 0, 100,Game.HEIGHT);	
	}
	
	public void setLastPillar(Pillar lastPillar) {
		this.lastPillar = lastPillar;
	}
}
