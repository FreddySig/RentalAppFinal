
public enum Status {
	AVAILABLE, RENTED, MAINTENANCE, DAMAGED;

	@Override
	public String toString() {
		switch (this) {
		case AVAILABLE: return "Available";
		case RENTED: return "Rented";
		case MAINTENANCE: return "Maintenance";
		case DAMAGED: return "Damaged";
		default: return "Status";
		}
	}
	
}
