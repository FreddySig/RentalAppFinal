import java.awt.Component;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Queries {
	
	public static DefaultTableModel availableV(List<RentalLocations> list) {
		String[] columns = { "ID", "Name", "Available Vehicles" };
		Object[][] data = new Object[list.size()][3];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = list.get(i).availableVehicles();
		}
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		return dtm;
	}
	
	public static ArrayList<RentalLocations> detailsByLoc(List<RentalLocations> list) {
		String name = JOptionPane.showInputDialog(null, "Input location name");
		if (name == null) name = "";
		
		return filterLocationsByName(list, name);
	}
	
	public static DefaultTableModel ratesByLoc(List<RentalLocations> list) {
		String name = JOptionPane.showInputDialog(null, "Input location name");
		if (name == null) name = "";
		
		List<RentalLocations> locs = new ArrayList<>();
		
		for (RentalLocations r : list) {
			if (r.getName().contentEquals(name)) {
				locs.add(r);
			}
		}
		
		String[] columns = { "ID", "Name", "Daily Rate" };
		Object[][] data = new Object[locs.size()][3];

		for (int i = 0; i < locs.size(); i++) {
			data[i][0] = locs.get(i).getId();
			data[i][1] = locs.get(i).getName();
			//data[i][2] = locs.get(i).getRates();
		}
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		return dtm;
	}
	
	public static ArrayList<RentalLocations> filterLocationsByName(List<RentalLocations> list, String name) {
		ArrayList<RentalLocations> locs = new ArrayList<>();
		for (RentalLocations r : list) {
			if (r.getName().contentEquals(name)) {
				locs.add(r);
			}
		}
		if (locs.size() == 0) return null;
		return locs;
	}
	
	public static DefaultTableModel calculateRevenue(List<RentalLocations> list) {
		String[] columns = { "ID", "Name", "Rented Vehicles", "Total Revenue" };
		Object[][] data = new Object[list.size()][4];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = list.get(i).rentedVehicles();
			data[i][3] = String.format("$%.2f", list.get(i).totalRevenue());
		}
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		return dtm;
	}
	
	
	public static ArrayList<RentalLocations> locsByZip(List<RentalLocations> list, Component frmRentalLocationManager) {
		int zip;
		ArrayList<RentalLocations> output = new ArrayList<>();
		try {
			zip = Integer.parseInt(JOptionPane.showInputDialog(frmRentalLocationManager, "Input Zip Code"));
		} catch (NumberFormatException ex) {
			return null;
		}
		list.forEach(rl -> {
			if (rl.getZip() == zip) output.add(rl);
		});
		return output;
	}

}
