package com.epsi.dugama.BlueR;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {
	/////////////////////////////// USE A AWT LIST ///////////////////////////////
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInfoCheckPresentDevice = new JLabel("Click on this button to check the actual present device :");
		lblInfoCheckPresentDevice.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInfoCheckPresentDevice.setBounds(10, 5, 277, 14);
		frame.getContentPane().add(lblInfoCheckPresentDevice);
		
		JButton btnCheckPresentDevice = new JButton("Check Present Device");
		btnCheckPresentDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Hello World !");
			}
		});
		btnCheckPresentDevice.setBounds(287, 1, 137, 23);
		frame.getContentPane().add(btnCheckPresentDevice);
		
		Canvas canvasSeparatorCheckDevice_ChooseDevice = new Canvas();
		canvasSeparatorCheckDevice_ChooseDevice.setBackground(Color.BLACK);
		canvasSeparatorCheckDevice_ChooseDevice.setForeground(Color.BLACK);
		canvasSeparatorCheckDevice_ChooseDevice.setFont(new Font("Arial", Font.PLAIN, 12));
		canvasSeparatorCheckDevice_ChooseDevice.setBounds(0, 25, 434, 1);
		frame.getContentPane().add(canvasSeparatorCheckDevice_ChooseDevice);
		
		Label lblChooseDevice = new Label("Please, choose a device :");
		lblChooseDevice.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChooseDevice.setBounds(10, 32, 266, 14);
		frame.getContentPane().add(lblChooseDevice);
	}
}
