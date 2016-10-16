package com.carreath.JumpyHusky.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import com.carreath.JumpyHusky.Game;

import java.awt.Graphics;

public class Menu {
	public static int x = Game.WIDTH/2 -100;
	public static int y = Game.WIDTH/2;

	public static Rectangle play = new Rectangle(x, 100,200,64);
	public static Rectangle help = new Rectangle(x, 200,200,64);
	public static Rectangle quit = new Rectangle(x, 300,200,64);
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("arial", 1, 50);
		
		g.setFont(font);
		g.drawString("Menu", x + 40, 50);
		g.drawString("Play", x + 50, 150);
		g.drawString("Help", x + 50, 250);
		g.drawString("Quit", x + 50, 350);

		g.drawRect(x, play.y, play.width, play.height);
		g.drawRect(x, help.y, help.width, help.height);
		g.drawRect(x, quit.y, quit.width, quit.height);
	}
	
}
