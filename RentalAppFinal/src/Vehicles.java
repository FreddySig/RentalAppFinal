import java.io.Serializable;

public class Vehicles implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int vehicleId;
	private VehicleType type;
	private String plateNum;
	private Status status;
	private int mpg;
	private double fuelLevel;
	private double dailyRate;


	public Vehicles(int vehicleId, VehicleType type, String plateNum, Status status, int mpg, double fuelLevel) {
		super();
		this.vehicleId = vehicleId;
		this.type = type;
		this.plateNum = plateNum;
		this.status = status;
		this.mpg = mpg;
		this.fuelLevel = fuelLevel;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getMpg() {
		return mpg;
	}

	public void setMpg(int mpg) {
		this.mpg = mpg;
	}

	public double getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public VehicleType getType() {
		return type;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public Status getStatus() {
		return status;
	}
	public double getDailyRate() {
		return dailyRate;
	}
	 
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	


}
