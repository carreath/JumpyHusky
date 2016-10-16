package com.carreath.JumpyHusky;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;

public class ImageLoader {
	private static BufferedImage splash;
	
	private static BufferedImage[] level1_Images;
	
	public static void load() {
		try {
			splash = ImageIO.read(new File("res/Images/Splash.png"));
			level1_Images = new BufferedImage[getImagesFromLocation("res/Images/Husky_Images")];
			level1_Images = loadFromLocation("res/Images/Husky_Images");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getSplashImage() {
		return splash;
	}
	public static BufferedImage[] getLevel1Images() {
		return level1_Images;
	}
	
	private static int getImagesFromLocation(String path){
		File[] files = new File(path).listFiles();
		int count = 0;
		
		for(File file : files){
			if(file != null)
				if(file.getName().contains(".png") || file.getName().contains(".jpg") ||
						file.getName().contains(".gif") || file.getName().contains(".bmp")) {
					count++;
				}
		}
		return count;
	}
	private static BufferedImage[] loadFromLocation(String path) {
		File[] files = new File(path).listFiles();
		Collection<BufferedImage> imageFiles = new ArrayList<>();		
		BufferedImage[] images = null;
		int i=0;
		
		for(File file : files){
			if(file != null)
				if(file.getName().contains(".png") || file.getName().contains(".jpg") ||
						file.getName().contains(".gif") || file.getName().contains(".bmp")) {
					try {
						BufferedImage f = ImageIO.read(file);
						imageFiles.add(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		
		images = new BufferedImage[imageFiles.size()];
		for(BufferedImage image : imageFiles){
			images[i] = image;
			i++;
		}
		
		return images;
	}
	public static void dispose() {
		splash = null;
		level1_Images = null;
	}
}
