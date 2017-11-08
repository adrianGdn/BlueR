package com.epsi.dugama.BlueR;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * This class allow you to use a BDD
 * 
 * @author Adrian Gandon
 */
public class DAO {
	
	/**
	 * Method that allow us to test easily the connection with the DB
	 */
	public static void testCoBDD() {
		String url = "jdbc:mysql://localhost/bluer";
		String login = "root";
		String mdp = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			// Step 1 : loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM device;";
			// Step 4 : query execution
			rs = (ResultSet) st.executeQuery(sql);
			// Step 5 : We travel "ResultSet"
			while (rs.next()) {
				System.out.println(rs.getString("deviceName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			try {
				// Step 6 : liberation of the memory
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * Method that allow us to get the device which was present on DB
	 * 
	 * @return List<Device> This is a list with the device which was present on DB
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
			// Step 1 : loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2 : retrieval of the connection
			cn = (Connection) DriverManager.getConnection(url, login, mdp);
			// Step 3 : creation of a statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM device;";
			// Step 4 : query execution
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
				// Step 6 : liberation of the memory
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;	
	}
}
