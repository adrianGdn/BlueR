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
	private static String url = "jdbc:mysql://localhost/bluer";
	private static String login = "root";
	private static String mdp = "";
	
	/**
	 * Method that allow us to get the device which was present on DB.
	 * 
	 * @return List<Device> This is a list with the device which was present on DB.
	 */
	public static List<Device> getDevices() {
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
				aDevice = new Device(rs.getString("deviceName"), rs.getString("idBluetooth"));
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
				aDevice = new Device(rs.getString("deviceName"), rs.getString("idBluetooth"));
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
	 * @return True if the device has been updated, false in other case.
	 */
	public static boolean updateOneDevice(Device updateDevice, String oldIdBluetooth) {
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
					+ "`deviceName` = '" + updateDevice.getDeviceName() + "'"
					+ " WHERE device.idBluetooth = '" + oldIdBluetooth + "';";
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
				String sql = "INSERT INTO `device` (`idBluetooth`, `deviceName`) "
						+ "VALUES ('" + aDevice.getIdBluetooth() + "', '" + aDevice.getDeviceName() + "');";
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
	
	
	/**
	 * Method that allow us to get the user which was present on DB.
	 * 
	 * @return List<User> This is a list with the device which was present on DB.
	 */
	public static List<User> getUsers() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<User> users = new ArrayList<User>();
		User aUser = null;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM user;";
			// Step 4 : Query execution
			rs = (ResultSet) st.executeQuery(sql);
			// Step 5 : We travel "ResultSet"
			while (rs.next()) {
				aUser = new User(rs.getString("firstName"), rs.getString("secondName"), DAO.getOneDevice(rs.getString("idBluetooth")));
				if(!rs.getString("mailAddress").equals(null)) {
					aUser.setMailAddress(rs.getString("mailAddress"));
				}
				if(!rs.getString("nbMobile").equals(null)) {
					aUser.setNbMobile(rs.getString("nbMobile"));
				}
				users.add(aUser);
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
		return users;	
	}
	/**
	 * Method that allow us to get a specific user which was present on DB.
	 * 
	 * @param firstNameOfTheUser The first name of the specific user search on DB. This is a String.
	 * @param secondNameOfTheUser The second name of the specific user search on DB. This is a String.
	 * @return The specific user searched on DB. This is a User object.
	 */
	public static User getOneUser(String firstNameOfTheUser, String secondNameOfTheUser) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		User aUser = null;
		boolean userFound = false;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM user;";
			// Step 4 : Query execution
			rs = (ResultSet) st.executeQuery(sql);
			// Step 5 : We travel "ResultSet" in order to find the searched value
			while (rs.next() && userFound == false) {
				aUser = new User(rs.getString("firstName"), rs.getString("secondName"), DAO.getOneDevice(rs.getString("idBluetooth")));
				if(!rs.getString("mailAddress").equals(null)) {
					aUser.setMailAddress(rs.getString("mailAddress"));
				}
				if(!rs.getString("nbMobile").equals(null)) {
					aUser.setNbMobile(rs.getString("nbMobile"));
				}
				if (aUser.getSecondName().equals(secondNameOfTheUser) && aUser.getFirstName().equals(firstNameOfTheUser)){
					userFound = true;
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
		if (userFound == true) {
			return aUser;	
		} else return null;
	}
	
	/**
	 * Method that allow us to delete a specific user which was present on DB.
	 * 
	 * @param firstNameOfTheUser The first name of the specific user that you search to delete on DB. This is a String.
	 * @param secondNameOfTheUser The second name of the specific user that you search to delete on DB. This is a String.
	 * @return True if the user has been deleted, false in other case.
	 */
	public static boolean deleteOneUser(String firstNameOfTheUser, String secondNameOfTheUser) {
		Connection cn = null;
		Statement st = null;
		
		boolean userIsDeleted = false;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			// Step 4 : Creation of the query
			String sql = "DELETE FROM user WHERE `firstName` = '" + firstNameOfTheUser + "' AND `secondName` = '" + secondNameOfTheUser + "';";
			// Step 5 : Query execution
			st.executeUpdate(sql);
			userIsDeleted = true;
			
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
		return userIsDeleted;
	}
	
	/**
	 * Method that allow us to update a specific user which was present on DB.
	 * 
	 * @param updateUser The specific user that you search to update on DB. This is a User object.
	 * @param oldFirstName The old first name of the user that you search to update on DB. This is a String.
	 * @param oldSecondName The old second name of the user that you search to update on DB. This is a String.
	 * @return True if the user has been updated, false in other case.
	 */
	public static boolean updateOneUser(User updateUser, String oldFirstName, String oldSecondName) {
		Connection cn = null;
		Statement st = null;
		
		boolean userIsUpdated = false;

		// For the case that the mail address or the mobile number are null
		String userMailAdressIfNull;
		if(updateUser.getMailAddress().equals(null)) {
			userMailAdressIfNull = "";
		} else userMailAdressIfNull = updateUser.getMailAddress();

		String userNbMobileIfNull;
		if(updateUser.getNbMobile().equals(null)) {
			userNbMobileIfNull = "";
		} else userNbMobileIfNull = updateUser.getNbMobile();
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			// Step 4 : Creation of the query
			String sql = "UPDATE user SET `firstName` = '" + updateUser.getFirstName() + "', "
					+ "`secondName` = '" + updateUser.getSecondName() + "', "
					+ "`idBluetooth` = '" + updateUser.getDevice().getIdBluetooth() + "', "
					+ "`mailAddress` = '" + userMailAdressIfNull + "', "
					+ "`nbMobile` = '" + userNbMobileIfNull + "'"
					+ " WHERE user.firstName = '" + oldFirstName + "' AND user.secondName = '" + oldSecondName + "';";
			// Step 5 : Query execution
			st.executeUpdate(sql);
			userIsUpdated = true;
			
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
		return userIsUpdated;
	}
	
	/**
	 * Method that allow us to add a user on DB.
	 * 
	 * @param aUser The user that you want to insert in the DB. This is a User object.
	 */
	public static void addUser(User aUser) {
		List<User> users = getUsers();
		boolean foundInBDD = false;
		for (User anotherUser : users) {
			if(anotherUser.getFirstName().equals(aUser.getFirstName()) 
					&& anotherUser.getSecondName().equals(aUser.getSecondName())) {
				foundInBDD = true;
			}
		}
		// We check if the device is found in the DB
		if(!foundInBDD)
		{
			// For the case that the mail address or the mobile number are null
			String userMailAdressIfNull;
			if(aUser.getMailAddress().equals(null)) {
				userMailAdressIfNull = "";
			} else userMailAdressIfNull = aUser.getMailAddress();

			String userNbMobileIfNull;
			if(aUser.getNbMobile().equals(null)) {
				userNbMobileIfNull = "";
			} else userNbMobileIfNull = aUser.getNbMobile();
			
			
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
				String sql = "INSERT INTO `user` (`firstName`, `secondName`, `idBluetooth`, `mailAddress`, `nbMobile`) "
						+ "VALUES ('" + aUser.getFirstName()
						+ "', '" + aUser.getSecondName()
						+ "', '" + aUser.getDevice().getIdBluetooth()
						+ "', '" + userMailAdressIfNull
						+ "', '" + userNbMobileIfNull + "');";
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

	
	/**
	 * Method that allow us to get a specific user that is linked to a device which was present on DB.
	 * 
	 * @param idBluetoothOfTheDeviceLinkedToTheUser The ID of the specific device search on DB that is linked to a user. This is a String.
	 * @return User The user linked to this device or null if the device has no user linked. This is a User class object.
	 */
	public static User getTheUserLinkedToTheDevice(String idBluetoothOfTheDeviceLinkedToTheUser) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		User aUser = null;
		boolean userFound = false;
		
		try {
			// Step 1 : Loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : Retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : Creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM user;";
			// Step 4 : Query execution
			rs = (ResultSet) st.executeQuery(sql);
			// Step 5 : We travel "ResultSet" in order to find the searched value
			while (rs.next() && userFound == false) {
				aUser = new User(rs.getString("firstName"), rs.getString("secondName"), DAO.getOneDevice(rs.getString("idBluetooth")));
				if(!rs.getString("mailAddress").equals(null)) {
					aUser.setMailAddress(rs.getString("mailAddress"));
				}
				if(!rs.getString("nbMobile").equals(null)) {
					aUser.setNbMobile(rs.getString("nbMobile"));
				}
				if (aUser.getDevice().getIdBluetooth().equals(idBluetoothOfTheDeviceLinkedToTheUser)) {
					userFound = true;
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
		if (userFound == true) {
			return aUser;	
		} else return null;
	}
}
