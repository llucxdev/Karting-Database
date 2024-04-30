package com.hibernate.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.hibernate.dao.DriverDAO;
import com.hibernate.model.Driver;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

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

	private JFrame frame;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 1166, 800);
		frame.getContentPane().add(tabbedPane);
		
		JPanel driverPanel = new JPanel();
		tabbedPane.addTab("Drivers", null, driverPanel, null);
		driverPanel.setLayout(null);
		
		JPanel teamPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamPanel, null);
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
		driversTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(driversTable);
		scrollPane.setBounds(12, 12, 1137, 350);
		driverPanel.add(scrollPane);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 387, 70, 15);
		driverPanel.add(lblName);
		
		JLabel lblBirth = new JLabel("Birth:");
		lblBirth.setBounds(12, 438, 70, 15);
		driverPanel.add(lblBirth);
		
		JLabel lblLaps = new JLabel("Laps:");
		lblLaps.setBounds(12, 494, 70, 15);
		driverPanel.add(lblLaps);
		
		JLabel lblRaces = new JLabel("Races");
		lblRaces.setBounds(12, 541, 70, 15);
		driverPanel.add(lblRaces);
		
		JLabel lblPodiums = new JLabel("Podiums:");
		lblPodiums.setBounds(12, 589, 70, 15);
		driverPanel.add(lblPodiums);
		
		JLabel lblWins = new JLabel("Wins:");
		lblWins.setBounds(12, 637, 70, 15);
		driverPanel.add(lblWins);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(202, 706, 117, 25);
		driverPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(521, 706, 117, 25);
		driverPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(840, 706, 117, 25);
		driverPanel.add(btnDelete);
		
		modelDatePicker = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDatePicker, properties);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(85, 428, 150, 25);
		driverPanel.add(datePicker);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(85, 382, 150, 25);
		driverPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldLaps = new JTextField();
		textFieldLaps.setColumns(10);
		textFieldLaps.setBounds(85, 492, 150, 25);
		driverPanel.add(textFieldLaps);
		
		textFieldRaces = new JTextField();
		textFieldRaces.setColumns(10);
		textFieldRaces.setBounds(85, 539, 150, 25);
		driverPanel.add(textFieldRaces);
		
		textFieldPodiums = new JTextField();
		textFieldPodiums.setColumns(10);
		textFieldPodiums.setBounds(85, 587, 150, 25);
		driverPanel.add(textFieldPodiums);
		
		textFieldWins = new JTextField();
		textFieldWins.setColumns(10);
		textFieldWins.setBounds(85, 635, 150, 25);
		driverPanel.add(textFieldWins);
		
		refreshDriverTable();
	}
}
