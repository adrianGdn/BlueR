package com.epsi.dugama.BlueR.bluetooth;

import java.io.IOException;
import java.util.ArrayList;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import com.epsi.dugama.BlueR.bluetooth.BluetoothHandler;
import javax.bluetooth.BluetoothStateException;
import com.intel.bluetooth.RemoteDeviceHelper;

public class DeviceDiscovery implements DiscoveryListener {

    public static final ArrayList<RemoteDevice> devicesDiscovered = new ArrayList<RemoteDevice>();

    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        try {
            System.out.println("Appareil : " + btDevice.getFriendlyName(true) + " ID: " + btDevice.getBluetoothAddress() );
            //System.out.println(RemoteDeviceHelper.readRSSI(btDevice));
            //System.out.println("Appareil : " + btDevice.getFriendlyName(true) + " ID: " + btDevice.getBluetoothAddress() );
            //System.out.println("Appareil : "+ btDevice.get) );
            devicesDiscovered.add(btDevice);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        System.out.println("servicesDiscovered");
    }

    public void serviceSearchCompleted(int transID, int respCode) {
        System.out.println("serviceSearchCompleted");
    }

    public void inquiryCompleted(int discType) {
        System.out.println("Recherche terminé !");
        synchronized (BluetoothHandler.inquiryCompletedEvent) {
            BluetoothHandler.inquiryCompletedEvent.notifyAll();
        }
    }
    
    double getBTdistance(int RSSI, int txPower) {
        return Math.pow(10d, ((double) txPower - RSSI) / (10 * 2));
    }

	
}
