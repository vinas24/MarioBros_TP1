//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.GameWorld;

public class Goomba extends MovingObject{

    public Goomba(Position pos, GameWorld game) {
        super(game, pos, Action.LEFT);
    }

    public String getIcon() {
        return Messages.GOOMBA;
    }

    public void update() {
        this.movAutomatico();
    }

    //Se cambiara con el double-dispatch
    public boolean receiveInteraction(Mario other) {
        if (other.estaCayendo()) {
            this.dead();
        } else {
            if (other.isMarioBig()) this.dead();
            other.atacadoPorGoomba();
        }
        return true;
    }


}
