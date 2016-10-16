package com.carreath.JumpyHusky.FlyeyObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.carreath.JumpyHusky.Game;
import com.carreath.JumpyHusky.GameObject;
import com.carreath.JumpyHusky.ID;
import com.carreath.JumpyHusky.ImageLoader;
import com.carreath.JumpyHusky.InitializeScreen;
import com.carreath.JumpyHusky.KeyInput;

public class Player extends GameObject {

	private double timer = 0;
	private final int PIXEL_CONVERSION = 1;
	private final int GRAVITY_CONSTANT = 5;
	private final int WIDTH;
	final int HEIGHT;
	private static int currentAnimation = 0;
	public static boolean gameOver = false;
	
	public static int score = 0;
	
	public Player(int x, int y, ID id, int width, int height) {
		super(x, y, id);
		
		velY = y;
		WIDTH = width;
		HEIGHT = height;
	}

	@Override
	public void tick() {
		y = (int)(-100 * PIXEL_CONVERSION * 
				(-GRAVITY_CONSTANT * (timer*timer) + (GRAVITY_CONSTANT * timer))) +
					velY;
		
		if(timer >= 0.5)
			currentAnimation = 0;
		
		timer += (1.0 / 80.0);
		
		if(!gameOver && KeyInput.jump && y > -20) {
				jump();
		}
		else if(y >= Game.HEIGHT - 215) {
			y = Game.HEIGHT - 215;
			velY = y;
			timer = 0;
			currentAnimation = 1;
		}
		
 		if(KeyInput.jump && gameOver && (y == Game.HEIGHT - 215)){
 			gameOver = false;
			InitializeScreen.initGame();
			jump();
		}
	}

	private void jump() {
		currentAnimation = 1;
		velY = y;
		timer = 0;
		KeyInput.jump = false;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(ImageLoader.getLevel1Images()[4 + currentAnimation], 
					x, y, WIDTH, HEIGHT, null);
		
		/** Debugging HitBoxes
		g.setColor(Color.WHITE);
		g.drawRect(getBounds().x,getBounds().y,getBounds().width,getBounds().height);
		**/
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x + 10, y + 15, WIDTH - 20, HEIGHT - 15 );
	}
	public static void scored() {
		score++;
	}
}
