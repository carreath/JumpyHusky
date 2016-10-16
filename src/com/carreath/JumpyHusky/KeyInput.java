package com.carreath.JumpyHusky;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	public static boolean jump = false;
	public static boolean pause = false; //FOR TESTING
	private boolean space = false;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE && !space) {
			jump = true;
			space = true;
		}
		if(key == KeyEvent.VK_P) {
			pause ^= true;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

}
