//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic;
import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

//TODO cambiar para que solo sea con gameObjects
public class GameObjectContainer {
    //NUEVO
    private List<GameObject> gameObjects;
    //ANTIGUO
    private List<Land> lista_land;
    private List<ExitDoor> lista_exitdoor;
    private List<Goomba> lista_goomba;
    private Mario mario;


    public GameObjectContainer() {
        //NUEVO
        gameObjects = new ArrayList<>();
        //ANTIGUO
        this.lista_land = new ArrayList<>();
        this.lista_exitdoor = new ArrayList<>();
        this.lista_goomba = new ArrayList<>();
        this.mario = new Mario(null, null);
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


    //TODO:CAMBIAR EL RESTO
    //Solo un metodo add generico
    public void add(GameObject obj){
        this.gameObjects.add(obj);
    }

    public String positionToIcon(Position p){
        String S = Messages.EMPTY;
        for(Land land: lista_land) {
            if(land.isInPosition(p)) S = land.getIcon();
        }

        boolean multiplesGoombas = false;
        if(S.equals(Messages.EMPTY)) {
            for(Goomba goomba: lista_goomba) {
                if(goomba.isInPosition(p)) {
                    if(!multiplesGoombas) {
                        S = goomba.getIcon();
                        multiplesGoombas = true;
                    } else{
                        S = goomba.getIcon() + goomba.getIcon();
                    }
                }
            }
        }

        if(S.equals(Messages.EMPTY)) {
            for (ExitDoor exitdoor : lista_exitdoor) {
                if (exitdoor.isInPosition(p)) S = exitdoor.getIcon();
            }

            if (mario.isInPosition(p)) {
                S += mario.getIcon();
            }
            if (mario.isAlive() && mario.isInPosition(p.moverPosicion(Action.DOWN)) && mario.isMarioBig()) {//he cambiado !mario.estaMuerto
                S += mario.getIcon();
            }


        }

        return S;
    }

    public void update(GameWorld game) {
        //Primero update de mario para que tenga prioridad en las colisiones
        this.mario.update();
        //colisiones mario
        game.doInteractionsFrom(mario);
        //colisiones con la puerta

        //Luego todos los goombas
        for(Goomba g: lista_goomba) {
            g.update(); //ya no hace falta lista_land
        }

        if(this.mario.isAlive()) game.doInteractionsFrom(mario);

        //borramos los goombas muertos
        lista_goomba.removeIf(g -> !g.isAlive());
    }


    //TODO: Ya lo cambiaremos con el double dispatch
    protected void doInteractionsFrom(Mario mario, Game game) {
        //interaccion con puerta
        for (ExitDoor e: this.lista_exitdoor) {
            if (mario.interactWith(e)){
                game.marioExited();
            }
        }
        //interaccion con goomba
        for(Goomba goomba: lista_goomba) {
            if(mario.interactWith(goomba)) {
                if(goomba.isAlive()) {
                    goomba.receiveInteraction(mario);
                    game.addPoints(100); //por cada goomba con que interactue +100p
                }
            }
        }
    }

    protected boolean landInPosition(Position pos) {
        for(Land land: lista_land) {
            if(land.isInPosition(pos))return true;
        }
        return false;
    }

    //Busca la si hay un obj solido en la posicion dada
    protected boolean isSolid(Position pos) {
        for(GameObject obj: this.gameObjects) {
            if(obj.isInPosition(pos) && obj.isSolid()) return true;
        }
        return false;
    }
}