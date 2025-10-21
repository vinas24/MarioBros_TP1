//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends MovingObject{
    private boolean big;

    public Mario(Position pos, GameWorld game) {
        super(game, pos, Action.RIGHT);
        this.big = true;
        this.dir = Action.RIGHT;
    }

    public String getIcon() {
        String m;
        switch (dir)
        {
            case LEFT -> m = Messages.MARIO_LEFT;
            case STOP -> m = Messages.MARIO_STOP;
            default -> m = Messages.MARIO_RIGHT;

        }
        return m;
    }

    public boolean isMarioBig() {
        return this.big;
    }

    @Override
    public void update() {
        if(game.accionesIsVacio()) {
            //Mov automático
            movAutomatico();
        } else {
            game.limpiarAcciones(); //eliminamos acciones incoherentes
            while(!game.accionesIsVacio()) {
                marioAction(game.siguenteAction());
            }
        }
    }

    private void marioAction(Action a) {
        if (a == Action.DOWN) {
            if(isGrounded())  {
                this.dir = Action.STOP;
            }
            else {
                fall(); //mario cae
                while (!isGrounded() && !fueraDelTablero()) moverMario(Action.DOWN);
            }
        } else {
            moverMario(a);
        }
    }

    private void moverMario(Action a){
        if(!isObstaculizedMario(a)) {
            move(a);
            //Cambiamos la direccion en la que mira
            if (a == Action.LEFT || a == Action.RIGHT || a == Action.STOP) {
                dir = a;
            }
            if(fueraDelTablero()) this.dead();
        } else{
            this.dir = a.invertirDireccion();
        }
    }

    //He cambiado el interact a usar GameObject
    public boolean interactWith(GameObject other) {
        boolean interact = compartePosition(other);
        if(isMarioBig() && !interact) {
            interact = other.isInPosition(posSuperior());
        }
        return interact;
    }

    public void atacadoPorGoomba() {
        if(big) big = false;
        else this.dead();
    }

    //Obstaculizado especial para mario, si es big
    //también comprueba la posicion superior
    private boolean isObstaculizedMario(Action a){
        boolean obstaculizado = isObstaculized(posSiguente(a));
        if(isMarioBig() && !obstaculizado) {
            obstaculizado = isObstaculized(posSuperiorSiguente(a));
        }
        return obstaculizado;
    }
}