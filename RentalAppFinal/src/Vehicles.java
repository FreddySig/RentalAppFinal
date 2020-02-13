public class Vehicles {
	private int vehicleId;
	private String type;
	private String plateNum;
	private String status;
	private int mpg;
	private double fuelLevel;
	private double rentRate;

	public void Vehicles(String type, String plateNum, String status) {
		this.type = type;
		this.plateNum = plateNum;
		this.status = status;
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

	public double getRentRate() {
		return rentRate;
	}

	public void setRentRate(double rentRate) {
		this.rentRate = rentRate;
	}

}
