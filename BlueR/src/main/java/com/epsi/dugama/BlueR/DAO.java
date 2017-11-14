package com.epsi.dugama.BlueR;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * This class allow you to use a BDD.
 * 
 * @author Adrian Gandon
 */
public class DAO {
	/**
	 * Method that allow us to get the device which was present on DB.
	 * 
	 * @return List<Device> This is a list with the device which was present on DB.
	 */
	public static List<Device> getDevices() {
		String url = "jdbc:mysql://localhost/bluer";
		String login = "root";
		String mdp = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Device> devices = new ArrayList<Device>();
		Device aDevice = null;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM device;";
			// Step 4 : Query execution
			rs = (ResultSet) st.executeQuery(sql);
			// Step 5 : We travel "ResultSet"
			while (rs.next()) {
				aDevice = new Device(rs.getString("deviceName"), rs.getString("idBluetooth"), rs.getString("mailAddress"));
				devices.add(aDevice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			try {
				// Step 6 : Liberation of the memory
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;	
	}
	
	/**
	 * Method that allow us to get a specific device which was present on DB.
	 * 
	 * @param idBluetoothOfTheSearchedDevice The ID of the specific device search on DB. This is a String.
	 * @return The specific device searched on DB. This is a Device object.
	 */
	public static Device getOneDevice(String idBluetoothOfTheSearchedDevice) {
		String url = "jdbc:mysql://localhost/bluer";
		String login = "root";
		String mdp = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Device aDevice = null;
		boolean deviceFound = false;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM device;";
			// Step 4 : Query execution
			rs = (ResultSet) st.executeQuery(sql);
			// Step 5 : We travel "ResultSet" in order to find the searched value
			while (rs.next() && deviceFound == false) {
				aDevice = new Device(rs.getString("deviceName"), rs.getString("idBluetooth"), rs.getString("mailAddress"));
				if (aDevice.getIdBluetooth().equals(idBluetoothOfTheSearchedDevice)) {
					deviceFound = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			try {
				// Step 6 : Liberation of the memory
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// We return a null value if we don't find the searched value
		if (deviceFound == true) {
			return aDevice;	
		} else return null;
	}
	
	/**
	 * Method that allow us to delete a specific device which was present on DB.
	 * 
	 * @param idBluetoothOfTheSearchedDevice The ID of the specific device that you search to delete on DB. This is a String.
	 * @return True if the device has been deleted, false in other case.
	 */
	public static boolean deleteOneDevice(String idBluetoothOfTheSearchedDevice) {
		String url = "jdbc:mysql://localhost/bluer";
		String login = "root";
		String mdp = "";
		Connection cn = null;
		Statement st = null;
		
		boolean deviceIsDeleted = false;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			// Step 4 : Creation of the query
			String sql = "DELETE FROM device WHERE `idBluetooth` = '" + idBluetoothOfTheSearchedDevice + "';";
			// Step 5 : Query execution
			st.executeUpdate(sql);
			deviceIsDeleted = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			try {
				// Step 6 : Liberation of the memory
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deviceIsDeleted;
	}
	
	/**
	 * Method that allow us to update a specific device which was present on DB.
	 * 
	 * @param updateDevice The specific device that you search to update on DB. This is a Device object.
	 * @param oldIdBluetooth The old ID of the specific device that you search to update on DB. This is a String.
	 * @return
	 */
	public static boolean updateOneDevice(Device updateDevice, String oldIdBluetooth) {
		String url = "jdbc:mysql://localhost/bluer";
		String login = "root";
		String mdp = "";
		Connection cn = null;
		Statement st = null;
		
		boolean deviceIsUpdated = false;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			// Step 4 : Creation of the query
			String sql = "UPDATE device SET `idBluetooth` = '" + updateDevice.getIdBluetooth() + "', "
					+ "`deviceName` = '" + updateDevice.getDeviceName() + "', "
					+ "`mailAddress` = '" + updateDevice.getMailAddress() + "' WHERE device.idBluetooth = '" + oldIdBluetooth + "';";
			// Step 5 : Query execution
			st.executeUpdate(sql);
			deviceIsUpdated = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			try {
				// Step 6 : Liberation of the memory
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deviceIsUpdated;
	}
	
	/**
	 * Method that allow us to add a device on DB.
	 * 
	 * @param aDevice The device that you want to insert in the DB. This is a Device object.
	 */
	public static void addDevice(Device aDevice) {
		List<Device> devices = getDevices();
		boolean foundInBDD = false;
		for (Device device : devices) {
			if(device.getIdBluetooth().equals(aDevice.getIdBluetooth()))
			{
				foundInBDD = true;
			}
		}
		// We check if the device is found in the DB
		if(!foundInBDD)
		{
			String url = "jdbc:mysql://localhost/bluer";
			String login = "root";
			String mdp = "";
			Connection cn = null;
			Statement st = null;
			
			try {
				// Step 1 : Loading the driver
				Class.forName("com.mysql.jdbc.Driver");
				// Step 2 : Retrieval of the connection
				cn = (Connection) DriverManager.getConnection(url, login, mdp);
				// Step 3 : Creation of a statement
				st = (Statement) cn.createStatement();
				// Step 4 : Creation of the query
				String sql = "INSERT INTO `device` (`idBluetooth`, `deviceName`, `mailAddress`) "
						+ "VALUES ('" + aDevice.getIdBluetooth() + "', '" + aDevice.getDeviceName() +"', '" + aDevice.getMailAddress() + "');";
				// Step 5 : Query execution
				st.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} finally {
				try {
					// Step 6 : Liberation of the memory
					cn.close();
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
