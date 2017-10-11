package jChess;

import GUI.GraveyardFrame;
import board.Board;
import board.Tile;
import piece.Piece;

public class AttackMove extends Move {

	public AttackMove(Board board, Tile currentTile, Tile destinationTile) {
		super(board, currentTile, destinationTile);
	}

	@Override
	public Board executeMove() { 
	   //	Piece pieceOnTile = destinationTile.getPieceOnTile();
		Board newBoard = new Board(board,currentTile,destinationTile);
		newBoard.getTile(destinationTile.getTileNumber()).getPieceOnTile().setHasMoved();
		//newBoard.getOpponentPlayer().getPlayerGraveyard().add(pieceOnTile);
		
		return newBoard;
	}
	
}
