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

    public Position getPos() {
        return pos;
    }

    public boolean isInPosition(Position pos) {
        return this.pos.equals(pos);
    }
}
