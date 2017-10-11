package jChess;

import java.util.Objects;

import board.Board;
import board.Tile;

public abstract class Move {
	
	protected Board board;
	public Tile currentTile;
	public Tile destinationTile;
	
	public Move(final Board board,final Tile currentTile, final Tile destinationTile){
		this.board = board;
		this.currentTile = currentTile;
		this.destinationTile = destinationTile;
	}
	
	
	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Move)) {
            return false;
        }

        Move user = (Move) o;

        return user.currentTile.getTileNumber() == currentTile.getTileNumber() &&
                user.destinationTile.getTileNumber() == destinationTile.getTileNumber();
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(currentTile.getTileNumber(),destinationTile.getTileNumber());
    }

	public abstract Board executeMove();
	
}
