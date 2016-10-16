package com.carreath.JumpyHusky;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -8029439076918694338L;

	public JFrame frame;
	
	public Window() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		frame.setFocusable(true);
		game.start();
		
	}
	public void close() {
		frame.dispose();
	}
}
