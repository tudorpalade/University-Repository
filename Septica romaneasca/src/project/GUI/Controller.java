package project.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import project.controller.card.Card;
import project.controller.game.Round;
import project.controller.player.Player;

public class Controller {
	ImageIcon imageIcon = new ImageIcon("GREEN.png");
	private Play play;
	private Table table;
	boolean decision = false;
	Player roundWinner;

	public Controller(Play play, Table table) {
		this.play = play;
		this.table = table;
		table.addCardsListener(new CardsListener());
		table.addDealListener(new DealListener());
	}

	class DealListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (table.allowDeal == true) {
				play.deck.dealCard(4, play.player1);
				play.deck.dealCard(4, play.player2);
			}
			setHandImages();
			table.setAllowDeal(false);
		}
	}

	class CardsListener implements MouseListener {
		int numberOfCard = 0;

		@Override
		public void mouseClicked(MouseEvent event) {
			for (int i = 0; i < play.round.currentPlayer.playerHand.size(); i++)
				if (event.getSource() == table.getPlayer1Image(i)) {
					numberOfCard = i;
					break;
				}
			if (table.allowCards[numberOfCard] == true) {
				play.round.playHand(play.round.currentPlayer,numberOfCard,decision);
				for (int i = 0; i < 4; i++) {
					table.allowCards[i] = true;
				}
				ImageIcon playedCardImage = new ImageIcon(play.round.pile.get(
						play.round.cardsPlayed).getFaceImage());
				table.pileStack[play.round.cardsPlayed]
						.setIcon(playedCardImage);
				setHandImages();
				play.round.cardsPlayed++;

				if ((play.round.getOpponent(play.round.currentPlayer) == play.round.whoStarted)) {
					if (!play.round.whoStarted.listOfAbleCardsToCut(
							play.round.pile.get(0)).isEmpty()
							&& !play.round.pile.isEmpty()) {
						play.round.currentPlayer = play.round.establishNextPlayer();
						setHandImages();
						DecisionTaker decisionTaker = new DecisionTaker();
						decisionTaker.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						decisionTaker.setVisible(true);
						decision = decisionTaker.decision;
						if (decision == false) {
							roundWinner = play.round.currentRoundWinner;
							roundWinner.playerWinnedCards.addAll(play.round.pile);
							if (play.deck.getDeckSize() != 0) {
								if (play.round.cardsPlayed > play.deck.getDeckSize()) {
									int deckSizeDiv2 = play.deck.getDeckSize() / 2;
									play.deck.dealCard(deckSizeDiv2,
											roundWinner);
									play.deck
											.dealCard(deckSizeDiv2, play.round
													.getOpponent(roundWinner));
								} else {
									play.deck.dealCard(
											play.round.cardsPlayed / 2,
											roundWinner);
									play.deck
											.dealCard(
													play.round.cardsPlayed / 2,
													play.round
															.getOpponent(roundWinner));
								}
							}
							else {
								table.deck.setIcon(imageIcon);
							}
							play.round = new Round(play.player1, play.player2,roundWinner, table);
							setHandImages();
							setPileImages();
						} else {
							int i = 0;
							for (Card handCard : play.round.currentPlayer.playerHand) {
								boolean sem = false;
								for (Card listCard : play.round.currentPlayer.listOfAbleCardsToCut(play.round.pile.get(0))) {
									if (handCard == listCard) {
										sem = true;
										break;
									}
								}
								if (sem == false) {
									table.allowCards[i] = false;
								}
								i++;
							}
						}
					} else {
						roundWinner = play.round.currentRoundWinner;
						roundWinner.playerWinnedCards.addAll(play.round.pile);
						if (play.deck.getDeckSize() != 0) {
							if (play.round.cardsPlayed > play.deck
									.getDeckSize()) {
								int deckSizeDiv2 = play.deck.getDeckSize() / 2;
								play.deck.dealCard(deckSizeDiv2, roundWinner);
								play.deck.dealCard(deckSizeDiv2,
										play.round.getOpponent(roundWinner));
							} else {
								play.deck.dealCard(play.round.cardsPlayed / 2,
										roundWinner);
								play.deck.dealCard(play.round.cardsPlayed / 2,
										play.round.getOpponent(roundWinner));
							}
						}
						else {
							table.deck.setIcon(imageIcon);
						}
						play.round = new Round(play.player1, play.player2,
								roundWinner, table);
						setHandImages();
						setPileImages();
					}
				} else {
					play.round.currentPlayer = play.round.establishNextPlayer();
					setHandImages();
				}
				if (play.player1.playerHand.isEmpty()
						&& play.player2.playerHand.isEmpty()
						&& play.deck.getDeckSize() == 0) {
					int whoWon = winner();
					WinnerFrame winnerFrame = new WinnerFrame(whoWon);
					winnerFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					winnerFrame.setVisible(true);
				}
			}
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
	}

	public void setHandImages() {
		if (!play.round.currentPlayer.playerHand.isEmpty()) {
			for (int j = 0; j < play.round.currentPlayer.playerHand.size(); j++) {
				ImageIcon imageIcon = new ImageIcon(
						play.round.currentPlayer.playerHand.get(j)
								.getFaceImage());
				table.player1Card[j].setIcon(imageIcon);
			}
			if (play.round.currentPlayer.playerHand.size() < 4) {
				for (int i = play.round.currentPlayer.playerHand.size(); i < 4; i++) {
					ImageIcon greenIcon = new ImageIcon("GREEN.png");
					table.player1Card[i].setIcon(greenIcon);
				}
			}
		}
	}

	public int winner() {
		int player1=0;
		int player2=0;
		
		for (Card card : play.round.player1.playerWinnedCards){
			if (card.getFaceName().isPoint()){
				player1++;
			}
		}
		for (Card card : play.round.player2.playerWinnedCards){
			if (card.getFaceName().isPoint()){
				player2++;
			}
		}
		if ( player1 > player2)
			return 1;
		else if (player1 < player2)
			return 2;
		return 0;
	}

	public void setPileImages() {
		for (int i = 0; i < 8; i++) {
			ImageIcon greenIcon = new ImageIcon("GREEN.png");
			table.pileStack[i].setIcon(greenIcon);
		}

	}
}
