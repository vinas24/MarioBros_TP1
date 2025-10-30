//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends MovingObject{
    private boolean big;
    private final ActionList lista_acciones;


    public Mario(Position pos, GameWorld game) {
        super(game, pos, Action.RIGHT);
        this.big = true;
        this.lista_acciones = new ActionList();
    }
    //IDK
    @Override
    public boolean isInPosition(Position p) {
        boolean inPos = super.isInPosition(p);
        if(big) inPos = inPos | super.isInPosition(p.moverPosicion(Action.DOWN));
        return inPos;
    }


    public String getIcon() {
        return switch (dirActual())
        {
            case -1 -> Messages.MARIO_LEFT;
            case 0 -> Messages.MARIO_STOP;
            default -> Messages.MARIO_RIGHT;

        };
    }

    public boolean isMarioBig() {
        return this.big;
    }

    @Override
    public void update() {
        if(lista_acciones.isVacio()) {
            super.update();
        } else {
            while(!lista_acciones.isVacio()) {
                marioAction(lista_acciones.siguienteAction());
            }
        }
        //TODO Comprobamos colisiones
    }

    private void marioAction(Action a) {
        if (a == Action.DOWN) {
            if(isGrounded())  {
                cambiarDir(Action.STOP);
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
            cambiarDir(a);
            if(fueraDelTablero()) this.dead();
        } else cambiarDir(a.invertirDireccion());
    }

    //He cambiado el interact a usar GameObject
    //TODO: posiblemente, con el nuevo isInPosition de mario no haga falta tanto check
    public boolean interactWith(GameObject other) {
        boolean interact = compartePosition(other);
        if(isMarioBig() && !interact) {
            interact = other.isInPosition(posSiguente(Action.UP));
        }
        return interact;
    }

    public void atacadoPorGoomba() {
        if(big) big = false;
        else this.dead();
    }

    //Obstaculizado especial para mario
    // si es big también comprueba la posicion superior
    private boolean isObstaculizedMario(Action a){
        boolean obstaculizado = isObstaculized(posSiguente(a));
        if(isMarioBig() && !obstaculizado) {
            obstaculizado = isObstaculized(posSiguente(Action.UP).moverPosicion(a)); //TF
        }
        return obstaculizado;
    }

    public void addAction(Action a){
        this.lista_acciones.add(a);
    }

    @Override
    public String toString() {

        return "Mario{" +
                "big=" + big +
                ", lista_acciones=" + lista_acciones + ", " +
                super.toString() + "}";
    }
}