package project.controller.card;

import java.awt.image.BufferedImage;
import java.util.List;


public class Card {
	private FaceName faceName;
	private Suits suits;
	private BufferedImage faceImage;
	private BufferedImage backImage;
	
	public Card(final FaceName faceName,
			final Suits suits,
			final BufferedImage faceImage,
			final BufferedImage backImage){
		
		this.faceName = faceName;
		this.suits = suits;
		this.faceImage = faceImage;
		this.backImage = backImage;
	}
	public BufferedImage getFaceImage(){
		return this.faceImage;
	}
	public BufferedImage getBackImage(){
		return this.backImage;
	}
	public Suits getSuits(){
		return this.suits;
	}
	public FaceName getFaceName(){
		return this.faceName;
	}
	public boolean cuts(List<Card> pile) {
		if(!pile.isEmpty()){
			if (this.faceName.equals(pile.get(0).getFaceName()) ||
					this.getFaceName().isSeven()){
				return true;
			}
		}
		return false;
	}
}
