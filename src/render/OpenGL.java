package render;

//Hier werden Level- und Playerinformationen zusammengeführt und in OpenGL ausgegeben.

import game.Level;
import game.Player;
import game.cube.Cube;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class OpenGL {

	final static public byte EFFECT_OFF = 0x00; // 0000 0000
	final static public byte EFFECT_RED = 0x01; // 0000 0001
	final static public byte EFFECT_TRANSPARENT = 0x02; // 0000 0010
	final static public float sizeOfCube = 10;
	int width, height;
	Level level;
	Player player;
	Objects objects;

	// Netzwerk spieler
	private static List<Player> listPlayer = new ArrayList();

	private byte effect = EFFECT_OFF;

	public OpenGL(Level level, Player player, int width, int height, List<Player> listPlayer) {
		this.level = level;
		this.player = player;
		this.width = width;
		this.height = height;
		this.level = level;
		this.listPlayer = listPlayer;
		objects = new Objects(level.getthemeSelection());
		init();
	}

	public void display() {

		GL11.glClearColor(0.5f, 0.5f, 0.5f, 0.1f);

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		float widthHeightRatio = width / height;
		GLU.gluPerspective(45, widthHeightRatio, 1, 1000);
		GLU.gluLookAt(player.getX(), player.getY(), player.getZ(), player.getCamX(), player.getCamY(), player.getCamZ(), 0, 1,
				0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// Level
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		for (byte i = 0; i < level.getSizeX(); i += 1) {
			for (byte j = 0; j < level.getSizeY(); j += 1) {
				for (byte k = 0; k < level.getSizeZ(); k += 1) {
					if (level.getCubeName(i, j, k).equals(Cube.CUBE_BOMB)) {
						objects.DrawCubeBomb(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXPLOSION)) {
						objects.DrawCubeExplosion(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
						objects.DrawCubeExplosion(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					}else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
						objects.DrawCubeExplosion(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_HEALTH)) {
						objects.DrawCubeItemHealth(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_XTRA_BOMB)) {
						objects.DrawCubeItemXtraBomb(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_BOMB_RANGE)) {
						objects.DrawCubeItemBombRange(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_BOMB_STRENGTH)) {
						objects.DrawCubeItemBombStrength(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_PORTAL)) {
						objects.DrawCubeItemPortal(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_SOLID)) {
						objects.DrawCubeSolid(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_OUTSIDE)) {
						objects.DrawCubeOutside(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_OBSTACLE)) {
						objects.DrawCubeObstacle(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_OBSTACLE_HIDE_EXIT)) {
						objects.DrawCubeObstacle(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXIT)) {
						objects.DrawCubeExit(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
						// Menüwürfel
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_NEW_GAME)) {
						objects.DrawMenuCubeNewGame(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_EXIT_PROGRAM)) {
						objects.DrawMenuCubeExitProgram(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_LOAD_LEVEL)) {
						objects.DrawMenuCubeLoadLevel(i * sizeOfCube, j * sizeOfCube, k * sizeOfCube);
					}
				}
			}
		}
		// Spieler zeichnen
		if (listPlayer != null) {
			for (int i = 0; i < listPlayer.size(); i++) {
				if (listPlayer.get(i).getNumber() != player.getNumber()) {
					objects.DrawPlayer(listPlayer.get(i).getX(), listPlayer.get(i).getY(), listPlayer.get(i).getZ());
				}
			}
		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		// TODO Effektprogrammierung ist kein Meilenstein
		// if ((effect & EFFECT_RED) == 0) {
		// GL11.glColor4f(1f, 0f, 0f, 0.1f);
		// Primitives.DrawCube(player.getX() - 1f, player.getY() - 1f,
		// player.getZ() - 1f, 3f);
		// }
		GL11.glFlush();
	}

	public void init() {
		float clipsize = 0.8f;

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHT0);

		// Textur

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		// Alpha-Farbkanal, Transparenz, einschalten
		// GL11.glEnable(GL11.GL_BLEND);
		// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glEnable(GL11.GL_LINE_SMOOTH); // Antialiasing fuer Linien
											// einschalten

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-clipsize, +clipsize, -clipsize, +clipsize, -clipsize * 100.0f, +clipsize * 100.0f);
		GL11.glViewport(0, 0, width, height);
	}

}
