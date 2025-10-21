//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Position;
import tp1.view.Messages;
import java.util.List;



public class Mario {
    private Position pos;
    private boolean big;
    private Action dir;
    private Action dirHor;
    private boolean muerto;
    private boolean haCaido;


    public Mario(Position pos) {
        this.pos = pos;
        this.dir = Action.RIGHT;
        this.dirHor = Action.RIGHT;
        this.big = true;
        this.muerto = false;
        this.haCaido = false;
    }

    public String getIcon() {
        String m;
        switch (dir)
        {
            case LEFT -> m = Messages.MARIO_LEFT;
            case STOP -> m = Messages.MARIO_STOP;
            default -> {
                if (dirHor == Action.RIGHT)
                    m = Messages.MARIO_RIGHT;
                else
                    m = Messages.MARIO_LEFT;
            }

        }
        return m;
    }

    public boolean isInPosition(Position pos) {
        return this.pos.equals(pos);
    }

    public boolean isMarioBig() {
        return this.big;
    }

    public boolean estaMuerto() {
        return this.muerto || pos.fueraTablero();
    }

    public boolean estaCayendo() {
        return this.dir == Action.DOWN || this.haCaido;
    }

    public void update(List<Land> l, ActionList acciones) {
        haCaido = false;
        if(acciones.isVacio())
            //Mov automático
            movAutomaticoMario(l);
        else {
            acciones.limpiarActions(); //eliminamos acciones incoherentes
            while(!acciones.isVacio()) {
                marioAction(acciones.siguienteAction(), l);
            }
        }
        //colisiones
    }

    private void marioAction(Action a, List<Land> l) {
        if (a == Action.DOWN) {
            this.haCaido = true;
            if(isMarioGrounded(l)) this.dir = Action.STOP;
            else {
                while (!isMarioGrounded(l) && !pos.fueraTablero()) moverMario(l, Action.DOWN);
            }
        } else {
            moverMario(l,a);
        }
        if (a == Action.RIGHT || a == Action.LEFT) dirHor = a;
    }

    private void moverMario(List<Land> l, Action a){
        if(!isMarioObstaculized(l,a)) {
            this.pos = pos.moverPosicion(a);
            this.dir = a;
            if(this.pos.fueraTablero()) this.muerto = true;
        } else{
            this.pos = pos.moverPosicion(Action.STOP);
            this.dir = a.invertirDireccion();
        }
    }

    private void movAutomaticoMario(List<Land> l){
        if(isMarioGrounded(l)) {
            if(estaCayendo()) {
                dir = Action.RIGHT;
                dirHor = Action.RIGHT;
            }
            if(isMarioObstaculized(l, dir)) dir = dir.invertirDireccion();
            else moverMario(l,dir);
        }
        else moverMario(l,Action.DOWN);
    }

    private boolean isMarioGrounded(List<Land> lands) {
        Position inferior = this.pos.inferior();
        boolean grounded = false;
        for(Land l: lands) {
            if (l.isInPosition(inferior)) {
                grounded = true;
                break; //feo
            }
        }
        return grounded;
    }

    private boolean isMarioObstaculized(List<Land> lands, Action dir) {
        Position siguiente = this.pos.moverPosicion(dir);

        boolean hayObstaculo = false;
        for(Land l: lands) {
            if(isMarioBig()) {
                if (l.isInPosition(siguiente) || l.isInPosition(siguiente.superior())) hayObstaculo = true;
            }
            else if (l.isInPosition(siguiente)) hayObstaculo = true;
        }
        if(siguiente.enBorde()) hayObstaculo = true;

        return hayObstaculo;
    }

    public boolean interactWith(ExitDoor other) {
        boolean interact = other.isInPosition(this.pos);
        if(isMarioBig() && !interact) {
            interact = other.isInPosition(this.pos.superior());
        }
        return interact;
    }

    public boolean interactWith(Goomba other) {
        boolean interact = other.isInPosition(this.pos);
        if(isMarioBig() && !interact) {
            interact = other.isInPosition(this.pos.superior());
        }
        return interact;
    }


    public void atacadoPorGoomba() {
        if(big) big = false;
        else muerto = true;
    }


}
