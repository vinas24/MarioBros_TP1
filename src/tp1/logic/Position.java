package tp1.logic;


import static tp1.logic.Game.DIM_X;
import static tp1.logic.Game.DIM_Y;

/**
 *
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 *
 */
public class Position {

	public int col;
	public int row;

	public Position(int row, int col) {
		this.col = col;
		this.row = row;
	}

	public boolean equals(int col, int row) {
		return this.col == col && this.row == row;
	}

	//TODO fill your code
	//Por si hace falta meto métodos
	public Position moverPosicion(Action a){
		return new Position(this.row + a.getY(), this.col + a.getX());
	}

	public Position inferior(){
		return new Position(this.row+1, this.col);
	}

	public boolean fueraTablero(){
		return this.row >= DIM_Y;
	}

	public boolean enBorde() {
		return (this.col == 0 || this.col == DIM_X);
	}

}
