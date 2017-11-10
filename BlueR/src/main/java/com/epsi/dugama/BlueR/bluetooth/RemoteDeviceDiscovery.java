package com.epsi.dugama.BlueR.bluetooth;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.*;

import com.intel.bluetooth.RemoteDeviceHelper;

/**
 * Minimal Device Discovery example.
 */
public class RemoteDeviceDiscovery {

    public static final Vector/*<RemoteDevice>*/ devicesDiscovered = new Vector();

    public static void main(String[] args) throws IOException, InterruptedException {

        final Object inquiryCompletedEvent = new Object();

        devicesDiscovered.clear();

        DiscoveryListener listener = new DiscoveryListener() {

			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
				System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
				int rssi = 0;
				devicesDiscovered.addElement(btDevice);
				try {
					rssi = RemoteDeviceHelper.readRSSI(btDevice); //risque de donner une exception
					System.out.println("name " + btDevice.getFriendlyName(false));

					System.out.println(rssi);
					
				} catch (IOException cantGetDeviceName) {
					cantGetDeviceName.printStackTrace();
				}
			}

            public void inquiryCompleted(int discType) {
                System.out.println("Device Inquiry completed!");
                synchronized(inquiryCompletedEvent){
                    inquiryCompletedEvent.notifyAll();
                }
            }

            public void serviceSearchCompleted(int transID, int respCode) {
            }

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            }
        };

        synchronized(inquiryCompletedEvent) {
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
            if (started) {
                System.out.println("wait for device inquiry to complete...");
                inquiryCompletedEvent.wait();
                System.out.println(devicesDiscovered.size() +  " device(s) found");
            }
        }
    }

}