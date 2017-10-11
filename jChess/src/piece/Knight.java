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

public class Knight extends Piece {
	
	private final int[] candidateLegalMoves = {-17,-15,-10,-6,6,10,15,17};

	public Knight(Alliance color,Tile tile) {
		super(color,tile);
	}

	@Override
	public List<Move> calculateLegalMoves(final Board board) {
		List<Move> legalMoves = new ArrayList<>();
		
		for (int candidateMove : candidateLegalMoves) {
			int currentDestinationOffset = this.tile.getTileNumber();
					if (!((isEightColumnExclusion(currentDestinationOffset, candidateMove)) || isFirstColumnExclusion(currentDestinationOffset, candidateMove)
						|| (isSecondColumnExclusion(currentDestinationOffset, candidateMove) || (isSeventhColumnExclusion(currentDestinationOffset, candidateMove))))) {
							currentDestinationOffset += candidateMove;
							if (BoardUtils.isOnTable(currentDestinationOffset)) {
								Tile destinationTile = board.getTile(currentDestinationOffset);
								if ( destinationTile.isOccupied() ) {
									if (!destinationTile.getPieceOnTile().getPieceColor().toString().equals(this.tile.getPieceOnTile().getPieceColor().toString()))  {      //daca e de culoare diferita
										legalMoves.add(new AttackMove(board,this.tile,destinationTile));
										moves.add(currentDestinationOffset);
									}
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
		if (BoardUtils.isInColumn(1, tileNo) && ((candidateDestination == 6 ) || (candidateDestination == 15 ) || (candidateDestination == -10 ) || (candidateDestination == -17 )))
			return true;
		return false;
	}
	
	private boolean isEightColumnExclusion( final int tileNo , final int candidateDestination) {
		if (BoardUtils.isInColumn(8, tileNo) && ((candidateDestination == -6 ) || (candidateDestination == -15 ) || (candidateDestination == 10 ) || (candidateDestination == 17 )))
			return true;
		return false;
	}
	
	private boolean isSecondColumnExclusion( final int tileNo , final int candidateDestination) {
		if (BoardUtils.isInColumn(2, tileNo) && ((candidateDestination == 6 ) || (candidateDestination == -10)))
			return true;
		return false;
	}
	
	private boolean isSeventhColumnExclusion( final int tileNo , final int candidateDestination) {
		if (BoardUtils.isInColumn(7, tileNo) && ((candidateDestination == -6 ) || (candidateDestination == 10 )))
			return true;
		return false;
	}

}
