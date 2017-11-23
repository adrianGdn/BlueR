package com.epsi.dugama.BlueR;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;

public class DeviceTest {
	/**
	 * A test which check the getters of the Device class with simple values.
	 */
	@Test
	public void deviceSimpleGettersTest() {
		// Preparation
		Device device1 = new Device("Device name", "Bluetooth id");
		// Begin
			// Gets tests
		assertThat(device1.getDeviceName(), equalTo("Device name"));
		assertThat(device1.getIdBluetooth(), equalTo("Bluetooth id"));
			// toString test
		assertThat(device1.toString(), equalTo("Le nom de l'appareil est : Device name, l'identifiant BLuetooth de cette appareil est : Bluetooth id"));
		// End
	}
}
