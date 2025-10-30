//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Game;
import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.view.Messages;

public class Land extends GameObject{


    public Land(Position pos, Game game) {
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
    
	//TODO nuevo interactWith
    public boolean interactWith(GameItem other) {
	     boolean canInteract = other.compartePosition(this);
	     if (canInteract) {
	          canInteract = other.receiveInteraction(this);
	     }
	     return canInteract;
	}
}
