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
import jChess.AttackMove;

public class Controller {
	 
	private Board board = new Board();
	private BoardGUI boardGUI = new BoardGUI();
	private Piece piecePressed =null;
	private List<Move> legalMoves = new ArrayList<>();
	private List<Move> allPossibleMoves = new ArrayList<>();
	
	private int stopCondition = 0;
	
	public Controller () {
		boardGUI.setVisible(true);
		boardGUI.populateBoard(board);
		boardGUI.addLabelListener(new LabelListener());
		
	}
	
	class LabelListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (stopCondition == 0) {
				boolean sem=false;
				if (piecePressed != null) {                      //partea a doua din proces  .Piesa e deja selectata. Tb sa alegem unde atacam
					for (JLabel labelClicked : boardGUI.labelList) {
						if ( labelClicked == arg0.getSource()) {
							int labelNo = boardGUI.labelList.indexOf(labelClicked);
							for (Move move : legalMoves) {
								if (move.currentTile == piecePressed.getTile() && move.destinationTile == board.getTile(labelNo)) {
									Piece pieceOnTile = move.destinationTile.getPieceOnTile();
									board = move.executeMove();
									if (move instanceof AttackMove) {
										board.getOpponentPlayer().getPlayerGraveyard().add(pieceOnTile);
									}
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
					
					
					List<Move> finalMoves = new ArrayList<>();
					List<Move> pieceMoves = new ArrayList<>();
					boolean semaphore = false;
					for (Piece piece  : board.getCurrentPlayer().getPlayerPieceList()) {
						pieceMoves = piece.calculateLegalMoves(board);
						for (Move move : pieceMoves) {
							Board fakeBoard = move.executeMove();
							if (!fakeBoard.isCheck()) {
								finalMoves.add(move);
								semaphore = true;
								break;
							}
						}
						if (semaphore == true)
							break;
					}
					if (finalMoves.isEmpty()) {
						if (board.isCheck())
							stopCondition = 2;  //mat
						else
							stopCondition = 1;      //pat
					}
				} 
				else {
						legalMoves = new ArrayList<>();
						allPossibleMoves = new ArrayList<>();
						for (JLabel labelClicked : boardGUI.labelList) {                                     //luam fiecare label din cele 64
						
							if ( labelClicked == arg0.getSource() && board.getCurrentPlayer().getPlayerPieceList().contains(board.getTile(boardGUI.labelList.indexOf(labelClicked)).getPieceOnTile())){  //daca labelul pe care am dat click
								int labelNo = boardGUI.labelList.indexOf(labelClicked);							//retinem numarul labelului curent
								Piece pieceOnTile = board.getBoard().get(board.getTile(labelNo));           //retinem piesa de pe label 
								Board fakeBoard = board;
								allPossibleMoves = pieceOnTile.calculateLegalMoves(fakeBoard);
								for (Move move : allPossibleMoves) {
									fakeBoard = move.executeMove();
									if (!fakeBoard.isCheck()) {
										legalMoves.add(move);
									}
								}
								if (!legalMoves.isEmpty()) {
									piecePressed = pieceOnTile;	
									for (Move move : legalMoves) {
										int destinationNumber = move.destinationTile.getTileNumber();
											boardGUI.labelList.get(destinationNumber).setBackground(new Color(165,210,79));
											boardGUI.labelList.get(destinationNumber).setOpaque(true);
										}
								}
								break;
							}				
						}
				}
			}
			if (stopCondition != 0) {
				if (stopCondition == 1) {
					boardGUI.getTextBox().setForeground(Color.BLACK);
					boardGUI.setTextBox("It`s stalemate");
				}
				else {
					boardGUI.getTextBox().setForeground(Color.BLACK);
					boardGUI.setTextBox(board.getOpponentPlayer().getPlayerAlliance().toString() + " player won !");
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
