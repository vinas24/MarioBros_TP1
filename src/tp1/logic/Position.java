package tp1.logic;


/**
 *
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 *
 */
public class Position {

	private int col;
	private int row;
	public Position(int row, int col) {
		this.col = col;
		this.row = row;
	}

	public boolean equals(int col, int row) {
		return this.col == col && this.row == row;
	}

	//TODO fill your code
	//Por si hace falta
	public Position PosInferior() {
		return new Position(this.col-1, this.row-1);
	}

}
