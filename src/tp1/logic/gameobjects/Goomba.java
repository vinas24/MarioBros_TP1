//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba {
    private Position pos;
    private Action dir;


    public Goomba(Position pos) {
        this.pos = pos;
        this.dir = Action.LEFT;
    }

    public String getIcon() {
        return Messages.GOOMBA;
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
    public void update(){
        //comienza mov a izq


    }

    private boolean isGoombaGrounded(){
        boolean grounded = false;

        return grounded;
    }
}
