package jChess;

public enum Alliance {
	WHITE {

		@Override
		public int colorIndex() {
			return -1;
		}
		
	},BLACK {
		@Override
		public int colorIndex() {
			return 1;
		}
	};
	
	public abstract int colorIndex();
}
