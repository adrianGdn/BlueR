package com.epsi.dugama.BlueR.bluetooth;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;

public class BluetoothHandler {

    public static Object inquiryCompletedEvent = new Object();

    public BluetoothHandler() {
        
    }

    /**
     * search the local device with blueCove
     * call this method on main to begin the scan
     */
    void start() {
        DiscoveryListener listener = new DeviceDiscovery();

        synchronized (inquiryCompletedEvent) {
            try {
                boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
                if (started) {
                    System.out.println("Recher en cours...");
                    inquiryCompletedEvent.wait();
                }
            } catch (BluetoothStateException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
}
