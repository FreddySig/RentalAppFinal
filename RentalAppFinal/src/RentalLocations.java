import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RentalLocations implements Serializable {
	
	private static final long serialVersionUID = 8743507069010815384L;
	private String name; // rental location name,cities for locations
	private int id;
	private String type = "";
	private final int vehicles = 50;
	private int rentedVehicles;

	private double dailyRate;
	private int zip;


	// total vehicles = 50
	public RentalLocations(String name, int rentedVehicles, int id, int zip) {
		this.name = name;
		this.rentedVehicles = rentedVehicles;
		this.id = id;
		this.zip = zip;
		this.dailyRate = getRates(); // sets daily rate based on zip
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getVehicles() {
		return vehicles;
	}

	public int getRentedVehicles() {
		return rentedVehicles;
	}

	public void setRentedVehicles(int rentedVehicles) {
		this.rentedVehicles = rentedVehicles;
	}

	public int availableVehicles() {
		return vehicles - rentedVehicles;
	}

	/**
	 * @return the city
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param city the city to set
	 */
	public void setName(String z) {
		this.name = z;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public double getRates() {
		if (type.equalsIgnoreCase("car")) {	
			if ((zip >= 98001) && (zip <= 98100)) {
				dailyRate = 27.50;
			}
			if ((zip >= 98101) && (zip <= 98200)) {
				dailyRate = 22.49;
			}
			if ((zip >= 98201) && (zip <= 98300)) {
				dailyRate = 31.00;
			}
			if ((zip >= 98301) && (zip <= 98400)) {
				dailyRate = 12.25;
			}
			if ((zip >= 98401) && (zip <= 98500)) {
				dailyRate = 75.24;
			}
			if ((zip >= 98501) && (zip <= 98600)) {
				dailyRate = 34.99;
			}
			if ((zip >= 98601) && (zip <= 98700)) {
				dailyRate = 28.99;
			}
			if ((zip >= 98701) && (zip <= 98800)) {
				dailyRate = 33.00;
			}
			if ((zip >= 98801) && (zip <= 98900)) {
				dailyRate = 19.99;
			}
			if ((zip >= 98901) && (zip <= 99000)) {
				dailyRate = 9.99;
			}
		}
		if (type.equalsIgnoreCase("truck")) {	
			if ((zip >= 98001) && (zip <= 98100)) {
				dailyRate = 55.00;
			}
			if ((zip >= 98101) && (zip <= 98200)) {
				dailyRate = 44.98;
			}
			if ((zip >= 98201) && (zip <= 98300)) {
				dailyRate = 61.49;
			}
			if ((zip >= 98301) && (zip <= 98400)) {
				dailyRate = 24.49;
			}
			if ((zip >= 98401) && (zip <= 98500)) {
				dailyRate = 98.74;
			}
			if ((zip >= 98501) && (zip <= 98600)) {
				dailyRate = 69.99;
			}
			if ((zip >= 98601) && (zip <= 98700)) {
				dailyRate = 56.49;
			}
			if ((zip >= 98701) && (zip <= 98800)) {
				dailyRate = 62.79;
			}
			if ((zip >= 98801) && (zip <= 98900)) {
				dailyRate = 27.99;
			}
			if ((zip >= 98901) && (zip <= 99000)) {
				dailyRate = 28.99;
			}
		}
		if (type.equalsIgnoreCase("van")) {	
			if ((zip >= 98001) && (zip <= 98100)) {
				dailyRate = 37.50;
			}
			if ((zip >= 98101) && (zip <= 98200)) {
				dailyRate = 32.49;
			}
			if ((zip >= 98201) && (zip <= 98300)) {
				dailyRate = 41.00;
			}
			if ((zip >= 98301) && (zip <= 98400)) {
				dailyRate = 22.25;
			}
			if ((zip >= 98401) && (zip <= 98500)) {
				dailyRate = 85.24;
			}
			if ((zip >= 98501) && (zip <= 98600)) {
				dailyRate = 44.99;
			}
			if ((zip >= 98601) && (zip <= 98700)) {
				dailyRate = 38.99;
			}
			if ((zip >= 98701) && (zip <= 98800)) {
				dailyRate = 43.00;
			}
			if ((zip >= 98801) && (zip <= 98900)) {
				dailyRate = 29.99;
			}
			if ((zip >= 98901) && (zip <= 99000)) {
				dailyRate = 19.99;
			}
		}

		return dailyRate;
	}

	public double total() {
		
		return rentedVehicles * dailyRate;
	}

	// Returns list of location names given zipcode
	public List<String> getLocs(int zip, List<RentalLocations> r) {
		List<String> list = new ArrayList<String>();
		for (RentalLocations loc : r) {
			if (loc.getZip() == zip) {
				list.add(loc.getName());
			}
		}
		return list;
	}

	/*
	 * // gets the rental rate given a name public void locRates(String name,
	 * List<RentalLocations> rL) { for (RentalLocations list : rL) { if
	 * (list.getName() == (name)) { System.out.println("Name:" + name + " " +
	 * list.getDailyRate()); // Needs to be edited based on GUI } } }
	 * 
	 * // gets the details when given a location name public void locDetails(String
	 * name, List<RentalLocations> rL) {// name of city desired for (RentalLocations
	 * list : rL) { if (list.getName() == name) {
	 * System.out.println(list.toString()); // needs to be edited based on GUI } } }
	 */
}
