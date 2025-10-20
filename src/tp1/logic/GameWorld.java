//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;

import tp1.logic.gameobjects.Mario;

public interface GameWorld {
    public void marioExited();
    public void doInteractionsFrom(Mario mario);
    public boolean isSolid(Position pos);
    public boolean landInPos(Position pos);
    public void addPoints(int n);
}
