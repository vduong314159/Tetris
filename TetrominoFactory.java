package Tetris;
/*
 * This is my TetrominoFactory class. It constructs random pieces. 
 */
public class TetrominoFactory {

	public TetrominoFactory() {	}
	
	public TetrisPiece getTetromino(Board board) {
		int rand = (int) (Math.random()*7); //this will give me a random number btwn 0 and 6
		TetrisPiece _newT = null; // not yet created
		
		switch (rand) {
			case 0:
				_newT = new IPiece(board);
				break;
			case 1:
				_newT = new JPiece(board);
				break;
			case 2:
				_newT = new LPiece(board);
				break;
			case 3:
				_newT = new OPiece(board);
				break;
			case 4:
				_newT = new SPiece(board);
				break;
			case 5:
				_newT = new TPiece(board);
				break;
			case 6:
				_newT = new ZPiece(board);
				break;
		}
		return _newT;
	}
	

}
