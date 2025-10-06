//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;

public class Land {
    private Position pos;


    public Land(Position pos) {
        this.pos = pos;
    }

    public String getIcon() {
        return Messages.LAND;
    }

    public boolean isInPosition(int col, int row) {
        return this.pos.equals(col, row);
    }
}
