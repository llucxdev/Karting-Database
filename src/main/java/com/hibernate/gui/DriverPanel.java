package com.hibernate.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.DriverDAO;
import com.hibernate.model.Driver;

public class DriverPanel extends JPanel{

	private JTable table;
	private DefaultTableModel model;
	private List<Driver> driverList;

	/**
	 * Create the application.
	 */
	public DriverPanel(JPanel panel) {
		initialize(panel);
	}
	
	public void refresh() {
		model.setRowCount(0);
		driverList = DriverDAO.selectAllDrivers();
		driverList.forEach(d -> {
			Object[] row = new Object[6];
			row[0] = d.getDriver_id();
			row[1] = d.getName();
			row[2] = d.getLast_name();
			row[3] = d.getAge();
			row[4] = d.getNationality();
			row[5] = d.getNumber();
			model.addRow(row);
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel panel) {
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Last Name");
		model.addColumn("Age");
		model.addColumn("Nationality");
		model.addColumn("Number");
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 829, 182);
		panel.add(scrollPane);
		
		refresh();
	}
}
