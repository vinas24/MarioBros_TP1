//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0);
	
	private  int x;
	private  int y;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Action invertirDireccion() {
		Action inv;
		switch (this){
			case UP -> inv = DOWN;
			case DOWN -> inv = UP;
			case LEFT -> inv = RIGHT;
			case RIGHT -> inv = LEFT;
			default -> inv = STOP;
		}
		return inv;
	}

	public boolean isEquals(Action a) {
		return this.x == a.x && this.y == a.y;
	}
}
