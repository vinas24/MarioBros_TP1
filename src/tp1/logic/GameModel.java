//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;

public interface GameModel {
    public boolean isFinished();
    public void update();
    public void reset(int nLvl);
    public void exit();
    public void addAction(Action action);
}
