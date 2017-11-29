package com.epsi.dugama.BlueR;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserTest {
	/**
	 * A test which check the getters of the User class with simple values.
	 */
	@Test
	public void userSimpleGettersTest() {
		// Preparation
		Device device1 = new Device("Device name", "Bluetooth id");
		User user1 = new User("Name", "2ndName", device1, "name.2ndName@gmail.com", "0623232323");
		User user2 = new User("Name", "2ndName", device1);
		// Begin
			// Gets tests
		assertThat(user1.getFirstName(), equalTo("Name"));
		assertThat(user1.getSecondName(), equalTo("2ndName"));
		assertThat(user1.getMailAddress(), equalTo("name.2ndName@gmail.com"));
		assertThat(user1.getNbMobile(), equalTo("0623232323"));
		assertThat(user1.getDevice(), equalTo(device1));
		assertThat(user2.getFirstName(), equalTo("Name"));
		assertThat(user2.getSecondName(), equalTo("2ndName"));
		assertThat(user2.getMailAddress(), equalTo(null));
		assertThat(user2.getNbMobile(), equalTo(null));
		assertThat(user2.getDevice(), equalTo(device1));
			// toString test
		assertThat(user1.toString(), equalTo("Cet utilisateur se nomme : 2ndName Name et le nom de son appareil est : Device name."));
		assertThat(user2.toString(), equalTo("Cet utilisateur se nomme : 2ndName Name et le nom de son appareil est : Device name."));
		// End
	}
}
