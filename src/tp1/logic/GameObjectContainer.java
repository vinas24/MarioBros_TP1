//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez

package tp1.logic;
import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

//TODO cambiar para que solo sea con gameObjects
public class GameObjectContainer {
    //NUEVO
    private final List<GameObject> gameObjects;
    private Mario mario;


    public GameObjectContainer() {
        //NUEVO
        gameObjects = new ArrayList<>();
        this.mario = new Mario(null, null);
    }

    public void add(Mario mario) {
        this.mario = mario;
        this.gameObjects.add(mario);
    }

    public void add(GameObject obj){
        this.gameObjects.add(obj);
    }

    //TODO: creo que si hay tres goombas, se imprimen los tres(MAL)
    public String positionToIcon(Position p) {
        String s = Messages.EMPTY;
        for(GameObject obj : gameObjects){
            if (obj.isInPosition(p) && obj.isAlive())
                s += obj.getIcon();
        }
        return s;
    }

    //TODO: cambiarlo para que no tome GAME
    public void update(GameWorld game) {
        //Primero update de mario para que tenga prioridad en las colisiones
        this.mario.update();
        //colisiones mario
        game.doInteractionsFrom(mario);
        //colisiones con la puerta

        //Luego todos los goombas
        for(GameObject g: gameObjects) {
            if(g instanceof Goomba) {
                g.update(); //ya no hace falta lista_land
            }
        }
        if(this.mario.isAlive()) game.doInteractionsFrom(mario);

        //borramos los goombas muertos
        gameObjects.removeIf(g -> !g.isAlive());
    }


    //TODO: Ya lo cambiaremos con el double dispatch
    public void doInteractionsFrom(Mario mario, Game game) {
        for(GameObject o: gameObjects) {
            if(mario.interactWith(o)) {
                if(o instanceof ExitDoor)  game.marioExited();
                else if(o instanceof Goomba) {
                    if(o.isAlive()) {
                       ((Goomba) o).receiveInteraction(mario);
                        game.addPoints(100); //por cada goomba con que interactue +100p
                    }
                }
            }
        }
    }
    //TODO no se que hay que hacer
//    public void doInteraction(GameItem other) {
//        for(GameObject o: gameObjects) {
//            if(other.interactWith(o) && o.interactWith(other)) {
//                if(o instanceof ExitDoor) game.marioExited();
//                else if(o instanceof Goomba) {
//                    if(o.isAlive()) {
//                       ((Goomba) o).receiveInteraction(mario);
//                        game.addPoints(100); //por cada goomba con que interactue +100p
//                    }
//                }
//            }
//        }
//    }

    //Busca la si hay un obj solido en la posicion dada
    protected boolean isSolid(Position pos) {
        for(GameObject obj: this.gameObjects) {
            if(obj.isInPosition(pos) && obj.isSolid()) return true;
        }
        return false;
    }
}