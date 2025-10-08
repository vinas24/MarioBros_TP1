//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Position;
import tp1.view.Messages;
import java.util.List;



public class Mario {
	private Position pos;
	//TODO hacer mario grande
	private boolean big;
	private Action dir;
	private boolean muerto;

	public Mario(Position pos) {
		this.pos = pos;
		this.dir = Action.RIGHT;
		this.big = false;
		this.muerto = false;
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

	public boolean isMarioBig() {
		return this.big;
	}

	public boolean estaMuerto() {
		return this.muerto || pos.fueraTablero();
	}

	public boolean estaCayendo() {
		return this.dir == Action.DOWN;
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
		//colisiones
	}

	private void marioAction(Action a, List<Land> l) {
        if (a == Action.DOWN) {
			while(!isMarioGrounded(l)) moverMario(l,Action.DOWN);
        } else {
            moverMario(l,a);
        }
	}

	private void moverMario(List<Land> l, Action a){
		if(!isMarioObstaculized(l,a)) {
			this.pos = pos.moverPosicion(a);
			this.dir = a;
		} else{
			this.pos = pos.moverPosicion(Action.STOP);
		}
	}

	private void movAutomaticoMario(List<Land> l){
		if(isMarioGrounded(l)) {
			if(dir == Action.DOWN) dir = Action.RIGHT;
			moverMario(l,dir);
		}
		else moverMario(l,Action.DOWN);
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

	public boolean interactWith(ExitDoor other) {
		return other.isInPosition(this.pos);
	}

	public boolean interactWith(Goomba other) {
		return other.isInPosition(this.pos);
	}


	public void atacadoPorGoomba() {
		if(big) big = false; //mas 100p
		else muerto = true;  //mas 100p
	}
}