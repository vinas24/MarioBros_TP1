//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Position;
import tp1.view.Messages;

import java.util.List;



public class Mario {
	private Position pos;
	private boolean big;
	private Action dir;

	public Mario(Position pos) {
		this.pos = pos;
		this.dir = Action.RIGHT;
	}

	public String getIcon() {
		String m;
		switch (dir)
		{
			case RIGHT -> m = Messages.MARIO_RIGHT;
			case LEFT -> m = Messages.MARIO_LEFT;
			default -> m = Messages.MARIO_STOP;
		}
		return m;
	}

	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}

	public boolean isMarioBig(){
		return this.big;
	}

	//TODO mario tendrá movimiento automatico o inducido por el jugador
	public void update(List<Land> l, ActionList acciones) {
		if(acciones.isVacio())
			//Mov automático
			movAutomaticoMario(l);
		else {
			acciones.limpiarActions(); //eliminamos acciones incoherentes
			while(!acciones.isVacio()) {
				marioAction(acciones.siguienteAction(), l);
			}
		}
		//Colisiones


	}

	private void marioAction(Action a, List<Land> l) {
        if (a == Action.DOWN) {
			while(!isMarioGrounded(l)) moverMario(Action.DOWN);
        } else {
            moverMario(a);
        }
	}
	private void moverMario(Action a){
		this.pos = pos.moverPosicion(a);
		this.dir = a;
	}

	private void movAutomaticoMario(List<Land> l){
		if(isMarioGrounded(l)) {
			if(isMarioObstaculized(l,dir)){
				dir = dir.invertirDireccion();
			} else {
				moverMario(dir);
			}
		}
		else moverMario(Action.DOWN);
	}

	private boolean isMarioGrounded(List<Land> lands) {
		Position inferior = this.pos.inferior();
		boolean grounded = false;
		for(Land l: lands) {
			if (l.isInPosition(inferior)) {
				grounded = true;
				break; //feo
			}
		}
		return grounded;
	}

	private boolean isMarioObstaculized(List<Land> lands, Action dir) {
		Position siguiente = this.pos.moverPosicion(dir);

		boolean hayObstaculo = false;
		for(Land l: lands) {
			if (l.isInPosition(siguiente)) hayObstaculo = true;
		}
		if(siguiente.enBorde()) hayObstaculo = true;

		return hayObstaculo;
	}

	private boolean isMarioDead() {
		return this.pos.fueraTablero();
	}

	//public boolean interactWith(Goomba other)

	public boolean interactWith(ExitDoor other) {
		return other.isInPosition(this.pos);
	}

}