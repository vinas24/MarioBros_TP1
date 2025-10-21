//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
    private Action dir;
    private boolean isFalling;

    public MovingObject(GameWorld game, Position pos) {
        super(game, pos);
    }
    public MovingObject(GameWorld game, Position pos, Action dir) {
        super(game, pos);
        this.dir = dir;
    }

    void movAutomatico() {
        if(isGrounded()){
            if(isObstaculized(dir)) {
                dir = dir.invertirDireccion();
            } else {
                move(dir);
            }
        }
        //mover hacia abajo
        else move(Action.DOWN);
        //si se encuentra fuera, estará muerto
        if(fueraDelTablero()) this.dead();
    }

    private boolean isGrounded() {
        Position inferior = posInferior();
        return game.landInPos(inferior);
    }

    private boolean isObstaculized(Action dir) {
        Position siguente = posSiguente(dir);
        return game.landInPos(siguente) || siguente.enBorde();
    }

}
