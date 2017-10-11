package player;

import java.util.ArrayList;
import java.util.List;

import jChess.Alliance;
import piece.King;
import piece.Piece;

public abstract class Player {
	private Alliance playerAlliance;
	private List<Piece> playerPieceList = new ArrayList<>();
	private List<Piece> playerGraveyard = new ArrayList<>();
	
	public Player(final Alliance color ,final List<Piece> list , final List<Piece> graveyard) {
		this.playerAlliance = color;
		this.playerPieceList = list;
		this.playerGraveyard = graveyard;
	}
	
	public Alliance getPlayerAlliance() {
		return this.playerAlliance;
	}
	public List<Piece> getPlayerPieceList(){
		return this.playerPieceList;
	}
	public List<Piece> getPlayerGraveyard(){
		return this.playerGraveyard;
	}
	
	public void setPlayerGraveyard(List<Piece> graveyard) {
		this.playerGraveyard = graveyard;
	}	
	public King getPlayerKing() {
		for (Piece currentPlayerPiece : getPlayerPieceList()) {
			if (currentPlayerPiece.className.equals("King")) {
				return (King)currentPlayerPiece;
			}
		}
		return null;
	}
	
}
