package piece;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.BoardUtils;
import board.Tile;
import jChess.AttackMove;
import jChess.Alliance;
import jChess.Move;
import jChess.PassiveMove;

public final class Bishop extends Piece{

	private final int[] candidateLegalMoves = {-9,-7,7,9};
	
	public Bishop(final Alliance color,Tile tile) {
		super(color,tile);
		
	}

	public List<Move> calculateLegalMoves(final Board board) {
		List<Move> legalMoves = new ArrayList<>();
		
		for (int candidateMove : candidateLegalMoves) {
			int currentDestinationOffset = this.tile.getTileNumber();  
			while ( BoardUtils.isOnTable(currentDestinationOffset) ) {
				if (((isEightColumnExclusion(currentDestinationOffset, candidateMove)) || isFirstColumnExclusion(currentDestinationOffset, candidateMove))) {
					break;
				}
				currentDestinationOffset += candidateMove;
				if (BoardUtils.isOnTable(currentDestinationOffset)) {
					Tile destinationTile = board.getTile(currentDestinationOffset);
					if ( destinationTile.isOccupied() ) {
						if (!destinationTile.getPieceOnTile().getPieceColor().toString().equals(this.tile.getPieceOnTile().getPieceColor().toString()))  {      //daca e de culoare diferita
							legalMoves.add(new AttackMove(board,this.tile,destinationTile));
							moves.add(currentDestinationOffset);
						}
						break;
					}
					else {
						legalMoves.add(new PassiveMove(board,this.tile,destinationTile));
						moves.add(currentDestinationOffset);
					}
				}
			}
		}
		return legalMoves;
	}
	
	private boolean isFirstColumnExclusion( final int tileNo , final int candidateDestination) {
		if (BoardUtils.isInColumn(1, tileNo) && ((candidateDestination == 7 ) || (candidateDestination == -9 )))
			return true;
		return false;
	}
	
	private boolean isEightColumnExclusion( final int tileNo , final int candidateDestination) {
		if (BoardUtils.isInColumn(8, tileNo) && ((candidateDestination == -7 ) || (candidateDestination == 9 )))
			return true;
		return false;
	}
	
}

