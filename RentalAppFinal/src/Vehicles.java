public class Vehicles {
	private int vehicleId;
	private String type;
	private String plateNum;
	private Status status;
	private int mpg;
	private double fuelLevel;
	private double rentRate;
	private int dailyRate;

	public Vehicles(String type, String plateNum, Status status) {
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

	public String getType() {
		return type;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public Status getStatus() {
		return status;
	}
	public double getDailyRate(double dailyRate) {
		return dailyRate;
	}
	 
	public void setDailyRate(int dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	
	public double getRates() {
		int zip = 0;
		
		if (type.equalsIgnoreCase("car")) {
			if ((zip >= 98001) && (zip <= 98099)) {
				dailyRate = 15;
			}
			if ((zip >= 98100) && (zip <= 98199)) {
				dailyRate = 12;
			}
			if ((zip >= 98200) &&(zip <= 98299)) {
				dailyRate = 19;
			}
			if ((zip >= 98300) && (zip <= 98399)) {
				dailyRate = 27;
			}
		}
		if (type.equalsIgnoreCase("truck")) {
			if ((zip >= 98001) && (zip <= 98099)) {
				dailyRate = 25;
			}
			if ((zip >= 98100) && (zip <= 98199)) {
				dailyRate = 22;
			}
			if ((zip >= 98200) &&(zip <= 98299)) {
				dailyRate = 29;
			}
			if ((zip >= 98300) && (zip <= 98399)) {
				dailyRate = 37;
			}
		}
		if (type.equalsIgnoreCase("van")) {
			if ((zip >= 98001) && (zip <= 98099)) {
				dailyRate = 20;
			}
			if ((zip >= 98100) && (zip <= 98199)) {
				dailyRate = 19;
			}
			if ((zip >= 98200) &&(zip <= 98299)) {
				dailyRate = 25;
			}
			if ((zip >= 98300) && (zip <= 98399)) {
				dailyRate = 33;
			}
		}
		return dailyRate;
	}

}
