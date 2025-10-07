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

    public ActionList(String[] prompt) {
        this.lista_acciones =  new ArrayList<>();
        Iterator<String> i = Arrays.stream(prompt).iterator();
        i.next(); //limpiamos el prmero;
        while(i.hasNext()){
            switch (i.next().toLowerCase()){
                case "u", "up" -> lista_acciones.add(Action.UP);
                case "d", "down" -> lista_acciones.add(Action.DOWN);
                case "l", "left" -> lista_acciones.add(Action.LEFT);
                case "right", "r" -> lista_acciones.add(Action.RIGHT);
                default -> lista_acciones.add(Action.STOP);
            }
        }
    }

    public boolean isVacio(){
        return lista_acciones.isEmpty();
    }

    //para el debugger
    public void mostrar(){
        for(Action a: lista_acciones) System.out.println(a);
    }
}
