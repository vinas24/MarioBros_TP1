//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Land extends GameObject{


    public Land(Position pos, GameWorld game) {
        super(game,pos);
    }

    ////La tierra no tienen Update
    @Override
    public void update() {}

    public String getIcon() {
        return Messages.LAND;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String toString() {
        return "Land{ " + super.toString() + "}";
    }
}
