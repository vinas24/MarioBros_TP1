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
    private List<Land> lista_land;
    private List<ExitDoor> lista_exitdoor;
    private List<Goomba> lista_goomba;
    private Mario mario;


    public GameObjectContainer() {
        this.lista_land = new ArrayList<>();
        this.lista_exitdoor = new ArrayList<>();
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
        this.lista_exitdoor.add(exit);
    }

    public void add(Mario mario) {
        this.mario = mario;
    }

    public String positionToIcon(int col, int row){
        Position p = new Position(row,col);
        String S = Messages.EMPTY;
        for(Land land: lista_land) {
            if(land.isInPosition(p)) S = land.getIcon();
        }

        if(S.equals(Messages.EMPTY)) {
            for(Goomba goomba: lista_goomba) {
                if(goomba.isInPosition(p)) S = goomba.getIcon();
            }
        }

        if(S.equals(Messages.EMPTY)) {
            for(ExitDoor exitdoor: lista_exitdoor) {
                if (exitdoor.isInPosition(p)) S = exitdoor.getIcon();
            }
        }

        if(S.equals(Messages.EMPTY) && mario.isInPosition(p)) S = mario.getIcon();
        return S;
    }

    //TODO falta dejar bien las colisiones y la suma de puntos
    public void update(ActionList acciones, Game game) {
        //Primero update de mario para que tenga prioridad en las colisiones
        this.mario.update(lista_land, acciones);
        //colisiones mario
        game.doInteractionsFrom(mario);
        //colisiones con la puerta
        isMarioInDoor(game);

        //Luego todos los goombas
        for(Goomba g: lista_goomba) {
            g.update(lista_land);
        }
        doInteractionsFrom(mario);

        //borramos los goombas muertos
        lista_goomba.removeIf(Goomba::isDead);
    }

    public void isMarioInDoor(Game game) {
        for (ExitDoor e: this.lista_exitdoor) {
            if (mario.interactWith(e)){
                game.marioExited();
            }
        }
    }


    public void doInteractionsFrom(Mario mario) {
        for(Goomba goomba: lista_goomba) {
            if(mario.interactWith(goomba)) goomba.receiveInteraction(mario);
        }
    }
}
