package tp1.logic;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;

	//TODO fill your code
	private GameObjectContainer container;
	private int nLevel;
	private int remainingTime;
	private Mario mario;

	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
		if(nLevel == 0) {
			initLevel0();
		}
		if(nLevel == 1) {
			initLevel1();
		}
	}

	public String positionToString(int col, int row) {
		// TODO Auto-generated method stub
		return container.positionToIcon(col, row);
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return this.container.isMarioInDoor();
	}

	public boolean playerLoses() {
		return this.remainingTime==0||numLives()==0;
	}

	public int remainingTime() {
		// TODO Auto-generated method stub
		return 100;
	}

	public int points() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return 3;
	}

	public boolean isFinished() {
		return playerLoses() || playerWins();
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}


	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;

		// 1. Mapa
		container = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			container.add(new Land(new Position(13,col)));
			container.add(new Land(new Position(14,col)));
		}

		container.add(new Land(new Position(Game.DIM_Y-3,9)));
		container.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			container.add(new Land(new Position(Game.DIM_Y-2, col)));
			container.add(new Land(new Position(Game.DIM_Y-1, col)));
		}

		container.add(new Land(new Position(9,2)));
		container.add(new Land(new Position(9,5)));
		container.add(new Land(new Position(9,6)));
		container.add(new Land(new Position(9,7)));
		container.add(new Land(new Position(5,6)));

		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;

		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				container.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		container.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(new Position(Game.DIM_Y-3, 0));
		this.container.add(this.mario);
		this.container.add(new Goomba(new Position(0, 19)));
	}

	private void initLevel1() {
		this.nLevel = 1;
		this.remainingTime = 100;

		// 1. Mapa
		container = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			container.add(new Land(new Position(13,col)));
			container.add(new Land(new Position(14,col)));
		}

		container.add(new Land(new Position(Game.DIM_Y-3,9)));
		container.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			container.add(new Land(new Position(Game.DIM_Y-2, col)));
			container.add(new Land(new Position(Game.DIM_Y-1, col)));
		}

		container.add(new Land(new Position(9,2)));
		container.add(new Land(new Position(9,5)));
		container.add(new Land(new Position(9,6)));
		container.add(new Land(new Position(9,7)));
		container.add(new Land(new Position(5,6)));

		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;

		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				container.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		container.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(new Position(Game.DIM_Y-3, 0));
		this.container.add(this.mario);

		container.add(new Goomba(new Position(0, 19)));
		container.add(new Goomba(new Position(4, 6)));
		container.add(new Goomba(new Position(12, 6)));
		container.add(new Goomba(new Position(12, 8)));
		container.add(new Goomba(new Position(12, 11)));
		container.add(new Goomba(new Position(12, 14)));
		container.add(new Goomba(new Position(10, 10)));
	}


	//para el comando reset
	//TODO revisarlo
	public void resetLevel(int nLevel) {
		if(nLevel == 0) {
			initLevel0();
		}
		else if(nLevel == 1) {
			initLevel1();
		}
		else{
			resetLevel(this.nLevel);
		}
	}

	public void update() {
		this.container.update();
	}
}
