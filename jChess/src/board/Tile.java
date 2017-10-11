package board;

import piece.Piece;

public class Tile {
	
	private final int tileNumber;
	private boolean occupied;
	private Piece pieceOnTile;
	
	public Tile (final int tileNumber , final Piece pieceOnTile) {
		this.tileNumber = tileNumber;
		this.occupied = false;
		this.pieceOnTile = pieceOnTile;
	}
	
	public void setPieceOnTile(Piece piece) {
		this.pieceOnTile = piece;
	}
	
	public Piece getPieceOnTile() {
		return this.pieceOnTile;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public int getTileNumber() {
		return tileNumber;
	}
	
	public void setTileOccupied() {
		this.occupied = true;
	}
}
