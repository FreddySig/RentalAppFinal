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
		setRates();
	}

	public int availableVehicles() {
		int availableVehicles = 0;
		for (Vehicles vehicle : inventory) {
			if (vehicle.getStatus() == Status.AVAILABLE) {
				availableVehicles++;
			}
		}

		return availableVehicles;
	}

	public int rentedVehicles() {
		int rentedVehicles = 0;
		for (Vehicles vehicle : inventory) {
			if (vehicle.getStatus() == Status.RENTED) {
				rentedVehicles++;
			}
		}

		return rentedVehicles;
	}

	public int totalVehicles() {
		return inventory.size();
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

	// takes the list of vehicles and returns the total rate for all vehicles,
	// available and at this location
	public double getRates() {
		double total = 0.0;
		for (Vehicles v : inventory) {
			total += v.getDailyRate();
		}
		return total;
	}
	
	//for rented vehicles
	public double totalRevenue() {
		double total = 0;
		for (Vehicles v : inventory) {
			if (v.getStatus() == Status.RENTED)
				total += v.getDailyRate();
		}
		return total;
	}
	
	
	public double setRates() {
		int zip = add.getZip();
		double total = 0.0;
		for (Vehicles v : inventory) {
			if (v.getType().toString().equalsIgnoreCase("car")) {
				if ((zip >= 98001) && (zip <= 98099)) {
					v.setDailyRate(15);
					total += 15;
				}
				if ((zip >= 98100) && (zip <= 98199)) {
					v.setDailyRate(12);
					total += 12;
				}
				if ((zip >= 98200) && (zip <= 98299)) {
					v.setDailyRate(19);
					total += 19;
				}
				if ((zip >= 98300) && (zip <= 98399)) {
					v.setDailyRate(27);
					total += 27;
				}
			}
			if (v.getType().toString().equalsIgnoreCase("truck")) {
				if ((zip >= 98001) && (zip <= 98099)) {
					v.setDailyRate(25);
					total += 25;
				}
				if ((zip >= 98100) && (zip <= 98199)) {
					v.setDailyRate(22);
					total += 22;
				}
				if ((zip >= 98200) && (zip <= 98299)) {
					v.setDailyRate(29);
					total += 29;
				}
				if ((zip >= 98300) && (zip <= 98399)) {
					v.setDailyRate(37);
					total += 37;
				}
			}
			if (v.getType().toString().equalsIgnoreCase("van")) {
				if ((zip >= 98001) && (zip <= 98099)) {
					v.setDailyRate(20);
					total += 20;
				}
				if ((zip >= 98100) && (zip <= 98199)) {
					v.setDailyRate(19);
					total += 19;
				}
				if ((zip >= 98200) && (zip <= 98299)) {
					v.setDailyRate(25);
					total += 25;
				}
				if ((zip >= 98300) && (zip <= 98399)) {
					v.setDailyRate(33);
					total += 33;
				}
			}
		}
		return total;
	}
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
