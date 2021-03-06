import java.io.Serializable;

public enum VehicleType implements Serializable {
	CAR, TRUCK, VAN;

	@Override
	public String toString() {
		switch (this) {
		case CAR: return "Car";
		case TRUCK: return "Truck";
		case VAN: return "Van";
		default: return "Vehicle";
		}
	}
	
}
