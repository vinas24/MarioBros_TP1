//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionList {

    private final List<Action> lista_acciones;

    public ActionList() {
        this.lista_acciones =  new ArrayList<>();
    }

    public boolean isVacio(){
        return lista_acciones.isEmpty();
    }

    public void add(Action a) {
        if(esValida(a))
            lista_acciones.add(a);
    }

    //Una action es valida, si sigue la dir Horizontal o vertical que
    //ya se encuentra presente en la lista, y maximo con 4 de cada direccion
    private boolean esValida(Action a) {
        int nVeces = 0;
        boolean existeOpuesto = false;
        int i = 0;

        while (i < lista_acciones.size() && !existeOpuesto) {
            Action ac = lista_acciones.get(i);

            if (ac.equals(a.invertirDireccion())) existeOpuesto = true;
            else if (ac.equals(a)) nVeces++;
            i++;
        }
        return !existeOpuesto && nVeces < 4;
    }

    public Action siguienteAction() {
        Action a = lista_acciones.getFirst();
        lista_acciones.removeFirst();
        return a;
    }

    @Override
    public String toString() {
        return "ActionList{" +
                "lista_acciones=" + Arrays.toString(lista_acciones.toArray()) +
                '}';
    }
}
