package project.controller.card;

public enum Suits {
	
	
	DIAMONDS {
		@Override
		public boolean isSpades() {
			return false;
		}

		@Override
		public boolean isHearts() {
			return false;
		}

		@Override
		public boolean isDiamonds() {
			return true;
		}

		@Override
		public boolean isClubs() {
			return false;
		}
	},
	CLUBS {
		@Override
		public boolean isSpades() {
			return false;
		}

		@Override
		public boolean isHearts() {
			return false;
		}

		@Override
		public boolean isDiamonds() {
			return false;
		}

		@Override
		public boolean isClubs() {
			return true;
		}
	},
	HEARTS {
		@Override
		public boolean isSpades() {
			return false;
		}

		@Override
		public boolean isHearts() {
			return true;
		}

		@Override
		public boolean isDiamonds() {
			return false;
		}

		@Override
		public boolean isClubs() {
			return false;
		}
	},
	SPADES {
		@Override
		public boolean isSpades() {
			return true;
		}

		@Override
		public boolean isHearts() {
			return false;
		}

		@Override
		public boolean isDiamonds() {
			return false;
		}

		@Override
		public boolean isClubs() {
			return false;
		}
	};
	
	public abstract boolean isSpades();
	public abstract boolean isHearts();
	public abstract boolean isDiamonds();
	public abstract boolean isClubs();
}
