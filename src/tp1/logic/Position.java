//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;


import java.util.Objects;

import static tp1.logic.Game.DIM_X;
import static tp1.logic.Game.DIM_Y;


public class Position {

	private final int col;
	private final int row;

	public Position(int row, int col) {
		this.col = col;
		this.row = row;
	}

    //TODO: Cambiar a un único return si el profe nos llora
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return col == position.col && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    public Position moverPosicion(Action a){
		return new Position(this.row + a.getY(), this.col + a.getX());
	}

	public boolean fueraTablero(){
		return this.row >= DIM_Y || this.col >= DIM_X;
	}

	public boolean enBorde() {
		return (this.col < 0 || this.col > DIM_X);
	}

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
