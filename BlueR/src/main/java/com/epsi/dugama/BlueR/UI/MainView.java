package com.epsi.dugama.BlueR.UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import com.epsi.dugama.BlueR.DAO;
import com.epsi.dugama.BlueR.Device;
import com.epsi.dugama.BlueR.bluetooth.*;

public class MainView {
	private JFrame frmBluer;
	protected List<Device> devices;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmBluer.setVisible(true);
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
		frmBluer = new JFrame();
		frmBluer.setBackground(Color.WHITE);
		frmBluer.setResizable(false);
		frmBluer.setType(Type.UTILITY);
		frmBluer.setTitle("BlueR");
		frmBluer.getContentPane().setForeground(Color.BLACK);
		frmBluer.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmBluer.setBounds(100, 100, 438, 305);
		frmBluer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBluer.getContentPane().setLayout(null);
		
		JLabel lblInfoCheckPresentDevice = new JLabel("Click on this button to check the actual present device :");
		lblInfoCheckPresentDevice.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInfoCheckPresentDevice.setBounds(10, 5, 277, 14);
		frmBluer.getContentPane().add(lblInfoCheckPresentDevice);
		
		final JButton btnCheckPresentDevice = new JButton("Check Present Device");
		btnCheckPresentDevice.setFont(new Font("Arial", Font.PLAIN, 10));
		lblInfoCheckPresentDevice.setLabelFor(btnCheckPresentDevice);
		btnCheckPresentDevice.setBounds(287, 1, 137, 23);
		frmBluer.getContentPane().add(btnCheckPresentDevice);
		
		Canvas canvasSeparatorCheckDevice_ChooseDevice = new Canvas();
		canvasSeparatorCheckDevice_ChooseDevice.setBackground(Color.BLACK);
		canvasSeparatorCheckDevice_ChooseDevice.setForeground(Color.BLACK);
		canvasSeparatorCheckDevice_ChooseDevice.setFont(new Font("Arial", Font.PLAIN, 12));
		canvasSeparatorCheckDevice_ChooseDevice.setBounds(0, 25, 434, 1);
		frmBluer.getContentPane().add(canvasSeparatorCheckDevice_ChooseDevice);
		
		Label lblChooseDevice = new Label("Please, choose a device :");
		lblChooseDevice.setFont(new Font("Arial", Font.PLAIN, 12));
		lblChooseDevice.setBounds(10, 32, 414, 14);
		frmBluer.getContentPane().add(lblChooseDevice);		
		
		final JComboBox<String> comboBox_DevicesList = new JComboBox<String>();
		comboBox_DevicesList.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_DevicesList.setEnabled(false);
		comboBox_DevicesList.setToolTipText("");
		comboBox_DevicesList.setBounds(10, 52, 414, 23);
		frmBluer.getContentPane().add(comboBox_DevicesList);
		
		// Addition of a picture
		JLabel lblPicture = new JLabel();
		ImageIcon image = new ImageIcon(this.getClass().getResource("/bluetoothPicture2.png"));
		
		Canvas canvasSeparatorChooseDevice_SendInfo = new Canvas();
		canvasSeparatorChooseDevice_SendInfo.setBackground(Color.BLACK);
		canvasSeparatorChooseDevice_SendInfo.setBounds(0, 91, 434, 1);
		frmBluer.getContentPane().add(canvasSeparatorChooseDevice_SendInfo);
		
		Label labelInfoForSendInfo = new Label("Click on this button to send some informations to the choosen device :");
		labelInfoForSendInfo.setFont(new Font("Arial", Font.PLAIN, 12));
		labelInfoForSendInfo.setBounds(10, 91, 412, 14);
		frmBluer.getContentPane().add(labelInfoForSendInfo);
		
		final JButton btnSendData = new JButton("Send data");
		btnSendData.setEnabled(false);
		btnSendData.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSendData.setBounds(170, 105, 85, 23);
		frmBluer.getContentPane().add(btnSendData);
		
		Canvas canvasSeparatorSendInfo_EndApp = new Canvas();
		canvasSeparatorSendInfo_EndApp.setBackground(Color.BLACK);
		canvasSeparatorSendInfo_EndApp.setBounds(0, 131, 434, 1);
		frmBluer.getContentPane().add(canvasSeparatorSendInfo_EndApp);
		
		Label lblBluetooth = new Label("Bluetooth");
		lblBluetooth.setFont(new Font("Arial", Font.BOLD, 54));
		lblBluetooth.setForeground(new Color(0, 0, 204));
		lblBluetooth.setBounds(114, 172, 269, 59);
		frmBluer.getContentPane().add(lblBluetooth);
		lblPicture.setIcon(image);
		lblPicture.setBounds(0, 131, 128, 140);
		frmBluer.getContentPane().add(lblPicture);
		
		JLabel lblDeveloper = new JLabel("Developt by DUBUS Alexis, MATTON Maxence and GANDON Adrian.");
		lblDeveloper.setEnabled(false);
		lblDeveloper.setFont(new Font("Arial", Font.ITALIC, 10));
		lblDeveloper.setBounds(93, 257, 331, 14);
		frmBluer.getContentPane().add(lblDeveloper);
		
		JLabel lblProjectBlueR = new JLabel("Project BlueR");
		lblProjectBlueR.setFont(new Font("Arial", Font.BOLD, 15));
		lblProjectBlueR.setEnabled(false);
		lblProjectBlueR.setBounds(318, 246, 106, 14);
		frmBluer.getContentPane().add(lblProjectBlueR);
		
		JButton btnSeeDBRegisteredDevice = new JButton("Click on me to see the registered device of the database");
		btnSeeDBRegisteredDevice.setBackground(SystemColor.menu);
		btnSeeDBRegisteredDevice.setFont(new Font("Arial", Font.PLAIN, 9));
		btnSeeDBRegisteredDevice.setBounds(155, 138, 275, 23);
		frmBluer.getContentPane().add(btnSeeDBRegisteredDevice);
		
		btnCheckPresentDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////////// Action for the "Check present device" here ///////////////////////////////
				comboBox_DevicesList.setEnabled(true);
				// We load the device of the DB --> will change after
				// This part had to be deleted, but was enabled to test the list
				//devices = DAO.getDevices();
				com.epsi.dugama.BlueR.bluetooth.DeviceDiscovery.init();
				devices = DeviceDiscovery.getDeviceDiscovered();
				
				for (int i = 0; i < devices.size(); i++) {
					comboBox_DevicesList.addItem(devices.get(i).getDeviceName() + " : "+ devices.get(i).getIdBluetooth());
					DAO.addDevice(devices.get(i));
				}
			}
		});
		comboBox_DevicesList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Allow the button to be clickable
				btnSendData.setEnabled(true);
			}
		});
		btnSendData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////////////////////////////// Action for the "Send data" here ///////////////////////////////
				JOptionPane.showMessageDialog(null, "The data has been correctly send.\nThanks for have use BlueR app.");
			}
		});
		btnSeeDBRegisteredDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frmBluer2 = new JFrame();
				
				
				/////////////////////////// FRM creation  ///////////////////////////
				frmBluer2.setBackground(Color.WHITE);
				frmBluer2.setResizable(false);
				frmBluer2.setType(Type.UTILITY);
				frmBluer2.setTitle("BlueR - Existing Device");
				frmBluer2.getContentPane().setForeground(Color.BLACK);
				frmBluer2.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
				frmBluer2.setBounds(632, 100, 438, 305);
				frmBluer2.getContentPane().setLayout(null);
					
				
				/////////////////////////// Get devices on DB operation ///////////////////////////
				JLabel lblInfoCheckPresentDevice2 = new JLabel("This is the list of the device which was registered on DB :");
				lblInfoCheckPresentDevice2.setFont(new Font("Arial", Font.PLAIN, 12));
				lblInfoCheckPresentDevice2.setBounds(10, 5, 414, 14);
				frmBluer2.getContentPane().add(lblInfoCheckPresentDevice2);
				
				JComboBox<String> comboBox_DevicesDBList2 = new JComboBox<String>();
				comboBox_DevicesDBList2.setFont(new Font("Arial", Font.PLAIN, 11));
				comboBox_DevicesDBList2.setEnabled(true);
				comboBox_DevicesDBList2.setToolTipText("");
				comboBox_DevicesDBList2.setBounds(10, 25, 414, 23);
				frmBluer2.getContentPane().add(comboBox_DevicesDBList2);
				
				//////////// Adding device on the JComboBox ////////////
				devices = DAO.getDevices();
				for (int i = 0; i < devices.size(); i++) {
					comboBox_DevicesDBList2.addItem(devices.get(i).getDeviceName());
				}
				
				/////////////////////////// Signature addition ///////////////////////////
				// Addition of a picture
				JLabel lblPicture2 = new JLabel();
				ImageIcon image2 = new ImageIcon(this.getClass().getResource("/bluetoothPicture2.png"));
				
				Label lblBluetooth2 = new Label("Bluetooth");
				lblBluetooth2.setFont(new Font("Arial", Font.BOLD, 54));
				lblBluetooth2.setForeground(new Color(0, 0, 204));
				lblBluetooth2.setBounds(114, 172, 269, 59);
				frmBluer2.getContentPane().add(lblBluetooth2);
				lblPicture2.setIcon(image2);
				lblPicture2.setBounds(0, 131, 128, 140);
				frmBluer2.getContentPane().add(lblPicture2);
				
				JLabel lblDeveloper2 = new JLabel("Developt by DUBUS Alexis, MATTON Maxence and GANDON Adrian.");
				lblDeveloper2.setEnabled(false);
				lblDeveloper2.setFont(new Font("Arial", Font.ITALIC, 10));
				lblDeveloper2.setBounds(93, 257, 331, 14);
				frmBluer2.getContentPane().add(lblDeveloper2);
				
				JLabel lblProjectBlueR2 = new JLabel("Project BlueR");
				lblProjectBlueR2.setFont(new Font("Arial", Font.BOLD, 15));
				lblProjectBlueR2.setEnabled(false);
				lblProjectBlueR2.setBounds(318, 246, 106, 14);
				frmBluer2.getContentPane().add(lblProjectBlueR2);
				
				/////////////////////////// Set the FRM as visible ///////////////////////////
				frmBluer2.setVisible(true);
			}
		});
	}
}
