import java.io.Serializable;

public enum TrailerType implements Serializable {
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
