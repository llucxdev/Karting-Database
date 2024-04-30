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
	DefaultTableModel model;
	List<Driver> driverList;
	JTable driversTable;
	UtilDateModel modelDatePicker;
	JDatePickerImpl datePicker;

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
		model.setRowCount(0);
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
			model.addRow(row);
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 1166, 846);
		frame.getContentPane().add(tabbedPane);
		
		JPanel driverPanel = new JPanel();
		tabbedPane.addTab("Drivers", null, driverPanel, null);
		driverPanel.setLayout(null);
		
		JPanel teamPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamPanel, null);
		teamPanel.setLayout(null);
		
		JLabel lblTuMadre = new JLabel("Tu madre");
		lblTuMadre.setBounds(189, 72, 70, 15);
		teamPanel.add(lblTuMadre);
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Laps");
		model.addColumn("Races");
		model.addColumn("Podiums");
		model.addColumn("Wins");
		model.addColumn("Team");
		model.addColumn("Kart");
		
		driversTable = new JTable(model);
		driversTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(driversTable);
		scrollPane.setBounds(12, 12, 1137, 350);
		driverPanel.add(scrollPane);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 384, 70, 15);
		driverPanel.add(lblName);
		
		JLabel lblBirth = new JLabel("Birth:");
		lblBirth.setBounds(12, 411, 70, 15);
		driverPanel.add(lblBirth);
		
		JLabel lblLaps = new JLabel("Laps:");
		lblLaps.setBounds(12, 448, 70, 15);
		driverPanel.add(lblLaps);
		
		JLabel lblRaces = new JLabel("Races");
		lblRaces.setBounds(12, 475, 70, 15);
		driverPanel.add(lblRaces);
		
		JLabel lblPodiums = new JLabel("Podiums:");
		lblPodiums.setBounds(12, 502, 70, 15);
		driverPanel.add(lblPodiums);
		
		JLabel lblWins = new JLabel("Wins:");
		lblWins.setBounds(12, 529, 70, 15);
		driverPanel.add(lblWins);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(118, 727, 117, 25);
		driverPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(399, 727, 117, 25);
		driverPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(657, 727, 117, 25);
		driverPanel.add(btnDelete);
		
		modelDatePicker = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDatePicker, properties);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(80, 411, 150, 25);
		driverPanel.add(datePicker);
		
		refreshDriverTable();
	}
}
