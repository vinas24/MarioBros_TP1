//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.logic;


import static tp1.logic.Game.DIM_X;
import static tp1.logic.Game.DIM_Y;


public class Position {

	private final int col;
	private final int row;

	public Position(int row, int col) {
		this.col = col;
		this.row = row;
	}

	public boolean equals(int col, int row) {
		return this.col == col && this.row == row;
	}

	public boolean equals(Position otro){return this.equals(otro.col, otro.row); }


	public Position moverPosicion(Action a){
		return new Position(this.row + a.getY(), this.col + a.getX());
	}

	public Position inferior(){
		return new Position(this.row+1, this.col);
	}
	
	public Position superior(){
		return new Position(this.row-1, this.col);
	}

	public boolean fueraTablero(){
		return this.row >= DIM_Y || this.col >= DIM_X;
	}

	public boolean enBorde() {
		return (this.col < 0 || this.col > DIM_X);
	}

}
