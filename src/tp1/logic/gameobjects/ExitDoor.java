//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor  extends  GameObject{


    public ExitDoor(Position pos, Game game) {
        super(game, pos);

    }

    //Las puertas no tienen Update
    @Override
    public void update() {}

    public String getIcon() {
        return Messages.EXIT_DOOR;
    }


}
