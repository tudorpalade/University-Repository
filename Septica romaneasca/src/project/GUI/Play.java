package project.GUI;

import project.controller.card.Deck;
import project.controller.game.Round;
import project.controller.player.Player;

public class Play{
	Table table;
	Deck deck = new Deck();
	Player auxPlayer;
	public Player player1 = new Player(1);
	public Player player2 = new Player(2);
	Player roundWinner;
	Round round = new Round(player1,player2,player1,table);
	public Play(final Table table){
		this.table=table;
	}
}
