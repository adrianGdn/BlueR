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
	 * Test that check if the DAO method which concern the Device work.
	 * 
	 * Prerequisite :
	 * - Launch your WAMP or XAMPP,
	 * - Check if the DB is correctly configured and installed.
	 */
	@Test
    public void daoDeviceTest()
    {
		// Preparation
			// We should have the same behavior for a devices list created here and with the DAO method
		Device device = new Device("Test Man Phone", "NUM23BER");
		Device retrieveDevice;
		String oldBluetoothID = device.getIdBluetooth();
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
		devicesWithoutDAOMethod.add(device);		
		DAO.updateOneDevice(device, oldBluetoothID);
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
	
	/**
	 * Test that check if the DAO method which concern the User work.
	 * 
	 * Prerequisite :
	 * - Launch your WAMP or XAMPP,
	 * - Check if the DB is correctly configured and installed.
	 */
	@Test
    public void daoUserTest()
    {
		// Preparation
			// We should have the same behavior for a users list created here and with the DAO method
		Device device = new Device("Test Man Phone User", "NUM23BERUSER");
		User aUser = new User("ZN1stName", "ZN2ndName", device, "zname.2ndName@gmail.com", "0623232323");
		User retrieveUser;
		String oldBluetoothID = device.getIdBluetooth();
		String oldFirstName = aUser.getFirstName();
		String oldSecondName = aUser.getSecondName();
		List<User> usersWithoutDAOMethod;
		List<User> usersWithDAOMethod;
		
		
		// Execution - Creation
		usersWithoutDAOMethod = DAO.getUsers();
		usersWithoutDAOMethod.add(aUser);
		DAO.addDevice(device);
		DAO.addUser(aUser);
			// We check if we cannot add a "clone" 
		DAO.addUser(aUser);
		usersWithDAOMethod = DAO.getUsers();
		retrieveUser = DAO.getOneUser(aUser.getFirstName(), aUser.getSecondName());
		// Verification - Creation
		assertThat(aUser.toString(), equalTo(retrieveUser.toString()));
		assertThat(usersWithoutDAOMethod.toString(), equalTo(usersWithDAOMethod.toString()));
		
		
		// Second execution - Update
		usersWithoutDAOMethod.remove(aUser);
		device.setDeviceName("The user phone of the testing man");
		device.setIdBluetooth("THE USER NUMBER 23");
		aUser.setDevice(device);
		aUser.setFirstName("ZRealFirstName");
		aUser.setSecondName("ZReal Second Name");
		aUser.setMailAddress("zrealname.real2ndName@gmail.com");
		aUser.setNbMobile("0632323232");
		usersWithoutDAOMethod.add(aUser);
		DAO.addDevice(device);
		DAO.updateOneUser(aUser, oldFirstName, oldSecondName);
		DAO.deleteOneDevice(oldBluetoothID);
		usersWithDAOMethod = DAO.getUsers();
		retrieveUser = DAO.getOneUser(aUser.getFirstName(), aUser.getSecondName());
		// Verification - Update
		assertThat(aUser.toString(), equalTo(retrieveUser.toString()));
		assertThat(usersWithoutDAOMethod.toString(), equalTo(usersWithDAOMethod.toString()));
		
		// Third execution - Deletion
		DAO.deleteOneUser(aUser.getFirstName(), aUser.getSecondName());
		DAO.deleteOneDevice(device.getIdBluetooth());
		usersWithDAOMethod = DAO.getUsers();
		usersWithoutDAOMethod.remove(aUser);
		// Verification - Deletion
		assertNull(DAO.getOneUser(aUser.getFirstName(), aUser.getSecondName()));
		assertThat(usersWithoutDAOMethod.toString(), equalTo(usersWithDAOMethod.toString()));
    }
	
	/**
	 * Test that check if the DAO method which allow us to get the user linked to a device.
	 * 
	 * Prerequisite :
	 * - Launch your WAMP or XAMPP,
	 * - Check if the DB is correctly configured and installed.
	 */
	@Test
    public void daoGetUserLinkedToDeviceTest()
    {
		// Preparation
		List<Device> devices = DAO.getDevices();
		List<User> users =  DAO.getUsers();
		Device device = devices.get(0);
		User aUser = null;
		User retrieveUser = null;
		User voidUser = null;
		
		boolean userFound = false;
		int counter = 0;
		
		// Execution
		while(counter < users.size() && userFound == false) {
			aUser = users.get(counter);
			if(aUser.getDevice().getIdBluetooth().equals(device.getIdBluetooth())) {
				userFound = true;
			}
		}
		retrieveUser = DAO.getTheUserLinkedToTheDevice(device.getIdBluetooth());
		voidUser = DAO.getTheUserLinkedToTheDevice("unexistant");
		
		// Verification
		assertThat(aUser.toString(), equalTo(retrieveUser.toString()));
		assertNull(voidUser);
    }
}
