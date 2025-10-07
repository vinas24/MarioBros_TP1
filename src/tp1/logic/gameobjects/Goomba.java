//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.Position;
import tp1.view.Messages;
import java.util.List;

public class Goomba {
    private Position pos;
    private Action dir;
    private boolean estaMuerto;


    public Goomba(Position pos) {
        this.pos = pos;
        this.dir = Action.LEFT;
        this.estaMuerto = false;
    }

    public String getIcon() {
        return Messages.GOOMBA;
    }

    public boolean isDead(){
        return estaMuerto;
    }

    public boolean isInPosition(int col, int row) {
        // TODO Auto-generated method stub
        return this.pos.equals(col, row);
    }

    //Los goombas tendrán mov automático
    // Si se encuentra sobre un objeto sólido, avanza un paso por turno en la dirección actual (empieza moviéndose hacia la izquierda).
    // Si choca con un objeto sólido o con la pared lateral del tablero, invierte su dirección.
    // Si no tiene suelo debajo, cae una casilla hacia abajo hasta volver a encontrarse con un objeto sólido.
    // Si sale del tablero por abajo, muere.
    // Cuando un Goomba muere, debe ser eliminado de la lista de Goombas.
    public void update(List<Land> l) {
        //TODO: Hacer que muera y se elimine si se va fuera del tablero
        if(isGoombaGrounded(l)) {
            if(isGoombaObstaculized(l,dir)){
                dir = dir.invertirDireccion();
            }
            //El Goomba se mueve en la nueva dir.
            this.pos = pos.moverPosicion(dir);
        }
        else this.pos = pos.moverPosicion(Action.DOWN);

        //si se encuentra fuera, estará muerto
        if(this.pos.fueraTablero()) this.estaMuerto = true;
    }


    private boolean isGoombaGrounded(List<Land> lands) {
        Position inferior = this.pos.inferior();
        boolean grounded = false;
        for(Land l: lands) {
            //TODO: no debería de poder acceder a los atributos de pos
           if (l.isInPosition(inferior)) grounded = true;
        }
        return grounded;
    }

    private boolean isGoombaObstaculized(List<Land> lands, Action dir) {
        Position p = this.pos.moverPosicion(dir);
        boolean hayObstaculo = false;
        for(Land l: lands) {
            //TODO: no debería de poder acceder a los atributos de pos
            if (l.isInPosition(p)) hayObstaculo = true;
        }
        if(p.enBorde()) hayObstaculo = true;

        return hayObstaculo;
    }


}
