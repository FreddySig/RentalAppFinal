import java.util.List;

import master.Vehicles;
public class Customers {
	private String name;
	private int licenseNumber;
	private List<Vehicles> rentedVehicle;
	
	public Customers(String name, int licenseNumber, List<Vehicles> rentedVehicle) {
		super();
		this.name = name;
		this.licenseNumber = licenseNumber;
		this.rentedVehicle = rentedVehicle;
	}

	/**
	 * @return the id
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String name) {
		this.name = name;
	}

	/**
	 * @return the licenseNumber
	 */
	public int getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(int licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	/**
	 * @return the rentedVehicle
	 */
	public List<Vehicles> getRentedVehicle() {
		return rentedVehicle;
	}

	/**
	 * @param rentedVehicle the rentedVehicle to set
	 */
	public void setRentedVehicle(List<Vehicles> rentedVehicle) {
		this.rentedVehicle = rentedVehicle;
	}
	
	public String viewVehicles() {
		String s = "";
		for(Vehicles v:rentedVehicle) {
			s = s + v.getVehicleId();
		}
		return s;
	}
	
	public String getInfo() {
		return "[Name: " + name + ", licenseNumber: " + licenseNumber + ", Rented Vehicle: " + viewVehicles() + "]";
	}
	
	public double total() {
		double tot = 0.0;
		for(Vehicles v: rentedVehicle) {
			tot += v.getRentRate();
		}
		return tot;
	}
	
}
