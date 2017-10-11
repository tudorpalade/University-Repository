package board;

import piece.Piece;

public class BoardUtils {
	
	
	public final static int[] FIRST_COLUMN = {0,8,16,24,32,40,48,56};
	public final static int[] SECOND_COLUMN = {1,9,17,25,33,41,49,57};
	public final static int[] SEVENTH_COLUMN = {6,14,22,30,38,46,54,62};
	public final static int[] EIGHT_COLUMN = {7,15,23,31,39,47,55,63};
	
	public static boolean isInColumn(final int column ,final int tileNumber){
		switch(column) {
		
		case 1: for (int tile : FIRST_COLUMN) {
			if (tileNumber == tile )
				return true;
		} break;
		case 2: for( int tile : SECOND_COLUMN) {
			if (tileNumber == tile)
				return true;
		} break;
		case 7: for( int tile : SEVENTH_COLUMN) {
			if (tileNumber == tile)
				return true;
		} break;
		case 8: for (int tile : EIGHT_COLUMN) {
			if (tileNumber == tile )
				return true;
		} break;
		}
		return false;
	} 
	
	public static boolean isOnTable(final int offset) {
		return (offset < 64) && (offset >-1);
	}
	
	public static int getLineNumber(final int tileNo) {
		if (tileNo < 8 && tileNo >-1)
			return 0;
		else if (tileNo >7 && tileNo <16)
			return 1;
		else if (tileNo >15 && tileNo <24)
			return 2;
		else if (tileNo >23 && tileNo <32)
			return 3;
		else if (tileNo >31 && tileNo <40)
			return 4;
		else if (tileNo >39 && tileNo <48)
			return 5;
		else if (tileNo >47 && tileNo <56)
			return 6;
		else
			return 7;
	}
	
	public static void displayBoard(Board board) {
		for (int i=0 ;i<64 ;i++) {
			if (board.tileList.get(i).getPieceOnTile() != null)
				System.out.print(board.tileList.get(i).getPieceOnTile().toString().substring(6, 7) +" " );
			else
				System.out.print("- ");
			if (i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i == 47 || i == 55 || i == 63) {
				System.out.println(" ");
			}
		}
		for (Piece piece : board.whitePlayer.getPlayerGraveyard()) {
			System.out.print("white: " + piece.className + " " + piece.pieceAlliance + " ");
		}
		System.out.println();
		for (Piece piece : board.blackPlayer.getPlayerGraveyard()) {
			System.out.print("black: " +  piece.className + " " + piece.pieceAlliance + " ");
		}
		System.out.println();
		System.out.println();
	}
}
