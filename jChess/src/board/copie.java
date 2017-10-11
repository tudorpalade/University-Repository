package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;

import board.Board;
import board.BoardUtils;
import board.Tile;
import jChess.Move;
import piece.Piece;
import jChess.Alliance;

public class Controller {
	 
	private Board board = new Board();
	private BoardGUI boardGUI = new BoardGUI();
	private Piece piecePressed =null;
	private List<Move> legalMoves = new ArrayList<>();
	
	private boolean stopCondition = false;
	
	public Controller () {
		boardGUI.setVisible(true);
		boardGUI.populateBoard(board);
		boardGUI.addLabelListener(new LabelListener());
		
	}
	
	class LabelListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (stopCondition == false) {
				boolean sem=false;
				if (piecePressed != null) {                      //partea a doua din proces  .Piesa e deja selectata. Tb sa alegem unde atacam
					for (JLabel labelClicked : boardGUI.labelList) {
						if ( labelClicked == arg0.getSource()) {
							int labelNo = boardGUI.labelList.indexOf(labelClicked);
							for (Move move : legalMoves) {
								if (move.currentTile == piecePressed.getTile() && move.destinationTile == board.getTile(labelNo)) {
									board = move.executeMove();
									board.setCurrentPlayer();
									BoardUtils.displayBoard(board);
									sem=true;
									break;
								}
							} 
							if (sem == true)
								break;
						}
					}
					boardGUI.refresh(board);
					boardGUI.addLabelListener(new LabelListener());
					piecePressed = null;
					
					setPlayerTurnLabel(board, boardGUI);                                  //White pleyer turn / Black player turn
					
				} 
				else {
					if (!board.isCheck()) {
						legalMoves = new ArrayList<>();
						for (JLabel labelClicked : boardGUI.labelList) {                                     //luam fiecare label din cele 64
						
							if ( labelClicked == arg0.getSource() && board.getCurrentPlayer().getPlayerPieceList().contains(board.getTile(boardGUI.labelList.indexOf(labelClicked)).getPieceOnTile())){  //daca labelul pe care am dat click
								int labelNo = boardGUI.labelList.indexOf(labelClicked);							//retinem numarul labelului curent
								Piece pieceOnTile = board.getBoard().get(board.getTile(labelNo));           //retinem piesa de pe label 
								piecePressed = pieceOnTile;													//avem piesa apasata																		// e labelul curent si piesa de pe label face parte din piesele playerului curren											//daca patratica pe care am apasat contine ceva
								legalMoves = pieceOnTile.calculateLegalMoves(board);					//calculam mutarile pe care le poate face
								for (int i = 0;i<64;i++) {														
									if (pieceOnTile.moves.contains(i)) {								
										boardGUI.labelList.get(i).setBackground(new Color(165,210,79));
										boardGUI.labelList.get(i).setOpaque(true);
									}
								}
								break;
							}
						}
					}
					else {
						List<Move> possibleMovesInCheck = new ArrayList<>();
						for (Piece currentPlayerPieces : board.getCurrentPlayer().getPlayerPieceList()) {
							for (Move fakeMove : currentPlayerPieces.calculateLegalMoves(board)) {
								Board fakeBoard = fakeMove.executeMove();
								if (!fakeBoard.isCheck()) {
									possibleMovesInCheck.add(fakeMove);
								}
							}	
						}
						
						for (JLabel labelClicked : boardGUI.labelList) {                                     //luam fiecare label din cele 64
							
							if ( labelClicked == arg0.getSource() && board.getCurrentPlayer().getPlayerPieceList().contains(board.getTile(boardGUI.labelList.indexOf(labelClicked)).getPieceOnTile())){  //daca labelul pe care am dat click
								int labelNo = boardGUI.labelList.indexOf(labelClicked);							//retinem numarul labelului curent
								Piece pieceOnTile = board.getBoard().get(board.getTile(labelNo));           //retinem piesa de pe label 
								piecePressed = pieceOnTile;													//avem piesa apasata																		// e labelul curent si piesa de pe label face parte din piesele playerului curren											//daca patratica pe care am apasat contine ceva
								//legalMoves = pieceOnTile.calculateLegalMoves(board);					//calculam mutarile pe care le poate face
								for (int i = 0;i<64;i++) {														
									if (pieceOnTile.moves.contains(i)) {								
										boardGUI.labelList.get(i).setBackground(new Color(165,210,79));
										boardGUI.labelList.get(i).setOpaque(true);
									}
								}
								break;
							}
						}
						
						
					}
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	protected boolean containsMove(final List<Move> moves, final Tile currentTile, final Tile destinationTile) {
		return false;
	}
	
	
	public void setPlayerTurnLabel(Board board , BoardGUI boardGUI) {
		if (board.getCurrentPlayer().getPlayerAlliance().equals(Alliance.WHITE)) {
			boardGUI.setTextBox("White player`s turn");
			boardGUI.getTextBox().setForeground(Color.white);
		}
		else {
			boardGUI.setTextBox("Black player`s turn");
			boardGUI.getTextBox().setForeground(Color.black);
		}
	}
	
}
