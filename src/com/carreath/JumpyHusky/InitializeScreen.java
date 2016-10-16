package com.carreath.JumpyHusky;

import com.carreath.JumpyHusky.FlyeyObjects.Ground;
import com.carreath.JumpyHusky.FlyeyObjects.Pillar;
import com.carreath.JumpyHusky.FlyeyObjects.Player;

public class InitializeScreen {

	public InitializeScreen() {
		
	}
	
	public static void initGame() {
		Player.score = 0;
		Game.handler.clearObjects();

		Player player = new Player(50, Game.HEIGHT/2, ID.Player, 64, 75);
		Game.handler.addObject(player);

		Game.handler.addObject(new Ground(player));
		
		Pillar p = new Pillar(0,0,ID.Pillar, null, player);
		Pillar f = p;
		
		Game.handler.addObject(p);
		
		for(int i=0;i<Game.WIDTH / Pillar.X_SEPARATION; i++) {
			p = new Pillar(0,0,ID.Pillar, p, player);
			Game.handler.addObject(p);
		}
		
		f.setLastPillar(p);
	}

}
