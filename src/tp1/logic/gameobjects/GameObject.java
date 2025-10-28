//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject {

	private Position pos;
	private boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
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
        return false;
    }

    public abstract void update();
	
	public abstract String getIcon();

	protected void move(Action dir) {
		this.pos = pos.moverPosicion(dir);
	}

    protected Position posSiguente(Action dir) {
        return  this.pos.moverPosicion(dir);
    }

    protected boolean fueraDelTablero() {
        return this.pos.fueraTablero();
    }

    protected boolean compartePosition(GameObject other) {
        return other.isInPosition(this.pos);
    }

    @Override
    public String toString() {
        return  "pos=" + pos +
                ", isAlive=" + isAlive +
                ", game=" + game;
    }
}
