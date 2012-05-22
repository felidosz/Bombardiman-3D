package game.cube;

import game.Player;

public class CubeExplosion extends Cube {

	public CubeExplosion() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLECTABLE);
	}

	@Override
	public void changePlayer(Player player) {
		player.hitPlayer(25);
	}

}