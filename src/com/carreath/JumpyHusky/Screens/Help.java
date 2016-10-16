package com.carreath.JumpyHusky.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import com.carreath.JumpyHusky.Game;

import java.awt.Graphics;

public class Help {
	public static int x = Game.WIDTH/2 -100;
	public static int y = Game.WIDTH/2;

	public static Rectangle back = new Rectangle(x, 100,200,64);
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("arial", 1, 50);
		
		g.setFont(font);
		g.drawString("Help", x + 40, 50);
		g.drawString("Back", x + 40, 150);
		g.drawRect(x, back.y, back.width, back.height);

		font = new Font("arial", 1, 25);
		g.setFont(font);
		g.drawString("This is a Flappy Bird clone designed soley", 30, 230);
		g.drawString("as a project for HOC applications at Harrison ", 30, 260); 
		g.drawString("House UNB." , 30, 290);
		g.drawString("However if you do need help...", 30, 340);
		g.drawString("- Space to jump", 30, 370);
		g.drawString("- Jump through the pumpkin stacks", 30, 400);
		g.drawString("    to earn points.", 30, 430);
		g.drawString("- Get the highest score possible", 30, 460);
	}
	
}
