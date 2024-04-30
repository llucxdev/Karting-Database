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
	private void initialize(JPanel panel) {
		
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
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 829, 182);
		panel.add(scrollPane);
		
		refresh();
	}
}
