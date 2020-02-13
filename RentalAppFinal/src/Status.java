
public enum Status {
	AVAILABLE, RENTED;

	@Override
	public String toString() {
		switch (this) {
		case AVAILABLE: return "Available";
		case RENTED: return "Rented";
		default: return "Status";
		}
	}
	
}
