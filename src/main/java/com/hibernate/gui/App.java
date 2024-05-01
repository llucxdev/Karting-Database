package com.hibernate.gui;

import java.awt.EventQueue;
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

import org.hibernate.Hibernate;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.hibernate.dao.DriverDAO;
import com.hibernate.model.Driver;
import com.hibernate.util.HibernateUtil;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
	DefaultTableModel driverModel;
	JTable driversTable;
	List<Driver> driverList;
	UtilDateModel modelDatePicker;
	JDatePickerImpl datePicker;
	private JTextField textFieldName;
	private JTextField textFieldLaps;
	private JTextField textFieldRaces;
	private JTextField textFieldPodiums;
	private JTextField textFieldWins;
	private JTextField textFieldImgText;

	// driver variables for the buttons
	Driver driver;
	int driver_id;
	String name;
	LocalDate dob;
	int age;
	int laps;
	int races;
	int podiums;
	int wins;
	int team;
	int kart;
	byte[] img;

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
		driverList = DriverDAO.selectAllDrivers();
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
	
	void displayImage() {
		
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
		tabbedPane.setBounds(12, 12, 1166, 770);
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

		modelDatePicker = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDatePicker, properties);

		driversTable = new JTable(driverModel);
		driversTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = driversTable.getSelectedRow();
				TableModel model = driversTable.getModel();
				driver_id = (int) model.getValueAt(i, 0);
				Driver driver = DriverDAO.selectDriver(driver_id);
				textFieldName.setText(driver.getName());
				modelDatePicker.setValue(Date.from(driver.getDob().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				textFieldLaps.setText(String.valueOf(driver.getLaps()));
				textFieldRaces.setText(String.valueOf(driver.getRaces()));
				textFieldPodiums.setText(String.valueOf(driver.getPodiums()));
				textFieldWins.setText(String.valueOf(driver.getWins()));
			}
		});
		driversTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(driversTable);
		scrollPane.setBounds(12, 12, 1137, 350);
		driverPanel.add(scrollPane);

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
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(439, 425, 150, 25);
		driverPanel.add(datePicker);

		textFieldName = new JTextField();
		textFieldName.setBounds(163, 425, 150, 25);
		driverPanel.add(textFieldName);
		textFieldName.setColumns(10);

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

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					name = textFieldName.getText();
					if (name.isEmpty()) {
						throw new IllegalArgumentException("Name cannot be empty");
					}

					Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
					dob = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate today = LocalDate.now();
					int age = Period.between(dob, today).getYears();
					// verificar si los datos opcionales se han rellenado o no
					if (!textFieldLaps.getText().isEmpty()) {
						laps = Integer.parseInt(textFieldLaps.getText());
					}
					if (!textFieldRaces.getText().isEmpty()) {
						races = Integer.parseInt(textFieldRaces.getText());
					}
					if (!textFieldPodiums.getText().isEmpty()) {
						podiums = Integer.parseInt(textFieldPodiums.getText());
					}
					if (!textFieldWins.getText().isEmpty()) {
						wins = Integer.parseInt(textFieldWins.getText());
					}

					try {
						String imagePath = textFieldImgText.getText();
						FileInputStream fis = null;
						img = null;
						if (!imagePath.isEmpty()) {
							File imageFile = new File(imagePath);
							fis = new FileInputStream(imageFile);
							img = new byte[(int) imageFile.length()];
							fis.read(img);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// without team and kart
					driver = new Driver(name, dob, age, laps, races, podiums, wins, team, kart, img);
					DriverDAO.insertDriver(driver);
					refreshDriverTable();
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(null, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(202, 706, 117, 25);
		driverPanel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driver = DriverDAO.selectDriver(driver_id);
				name = textFieldName.getText();
				if (name.isEmpty()) {
					throw new IllegalArgumentException("Name cannot be empty");
				}
				Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
				dob = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate today = LocalDate.now();
				int age = Period.between(dob, today).getYears();
				if (!textFieldLaps.getText().isEmpty()) {
					laps = Integer.parseInt(textFieldLaps.getText());
				}
				if (!textFieldRaces.getText().isEmpty()) {
					races = Integer.parseInt(textFieldRaces.getText());
				}
				if (!textFieldPodiums.getText().isEmpty()) {
					podiums = Integer.parseInt(textFieldPodiums.getText());
				}
				if (!textFieldWins.getText().isEmpty()) {
					wins = Integer.parseInt(textFieldWins.getText());
				}
				// without team and kart
				DriverDAO.updateDriver(driver, name, dob, age, laps, races, podiums, wins, team, kart);
				refreshDriverTable();
			}
		});
		btnUpdate.setBounds(521, 706, 117, 25);
		driverPanel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DriverDAO.deleteDriver(driver_id);
				refreshDriverTable();
			}
		});
		btnDelete.setBounds(840, 706, 117, 25);
		driverPanel.add(btnDelete);

		JLabel lblImg = new JLabel();
		lblImg.setBounds(748, 384, 300, 300);
		driverPanel.add(lblImg);

		JLabel lblImage = new JLabel("Select Image:");
		lblImage.setBounds(348, 478, 92, 14);
		driverPanel.add(lblImage);

		JButton btnSelectImage = new JButton("Select Image");
		btnSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
					File f = chooser.getSelectedFile();
					String fileName = f.getAbsolutePath();
					textFieldImgText.setText(fileName);
				}
			}
		});
		btnSelectImage.setBounds(609, 470, 117, 25);
		driverPanel.add(btnSelectImage);

		textFieldImgText = new JTextField();
		textFieldImgText.setEditable(false);
		textFieldImgText.setBounds(439, 472, 150, 20);
		driverPanel.add(textFieldImgText);
		textFieldImgText.setColumns(10);

		refreshDriverTable();
	}
}
