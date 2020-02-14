import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RentalLocations implements Serializable {
	
	private static final long serialVersionUID = 8743507069010815384L;
	private String name; // rental location name,cities for locations
	private int id;
	private Address add;
	private List<Vehicles> inventory;


	public RentalLocations(String name, int id, Address add) {
		this.name = name;
		this.id = id;
		this.add = add;
		this.inventory = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public Address getAddress() {
		return add;
	}
	
	public void setAddress(Address add) {
		this.add = add;
	}
	
	public List<Vehicles> getInventory() {
		return inventory;
	}
	
	public void addToInventory(Vehicles vehicle) {
		inventory.add(vehicle);
	}

	public int availableVehicles() {
		int availableVehicles = 0;
		for(Vehicles vehicle : inventory ) {
			if(vehicle.getStatus() == Status.AVAILABLE) {
				availableVehicles++;
			}
		}
		
		return availableVehicles;
	}
	
	public int rentedVehicles() {
		int rentedVehicles = 0;
		for(Vehicles vehicle : inventory ) {
			if(vehicle.getStatus() == Status.RENTED) {
				rentedVehicles++;
			}
		}
		
		return rentedVehicles;
	}
	
	public int totalVehicles() {
		return inventory.size();
	}
	
	public double totalRevenue() {
		double total = 0;
		for (Vehicles v : inventory) {
			if (v.getStatus() == Status.RENTED) total += v.getRentRate();
		}
		return total;
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


	// Returns list of location names given zipcode
	public List<String> getLocs(int zip, List<RentalLocations> r) {
		List<String> list = new ArrayList<String>();
		for (RentalLocations loc : r) {
			if (loc.getAddress().getZip() == zip) {
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

