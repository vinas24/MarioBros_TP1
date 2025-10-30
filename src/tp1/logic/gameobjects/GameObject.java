//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem{

	private Position pos;
	private boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
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
    //TODO public por estar en GameItem
    public boolean compartePosition(GameObject other) {
        return other.isInPosition(this.pos);
    }
    
    //TODO hacer metodos receiveIteraction por defecto aqui, e implementar los necesarios en cada clase
    public boolean receiveInteraction(Land obj) {
    	return false;
    }
	public boolean receiveInteraction(ExitDoor obj) {
    	return false;
    }
	public boolean receiveInteraction(Mario obj) {
    	return false;
    }
	public boolean receiveInteraction(Goomba obj) {
    	return false;
    }
	
	@Override
    public String toString() {
        return  "pos=" + pos +
                ", isAlive=" + isAlive +
                ", game=" + game;
    }
}
