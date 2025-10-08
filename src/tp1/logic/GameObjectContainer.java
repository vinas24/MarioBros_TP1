//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic;
import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.view.Messages;


public class GameObjectContainer {
    //TODO fill your code
    private List<Land> lista_land;
    private ExitDoor exitdoor;
    private List<Goomba> lista_goomba;
    private Mario mario;


    public GameObjectContainer() {
        this.lista_land = new ArrayList<>();
        this.exitdoor = new ExitDoor(null);
        this.lista_goomba = new ArrayList<>();
        this.mario = new Mario(null);
    }

    public GameObjectContainer(List<Goomba> lista_goomba) {
        this.lista_goomba = lista_goomba;
    }

    public GameObjectContainer(Mario mario) {
        this.mario = mario;
    }

    public void add(Land land) {
        this.lista_land.add(land);
    }

    public void add(Goomba goomba) {
        this.lista_goomba.add(goomba);
    }

    public void add(ExitDoor exit) {
        this.exitdoor = exit;
    }

    public void add(Mario mario) {
        this.mario = mario;
    }

    public boolean isMarioInDoor() {
        int x = 0, y = 0;
        boolean ok = false;
        while(x<Game.DIM_X) {
            while(y<Game.DIM_Y) {
                if(mario.isInPosition(y, x) && exitdoor.isInPosition(y, x)) ok = true;
                y++;
            }
            x++;
        }
        return ok;
    }

    public String positionToIcon(int col, int row){
        String S = Messages.EMPTY;
        for(Land land: lista_land) {
            if(land.isInPosition(col, row)) S = land.getIcon();
        }

        if(S.equals(Messages.EMPTY)) {
            for(Goomba goomba: lista_goomba) {
                if(goomba.isInPosition(col, row)) S = goomba.getIcon();
            }
        }

        if(S.equals(Messages.EMPTY) && exitdoor.isInPosition(col, row)) S = exitdoor.getIcon();

        if(S.equals(Messages.EMPTY) && mario.isInPosition(col, row)) S = mario.getIcon();
        return S;
    }

    public void update() {
        //Primero update de mario,
        //para darle prioridad en las colisiones
        //TODO: Por ahora paso un action list vacio
        this.mario.update(lista_land, new ActionList());
        //Luego todos los goombas
        for(Goomba g: lista_goomba) {
            g.update(lista_land);
        }
        //borramos los goombas muertos
        //Mire en stackOverflow como, dentro del for se rompía
        lista_goomba.removeIf(Goomba::isDead);
    }



}
