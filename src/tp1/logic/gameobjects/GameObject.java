//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class GameObject {

	protected Position pos; // TODO If you can, make it private.
	private boolean isAlive;
	protected Game game; 
	
	public GameObject(Game game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
		// TODO fill your code here, it should depends on the status of the object
		return this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}

    public boolean isSolid() {
        return this.getClass() == Land.class;
    }

    public abstract void update();
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {
		this.pos = pos.moverPosicion(dir);
	}
}
