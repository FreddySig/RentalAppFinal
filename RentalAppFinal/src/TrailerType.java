
public enum TrailerType {
	COVERED, UNCOVERED;

	@Override
	public String toString() {
		switch (this) {
		case COVERED: return "Covered";
		case UNCOVERED: return "Uncovered";
		default: return "Trailer";
		}
	}

}
