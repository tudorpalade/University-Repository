package project.controller.card;

public enum FaceName {

	SEVEN {
		@Override
		public boolean isPoint() {
			return false;
		}

		@Override
		public boolean isSeven() {
			return true;
		}
	},
	EIGHT {
		@Override
		public boolean isPoint() {
			return false;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	},
	NINE {
		@Override
		public boolean isPoint() {
			return false;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	},
	TEN {
		@Override
		public boolean isPoint() {
			return true;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	},
	JACK {
		@Override
		public boolean isPoint() {
			return false;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	},
	QUEEN {
		@Override
		public boolean isPoint() {
			return false;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	},
	KING {
		@Override
		public boolean isPoint() {
			return false;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	},
	ACE {
		@Override
		public boolean isPoint() {
			return true;
		}

		@Override
		public boolean isSeven() {
			return false;
		}
	};
	
	public abstract boolean isPoint();
	public abstract boolean isSeven();
}
