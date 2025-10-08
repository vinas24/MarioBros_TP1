//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class ActionList {

    private List<Action> lista_acciones;

    public ActionList() {
        this.lista_acciones =  new ArrayList<>();
    }


    public boolean isVacio(){
        return lista_acciones.isEmpty();
    }

    public void add(Action a) {
        lista_acciones.add(a);
    }

    //para el debugger
    public void mostrar(){
        for(Action a: lista_acciones) System.out.println(a);
    }

    public Action siguienteAction() {
        Action a = lista_acciones.getFirst();
        lista_acciones.removeFirst();
        return a;
    }


    //Metodo feisimo, no sé como hacerlo mejor, pero funciona
    //filtra para que las horizontales sean iguales, y maximo cuatro
    //Escogerá quedarse con la primera accion horizontal que encuentre
    //con las verticales hace lo mismo
    public void limpiarActions() {
        boolean horiz = false, vert = false;
        int nHor = 0, nVert = 0;
        Action h = Action.RIGHT, v = Action.UP;

        for (int i = 0; i < lista_acciones.size(); i++){
            switch (lista_acciones.get(i)){
                case LEFT,RIGHT -> {
                    if(!horiz) { //para la prim instancia de mov hor
                        horiz = true;
                        nHor++;
                        h = lista_acciones.get(i);
                    } else if(lista_acciones.get(i).isEquals(h) && nHor < 4) {
                        nHor++;
                    } else {
                        lista_acciones.remove(i);
                        i--;
                    }
                }
                case UP, DOWN -> {
                    if(!vert) { //para la prim instancia de mov vert
                        vert = true;
                        nVert++;
                        v = lista_acciones.get(i);
                    } else if(lista_acciones.get(i).isEquals(v) && nVert < 4) {
                        nVert++;
                    } else {
                        lista_acciones.remove(i);
                        i--;
                    }
                }

            }
        }
    }
}
