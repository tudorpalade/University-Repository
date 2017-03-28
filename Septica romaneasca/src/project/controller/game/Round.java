package project.controller.game;

import java.util.ArrayList;
import java.util.List;



import project.GUI.Table;
import project.controller.card.Card;
import project.controller.player.Player;



public class Round {
	
	public List<Card> pile = new ArrayList<>();
	
	public int cardsPlayed = 0;
	public Player player1;
	public Player player2;
	public Player currentPlayer;
	public Player currentRoundWinner;
	public final Player whoStarted ;
	public Table table;
	
	public Round(final Player player1,
			final Player player2,
			final Player currentPlayer,
			final Table table){
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = currentPlayer;
		this.currentRoundWinner = currentPlayer;
		this.table=table;
		this.whoStarted=currentPlayer;
	}
	
	public void playHand(Player player,int numberOfCard,boolean decision){
		Card auxCard;
		if (decision == true){
			auxCard = currentPlayer.playCard(numberOfCard);
			pile.add(auxCard);		
			if (auxCard.cuts(pile)){
				this.currentRoundWinner = currentPlayer;
			}
		}else {
			auxCard = currentPlayer.playCard(numberOfCard);
			if (auxCard.cuts(pile)){
				this.currentRoundWinner = currentPlayer;
			}
			pile.add(auxCard);
		}
	}

	public Player establishNextPlayer() {
		if (this.currentPlayer == player1)
			return player2;
		return player1;
	}
	public Player getOpponent(Player player) {
		if (player.playerLabel == 1)
			return player2;
		return player1;
	}
	
}
