package com.carreath.JumpyHusky;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.carreath.JumpyHusky.Screens.Help;
import com.carreath.JumpyHusky.Screens.Menu;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -7368191758987977191L;

	public static final int WIDTH = 600;
	public static final int HEIGHT = WIDTH / 4 * 6;
	
	private Thread thread;
	private boolean running = false;
	
	public static Handler handler;
	private Menu menu;
	private Help help;
	private MouseInput mouseInput;
	private boolean musicLoaded = false;
	private Window window;
	
	public enum STATE {
		Splash,
		Menu,
		Help,
		Game;
	}

	public static STATE gameState = STATE.Splash;
	public static STATE lastState = STATE.Splash;
	
	public Game() {
		handler = new Handler();
		menu = new Menu();
		help = new Help();
		mouseInput = new MouseInput(this);
		
		//Initialize Listeners
		this.addKeyListener(new KeyInput());
		this.addMouseListener(mouseInput);
		
		//Open Splash Screen
		window = new Window(WIDTH, HEIGHT, "FlyeyHusky", this);
		
		//Load all images and Sounds
		ImageLoader.load();

		musicLoaded = true;
		
		InitializeScreen.initGame();
		
		gameState = STATE.Menu;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		try {
			running = false;
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 120.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			if(System.currentTimeMillis() - timer > 100 && musicLoaded) 
				checkMusic();
		}
		
		stop();
		
		ImageLoader.dispose();
		
		window.close();
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
				this.createBufferStrategy(3);
				return;
		}
		Graphics g = bs.getDrawGraphics();
		
		if(gameState != STATE.Splash) {
			g.setColor(Color.black);
			g.fillRect(0,0,WIDTH,HEIGHT);
			
			if(gameState == STATE.Game) {
				g.drawImage(ImageLoader.getSplashImage(),Game.WIDTH / 6 - 40, Game.WIDTH/6 - 120,Game.WIDTH/6 * 5,Game.HEIGHT - 100, null);
				handler.render(g);
			}
			else if(gameState == STATE.Menu) {
				g.drawImage(ImageLoader.getSplashImage(),Game.WIDTH / 4, Game.WIDTH/2 + 50,Game.WIDTH/2,Game.WIDTH - 100, null);
				menu.render(g);
			}
			else {
				g.drawImage(ImageLoader.getSplashImage(),Game.WIDTH / 4, Game.WIDTH/2 + 50,Game.WIDTH/2,Game.WIDTH - 100, null);
				help.render(g);
			}
		}
		else {
			//Display Splash screen
			g.setColor(Color.black);
			g.fillRect(0,0,WIDTH,HEIGHT);
			g.drawImage(ImageLoader.getSplashImage(),Game.WIDTH / 4, Game.WIDTH/4,Game.WIDTH/2,Game.WIDTH - 100, null);

			g.setColor(Color.white);
			g.setFont(new Font("Arial",1,50));
		}
		bs.show();
		g.dispose();
	}
	private void tick() {
		if(gameState == STATE.Game) {
			handler.tick();
		}
	}
	
	public static void main(String[] args) {
		new Game();		
	}
	public void checkMusic(){	
	}
	
}
