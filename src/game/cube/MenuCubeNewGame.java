package game.cube;

import game.Level;
import game.Player;
import render.Menu;

public class MenuCubeNewGame extends Cube {

	public MenuCubeNewGame() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		// Menu.executeOptions();
		Menu.postInitialize();
		// TODO Println-Zeile löschen! Ausgabe nur zu Probezwecken!
		System.out.println("");
		System.out.println("Neues Spiel gestartet!");
		level.buildDefaultLevel();

		int player1_start_x = 0;
		if (level.getSizeX() % 2 == 0) { // Größe in X gerade
			player1_start_x = level.getSizeX() * 10 - 15;
		} else { // Größe in X ungerade
			if (level.getSizeY() % 2 == 0) { // Größe in Y gerade
				player1_start_x = level.getSizeX() * 10 - 15;
			} else { // Größe in Y ungerade
				player1_start_x = level.getSizeX() * 10 - 25;
			}
		}

		// FIXME Netzwerkfähigkeit
		level.setInMenu(false);
		player.reinit(player1_start_x, level.getSizeY() * 10 - 15, 15, 0, Player.START_BOMBS, Player.GRAVITY);

		System.out.println("Du startest mit " + player.getScore() + " Punkten!");
		System.out.println("");
	}

}
