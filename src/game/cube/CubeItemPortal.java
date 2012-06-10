package game.cube;
import java.util.Random;

import game.Level;
import game.Player;


/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 *
 */
public class CubeItemPortal extends Cube {
	
	CubeItemPortal() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE); 
	}

	@Override
	public void change(Player player, Level level) {	
		int destCubeX = 0, destCubeY = 0, destCubeZ = 0;
		
		destCubeX = level.getSizeX() - player.getCubeX();
		destCubeY = level.getSizeY() - player.getCubeY();
		destCubeZ = level.getSizeZ() - player.getCubeZ();
		
		// FIXME @Philipp
		// Wenn das Portal zu einem besetzten Block schickt, dann suche
		// Nachbarblöcke statt Zufallposition
		if ((level.getCubeName(destCubeX, destCubeY, destCubeZ) == "CubeSolid") 
			|| (level.getCubeName(destCubeX, destCubeY, destCubeZ) == "CubeObstacle") 
			|| (level.getCubeName(destCubeX, destCubeY, destCubeZ) == "CubeObstacleHideExit")) {
		
			do {
				Random randomx = new Random();
				destCubeX = 1 + Math.abs(randomx.nextInt()) % level.getSizeX();
				Random randomy = new Random();
				destCubeY = 1 + Math.abs(randomy.nextInt()) % level.getSizeY();
				Random randomz = new Random();
				destCubeZ = 1 + Math.abs(randomz.nextInt()) % level.getSizeZ();
				
			} while ((level.getCubeName(destCubeX, destCubeY, destCubeZ) == "CubeSolid") 
					|| (level.getCubeName(destCubeX, destCubeY, destCubeZ) == "CubeObstacle") 
					|| (level.getCubeName(destCubeX, destCubeY, destCubeZ) == "CubeObstacleHideExit"));
			
		}
			
	
		System.out.println("Du hast ein Portal betreten!");		
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
		
		player.setPlayerPosition(destCubeX, destCubeY, destCubeZ);
	}
}
