package com.epsi.dugama.BlueR;

/**
 * Device is an object that will register informations about a device connected to this software in Bluetooth.
 * @author Adrian Gandon
 */
public class Device {
	private String deviceName;
	private String idBluetooth;
	
	
	@Override
	public String toString() {
		return "Le nom de l'appareil est : " + this.getDeviceName()
                + ", l'identifiant BLuetooth de cette appareil est : "+ this.getIdBluetooth();
	}
	
	/**
	 * Allow you to create a Device object.
	 * @param deviceName The name of the device, for show it in a form. It's a string.
	 * @param idBluetooth The Bluetooth ID of the device, for send different things. It's a string.
	 */
	public Device(String deviceName, String idBluetooth) {
		this.setDeviceName(deviceName);
		this.setIdBluetooth(idBluetooth);
	}
	
	/**
	 * Allow you to get the device name.
	 * @return Return the current name of that device. It's a string.
	 */
	public String getDeviceName() {
		return this.deviceName;
	}
	
	/**
	 * Allow you to get the device Bluetooth ID.
	 * @return Return the current Bluetooth ID of that device. It's a string.
	 */
	public String getIdBluetooth() {
		return this.idBluetooth;
	}
	
	/**
	 * Allow you to set the device name.
	 * @param deviceName The name of the device, for show it in a form. Is a string.
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	/**
	 * Allow you to set the device Bluetooth ID.
	 * @param idBluetooth The Bluetooth ID of the device, for send different things. It's a string.
	 */
	public void setIdBluetooth(String idBluetooth) {
		this.idBluetooth = idBluetooth;
	}
}
