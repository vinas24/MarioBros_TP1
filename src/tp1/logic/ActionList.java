package tp1.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
}
