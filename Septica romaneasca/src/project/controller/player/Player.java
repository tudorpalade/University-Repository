package project.controller.player;

import java.util.ArrayList;
import java.util.List;

import project.controller.card.Card;


public class Player {	
	public int playerLabel;
	public List<Card> playerHand = new ArrayList<>();
	public List<Card> playerWinnedCards = new ArrayList<>();
	
	public Player(final int playerLabel){
		this.playerLabel = playerLabel;
	}
	
	
	public List<Card> listOfAbleCardsToCut(final Card card){
		List<Card> cutingCards = new ArrayList<>();
		for (Card currentCard : playerHand){
			if ((currentCard.getFaceName().equals(card.getFaceName()) || 
				currentCard.getFaceName().isSeven()) &&	!playerHand.isEmpty())
				cutingCards.add(currentCard);
			}
		return cutingCards;
	}
	
	
	public void displayPlayerHand(){
		for (int i=0;i<playerHand.size();i++){
			System.out.println(playerHand.get(i).getFaceName() + " of " +playerHand.get(i).getSuits());
		}
	}
	public void displayPlayerWinnedCards(){
		for (int i=0;i<playerWinnedCards.size();i++){
			System.out.println(playerWinnedCards.get(i).getFaceName() + " of " +playerWinnedCards.get(i).getSuits());
		}
	}
	
	public Card playCard(final int numberOfCard){
		Card tempCard;
			tempCard = playerHand.get(numberOfCard);
			playerHand.remove(tempCard);
		return tempCard;
	}

}
