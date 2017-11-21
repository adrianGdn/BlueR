package com.epsi.dugama.BlueR.bluetooth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DataElement;
import com.epsi.dugama.BlueR.Device;

import com.intel.bluetooth.RemoteDeviceHelper;

public class DeviceDiscovery implements DiscoveryListener {

	public static final ArrayList<RemoteDevice> bluetoothDevicesDiscovered = new ArrayList<RemoteDevice>();
	public static final ArrayList<Device> devicesDiscovered = new ArrayList<Device>();

	private static Object lock = new Object();

	public static void main(String[] args) {
		init();
	}
	
	
	public static void init()
	{
		try {
			// 1
			LocalDevice localDevice = LocalDevice.getLocalDevice();
			// 2
			DiscoveryAgent agent = localDevice.getDiscoveryAgent();
			// 3
			DiscoveryListener listener = new DeviceDiscovery();
			agent.startInquiry(DiscoveryAgent.GIAC, listener);

			try {
				synchronized (lock) {
					lock.wait();
				}
			}

			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Device Inquiry Completed. ");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Device> getDeviceDiscovered()
	{
		return devicesDiscovered;
	}

	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {

		String name;
		try {
			name = btDevice.getFriendlyName(false);
			devicesDiscovered.add(new Device(name, btDevice.getBluetoothAddress()));
			bluetoothDevicesDiscovered.add(btDevice);
			System.out.println("device found: " + name + "");
		} catch (Exception e) {
			name = btDevice.getBluetoothAddress();
			e.printStackTrace();
		}
		
		//rssi = RemoteDeviceHelper.readRSSI(btDevice); //risque de donner une exception
		//System.out.println("device found: " + name + " rssi " + rssi);
	}

	public void inquiryCompleted(int arg0) {
		synchronized (lock) {
			lock.notify();
		}

	}

	public void serviceSearchCompleted(int arg0, int arg1) {
		synchronized (lock) {
			lock.notify();
		}
	}

	public void servicesDiscovered(int arg0, ServiceRecord[] services) {
		for (int i = 0; i < services.length; i++) {
			String url = services[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
			if (url == null) {
				continue;
			}

			DataElement serviceName = services[i].getAttributeValue(0x0100);
			try {
				System.out.println( "rssi" + RemoteDeviceHelper.readRSSI(services[i].getHostDevice())); //risque de donner une exception
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (serviceName != null) {
				System.out.println("service " + serviceName.getValue() + " found " + url  +" ID: " +
						services[i].getAttributeIDs());
			} else {
				System.out.println("service found " + url + " ID: " +
						services[i].getAttributeIDs());
			}

			if (serviceName.getValue().equals("OBEX Object Push")) {
				sendMessageToDevice(url);
			}
		}

	}

	private static void sendMessageToDevice(String serverURL) {
		try {
			System.out.println("Connecting to " + serverURL);

			ClientSession clientSession = (ClientSession) Connector.open(serverURL);
			HeaderSet hsConnectReply = clientSession.connect(null);
			if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
				System.out.println("Failed to connect");
				return;
			}

			HeaderSet hsOperation = clientSession.createHeaderSet();
			hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
			hsOperation.setHeader(HeaderSet.TYPE, "text");

			// Create PUT Operation
			Operation putOperation = clientSession.put(hsOperation);

			// Sending the message
			byte data[] = "Hello World !!!".getBytes("iso-8859-1");
			OutputStream os = putOperation.openOutputStream();
			os.write(data);
			os.close();

			putOperation.close();
			clientSession.disconnect(null);
			clientSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	double getBTdistance(int RSSI, int txPower) {
		return Math.pow(10d, ((double) txPower - RSSI) / (10 * 2));
	}

	// @Override
	public void deviceDiscoveredOld(RemoteDevice btDevice, DeviceClass cod) {
		try {
			System.out.println("Appareil : " + btDevice.getFriendlyName(true) + " ID: " + btDevice.getBluetoothAddress());
			// System.out.println(RemoteDeviceHelper.readRSSI(btDevice));
			// System.out.println("Appareil : " + btDevice.getFriendlyName(true) + " ID: " +
			// btDevice.getBluetoothAddress() );
			// System.out.println("Appareil : "+ btDevice.get) );
			bluetoothDevicesDiscovered.add(btDevice);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void servicesDiscoveredOld(int transID, ServiceRecord[] servRecord) {
		System.out.println("servicesDiscovered");
	}

	public void serviceSearchCompletedOld(int transID, int respCode) {
		System.out.println("serviceSearchCompleted");
	}
	/*
	public void inquiryCompletedOld(int discType) {
		System.out.println("Recherche termin� !");
		synchronized (BluetoothHandler.inquiryCompletedEvent) {
			BluetoothHandler.inquiryCompletedEvent.notifyAll();
		}
	} */

}
