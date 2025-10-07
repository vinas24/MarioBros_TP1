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
	private boolean estaMuerto;

	public Mario(Position pos) {
		this.pos = pos;
		this.dir = Action.RIGHT;
		this.estaMuerto = false;
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

	public boolean isInPosition(int col, int row) {
		return this.pos.equals(col, row);
	}

	public boolean isMuerto() {
		return this.estaMuerto;
	}

	//TODO mario tendrá movimiento automatico o inducido por el jugador
	public void update(List<Land> l, ActionList acciones) {
		if(acciones.isVacio()){
			//Mov automático
			movAutomaticoMario(l);
		} else {
			//haremos la gestion de acciones
		}
		//Colisiones


	}

	private void movAutomaticoMario(List<Land> l){
		if(isMarioGrounded(l)) {
			if(isMarioObstaculized(l,dir)){
				dir = dir.invertirDireccion();
			} else {
				this.pos = pos.moverPosicion(dir);
			}
		}
		else this.pos = pos.moverPosicion(Action.DOWN);
	}

	private boolean isMarioGrounded(List<Land> lands) {
		Position inferior = this.pos.inferior();
		boolean grounded = false;
		for(Land l: lands) {
			//TODO: no debería de poder acceder a los atributos de pos
			if (l.isInPosition(inferior)) grounded = true;
		}
		return grounded;
	}

	private boolean isMarioObstaculized(List<Land> lands, Action dir) {
		Position p = this.pos.moverPosicion(dir);
		boolean hayObstaculo = false;
		for(Land l: lands) {
			//TODO: no debería de poder acceder a los atributos de pos
			if (l.isInPosition(p)) hayObstaculo = true;
		}
		if(p.enBorde()) hayObstaculo = true;

		return hayObstaculo;
	}

}
