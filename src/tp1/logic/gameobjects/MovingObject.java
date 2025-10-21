//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
    //TODO Cambiarlo a private
    protected Action dir;
    private boolean isFalling;

    public MovingObject(GameWorld game, Position pos, Action dir) {
        super(game, pos);
        this.dir = dir;
    }

    protected void movAutomatico() {
        if(isGrounded()){
            this.isFalling = false;
            if(isObstaculized(posSiguente(dir))) {
                dir = dir.invertirDireccion();
            } else {
                this.move(dir);
            }
        }
        //mover hacia abajo
        else {
            this.move(Action.DOWN);
            this.isFalling = true;
        }
        //si se encuentra fuera, estará muerto
        if(this.fueraDelTablero()) this.dead();
    }

    //cuando implementemos la lista de GameObject usaremos isSolid
    protected boolean isGrounded() {
        Position inferior = this.posInferior();
        return game.landInPos(inferior);
    }
    //cuando implementemos la lista de GameObject usaremos isSolid
    protected boolean isObstaculized(Position pos) {
        return game.landInPos(pos) || pos.enBorde();
    }

    protected boolean isFalling() {
        return this.isFalling || this.dir == Action.DOWN;
    }

    protected void fall() {
        this.isFalling = true;
    }






}
