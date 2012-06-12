package control;

import game.Level;
import game.Player;

import org.lwjgl.input.Mouse;


/**
 * 
 * Enthält Methoden die für die Maussteuerung notwendig sind
 *
 */
public class Control_Mouse extends Control {

	public Control_Mouse(Player player, Level level) {
		super(player, level);
		Mouse.setGrabbed(true);
	}

	public void mouse_Move(Player player) {
		while (Mouse.next()) {
			player.yaw((-Mouse.getDX()) / 100f);
			player.pitch((Mouse.getDY()) / 100f);

			if (Mouse.isButtonDown(0)) { // Linksklick legt eine Bombe
				player.setBomb();
			}
		}
	}

}
