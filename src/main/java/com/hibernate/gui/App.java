package com.hibernate.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.hibernate.gui.DriverPanel;
import javax.swing.JPanel;

public class App {

	private JFrame frame;
	private JMenuItem mntmDrivers;
	private JPanel panel;
	private DriverPanel driverPanel;

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

	private void showDriverPanel() {
        panel.setVisible(true);
        driverPanel.setVisible(true);
        driverPanel.refresh();
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 963, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(12, 12, 261, 37);
		frame.getContentPane().add(menuBar);

		mntmDrivers = new JMenuItem("Drivers");
		mntmDrivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showDriverPanel();
			}
		});
		mntmDrivers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mntmDrivers.setBackground(new Color(255, 127, 127));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mntmDrivers.setBackground(null);
			}
		});
		menuBar.add(mntmDrivers);

		JMenuItem mntmTeams = new JMenuItem("Teams");
		mntmTeams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mntmTeams.setBackground(new Color(173, 216, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mntmTeams.setBackground(null);
			}
		});
		menuBar.add(mntmTeams);

		panel = new JPanel();
		panel.setBounds(12, 61, 929, 551);
		frame.getContentPane().add(panel);
		panel.setVisible(false);

		driverPanel = new DriverPanel(panel);
		driverPanel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		frame.getContentPane().add(driverPanel);
		driverPanel.setVisible(false);
	}
}
