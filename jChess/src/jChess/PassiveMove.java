package jChess;

import board.Board;
import board.Tile;

public class PassiveMove extends Move {

	public PassiveMove(Board board, Tile currentTile, Tile destinationTile) {
		super(board, currentTile, destinationTile);
	}
	
	@Override
	public Board executeMove() { 
		Board newBoard = new Board(board,currentTile,destinationTile);
		newBoard.getTile(destinationTile.getTileNumber()).getPieceOnTile().setHasMoved();
		return newBoard;
	}
 
}
