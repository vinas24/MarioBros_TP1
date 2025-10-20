//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;

public interface GameStatus {
    public int remainingTime();
    public String positionToString(int col, int row);
    public boolean playerWins();
    public boolean playerLoses();
    public boolean playerExited();
}
