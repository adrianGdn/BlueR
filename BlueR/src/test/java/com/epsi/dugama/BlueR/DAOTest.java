package com.epsi.dugama.BlueR;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple MainApp.
 */
public class DAOTest {
	/**
	 * Test that check if all the DAO method work.
	 * 
	 * Prerequisite :
	 * - Launch your WAMP or XAMPP,
	 * - Check if the DB is correctly configured and installed.
	 */
	@Test
    public void daoMainTest()
    {
		// Preparation
			// We should have the same behavior for a devices list created here and with the DAO method
		Device device = new Device("Test Man Phone", "NUM23BER", "test.man@gmail.com");
		Device retrieveDevice;
		String oldBlutoothID = device.getIdBluetooth();
		List<Device> devicesWithoutDAOMethod;
		List<Device> devicesWithDAOMethod;
		
		
		// Execution - Creation
		devicesWithoutDAOMethod = DAO.getDevices();
		devicesWithoutDAOMethod.add(device);
		DAO.addDevice(device);
			// We check if we cannot add a "clone" 
		DAO.addDevice(device);
		devicesWithDAOMethod = DAO.getDevices();
		retrieveDevice = DAO.getOneDevice(device.getIdBluetooth());
		// Verification - Creation
		assertThat(device.toString(), equalTo(retrieveDevice.toString()));
		assertThat(devicesWithoutDAOMethod.toString(), equalTo(devicesWithDAOMethod.toString()));
		
		
		// Second execution - Update
		devicesWithoutDAOMethod.remove(device);
		device.setDeviceName("The phone of the testing man");
		device.setIdBluetooth("THE NUMBER 23");
		device.setMailAddress("testman@gmail.com");
		devicesWithoutDAOMethod.add(device);		
		DAO.updateOneDevice(device, oldBlutoothID);
		devicesWithDAOMethod = DAO.getDevices();
		retrieveDevice = DAO.getOneDevice(device.getIdBluetooth());
		// Verification - Update
		assertThat(device.toString(), equalTo(retrieveDevice.toString()));
		assertThat(devicesWithoutDAOMethod.toString(), equalTo(devicesWithDAOMethod.toString()));
		
		// Third execution - Deletion
		DAO.deleteOneDevice(device.getIdBluetooth());
		devicesWithDAOMethod = DAO.getDevices();
		devicesWithoutDAOMethod.remove(device);
		// Verification - Deletion
		assertNull(DAO.getOneDevice(device.getIdBluetooth()));
		assertThat(devicesWithoutDAOMethod.toString(), equalTo(devicesWithDAOMethod.toString()));
    }
}
