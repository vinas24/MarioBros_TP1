//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
    private Action dir;
    private boolean isFalling;

    public MovingObject(GameWorld game, Position pos, Action dir) {
        super(game, pos);
        this.dir = dir;
        this.isFalling = false;
    }

    public void update() {
        this.movAutomatico();
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
        Position inferior = this.posSiguente(Action.DOWN);
        return game.isSolid(inferior);
    }

    //cuando implementemos la lista de GameObject usaremos isSolid
    protected boolean isObstaculized(Position pos) {
        return game.isSolid(pos) || pos.enBorde();
    }

    protected boolean isFalling() {
        return this.isFalling || this.dir == Action.DOWN;
    }

    protected void fall() {
        this.isFalling = true;
    }

    protected int dirActual() {
         return switch (dir)
        {
            case LEFT -> -1;
            case STOP -> 0;
            default -> 1;

        };
    }

    protected void cambiarDir(Action a) {
        if (a == Action.LEFT || a == Action.RIGHT || a == Action.STOP)
            this.dir = a;
    }

    @Override
    public String toString() {
        return  "dir=" + dir +
                ", isFalling=" + isFalling + ", " +
                super.toString();
    }
}
