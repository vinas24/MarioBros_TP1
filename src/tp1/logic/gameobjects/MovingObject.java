//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
    //TODO: cambiar de protected a private
    protected Action dir;
    private boolean isFalling;

    public MovingObject(Game game, Position pos) {
        super(game, pos);
    }


    void movAutomatico() {
        if(isGrounded()){
            if(isObstaculized(dir)) {
                dir = dir.invertirDireccion();
            } else {
                this.pos = pos.moverPosicion(dir);
            }
        }
        //TODO mover hacia abajo
    }

    private boolean isGrounded() {
        Position inferior = this.pos.inferior();
        return game.isSolid(inferior);
    }

    private boolean isObstaculized(Action dir) {
        Position siguente = this.pos.moverPosicion(dir);
        return game.isSolid(siguente) || siguente.enBorde();
    }

}
