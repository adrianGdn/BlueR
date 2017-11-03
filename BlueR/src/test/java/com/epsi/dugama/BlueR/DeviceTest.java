package com.epsi.dugama.BlueR;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class DeviceTest {
	/**
	 * A test which check the getters of the User class with simple values.
	 */
	@Test
	public void deviceSimpleGettersTest() {
		// Preparation
		Device device = new Device("Device name", "Bluetooth id", "Mail address");
		Device device2 = new Device("Device name", "Bluetooth id");
		// Begin
			// Gets tests
		assertThat(device.getDeviceName(), equalTo("Device name"));
		assertThat(device2.getDeviceName(), equalTo("Device name"));
		assertThat(device.getIdBluetooth(), equalTo("Bluetooth id"));
		assertThat(device2.getIdBluetooth(), equalTo("Bluetooth id"));
		assertThat(device.getMailAddress(), equalTo("Mail address"));
		assertThat(device2.getMailAddress(), equalTo(null));
			// toString test
		assertThat(device.toString(), equalTo("Le nom de l'appareil est : Device name, l'adresse mail associé à cette appareil est : Mail address, l'identifiant BLuetooth de cette appareil est : Bluetooth id"));
		assertThat(device2.toString(), equalTo("Le nom de l'appareil est : Device name, l'adresse mail associé à cette appareil est : null, l'identifiant BLuetooth de cette appareil est : Bluetooth id"));
		// End
	}
}
