package com.epsi.dugama.BlueR;

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
}
