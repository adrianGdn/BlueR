package com.epsi.dugama.BlueR.UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import com.epsi.dugama.BlueR.SendMailTLS;
import com.epsi.dugama.BlueR.User;
import com.epsi.dugama.BlueR.bluetooth.*;

public class MainView {
	private JFrame frmBluer;
	private JFrame frmBluer3;
	protected List<Device> devices;
	protected List<User> users;

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
		
		JButton btnSeeDBRegisteredDevice = new JButton("See the device of the DB");
		btnSeeDBRegisteredDevice.setBackground(SystemColor.menu);
		btnSeeDBRegisteredDevice.setFont(new Font("Arial", Font.PLAIN, 9));
		btnSeeDBRegisteredDevice.setBounds(280, 138, 145, 23); //275 w
		frmBluer.getContentPane().add(btnSeeDBRegisteredDevice);
		
		JButton btnSeeDBRegisteredUser = new JButton("See the user of the DB");
		btnSeeDBRegisteredUser.setBackground(SystemColor.menu);
		btnSeeDBRegisteredUser.setFont(new Font("Arial", Font.PLAIN, 9));
		btnSeeDBRegisteredUser.setBounds(132, 138, 145, 23);
		frmBluer.getContentPane().add(btnSeeDBRegisteredUser);
		
		
		btnCheckPresentDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////////// Action for the "Check present device" here ///////////////////////////////
				comboBox_DevicesList.setEnabled(true);
				//devices = DAO.getDevices();
				com.epsi.dugama.BlueR.bluetooth.DeviceDiscovery.init();
				devices = DeviceDiscovery.getDeviceDiscovered();
				
				// Allow to reset the list and then to add device in it
				comboBox_DevicesList.removeAllItems();
				for (int i = 0; i < devices.size(); i++) {
					comboBox_DevicesList.addItem( devices.get(i).getIdBluetooth() + " - " + devices.get(i).getDeviceName());
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
				String bluetoothId = comboBox_DevicesList.getSelectedItem().toString().substring(0, 12);
				User selectedUser = DAO.getTheUserLinkedToTheDevice(bluetoothId);
				if(!selectedUser.getMailAddress().equals(null))
				{
					SendMailTLS.sendMail(selectedUser.getMailAddress());
					JOptionPane.showMessageDialog(null, "The data has been correctly send.\nThanks for using BlueR app.", "Information", JOptionPane.INFORMATION_MESSAGE);
				} else JOptionPane.showMessageDialog(null, "Actually, this detected device has no user registered with it.\nPlease register a user and a valid mail address for that user before trying to send data.", "Error", JOptionPane.WARNING_MESSAGE);
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
				frmBluer2.setBounds(565, 100, 438, 305);
				frmBluer2.getContentPane().setLayout(null);
					
				
				/////////////////////////// Get devices on DB operation ///////////////////////////
				JLabel lblInfoCheckPresentDevice2 = new JLabel("This is the list of the device which was registered on DB :");
				lblInfoCheckPresentDevice2.setFont(new Font("Arial", Font.PLAIN, 12));
				lblInfoCheckPresentDevice2.setBounds(10, 5, 414, 14);
				frmBluer2.getContentPane().add(lblInfoCheckPresentDevice2);
				
				final JComboBox<String> comboBox_DevicesDBList2 = new JComboBox<String>();
				comboBox_DevicesDBList2.setFont(new Font("Arial", Font.PLAIN, 11));
				comboBox_DevicesDBList2.setEnabled(true);
				comboBox_DevicesDBList2.setToolTipText("");
				comboBox_DevicesDBList2.setBounds(10, 25, 414, 23);
				frmBluer2.getContentPane().add(comboBox_DevicesDBList2);
				
				//////////// Adding device on the JComboBox ////////////
				devices = DAO.getDevices();
				for (int i = 0; i < devices.size(); i++) {
					comboBox_DevicesDBList2.addItem(devices.get(i).getIdBluetooth() + " - " + devices.get(i).getDeviceName());
				}
				
				JButton btnDeleteDevice = new JButton("Delete this device");
				btnDeleteDevice.setFont(new Font("Arial", Font.PLAIN, 11));
				btnDeleteDevice.setBounds(305, 55, 120, 23);
				frmBluer2.getContentPane().add(btnDeleteDevice);
				
				JButton btnUpdateDevice = new JButton("Update this device");
				btnUpdateDevice.setFont(new Font("Arial", Font.PLAIN, 11));
				btnUpdateDevice.setBounds(10, 55, 125, 23);
				frmBluer2.getContentPane().add(btnUpdateDevice);
				
				/////////////////////////// Event action ///////////////////////////
				btnDeleteDevice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// Deletion confirmation
							if(JOptionPane.showConfirmDialog(null, "Are you sure to delete this device ?", "Deletion confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
								List<User> usersForDeletion = DAO.getUsers();
								boolean userFound = false;
								int listCounter = 0;
								while (!userFound && listCounter < usersForDeletion.size()) {
									if(usersForDeletion.get(listCounter).getDevice().getIdBluetooth().equals(comboBox_DevicesDBList2.getSelectedItem().toString().substring(0, 12))) {
										userFound = true;
									}
									listCounter++;
								}
								if(!userFound) {
									// 12 is the size of the Bluetooth ID
									DAO.deleteOneDevice(comboBox_DevicesDBList2.getSelectedItem().toString().substring(0, 12));
									devices = DAO.getDevices();
									comboBox_DevicesDBList2.removeAllItems();
									for (int i = 0; i < devices.size(); i++) {
										comboBox_DevicesDBList2.addItem(devices.get(i).getIdBluetooth() + " - " + devices.get(i).getDeviceName());
									}
									JOptionPane.showMessageDialog(null, "The device has been correctly deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
								} else JOptionPane.showMessageDialog(null, "You must delete the user related to that device first.", "Error", JOptionPane.ERROR_MESSAGE);
							} else JOptionPane.showMessageDialog(null, "The device hasn't been deleted has choosen.", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
						catch (Exception deviceNotDeleted) {
							JOptionPane.showMessageDialog(null, "An error has occur when trying to delete this device.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				btnUpdateDevice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// Data request
							String updateDeviceName = JOptionPane.showInputDialog(null, "Please enter the new device name :", "Update request", JOptionPane.QUESTION_MESSAGE);
							if (!updateDeviceName.equals(null) && !updateDeviceName.equals("") && !updateDeviceName.contains("'")
									 && !updateDeviceName.contains("!") && !updateDeviceName.contains("?") && !updateDeviceName.contains("&")
									 && !updateDeviceName.contains("/") && !updateDeviceName.contains("(") && !updateDeviceName.contains(")") && !updateDeviceName.contains("`")) {
								// Device update - 12 is the size of the Bluetooth ID
								Device updateDevice = DAO.getOneDevice(comboBox_DevicesDBList2.getSelectedItem().toString().substring(0, 12));
								updateDevice.setDeviceName(updateDeviceName);
								// Sending data
								DAO.updateOneDevice(updateDevice, comboBox_DevicesDBList2.getSelectedItem().toString().substring(0, 12));
								// Refresh list
								devices = DAO.getDevices();
								comboBox_DevicesDBList2.removeAllItems();
								for (int i = 0; i < devices.size(); i++) {
									comboBox_DevicesDBList2.addItem(devices.get(i).getIdBluetooth() + " - " + devices.get(i).getDeviceName());
								}
								JOptionPane.showMessageDialog(null, "The device has been correctly updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
							} else JOptionPane.showMessageDialog(null, "You choose to cancel the modification or you've entered a wrong new name.", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
						catch (Exception deviceNotUpdated) {
							JOptionPane.showMessageDialog(null, "The device hasn't been correctly updated.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
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
		btnSeeDBRegisteredUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBluer3 = new JFrame();
				
				
				/////////////////////////// FRM creation  ///////////////////////////
				frmBluer3.setBackground(Color.WHITE);
				frmBluer3.setResizable(false);
				frmBluer3.setType(Type.UTILITY);
				frmBluer3.setTitle("BlueR - Existing User");
				frmBluer3.getContentPane().setForeground(Color.BLACK);
				frmBluer3.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
				frmBluer3.setBounds(100, 432, 438, 305);
				frmBluer3.getContentPane().setLayout(null);
					
				
				/////////////////////////// Get users on DB operation ///////////////////////////
				JLabel lblInfoCheckPresentDevice3 = new JLabel("This is the list of the user which was registered on DB :");
				lblInfoCheckPresentDevice3.setFont(new Font("Arial", Font.PLAIN, 12));
				lblInfoCheckPresentDevice3.setBounds(10, 5, 414, 14);
				frmBluer3.getContentPane().add(lblInfoCheckPresentDevice3);
				
				final JComboBox<String> comboBox_UserDBList = new JComboBox<String>();
				comboBox_UserDBList.setFont(new Font("Arial", Font.PLAIN, 11));
				comboBox_UserDBList.setEnabled(true);
				comboBox_UserDBList.setToolTipText("");
				comboBox_UserDBList.setBounds(10, 25, 414, 23);
				frmBluer3.getContentPane().add(comboBox_UserDBList);
				
				//////////// Adding device on the JComboBox ////////////
				users = DAO.getUsers();
				for (int i = 0; i < users.size(); i++) {
					comboBox_UserDBList.addItem(users.get(i).getFirstName() + " " + users.get(i).getSecondName() + " - " + users.get(i).getMailAddress());
				}
				
				JButton btnDeleteUser = new JButton("Delete this user");
				btnDeleteUser.setFont(new Font("Arial", Font.PLAIN, 11));
				btnDeleteUser.setBounds(305, 55, 120, 23);
				frmBluer3.getContentPane().add(btnDeleteUser);
				
				JButton btnUpdateUser = new JButton("Update this user");
				btnUpdateUser.setFont(new Font("Arial", Font.PLAIN, 11));
				btnUpdateUser.setBounds(158, 55, 125, 23);
				frmBluer3.getContentPane().add(btnUpdateUser);
				
				JButton btnCreateUser = new JButton("Create a user");
				btnCreateUser.setFont(new Font("Arial", Font.PLAIN, 11));
				btnCreateUser.setBounds(10, 55, 125, 23);
				frmBluer3.getContentPane().add(btnCreateUser);
				
				/////////////////////////// Event action ///////////////////////////
				btnDeleteUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// Deletion confirmation
							if(JOptionPane.showConfirmDialog(null, "Are you sure to delete this user ?", "Deletion confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
								// We search to find the location of the name and the surname in the string
								String nameAndSurname = comboBox_UserDBList.getSelectedItem().toString();
								int separationBetweenNameAndSurname = nameAndSurname.indexOf(' ', 1);
								int separationBetweenSurnameAndMail = nameAndSurname.indexOf('-', 1);
								// We delete the user
								DAO.deleteOneUser(nameAndSurname.substring(0 , separationBetweenNameAndSurname), 
										nameAndSurname.substring(separationBetweenNameAndSurname+1 , separationBetweenSurnameAndMail-1));
								// We get the updated users list
								users = DAO.getUsers();
								// We clean the users list
								comboBox_UserDBList.removeAllItems();
								for (int i = 0; i < users.size(); i++) {
									comboBox_UserDBList.addItem(users.get(i).getFirstName() + " " + users.get(i).getSecondName() + " - " + users.get(i).getMailAddress());
								}
								JOptionPane.showMessageDialog(null, "The user has been correctly deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
							} else JOptionPane.showMessageDialog(null, "The user hasn't been deleted has choosen.", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
						catch (Exception userNotDeleted) {
							JOptionPane.showMessageDialog(null, "An error has occur when trying to delete this user.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				btnUpdateUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// We search to find the location of the name and the surname in the string
						String nameAndSurname = comboBox_UserDBList.getSelectedItem().toString();
						int separationBetweenNameAndSurname = nameAndSurname.indexOf(' ', 1);
						int separationBetweenSurnameAndMail = nameAndSurname.indexOf('-', 1);
						final User updatedUser = DAO.getOneUser(nameAndSurname.substring(0 , separationBetweenNameAndSurname), 
								nameAndSurname.substring(separationBetweenNameAndSurname+1 , separationBetweenSurnameAndMail-1));
						/////////////////////////// Adding editable fields ///////////////////////////
						final JTextField txt_UpdateUserFirstName;
						txt_UpdateUserFirstName = new JTextField();
						txt_UpdateUserFirstName.setText(updatedUser.getFirstName());
						txt_UpdateUserFirstName.setBounds(10, 80, 125, 23);
						txt_UpdateUserFirstName.setColumns(20);
						frmBluer3.getContentPane().add(txt_UpdateUserFirstName);
						
						final JTextField txt_UpdateUserSecondName;
						txt_UpdateUserSecondName = new JTextField();
						txt_UpdateUserSecondName.setText(updatedUser.getSecondName());
						txt_UpdateUserSecondName.setBounds(10, 110, 125, 23);
						txt_UpdateUserSecondName.setColumns(20);
						frmBluer3.getContentPane().add(txt_UpdateUserSecondName);
						
						final JTextField txt_UpdateUserMailAddress;
						txt_UpdateUserMailAddress = new JTextField();
						txt_UpdateUserMailAddress.setText(updatedUser.getMailAddress());
						txt_UpdateUserMailAddress.setBounds(140, 80, 150, 23);
						txt_UpdateUserMailAddress.setColumns(50);
						frmBluer3.getContentPane().add(txt_UpdateUserMailAddress);
						
						final JTextField txt_UpdateUserPhoneNumber;
						txt_UpdateUserPhoneNumber = new JTextField();
						txt_UpdateUserPhoneNumber.setText(updatedUser.getNbMobile());
						txt_UpdateUserPhoneNumber.setBounds(140, 110, 150, 23);
						txt_UpdateUserPhoneNumber.setColumns(10);
						frmBluer3.getContentPane().add(txt_UpdateUserPhoneNumber);
						
						final JButton btnUpdateOnDB = new JButton("Confirm");
						btnUpdateOnDB.setFont(new Font("Arial", Font.PLAIN, 11));
						btnUpdateOnDB.setBounds(295, 110, 125, 23);
						frmBluer3.getContentPane().add(btnUpdateOnDB);
						
						final JComboBox<String> comboBox_UpdateUserDevicesList = new JComboBox<String>();
						comboBox_UpdateUserDevicesList.setFont(new Font("Arial", Font.PLAIN, 11));
						comboBox_UpdateUserDevicesList.setEnabled(true);
						comboBox_UpdateUserDevicesList.setToolTipText("");
						comboBox_UpdateUserDevicesList.setBounds(295, 80, 125, 23);
						frmBluer3.getContentPane().add(comboBox_UpdateUserDevicesList);
						
						//////////// Adding device on the JComboBox ////////////
						devices = DAO.getDevices();
						for (int i = 0; i < devices.size(); i++) {
							comboBox_UpdateUserDevicesList.addItem(devices.get(i).getIdBluetooth());
							if(devices.get(i).getIdBluetooth().equals(updatedUser.getDevice().getIdBluetooth())) {
								comboBox_UpdateUserDevicesList.setSelectedIndex(i);
							}
						}
						
						btnUpdateOnDB.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									String mail = txt_UpdateUserMailAddress.getText();
									int separator = mail.indexOf('@', 1);
									String mailType = mail.substring(separator , mail.length());
									boolean txtFirstNameCorrect = true;
									boolean txtSecondNameCorrect = true;
									boolean txtMailAddressCorrect = true;
									boolean txtMobileNumberCorrect = true;
									boolean txtIdBluetoothCorrect = true;
									
									if(txt_UpdateUserFirstName.getText().equals("") || txt_UpdateUserFirstName.getText().contains("'")) {
										txtFirstNameCorrect = false;
									}
									if(txt_UpdateUserSecondName.getText().equals("") || txt_UpdateUserSecondName.getText().contains("'")) {
										txtSecondNameCorrect = false;
									}
									if(txt_UpdateUserMailAddress.getText().equals("") || txt_UpdateUserMailAddress.getText().contains("'")) {
										txtMailAddressCorrect = false;
									}
									if(!mailType.equals("@gmail.com") && !mailType.equals("@gmail.fr") && !mailType.equals("@me.com") && !mailType.equals("@icloud.com")
											&& !mailType.equals("@epsi.fr") && !mailType.equals("@hotmail.com") && !mailType.equals("@hotmail.fr") && !mailType.equals("@alexis-dubus.com")
											|| mailType.contains("'")) {
										txtMailAddressCorrect = false;
									}
									if(txt_UpdateUserPhoneNumber.getText().equals("") || txt_UpdateUserPhoneNumber.getText().contains("'")) {
										txtMobileNumberCorrect = false;
									}
									
									if(txtFirstNameCorrect == true && txtSecondNameCorrect == true && txtMailAddressCorrect == true
											&& txtMobileNumberCorrect == true && txtIdBluetoothCorrect == true) {
										// Deletion confirmation
										if(JOptionPane.showConfirmDialog(null, "Are you sure to update this user ?", "Update confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
											// We search to find the location of the name and the surname in the string
											// We update the user
											String oldFirstName = updatedUser.getFirstName();
											String oldSecondName = updatedUser.getSecondName();
											updatedUser.setFirstName(txt_UpdateUserFirstName.getText());
											updatedUser.setSecondName(txt_UpdateUserSecondName.getText());
											updatedUser.setMailAddress(txt_UpdateUserMailAddress.getText());
											updatedUser.setNbMobile(txt_UpdateUserPhoneNumber.getText());
											updatedUser.setDevice(DAO.getOneDevice(comboBox_UpdateUserDevicesList.getSelectedItem().toString()));
											DAO.updateOneUser(updatedUser, oldFirstName, oldSecondName);
											// We get the updated users list
											users = DAO.getUsers();
											// We clean the users list
											comboBox_UserDBList.removeAllItems();
											for (int i = 0; i < users.size(); i++) {
												comboBox_UserDBList.addItem(users.get(i).getFirstName() + " " + users.get(i).getSecondName() + " - " + users.get(i).getMailAddress());
											}
											JOptionPane.showMessageDialog(null, "The user has been correctly updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
										} else JOptionPane.showMessageDialog(null, "The user hasn't been updated has choosen.", "Information", JOptionPane.INFORMATION_MESSAGE);
										// We delete the label of the interface
										txt_UpdateUserFirstName.setVisible(false);
										txt_UpdateUserSecondName.setVisible(false);
										txt_UpdateUserMailAddress.setVisible(false);
										txt_UpdateUserPhoneNumber.setVisible(false);
										comboBox_UpdateUserDevicesList.setVisible(false);
										btnUpdateOnDB.setVisible(false);
										frmBluer3.getContentPane().remove(txt_UpdateUserFirstName);
										frmBluer3.getContentPane().remove(txt_UpdateUserSecondName);
										frmBluer3.getContentPane().remove(txt_UpdateUserMailAddress);
										frmBluer3.getContentPane().remove(txt_UpdateUserPhoneNumber);
										frmBluer3.getContentPane().remove(comboBox_UpdateUserDevicesList);
										frmBluer3.getContentPane().remove(btnUpdateOnDB);
									} else JOptionPane.showMessageDialog(null, "One of the updated fields have wrong values.", "Warning", JOptionPane.WARNING_MESSAGE);
								}
								catch (Exception userNotDeleted) {
									JOptionPane.showMessageDialog(null, "An error has occur when trying to update this user.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						});
					}
				});
				
				btnCreateUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/////////////////////////// Adding editable fields ///////////////////////////
						final JTextField txt_CreateUserFirstName;
						txt_CreateUserFirstName = new JTextField();
						txt_CreateUserFirstName.setText("First name");
						txt_CreateUserFirstName.setBounds(10, 80, 125, 23);
						txt_CreateUserFirstName.setColumns(20);
						frmBluer3.getContentPane().add(txt_CreateUserFirstName);
						
						final JTextField txt_CreateUserSecondName;
						txt_CreateUserSecondName = new JTextField();
						txt_CreateUserSecondName.setText("Second name");
						txt_CreateUserSecondName.setBounds(10, 110, 125, 23);
						txt_CreateUserSecondName.setColumns(20);
						frmBluer3.getContentPane().add(txt_CreateUserSecondName);
						
						final JTextField txt_CreateUserMailAddress;
						txt_CreateUserMailAddress = new JTextField();
						txt_CreateUserMailAddress.setText("mail.address@exemple.com");
						txt_CreateUserMailAddress.setBounds(140, 80, 150, 23);
						txt_CreateUserMailAddress.setColumns(50);
						frmBluer3.getContentPane().add(txt_CreateUserMailAddress);
						
						final JTextField txt_CreateUserPhoneNumber;
						txt_CreateUserPhoneNumber = new JTextField();
						txt_CreateUserPhoneNumber.setText("Phone num.");
						txt_CreateUserPhoneNumber.setBounds(140, 110, 150, 23);
						txt_CreateUserPhoneNumber.setColumns(10);
						frmBluer3.getContentPane().add(txt_CreateUserPhoneNumber);
						
						final JButton btnCreateOnDB = new JButton("Confirm");
						btnCreateOnDB.setFont(new Font("Arial", Font.PLAIN, 11));
						btnCreateOnDB.setBounds(295, 110, 125, 23);
						frmBluer3.getContentPane().add(btnCreateOnDB);
						
						final JComboBox<String> comboBox_CreateUserDevicesList = new JComboBox<String>();
						comboBox_CreateUserDevicesList.setFont(new Font("Arial", Font.PLAIN, 11));
						comboBox_CreateUserDevicesList.setEnabled(true);
						comboBox_CreateUserDevicesList.setToolTipText("");
						comboBox_CreateUserDevicesList.setBounds(295, 80, 125, 23);
						frmBluer3.getContentPane().add(comboBox_CreateUserDevicesList);
						
						//////////// Adding device on the JComboBox ////////////
						devices = DAO.getDevices();
						for (int i = 0; i < devices.size(); i++) {
							comboBox_CreateUserDevicesList.addItem(devices.get(i).getIdBluetooth());
						}
						
						btnCreateOnDB.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									String mail = txt_CreateUserMailAddress.getText();
									int separator = mail.indexOf('@', 1);
									String mailType = mail.substring(separator , mail.length());
									boolean txtFirstNameCorrect = true;
									boolean txtSecondNameCorrect = true;
									boolean txtMailAddressCorrect = true;
									boolean txtMobileNumberCorrect = true;
									boolean txtIdBluetoothCorrect = true;
									
									if(txt_CreateUserFirstName.getText().equals("First name") || txt_CreateUserFirstName.getText().contains("'") 
											|| txt_CreateUserFirstName.getText().equals("") || txt_CreateUserFirstName.getText().contains(" ")) {
										txtFirstNameCorrect = false;
									}
									if(txt_CreateUserSecondName.getText().equals("Second name") || txt_CreateUserSecondName.getText().contains("'")
											|| txt_CreateUserSecondName.getText().equals("") || txt_CreateUserSecondName.getText().contains(" ")) {
										txtSecondNameCorrect = false;
									}
									if(txt_CreateUserMailAddress.getText().equals("mail.address@exemple.com") || txt_CreateUserMailAddress.getText().contains("'")
											|| txt_CreateUserMailAddress.getText().equals("") || txt_CreateUserMailAddress.getText().contains(" ")) {
										txtMailAddressCorrect = false;
									}
									if(!mailType.equals("@gmail.com") && !mailType.equals("@gmail.fr") && !mailType.equals("@me.com") && !mailType.equals("@icloud.com")
											&& !mailType.equals("@epsi.fr") && !mailType.equals("@hotmail.com") && !mailType.equals("@hotmail.fr") && !mailType.equals("@alexis-dubus.com")) {
										txtMailAddressCorrect = false;
									}
									if(txt_CreateUserPhoneNumber.getText().equals("Phone num.") || txt_CreateUserMailAddress.getText().contains("'")
											|| txt_CreateUserPhoneNumber.getText().equals("") || txt_CreateUserPhoneNumber.getText().contains(" ")) {
										txtMobileNumberCorrect = false;
									}
									
									if(txtFirstNameCorrect == true && txtSecondNameCorrect == true && txtMailAddressCorrect == true
											&& txtMobileNumberCorrect == true && txtIdBluetoothCorrect == true) {
										// Deletion confirmation
										if(JOptionPane.showConfirmDialog(null, "Are you sure to create this user ?", "Creation confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
											// We search to find the location of the name and the surname in the string
											// We update the user
											User createdUser = new User(txt_CreateUserFirstName.getText(), txt_CreateUserSecondName.getText(), 
											DAO.getOneDevice(comboBox_CreateUserDevicesList.getSelectedItem().toString()), txt_CreateUserMailAddress.getText(), txt_CreateUserPhoneNumber.getText());
											
											// We check if the user that we try to add is not present in the DB
											List<User> usersCheckList = DAO.getUsers();
											boolean userFounds = false;
											int userCounter = 0;
											while(!userFounds && userCounter < usersCheckList.size()) {
												if(usersCheckList.get(userCounter).getFirstName().equals(txt_CreateUserFirstName.getText()) || usersCheckList.get(userCounter).getSecondName().equals(txt_CreateUserSecondName.getText())) {
													userFounds = true;
												}												
												userCounter++;
											}
											
											// If the user was not present in the DB, we can add it
											if(!userFounds) {
												DAO.addUser(createdUser);
												// We get the updated users list
												users = DAO.getUsers();
												// We clean the users list
												comboBox_UserDBList.removeAllItems();
												for (int i = 0; i < users.size(); i++) {
													comboBox_UserDBList.addItem(users.get(i).getFirstName() + " " + users.get(i).getSecondName() + " - " + users.get(i).getMailAddress());
												}
												JOptionPane.showMessageDialog(null, "The user has been correctly created.", "Information", JOptionPane.INFORMATION_MESSAGE);
											} else JOptionPane.showMessageDialog(null, "You cannot add a user that exist already.", "Warning", JOptionPane.WARNING_MESSAGE);
										} else JOptionPane.showMessageDialog(null, "The user hasn't been created has choosen.", "Information", JOptionPane.INFORMATION_MESSAGE);
										// We delete the label of the interface
										txt_CreateUserFirstName.setVisible(false);
										txt_CreateUserSecondName.setVisible(false);
										txt_CreateUserMailAddress.setVisible(false);
										txt_CreateUserPhoneNumber.setVisible(false);
										comboBox_CreateUserDevicesList.setVisible(false);
										btnCreateOnDB.setVisible(false);
										frmBluer3.getContentPane().remove(txt_CreateUserFirstName);
										frmBluer3.getContentPane().remove(txt_CreateUserSecondName);
										frmBluer3.getContentPane().remove(txt_CreateUserMailAddress);
										frmBluer3.getContentPane().remove(txt_CreateUserPhoneNumber);
										frmBluer3.getContentPane().remove(comboBox_CreateUserDevicesList);
										frmBluer3.getContentPane().remove(btnCreateOnDB);
									} else JOptionPane.showMessageDialog(null, "One of the fields have wrong values.", "Warning", JOptionPane.WARNING_MESSAGE);
								}
								catch (Exception userNotDeleted) {
									JOptionPane.showMessageDialog(null, "An error has occur when trying to create a new user.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						});
					}
				});
				
				/////////////////////////// Signature addition ///////////////////////////
				// Addition of a picture
				JLabel lblPicture3 = new JLabel();
				ImageIcon image3 = new ImageIcon(this.getClass().getResource("/bluetoothPicture2.png"));
				
				Label lblBluetooth3 = new Label("Bluetooth");
				lblBluetooth3.setFont(new Font("Arial", Font.BOLD, 54));
				lblBluetooth3.setForeground(new Color(0, 0, 204));
				lblBluetooth3.setBounds(114, 172, 269, 59);
				frmBluer3.getContentPane().add(lblBluetooth3);
				lblPicture3.setIcon(image3);
				lblPicture3.setBounds(0, 131, 128, 140);
				frmBluer3.getContentPane().add(lblPicture3);
				
				JLabel lblDeveloper3 = new JLabel("Developt by DUBUS Alexis, MATTON Maxence and GANDON Adrian.");
				lblDeveloper3.setEnabled(false);
				lblDeveloper3.setFont(new Font("Arial", Font.ITALIC, 10));
				lblDeveloper3.setBounds(93, 257, 331, 14);
				frmBluer3.getContentPane().add(lblDeveloper3);
				
				JLabel lblProjectBlueR3 = new JLabel("Project BlueR");
				lblProjectBlueR3.setFont(new Font("Arial", Font.BOLD, 15));
				lblProjectBlueR3.setEnabled(false);
				lblProjectBlueR3.setBounds(318, 246, 106, 14);
				frmBluer3.getContentPane().add(lblProjectBlueR3);
				
				/////////////////////////// Set the FRM as visible ///////////////////////////
				frmBluer3.setVisible(true);
			}
		});
	}
}
