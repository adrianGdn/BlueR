package com.epsi.dugama.BlueR;

/**
 * User is an object that will register informations about a user registered to a device with the Bluetooth ID of it.
 * @author Adrian Gandon
 */
public class User {
	private String firstName;
	private String secondName;
	private String mailAddress;
	private String nbMobile;
	private Device aDevice;
	
	/**
	 * Allow you to create a User object.
	 * 
	 * @param firstName The first name of the user. This is a string.
	 * @param secondName The second name of the user. This is a string.
	 * @param mailAddress The mail address of the user. This is a string.
	 * @param nbMobile This mobile number of the user. This is a string.
	 * @param aDevice The device of this user. This is a Device object.
	 */
	public User(String firstName, String secondName, Device aDevice, String mailAddress, String nbMobile) {
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setMailAddress(mailAddress);
		this.setNbMobile(nbMobile);
		this.setDevice(aDevice);
	}
	
	/**
	 * Allow you to create a User object without mail address and mobile number set.
	 * 
	 * @param firstName The first name of the user. This is a string.
	 * @param secondName The second name of the user. This is a string.
	 * @param aDevice The device of this user. This is a Device object.
	 */
	public User(String firstName, String secondName, Device aDevice) {
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setDevice(aDevice);
	}
	
	
	/**
	 * Allow you to get the first name of that user.
	 * 
	 * @return The first name of the user. This is a string.
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * Allow you to get the second name of that user.
	 * 
	 * @return The second name of the user. This is a string.
	 */
	public String getSecondName() {
		return this.secondName;
	}
	/**
	 * Allow you to get the mail address of that user.
	 * 
	 * @return The mail address of the user. This is a string.
	 */
	public String getMailAddress() {
		return this.mailAddress;
	}
	/**
	 * Allow you to get the mobile number of that user.
	 * 
	 * @return This mobile number of the user. This is a string.
	 */
	public String getNbMobile() {
		return this.nbMobile;
	}
	/**
	 * Allow you to get the device of that user.
	 * 
	 * @return The device of this user. This is a Device object.
	 */
	public Device getDevice() {
		return this.aDevice;
	}
	
	
	/**
	 * Allow you to get the first name of that user.
	 * 
	 * @param firstName The first name of the user. This is a string.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Allow you to set the second name of that user.
	 * 
	 * @param secondName The second name of the user. This is a string.
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	/**
	 * Allow you to set the mail address of that user.
	 * 
	 * @param mailAddress The mail address of the user. This is a string.
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	/**
	 * Allow you to set the mobile number of that user.
	 * 
	 * @param nbMobile This mobile number of the user. This is a string.
	 */
	public void setNbMobile(String nbMobile) {
		this.nbMobile = nbMobile;
	}
	/**
	 * Allow you to set the device of that user.
	 * 
	 * @param aDevice The device of this user. This is a Device object.
	 */
	public void setDevice(Device aDevice) {
		this.aDevice = aDevice;
	}
	
	@Override
	public String toString() {
		return "Cet utilisateur se nomme : " + this.getSecondName() + " " + this.getFirstName() 
				+ " et le nom de son appareil est : " + this.getDevice().getDeviceName() + ".";
	}
	
}
