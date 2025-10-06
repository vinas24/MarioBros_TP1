//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario {
	private Position pos;
	private boolean big;

	public Mario(Position pos) {
		this.pos = pos;
	}

	public String getIcon() {
		return Messages.MARIO_RIGHT;
	}

	public boolean isInPosition(int col, int row) {
		return this.pos.equals(col, row);
	}

	//TODO mario tendrá movimiento automatico o inducido por el jugador
	public void update(){

	}
}
