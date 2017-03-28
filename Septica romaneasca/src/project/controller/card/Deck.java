package project.controller.card;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import project.controller.player.Player;


public class Deck {
	
	private List<Card> deck = new ArrayList<>();
	
	public Deck(){
		BufferedImage backImage;
		BufferedImage bigImage;
		try {
			backImage = ImageIO.read(new File("spate.png"));
			bigImage = ImageIO.read(new File("cards.png"));
			BufferedImage tempImage;
			final int width=122;
			final int height=172;
			int row=0;
			int col=5;
			for (Suits suits : Suits.values()){
				for(FaceName faceName :FaceName.values()){
					tempImage = bigImage.getSubimage((col * width)+ (col * 10), 
													 (row * height) + (row * 14), 
													  width, 
													  height);
					//Resizing image**********************
					BufferedImage resized = new BufferedImage(61, 81, tempImage.getType());
					Graphics2D g = resized.createGraphics();
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g.drawImage(tempImage, 0, 0, 61, 81, 0, 0, tempImage.getWidth(),
					    tempImage.getHeight(), null);
					g.dispose();
					//************************************
					deck.add(new Card(faceName,suits,resized,backImage)) ;
					col ++;
				}
			  col=5;
			  row++;
			}
			Random random = new Random();
			for (int i=0;i<deck.size();i++){
				int randomAux = random.nextInt(deck.size()-1);
				Collections.swap(deck, i, randomAux);
			}
			displayDeck();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void dealCard(final int numberOfCards ,final Player player){
		for (int i=0; i < numberOfCards ; i++){
			player.playerHand.add(deck.get(deck.size()-1));
			deck.remove(deck.size()-1);
		}
	}
	public boolean isEmpty(){
		return deck.size() == 0 ? true : false;
	}
	public Card getCard(final int cardPosition){
		return deck.get(cardPosition);
	}
	public int getDeckSize(){
		return this.deck.size();
	}
	public void displayDeck(){
		for (int i=0;i<deck.size();i++){
			System.out.println(deck.get(i).getFaceName() + " of " +deck.get(i).getSuits());
		}
	}
}
