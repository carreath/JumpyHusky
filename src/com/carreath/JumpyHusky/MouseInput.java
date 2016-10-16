package com.carreath.JumpyHusky;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.carreath.JumpyHusky.Game.STATE;
import com.carreath.JumpyHusky.Screens.Help;
import com.carreath.JumpyHusky.Screens.Menu;

public class MouseInput extends MouseAdapter {
	private Game game;	
	public MouseInput(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
			int mX = e.getX();
			int mY = e.getY();
			
			if(Game.gameState == STATE.Game){
			}
			else if(Game.gameState == STATE.Menu) {
				if(mouseOver(mX, mY, Menu.play.x, Menu.play.y, Menu.play.width, Menu.play.height)) {
					Game.gameState = STATE.Game;
				}
				if(mouseOver(mX, mY, Menu.help.x, Menu.help.y, Menu.help.width, Menu.help.height)) {
					Game.gameState = STATE.Help;
				}
				if(mouseOver(mX, mY, Menu.quit.x, Menu.quit.y, Menu.quit.width, Menu.quit.height)) {
					game.stop();
				}
			}
			else if(Game.gameState == STATE.Help) {
				if(mouseOver(mX, mY, Help.back.x, Help.back.y, Help.back.width, Help.back.height)) {
					Game.gameState = STATE.Menu;
				}
			}
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	private boolean mouseOver(int mX, int mY, int x, int y, int width, int height) {
		boolean isOver = false;
		
		if(mX > x && mX < x + width) {
			if(mY > y && mY < y + height) {
				isOver = true;
			}
		}
		
		return isOver;
	}

}
