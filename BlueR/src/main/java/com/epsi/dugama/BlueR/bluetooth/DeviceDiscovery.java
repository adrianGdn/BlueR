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
import javax.bluetooth.UUID;
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

	public static  ArrayList<RemoteDevice> bluetoothDevicesDiscovered;
	public static  ArrayList<Device> devicesDiscovered = new ArrayList<Device>();
	private static final UUID OBEX_OBJECT_PUSH = null;

	private static Object lock = new Object();

	public static void main(String[] args) {
		init();
	}
	
	public  DeviceDiscovery()
	{
		bluetoothDevicesDiscovered = new ArrayList<RemoteDevice>();
	}
	
	/**
	 * init the device discovery
	 */
	public static void init()
	{
		DeviceDiscovery listener = new DeviceDiscovery();
		
		bluetoothDevicesDiscovered.clear();
		devicesDiscovered.clear();
		try {
			LocalDevice localDevice = LocalDevice.getLocalDevice();
			DiscoveryAgent agent = localDevice.getDiscoveryAgent();
			UUID[] searchUuidSet = new UUID[1] ;
			searchUuidSet[0] = new UUID(0x1105);
		    int[] attrIDs =  new int[] {
		           0x0100 // Service name
		    };
			
			System.out.println("device inquiry started");
			agent.startInquiry(DiscoveryAgent.GIAC, listener);
			
			try {
				synchronized (lock) {
					lock.wait();
				}
			}

			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			

			for(RemoteDevice device : listener.bluetoothDevicesDiscovered) {
				agent.searchServices(attrIDs, searchUuidSet, device, listener); 
				try {
					synchronized (lock) {
						lock.wait();
					}
				}

				catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("Device Inquiry Completed. ");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return array {@link DeviceDiscovery}
	 */
	public static ArrayList<Device> getDeviceDiscovered()
	{
		return devicesDiscovered;
	}
	
	/**
	 * Automatically called when a device is discovered
	 */
	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {

		String name;
		try {
			name = btDevice.getFriendlyName(false);
			devicesDiscovered.add(new Device(name, btDevice.getBluetoothAddress()));
			bluetoothDevicesDiscovered.add(btDevice);
			System.out.println("device found: " + name + "");
			//sendMessageToDevice("btgoep://"+btDevice.getBluetoothAddress() + ":2");
			//testConnexion(btDevice);
		} catch (Exception e) {
			name = btDevice.getBluetoothAddress();
			e.printStackTrace();
		}
		
		//rssi = RemoteDeviceHelper.readRSSI(btDevice); //risque de donner une exception
		//System.out.println("device found: " + name + " rssi " + rssi);
	}
	
	/**
	 * used when the device search is finished 
	 */
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
	
	/**
	 * 
	 */
	public void servicesDiscovered(int arg0, ServiceRecord[] services) {
		for (int i = 0; i < services.length; i++) {
			String url = services[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
			if (url == null) {
				continue;
			}

			DataElement serviceName = services[i].getAttributeValue(0x0100);
		/*	try {
				System.out.println( "rssi" + RemoteDeviceHelper.readRSSI(services[i].getHostDevice())); //risque de donner une exception
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
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
	
	public static void testConnexion(RemoteDevice btDevice)
	{
		UUID serviceUUID = OBEX_OBJECT_PUSH;
		final Object serviceSearchCompletedEvent = new Object();
		DiscoveryListener listener = new DeviceDiscovery();
		UUID[] searchUuidSet = new UUID[] { serviceUUID };
        int[] attrIDs =  new int[] {
                0x0100 // Service name
        };
		
		synchronized(serviceSearchCompletedEvent) {
            System.out.println("search services on " + btDevice.getBluetoothAddress());
            try {
				LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
			} catch (BluetoothStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				serviceSearchCompletedEvent.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	/**
	 * send a message to the device
	 * @param serverURL the server URL (bluetooth id ? )
	 */
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
			System.out.println("no connexion... ");
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
