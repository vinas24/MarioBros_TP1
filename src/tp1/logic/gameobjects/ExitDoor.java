//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
    private Position pos;

    public ExitDoor(Position pos) {
        this.pos = pos;
    }

    public String getIcon() {
        return Messages.EXIT_DOOR;
    }

    public boolean isInPosition(int col, int row) {
        // TODO Auto-generated method stub
        return this.pos.equals(col, row);
    }
}
