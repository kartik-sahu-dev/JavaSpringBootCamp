package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	
	GamingConsole game;
	
	public GameRunner(@Qualifier("SuperContraGame") GamingConsole game) {
		this.game = game;
	}

	public void run() {
		System.out.println("Game Running: "+game);
		game.up();
		game.down();
		game.right();
		game.left();
	}
	
}
