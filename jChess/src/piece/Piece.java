package piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import board.Board;
import board.Tile;
import jChess.Alliance;
import jChess.Move;

public abstract class Piece {
	
	public List<Integer> moves = new ArrayList<>();
	private Alliance color;
	public Tile tile;
	private ImageIcon icon;
	protected boolean hasMoved;
	
	public String className ;
	public String pieceAlliance;
	
	public Piece(final Alliance color, final Tile tile) {
		this.color=color; 
		this.tile = tile;
		className = this.getClass().getSimpleName();
		pieceAlliance = this.getPieceColor().toString();
		tile.setPieceOnTile(this);
		tile.setTileOccupied();
		
		icon = new ImageIcon(pieceAlliance+className+".png");
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon= new ImageIcon(newimg);  // transform it back
		
		
	}
	
	public void setHasMoved() {
		hasMoved = true;
	}
	
	public boolean getHasMoved() {
		return this.hasMoved;
	}
	
	public ImageIcon getIcon() {
		return this.icon;
	}
	public Tile getTile() {
		return this.tile;
	}
	public void setTile(final Tile tile) {
		this.tile= tile;
	}
	public Alliance getPieceColor() {
		return this.color;
	}

	public abstract List<Move> calculateLegalMoves(final Board board);
	
}
