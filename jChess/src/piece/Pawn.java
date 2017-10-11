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
import jChess.PawnDoubleMove;

public class Pawn extends Piece {

	private final int[] candidateLegalMoves = {7,8,9,16};
	
	public Pawn(Alliance color,Tile tile, boolean hasMoved) {
		super(color,tile);
		this.hasMoved = hasMoved;
	}

	@Override
	public List<Move> calculateLegalMoves(final Board board) {
		
		List<Move> legalMoves = new ArrayList<>();
		
		for (int candidateMove : candidateLegalMoves) {
			int currentDestinationOffset = this.tile.getTileNumber();
			if (isFirstColumnExclusion(currentDestinationOffset, candidateMove) || isEightColumnExclusion(currentDestinationOffset, candidateMove)) {
				continue;
			}
			currentDestinationOffset += candidateMove * this.getPieceColor().colorIndex();
			if (BoardUtils.isOnTable(currentDestinationOffset)) {
				Tile destinationTile = board.getTile(currentDestinationOffset);
				if ((candidateMove == 7 || candidateMove == 9) && destinationTile.isOccupied()) {
					if (!destinationTile.getPieceOnTile().getPieceColor().toString().equals(this.getPieceColor().toString())) {
						legalMoves.add(new AttackMove(board,this.tile,destinationTile));
						moves.add(currentDestinationOffset);
					}
				}
				if ((candidateMove == 16) && (this.hasMoved == false) && !(board.getTile(currentDestinationOffset + 8 * (-1)*this.getPieceColor().colorIndex() )).isOccupied()
						&& !(board.getTile(currentDestinationOffset)).isOccupied()) {
					legalMoves.add(new PawnDoubleMove(board,this.tile,destinationTile));
					moves.add(currentDestinationOffset);
				}
				if (candidateMove == 8 && !destinationTile.isOccupied()) {
					legalMoves.add(new PassiveMove(board,this.tile,destinationTile));
					moves.add(currentDestinationOffset);
				}
			}
		}
		return legalMoves;
	}

	private boolean isFirstColumnExclusion( final int tileNo, final int candidateDestination) {
		if (BoardUtils.isInColumn(1, tileNo) && 
			(( candidateDestination == 9 && this.getPieceColor().equals(Alliance.WHITE) ) ||
			( candidateDestination == 7  && this.getPieceColor().equals(Alliance.BLACK) ))) {
			return true;
		}
		return false;
	}
	
	private boolean isEightColumnExclusion( final int tileNo, final int candidateDestination) {
		if (BoardUtils.isInColumn(8, tileNo) && 
			(( candidateDestination == 9 && this.getPieceColor().equals(Alliance.BLACK) ) ||
			( candidateDestination == 7  && this.getPieceColor().equals(Alliance.WHITE) ))) {
			return true;
		}
		return false;
	}
	
	public void setHasMoved() {
		hasMoved = true;
	}
	
}
