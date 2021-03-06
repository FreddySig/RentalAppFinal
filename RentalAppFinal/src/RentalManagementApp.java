
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class RentalManagementApp {

	private JFrame frmRentalLocationManager;
	private JTable tblLocations;
	private List<RentalLocations> locationList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalManagementApp window = new RentalManagementApp();
					window.frmRentalLocationManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RentalManagementApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Arial", Font.BOLD, 20));
		
		frmRentalLocationManager = new JFrame();
		frmRentalLocationManager.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 22));
		frmRentalLocationManager.setTitle("Rental Location Manager");
		frmRentalLocationManager.setBounds(100, 100, 1000, 500);
		frmRentalLocationManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmRentalLocationManager.getContentPane().add(scrollPane, BorderLayout.CENTER);

		tblLocations = new JTable() { 
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tblLocations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tblLocations.setRowHeight(30);
		scrollPane.setViewportView(tblLocations);
		
		JButton btnResetView = new JButton("Reset View");
		btnResetView.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnResetView.addActionListener(e -> {
			updateLocationTable(locationList);
		});
		frmRentalLocationManager.getContentPane().add(btnResetView, BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frmRentalLocationManager.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmSave.addActionListener(e -> {
			if (saveData(locationList)) {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "Data saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmLoad.addActionListener(e -> {
			locationList = loadData();
			updateLocationTable(locationList);
		});
		mnFile.add(mntmLoad);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "See ya!");
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnLocation = new JMenu("Location");
		mnLocation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnLocation);

		JMenuItem mntmNewLocation = new JMenuItem("New Location");
		mntmNewLocation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewLocation.addActionListener(e -> {
			newLocation(locationList);
		});
		
		JMenu mnLookupLocations = new JMenu("Lookup Locations");
		mnLookupLocations.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLocation.add(mnLookupLocations);
		
		JMenuItem mntmByZipCode = new JMenuItem("by ZIP Code");
		mntmByZipCode.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLookupLocations.add(mntmByZipCode);
		mntmByZipCode.addActionListener(e ->{
			List<RentalLocations> list = Queries.locsByZip(locationList,frmRentalLocationManager);
			if (list == null) return;
			else if (list.size() > 0) updateLocationTable(list);
			else {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "No locations found with that ZIP.", "Lookup by ZIP", JOptionPane.INFORMATION_MESSAGE);
				updateLocationTable(locationList);
			}
		});
						
		JMenuItem mntmByName = new JMenuItem("by Name");
		mntmByName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLookupLocations.add(mntmByName);
		mntmByName.addActionListener(e -> {
			// Filter rates by a certain Location Name
			ArrayList<RentalLocations> list = Queries.detailsByLoc(locationList);
			if (list == null) return;
			else if (list.size() > 0) updateLocationTable(list);
			else {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "No locations found with that name.", "Lookup by Name", JOptionPane.INFORMATION_MESSAGE);
				updateLocationTable(locationList);
			}
		});
		mnLocation.add(mntmNewLocation);
		
		JMenuItem mntmDeleteLocation = new JMenuItem("Delete Location");
		mntmDeleteLocation.addActionListener(e -> {			
			int index = tblLocations.getSelectedRow();
			if (index > -1) {
				RentalLocations rl = locationList.get(index);
				int choice = JOptionPane.showConfirmDialog(
						frmRentalLocationManager, 
						String.format("Are you sure you want to delete the location: %s?", rl.getName()), 
						"Delete Location", 
						JOptionPane.WARNING_MESSAGE);
				if (choice == 0) {
					locationList.remove(rl);
					updateLocationTable(locationList);
				}
			} else {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "No location selected!", "Delete Location", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JMenuItem mntmEditLocation = new JMenuItem("Edit Location");
		mntmEditLocation.addActionListener(e -> {
			
			
			while (true) {
				int id = 0;
				
				try {
					String idIn = JOptionPane.showInputDialog(
							frmRentalLocationManager, "Enter ID of location to edit:", "Edit Location", JOptionPane.QUESTION_MESSAGE);
					if (idIn == null) break;
					id = Integer.parseInt(idIn);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmRentalLocationManager, "ID must be a number", "Invalid ID", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
				boolean locFound = false;
				for (RentalLocations rl : locationList) {
					if (rl.getId() == id) {
						
						JPanel pnlEditLocation = new JPanel(new GridBagLayout());
						GridBagConstraints c = new GridBagConstraints();
						c.fill = (GridBagConstraints.HORIZONTAL);
						c.ipadx = 5;
						c.ipady = 10;
						
						JLabel lblName = new JLabel("Location Name:");
						c.gridx = 0;
						c.gridy = 0;
						pnlEditLocation.add(lblName, c);

						JTextField txtName = new JTextField(20);
						c.gridx = 1;
						c.gridy = 0;
						pnlEditLocation.add(txtName, c);

						JLabel lblNameErr = new JLabel();
						lblNameErr.setForeground(Color.RED);
						c.gridx = 2;
						c.gridy = 0;
						pnlEditLocation.add(lblNameErr, c);

						JLabel lblVehicles = new JLabel("# of Rented Vehicles:");
						c.gridx = 0;
						c.gridy = 2;
						pnlEditLocation.add(lblVehicles, c);

						JTextField txtVehicle = new JTextField(20);
						c.gridx = 1;
						c.gridy = 2;
						pnlEditLocation.add(txtVehicle, c);

						JLabel lblVehiclesErr = new JLabel();
						lblVehiclesErr.setForeground(Color.RED);
						c.gridx = 2;
						c.gridy = 2;
						pnlEditLocation.add(lblVehiclesErr, c);

						JLabel lblZip = new JLabel("ZIP Code:");
						c.gridx = 0;
						c.gridy = 3;
						pnlEditLocation.add(lblZip, c);

						JTextField txtZip = new JTextField(20);
						c.gridx = 1;
						c.gridy = 3;
						pnlEditLocation.add(txtZip, c);

						JLabel lblZipErr = new JLabel();
						lblZipErr.setForeground(Color.RED);
						c.gridx = 2;
						c.gridy = 3;
						pnlEditLocation.add(lblZipErr, c);
						
						txtName.setText(rl.getName());
						txtVehicle.setText(String.format("%d", rl.rentedVehicles()));
						txtZip.setText(String.format("%d", rl.getAddress().getZip()));

						boolean complete = false;
						while (!complete) {
							
							int result = JOptionPane.showOptionDialog(null, new Object[] { pnlEditLocation }, "Edit Location",
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

							if (result == 0) {
								
								String nameIn = txtName.getText();
								String rentedVehiclesIn = txtVehicle.getText();
								String zipIn = txtZip.getText();
								complete = true;
								int rentedVehicles = 0, zip = 0;
								
								lblNameErr.setText("");
								lblVehiclesErr.setText("");
								lblZipErr.setText("");
								
								if (nameIn.length() < 6 || nameIn.length() > 20) {
									lblNameErr.setText("* Must be between 6 and 20 characters");
									complete = false;
								}
								
								try {
									rentedVehicles = Integer.parseInt(rentedVehiclesIn); 
								} catch (NumberFormatException ex) {
									lblVehiclesErr.setText("* Must be a number");
									complete = false;
								}
								
								if (!(zipIn.length() == 5)) {
									lblZipErr.setText("* Must be 5 digits");
									complete = false;
								}
								
								try {
									zip = Integer.parseInt(zipIn);
								} catch (NumberFormatException ex) {
									lblZipErr.setText("* Must be a number");
									complete = false;
								}
								
								if (!complete) continue;
								
								rl.setName(nameIn);
								//rl.setRentedVehicles(rentedVehicles);
								rl.getAddress().setZip(zip);
								
								updateLocationTable(locationList);
							} else {
								complete = true;
							}
						}
						
						updateLocationTable(locationList);
						locFound = true;
						break;
					}
				}
				if (locFound) break;
				
				JOptionPane.showMessageDialog(frmRentalLocationManager, "Location not found.\nPlease check the ID.", "Location Not Found", JOptionPane.ERROR_MESSAGE);
			}
		});
		mntmEditLocation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLocation.add(mntmEditLocation);
		mntmDeleteLocation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLocation.add(mntmDeleteLocation);
		
		JMenuItem mntmViewInventory = new JMenuItem("Manage Inventory");
		mntmViewInventory.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmViewInventory.addActionListener(e -> {
			int index = tblLocations.getSelectedRow();
			if (index > -1) {
				int id = (int)tblLocations.getValueAt(index, 0);
				RentalLocations selectedLocation = null;
				for (RentalLocations rl : locationList) {
					if (rl.getId() == id) {
						selectedLocation = rl;
						break;
					}
				}
				ManageInventory(selectedLocation);
			} else {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "No location selected!", "View Inventory", JOptionPane.ERROR_MESSAGE);
			}
		});
		mnLocation.add(mntmViewInventory);
		
		JMenu mnQueries = new JMenu("Queries");
		mnQueries.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnQueries);

		JMenu mnLookupRentalRates = new JMenu("Lookup Rental Rates");
		mnLookupRentalRates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnQueries.add(mnLookupRentalRates);

		JMenuItem mntmByLocationName = new JMenuItem("by Location Name");
		mntmByLocationName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmByLocationName.addActionListener(e -> {
			// Filter rates by a certain Location Name
			DefaultTableModel dtm = Queries.ratesByLoc(locationList);
			if (dtm.getRowCount() > 0) tblLocations.setModel(dtm);
			
		});
		mnLookupRentalRates.add(mntmByLocationName);

		JMenuItem mntmAvailableVehicles = new JMenuItem("Available Vehicles");
		mntmAvailableVehicles.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmAvailableVehicles.addActionListener(e -> {
			// Complete list, pull availableVehicle for each location
			tblLocations.setModel(Queries.availableV(locationList));
		});
		mnQueries.add(mntmAvailableVehicles);

		JMenu mnCalculate = new JMenu("Calculate");
		mnCalculate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnCalculate);

		JMenuItem mntmDailyRevenueFor = new JMenuItem("Daily Revenue for Location");
		mntmDailyRevenueFor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmDailyRevenueFor.addActionListener(e -> {
			tblLocations.setModel(Queries.calculateRevenue(locationList));
		});
		mnCalculate.add(mntmDailyRevenueFor);
		
		frmRentalLocationManager.setLocationRelativeTo(null);
		
		locationList = loadData();
		updateLocationTable(locationList);
	}

	private void newLocation(List<RentalLocations> list) {

		JPanel pnlNewLocation = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = (GridBagConstraints.HORIZONTAL);
		c.ipadx = 5;
		c.ipady = 10;
		
		JLabel lblName = new JLabel("Location Name:");
		c.gridx = 0;
		c.gridy = 0;
		pnlNewLocation.add(lblName, c);

		JTextField txtName = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		pnlNewLocation.add(txtName, c);

		JLabel lblNameErr = new JLabel();
		lblNameErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 0;
		pnlNewLocation.add(lblNameErr, c);

		JLabel lblStreet = new JLabel("Street:");
		c.gridx = 0;
		c.gridy = 1;
		pnlNewLocation.add(lblStreet, c);
		
		JTextField txtStreet = new JTextField(20);
		c.gridx = 1;
		c.gridy = 1;
		pnlNewLocation.add(txtStreet, c);
		
		JLabel lblCity = new JLabel("City:");
		c.gridx = 0;
		c.gridy = 2;
		pnlNewLocation.add(lblCity, c);
		
		JTextField txtCity = new JTextField(15);
		c.gridx = 1;
		c.gridy = 2;
		pnlNewLocation.add(txtCity, c);
		
		JLabel lblState = new JLabel("State:");
		c.gridx = 0;
		c.gridy = 3;
		pnlNewLocation.add(lblState, c);
		
		JTextField txtState = new JTextField(15);
		c.gridx = 1;
		c.gridy = 3;
		pnlNewLocation.add(txtState, c);

		JLabel lblZip = new JLabel("ZIP Code:");
		c.gridx = 0;
		c.gridy = 4;
		pnlNewLocation.add(lblZip, c);

		JTextField txtZip = new JTextField(15);
		c.gridx = 1;
		c.gridy = 4;
		pnlNewLocation.add(txtZip, c);

		JLabel lblZipErr = new JLabel();
		lblZipErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 4;
		pnlNewLocation.add(lblZipErr, c);

		boolean complete = false;
		while (!complete) {
			
			int result = JOptionPane.showOptionDialog(null, new Object[] { pnlNewLocation }, "New Location",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (result == 0) {
				
				String nameIn = txtName.getText();
				String zipIn = txtZip.getText();
				complete = true;
				int zip = 0;
				
				lblNameErr.setText("");
				lblZipErr.setText("");
				
				if (nameIn.length() < 6 || nameIn.length() > 20) {
					lblNameErr.setText("* Must be between 6 and 20 characters");
					complete = false;
				}
				
				if (!(zipIn.length() == 5)) {
					lblZipErr.setText("* Must be 5 digits");
					complete = false;
				}
				
				try {
					zip = Integer.parseInt(zipIn);
				} catch (NumberFormatException ex) {
					lblZipErr.setText("* Must be a number");
					complete = false;
				}
				
				if (!complete) continue;

				int id;
				try {
					id = locationList.get(locationList.size() - 1).getId() + 1;
				} catch (ArrayIndexOutOfBoundsException ex) {
					id = 1;
				}
				
				String street = txtStreet.getText();
				String city = txtCity.getText();
				String state = txtState.getText();
				
				RentalLocations loc = new RentalLocations(nameIn, id, street, city, state, zip);

				list.add(loc);
				updateLocationTable(locationList);
			} else {
				complete = true;
			}
		}
	}

	private void updateLocationTable(List<RentalLocations> list) {
		String[] columns = { "Location ID", "Name", "Street Address", "City", "State", "ZIP Code", "Total Inventory", "Available", "Rented" };
		Object[][] data = new Object[list.size()][9];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = list.get(i).getAddress().getStreet();
			data[i][3] = list.get(i).getAddress().getCity();
			data[i][4] = list.get(i).getAddress().getState();
			data[i][5] = list.get(i).getAddress().getZip();
			data[i][6] = list.get(i).totalVehicles();
			data[i][7] = list.get(i).availableVehicles();
			data[i][8] = list.get(i).rentedVehicles();
		}

		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		tblLocations.setModel(dtm);
	}
	
	private void updateInventoryTable(List<Vehicles> list, JTable table) {
		String[] columns = { "ID", "Type", "Plate", "MPG", "Fuel Level", "Rental Rate", "Status" };
		Object[][] data = new Object[list.size()][7];
		
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getVehicleId();
			data[i][1] = list.get(i).getType();
			data[i][2] = list.get(i).getPlateNum();
			data[i][3] = list.get(i).getMpg();
			data[i][4] = String.format("%.0f%%", (list.get(i).getFuelLevel() * 100));
			data[i][5] = String.format("$%.2f", list.get(i).getDailyRate());
			data[i][6] = list.get(i).getStatus();
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		table.setModel(dtm);
	}
	
	private void ManageInventory(RentalLocations rl) {
		JFrame frmInventory = new JFrame("Manage Inventory for " + rl.getName());
		frmInventory.setSize(600, 400);
		frmInventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInventory.setLocationRelativeTo(frmRentalLocationManager);
		
		frmInventory.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				updateLocationTable(locationList);
			}
		});
		
		JTable tblInventory = new JTable();
		
		frmInventory.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				updateInventoryTable(rl.getInventory(), tblInventory);
			}
			@Override
			public void windowLostFocus(WindowEvent e) {
			}	
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tblInventory);
		frmInventory.getContentPane().add(scrollPane);
		
		JButton btnNewVehicle = new JButton("New Vehicle");
		btnNewVehicle.addActionListener(e -> {
			createNewVehicle(rl);
		});
		frmInventory.add(btnNewVehicle, BorderLayout.SOUTH);
		
		frmInventory.setVisible(true);
		
		updateInventoryTable(rl.getInventory(), tblInventory);
	}
	
	private void createNewVehicle(RentalLocations rl) {
		JFrame frmNewVehicle = new JFrame("New Vehicle");
		frmNewVehicle.setSize(400, 250);
		frmNewVehicle.setLocationRelativeTo(frmRentalLocationManager);
		frmNewVehicle.getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 5;
		c.ipady = 10;
		
		JLabel lblID = new JLabel("Vehicle ID:");
		c.gridx = 0;
		c.gridy= 0;
		frmNewVehicle.add(lblID, c);
		
		JTextField txtID = new JTextField(15);
		txtID.setEditable(false);
		txtID.setText(String.format("%d", rl.totalVehicles() + 1));
		c.gridx = 1;
		c.gridy = 0;
		frmNewVehicle.add(txtID, c);
		
		JLabel lblType = new JLabel("Type:");
		c.gridx = 0;
		c.gridy = 1;
		frmNewVehicle.add(lblType, c);
		
		JComboBox<VehicleType> cmbType = new JComboBox<>(VehicleType.values());
		c.gridx = 1;
		c.gridy = 1;
		frmNewVehicle.add(cmbType, c);
		
		JLabel lblPlate = new JLabel("Plate Number:");
		c.gridx = 0;
		c.gridy = 2;
		frmNewVehicle.add(lblPlate, c);
		
		JTextField txtPlate = new JTextField(15);
		c.gridx = 1;
		c.gridy = 2;
		frmNewVehicle.add(txtPlate, c);
		
		JLabel lblPlateErr = new JLabel();
		lblPlateErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 2;
		frmNewVehicle.add(lblPlateErr, c);
		
		JLabel lblMPG = new JLabel("MPG:");
		c.gridx = 0;
		c.gridy = 3;
		frmNewVehicle.add(lblMPG, c);
		
		JTextField txtMPG = new JTextField(15);
		c.gridx = 1;
		c.gridy = 3;
		frmNewVehicle.add(txtMPG, c);
		
		JLabel lblMPGErr = new JLabel();
		lblMPGErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 3;
		frmNewVehicle.add(lblMPGErr, c);
		
		JPanel pnlButtons = new JPanel();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e -> {
			Vehicles v = new Vehicles(
					Integer.parseInt(txtID.getText()), 
					(VehicleType)cmbType.getSelectedItem(), 
					txtPlate.getText(), 
					Status.AVAILABLE, 
					Integer.parseInt(txtMPG.getText()), 
					1);
			rl.addToInventory(v);
			
			frmNewVehicle.dispatchEvent(new WindowEvent(frmNewVehicle, WindowEvent.WINDOW_CLOSING));
		});
		pnlButtons.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			frmNewVehicle.dispatchEvent(new WindowEvent(frmNewVehicle, WindowEvent.WINDOW_CLOSING));
		});
		pnlButtons.add(btnCancel);
		
		JButton btnRandomVehicle = new JButton("Random");
		btnRandomVehicle.addActionListener(e -> {
			rl.addToInventory(constructRandomVehicle(rl.totalVehicles() + 1));
		});
		pnlButtons.add(btnRandomVehicle);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		frmNewVehicle.add(pnlButtons, c);
		
		
		
		frmNewVehicle.setVisible(true);
	}
	
	private Vehicles constructRandomVehicle(int id) {
		Random rng = new Random();
		
		VehicleType type = VehicleType.values()[rng.nextInt(VehicleType.values().length)];
		StringBuilder plate = new StringBuilder();
		for (int i = 0; i < 3; i++) plate.append((char)(rng.nextInt(26) + 65));
		for (int i = 0; i < 4; i++) plate.append(rng.nextInt(10));
		Status status = Status.values()[rng.nextInt(Status.values().length)];
		int mpg = rng.nextInt(16) + 15;
		double fuel = rng.nextDouble();
		
		Vehicles v = new Vehicles(id, type, plate.toString(), status, mpg, fuel);
		
		return v;
	}
	
	private boolean saveData(List<RentalLocations> list) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("locations.data"))) {
			oos.writeObject(list);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<RentalLocations> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("locations.data"))) {
			ArrayList<RentalLocations> list = (ArrayList<RentalLocations>)ois.readObject();
			return list;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<RentalLocations>();
	}
}
