package com.hibernate.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.hibernate.dao.DriverDAO;
import com.hibernate.dao.TeamDAO;
import com.hibernate.model.Driver;
import com.hibernate.model.Team;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import javax.swing.SwingConstants;
import java.awt.Font;

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}
}

public class App {

	// swing variables
	private JFrame frmKartingdatabase;
	private JTextField textFieldDriverName;
	private JTextField textFieldLaps;
	private JTextField textFieldRaces;
	private JTextField textFieldPodiums;
	private JTextField textFieldWins;
	private JTextField textFieldDriverImageText;
	private JLabel lblImg;
	
	//tables variables
	private DefaultTableModel driverModel;
	private JTable driversTable;
	
	private DefaultTableModel teamModel;
	private JTable teamTable;

	//datePicker variables
	private UtilDateModel modelDatePicker;
	private JDatePickerImpl datePicker;
	
	// driver variables
	private Driver driver;
	private int driver_id;
	
	// team variables
	private Team team;
	private int team_id;
	private JTextField textFieldTeamName;
	private JTextField textFieldTeamImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmKartingdatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	public void refreshDriverTable() {
		driverModel.setRowCount(0);
		List<Driver> driverList = DriverDAO.selectAllDrivers();
		driverList.forEach(d -> {
			Object[] row = new Object[9];
			row[0] = d.getDriver_id();
			row[1] = d.getName();
			row[2] = d.getAge();
			row[3] = d.getLaps();
			row[4] = d.getRaces();
			row[5] = d.getPodiums();
			row[6] = d.getWins();
			row[7] = d.getTeam();
			row[8] = d.getKart();
			driverModel.addRow(row);
		});
	}
	
	public void refreshTeamTable() {
		driverModel.setRowCount(0);
		List<Team> teamList = TeamDAO.selectAllTeams();
		teamList.forEach(t -> {
			Object[] row = new Object[4];
			row[0] = t.getTeam_id();
			row[1] = t.getDate();
			row[2] = t.getName();
			List<Driver> driversList = t.getDrivers();
			//driversList.forEach(d -> d.getName());
			row[3] = t.getDrivers();
		});
	}
	
	private int parseTextFieldToInt(JTextField textField) {
	    String text = textField.getText();
	    if (!text.isEmpty() && text.matches("\\d+")) {
	        return Integer.parseInt(text);
	    } else {
	        return 0;
	    }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKartingdatabase = new JFrame();
		frmKartingdatabase.setTitle("Karting Database");
		frmKartingdatabase.setBounds(100, 100, 1200, 850);
		frmKartingdatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKartingdatabase.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 1166, 782);
		frmKartingdatabase.getContentPane().add(tabbedPane);

		JPanel driverPanel = new JPanel();
		tabbedPane.addTab("Drivers", null, driverPanel, null);
		driverPanel.setLayout(null);

		JPanel teamPanel = new JPanel();
		tabbedPane.addTab("Teams and Karts", null, teamPanel, null);
		teamPanel.setLayout(null);

		driverModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		driverModel.addColumn("ID");
		driverModel.addColumn("Name");
		driverModel.addColumn("Age");
		driverModel.addColumn("Laps");
		driverModel.addColumn("Races");
		driverModel.addColumn("Podiums");
		driverModel.addColumn("Wins");
		driverModel.addColumn("Team");
		driverModel.addColumn("Kart");
		
		driversTable = new JTable(driverModel);
		driversTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = driversTable.getSelectedRow();
				TableModel model = driversTable.getModel();
				driver_id = (int) model.getValueAt(i, 0);
				Driver driver = DriverDAO.selectDriver(driver_id);
				textFieldDriverName.setText(driver.getName());
				modelDatePicker.setValue(Date.from(driver.getDob().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				textFieldLaps.setText(String.valueOf(driver.getLaps()));
				textFieldRaces.setText(String.valueOf(driver.getRaces()));
				textFieldPodiums.setText(String.valueOf(driver.getPodiums()));
				textFieldWins.setText(String.valueOf(driver.getWins()));
				Blob img = driver.getImg();
				if (img != null) {
					try {
						byte[] imageBytes = img.getBytes(1, (int) img.length());
						ImageIcon imageIcon = new ImageIcon(imageBytes);
						Image image = imageIcon.getImage();
						image.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon resizedImage = new ImageIcon(image);
						lblImg.setIcon(resizedImage);
					} catch (Exception imgException) {
						
					}
				} else {
					lblImg.setIcon(null);
				}
				
			}
		});
		driversTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPaneDrivers = new JScrollPane(driversTable);
		scrollPaneDrivers.setBounds(12, 12, 1137, 350);
		driverPanel.add(scrollPaneDrivers);
		
		teamModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		teamModel.addColumn("ID");
		teamModel.addColumn("Date");
		teamModel.addColumn("Name");
		teamModel.addColumn("Drivers");
		
		teamTable = new JTable(teamModel);
		teamTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		
		JScrollPane scrollPaneTeams = new JScrollPane(teamTable);
		scrollPaneTeams.setBounds(12, 49, 500, 413);
		teamPanel.add(scrollPaneTeams);
		
		JLabel lblTeam = new JLabel("Teams");
		lblTeam.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setBounds(227, 22, 70, 15);
		teamPanel.add(lblTeam);
		
		JLabel lblTeamName = new JLabel("Team name:");
		lblTeamName.setBounds(12, 498, 101, 15);
		teamPanel.add(lblTeamName);
		
		textFieldTeamName = new JTextField();
		textFieldTeamName.setBounds(131, 493, 150, 25);
		teamPanel.add(textFieldTeamName);
		textFieldTeamName.setColumns(10);
		
		JLabel lblImage_1 = new JLabel("Select Image:");
		lblImage_1.setBounds(12, 545, 101, 14);
		teamPanel.add(lblImage_1);
		
		textFieldTeamImage = new JTextField();
		textFieldTeamImage.setEditable(false);
		textFieldTeamImage.setBounds(131, 543, 150, 20);
		teamPanel.add(textFieldTeamImage);
		textFieldTeamImage.setColumns(10);
		
		JButton btnSelectTeamImage = new JButton("Select Image");
		btnSelectTeamImage.setBounds(293, 540, 132, 25);
		teamPanel.add(btnSelectTeamImage);
		
		JButton btnAddTeam = new JButton("Add");
		btnAddTeam.setBounds(31, 589, 82, 25);
		teamPanel.add(btnAddTeam);
		
		JButton btnUpdateTeam = new JButton("Upd");
		btnUpdateTeam.setBounds(165, 589, 82, 25);
		teamPanel.add(btnUpdateTeam);
		
		JButton btnDeleteTeam = new JButton("Del");
		btnDeleteTeam.setBounds(293, 589, 82, 25);
		teamPanel.add(btnDeleteTeam);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(90, 430, 70, 15);
		driverPanel.add(lblName);

		JLabel lblBirth = new JLabel("Birth:");
		lblBirth.setBounds(348, 435, 70, 15);
		driverPanel.add(lblBirth);

		JLabel lblLaps = new JLabel("Laps:");
		lblLaps.setBounds(90, 475, 70, 15);
		driverPanel.add(lblLaps);

		JLabel lblRaces = new JLabel("Races");
		lblRaces.setBounds(90, 522, 70, 15);
		driverPanel.add(lblRaces);

		JLabel lblPodiums = new JLabel("Podiums:");
		lblPodiums.setBounds(90, 570, 70, 15);
		driverPanel.add(lblPodiums);

		JLabel lblWins = new JLabel("Wins:");
		lblWins.setBounds(90, 618, 70, 15);
		driverPanel.add(lblWins);
		
		modelDatePicker = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDatePicker, properties);
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(439, 425, 150, 25);
		driverPanel.add(datePicker);

		textFieldDriverName = new JTextField();
		textFieldDriverName.setBounds(163, 425, 150, 25);
		driverPanel.add(textFieldDriverName);
		textFieldDriverName.setColumns(10);

		textFieldLaps = new JTextField();
		textFieldLaps.setColumns(10);
		textFieldLaps.setBounds(163, 473, 150, 25);
		driverPanel.add(textFieldLaps);

		textFieldRaces = new JTextField();
		textFieldRaces.setColumns(10);
		textFieldRaces.setBounds(163, 520, 150, 25);
		driverPanel.add(textFieldRaces);

		textFieldPodiums = new JTextField();
		textFieldPodiums.setColumns(10);
		textFieldPodiums.setBounds(163, 568, 150, 25);
		driverPanel.add(textFieldPodiums);

		textFieldWins = new JTextField();
		textFieldWins.setColumns(10);
		textFieldWins.setBounds(163, 616, 150, 25);
		driverPanel.add(textFieldWins);

		JButton btnAddDriver = new JButton("Add");
		btnAddDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textFieldDriverName.getText();
					if (name.isEmpty()) {
						throw new IllegalArgumentException("Name cannot be empty");
					}
					
					LocalDate dob = null;
					int age = 0;
					if ((java.util.Date) datePicker.getModel().getValue() != null) {
						Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
						dob = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						LocalDate today = LocalDate.now();
						age = Period.between(dob, today).getYears();
					} else {
						throw new IllegalArgumentException("Birth cannot be empty");
					}
					int laps = parseTextFieldToInt(textFieldLaps);
					int races = parseTextFieldToInt(textFieldRaces);
					int podiums = parseTextFieldToInt(textFieldPodiums);
					int wins = parseTextFieldToInt(textFieldWins);
					Blob img = null;
					if (!textFieldDriverImageText.getText().isEmpty()) {
						try {
							String imagePath = textFieldDriverImageText.getText();
					        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
					        img = new com.mysql.cj.jdbc.Blob(imageBytes, null);
						} catch (Exception imgException) {
							
						}
					}
					driver = new Driver(name, dob, age, laps, races, podiums, wins, img);
					DriverDAO.insertDriver(driver);
					JOptionPane.showMessageDialog(frmKartingdatabase, "Driver inserted successfully");
					refreshDriverTable();
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(null, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddDriver.setBounds(202, 706, 117, 25);
		driverPanel.add(btnAddDriver);

		JButton btnUpdateDriver = new JButton("Update");
		btnUpdateDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driver = DriverDAO.selectDriver(driver_id);
				String name = textFieldDriverName.getText();
				if (name.isEmpty()) {
					throw new IllegalArgumentException("Name cannot be empty");
				}
				Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
				LocalDate dob = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate today = LocalDate.now();
				int age = Period.between(dob, today).getYears();
				
				int laps = parseTextFieldToInt(textFieldLaps);
				int races = parseTextFieldToInt(textFieldRaces);
				int podiums = parseTextFieldToInt(textFieldPodiums);
				int wins = parseTextFieldToInt(textFieldWins);
				int team = 0;
				int kart = 0;
				Blob img = null;
				if (!textFieldDriverImageText.getText().isEmpty()) {
					try {
						String imagePath = textFieldDriverImageText.getText();
				        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
				        img = new com.mysql.cj.jdbc.Blob(imageBytes, null);
					} catch (Exception imgException) {
						
					}
				}
				// without team and kart
				DriverDAO.updateDriver(driver, name, dob, age, laps, races, podiums, wins, team, kart, img);
				refreshDriverTable();
			}
		});
		btnUpdateDriver.setBounds(521, 706, 117, 25);
		driverPanel.add(btnUpdateDriver);

		JButton btnDeleteDriver = new JButton("Delete");
		btnDeleteDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DriverDAO.deleteDriver(driver_id);
				refreshDriverTable();
			}
		});
		btnDeleteDriver.setBounds(840, 706, 117, 25);
		driverPanel.add(btnDeleteDriver);

		lblImg = new JLabel();
		lblImg.setBounds(748, 384, 300, 300);
		driverPanel.add(lblImg);

		JLabel lblImage = new JLabel("Select Image:");
		lblImage.setBounds(348, 478, 92, 14);
		driverPanel.add(lblImage);

		JButton btnSelectDriverImage = new JButton("Select Image");
		btnSelectDriverImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
					File f = chooser.getSelectedFile();
					String fileName = f.getAbsolutePath();
					textFieldDriverImageText.setText(fileName);
				}
			}
		});
		btnSelectDriverImage.setBounds(609, 470, 132, 25);
		driverPanel.add(btnSelectDriverImage);

		textFieldDriverImageText = new JTextField();
		textFieldDriverImageText.setEditable(false);
		textFieldDriverImageText.setBounds(439, 472, 150, 20);
		driverPanel.add(textFieldDriverImageText);
		textFieldDriverImageText.setColumns(10);

		refreshDriverTable();
	}
}
