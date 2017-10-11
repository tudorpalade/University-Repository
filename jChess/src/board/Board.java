package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jChess.Alliance;
import jChess.Move;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import player.BlackPlayer;
import player.Player;
import player.WhitePlayer;

public class Board {
	
	public Player whitePlayer;
	public Player blackPlayer;
	private Player currentPlayer;
	protected List<Tile> tileList = new ArrayList<>();
	protected Map<Tile,Piece> board = new HashMap<>();
	protected List<Piece> whitePlayerPieces = new ArrayList<>();
	protected List<Piece> blackPlayerPieces = new ArrayList<>();

	
	public Board(){
		
		for (int i = 0 ;i<64;i++) {                            // Create an empty board
			tileList.add(new Tile(i,null));
		}
		board.put(tileList.get(0),new Rook(Alliance.BLACK,tileList.get(0)));     //remove tile from Rook
		board.put(tileList.get(1),new Knight(Alliance.BLACK,tileList.get(1)));
		board.put(tileList.get(2),new Bishop(Alliance.BLACK,tileList.get(2)));
		board.put(tileList.get(3),new Queen(Alliance.BLACK,tileList.get(3)));
		board.put(tileList.get(4),new King(Alliance.BLACK,tileList.get(4)));
		board.put(tileList.get(5),new Bishop(Alliance.BLACK,tileList.get(5)));
		board.put(tileList.get(6),new Knight(Alliance.BLACK,tileList.get(6)));
		board.put(tileList.get(7),new Rook(Alliance.BLACK,tileList.get(7)));
		
		board.put(tileList.get(8),new Pawn(Alliance.BLACK,tileList.get(8),false));
		board.put(tileList.get(9),new Pawn(Alliance.BLACK,tileList.get(9),false));
		board.put(tileList.get(10),new Pawn(Alliance.BLACK,tileList.get(10),false));
		board.put(tileList.get(11),new Pawn(Alliance.BLACK,tileList.get(11),false));
		board.put(tileList.get(12),new Pawn(Alliance.BLACK,tileList.get(12),false));
		board.put(tileList.get(13),new Pawn(Alliance.BLACK,tileList.get(13),false));
		board.put(tileList.get(14),new Pawn(Alliance.BLACK,tileList.get(14),false));
		board.put(tileList.get(15),new Pawn(Alliance.BLACK,tileList.get(15),false));
		
		for (int i=0;i<16;i++) {
			blackPlayerPieces.add(tileList.get(i).getPieceOnTile());
		}
		
		for ( int i = 16;i<48 ; i++) {
			board.put(tileList.get(i), null);
		}
		
		board.put(tileList.get(48),new Pawn(Alliance.WHITE,tileList.get(48),false));
		board.put(tileList.get(49),new Pawn(Alliance.WHITE,tileList.get(49),false));
		board.put(tileList.get(50),new Pawn(Alliance.WHITE,tileList.get(50),false));
		board.put(tileList.get(51),new Pawn(Alliance.WHITE,tileList.get(51),false));
		board.put(tileList.get(52),new Pawn(Alliance.WHITE,tileList.get(52),false));
		board.put(tileList.get(53),new Pawn(Alliance.WHITE,tileList.get(53),false));
		board.put(tileList.get(54),new Pawn(Alliance.WHITE,tileList.get(54),false));
		board.put(tileList.get(55),new Pawn(Alliance.WHITE,tileList.get(55),false));
		
		board.put(tileList.get(56),new Rook(Alliance.WHITE,tileList.get(56)));     //remove tile from Rook
		board.put(tileList.get(57),new Knight(Alliance.WHITE,tileList.get(57)));
		board.put(tileList.get(58),new Bishop(Alliance.WHITE,tileList.get(58)));
		board.put(tileList.get(59),new Queen(Alliance.WHITE,tileList.get(59)));
		board.put(tileList.get(60),new King(Alliance.WHITE,tileList.get(60)));
		board.put(tileList.get(61),new Bishop(Alliance.WHITE,tileList.get(61)));
		board.put(tileList.get(62),new Knight(Alliance.WHITE,tileList.get(62)));
		board.put(tileList.get(63),new Rook(Alliance.WHITE,tileList.get(63)));
		
		for (int i=48;i<64;i++) {
			whitePlayerPieces.add(tileList.get(i).getPieceOnTile());
		}
		
		whitePlayer = new WhitePlayer(Alliance.WHITE , whitePlayerPieces , new ArrayList<>());
		blackPlayer = new BlackPlayer(Alliance.BLACK , blackPlayerPieces , new ArrayList<>());
		currentPlayer = whitePlayer;
	}

	
	public Board(final Board board , final Tile currentTile , final Tile destinationTile) {
		
		
		
		for (int i = 0 ;i<64;i++) {                            // Create an empty board
			tileList.add(new Tile(i,null));
		}
		
 		Piece pieceOnTile = currentTile.getPieceOnTile();
		
		for (Map.Entry<Tile,Piece> entry : board.getBoard().entrySet())
		{
			if (entry.getKey().isOccupied() || (entry.getKey() == destinationTile) ) {
				if (entry.getKey() == currentTile) {
					this.board.put(tileList.get(entry.getKey().getTileNumber()),null);
				}
				else if (entry.getKey() == destinationTile) {
					pieceOnTile = returnNewPiece(pieceOnTile,this.tileList , entry.getKey().getTileNumber());
					this.board.put(tileList.get(entry.getKey().getTileNumber()),pieceOnTile);
					if (pieceOnTile.getPieceColor().equals(Alliance.WHITE)) {
						whitePlayerPieces.add(pieceOnTile);
					}
					else 
						blackPlayerPieces.add(pieceOnTile);
				}
				else {
					Piece newPiece = returnNewPiece(entry.getValue() , this.tileList);
					this.board.put(tileList.get(entry.getKey().getTileNumber()),newPiece);	
					if (newPiece.getPieceColor().equals(Alliance.WHITE)) {
						whitePlayerPieces.add(newPiece);
					}
					else 
						blackPlayerPieces.add(newPiece);
				}
				
			}
			else {
					this.board.put(tileList.get(entry.getKey().getTileNumber()),null);	
			}
		}
		whitePlayer = new WhitePlayer(Alliance.WHITE , whitePlayerPieces , board.whitePlayer.getPlayerGraveyard());
		blackPlayer = new BlackPlayer(Alliance.BLACK , blackPlayerPieces , board.blackPlayer.getPlayerGraveyard());
		currentPlayer = board.getCurrentPlayer(whitePlayer , blackPlayer);
	}
	
	public Map<Tile,Piece> getBoard(){
		return board;
	}
	

	public Tile getTile(int currentOffset) {
		return tileList.get(currentOffset);
	}

	private Piece returnNewPiece(Piece piece , List<Tile> tileList) {
		if ( piece instanceof Bishop )
			return new Bishop(piece.getPieceColor(),tileList.get(piece.getTile().getTileNumber()));
		else if (piece instanceof King )
			return new King(piece.getPieceColor(),tileList.get(piece.getTile().getTileNumber()));
		else if (piece instanceof Knight )
			return new Knight(piece.getPieceColor(),tileList.get(piece.getTile().getTileNumber()));
		else if (piece instanceof Pawn )
			return new Pawn(piece.getPieceColor(),tileList.get(piece.getTile().getTileNumber()),piece.getHasMoved());
		else if (piece instanceof Queen )
			return new Queen(piece.getPieceColor(),tileList.get(piece.getTile().getTileNumber()));
		else if (piece instanceof Rook )
			return new Rook(piece.getPieceColor(),tileList.get(piece.getTile().getTileNumber()));
		return null;
	}
	
	private Piece returnNewPiece(Piece piece , List<Tile> tileList , int tileNo) {
		if ( piece instanceof Bishop )
			return new Bishop(piece.getPieceColor(),tileList.get(tileNo));
		else if (piece instanceof King )
			return new King(piece.getPieceColor(),tileList.get(tileNo));
		else if (piece instanceof Knight )
			return new Knight(piece.getPieceColor(),tileList.get(tileNo));
		else if (piece instanceof Pawn )
			return new Pawn(piece.getPieceColor(),tileList.get(tileNo),piece.getHasMoved());
		else if (piece instanceof Queen )
			return new Queen(piece.getPieceColor(),tileList.get(tileNo));
		else if (piece instanceof Rook )
			return new Rook(piece.getPieceColor(),tileList.get(tileNo));
		return null;
	}
	
	public Player getCurrentPlayer(Player white, Player black) {
		if (this.currentPlayer.getPlayerAlliance().equals(Alliance.WHITE))
			return white;
		return black;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public Player getOpponentPlayer() {
		if ( this.currentPlayer.getPlayerAlliance().equals(Alliance.WHITE) ) {
			return blackPlayer;
		}
		return whitePlayer;
	}
	
	public void setCurrentPlayer() {
		if (this.currentPlayer.getPlayerAlliance().equals(Alliance.WHITE)) {
			this.currentPlayer = blackPlayer;
		}
		else
			this.currentPlayer = whitePlayer;
	}
	
	public boolean isCheck() {
		King myKing = getCurrentPlayer().getPlayerKing();
		
		for (Piece piece : getOpponentPlayer().getPlayerPieceList()) {
			List<Move> pieceMoves = piece.calculateLegalMoves(this);
			for (Move move : pieceMoves) {
				if (move.destinationTile == myKing.getTile()) {
					return true;
				}
			}
		}
		return false;
	}
}
