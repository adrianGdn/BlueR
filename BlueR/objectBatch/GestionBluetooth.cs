using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Bluetooth;
using Bluetooth.Services;
using InTheHand.Net;
using InTheHand.Net.Sockets;

namespace objectBatch
{
    public class GestionBluetooth
    {
        public static List<Device> Scan()
        {
            List<Device> listDevice = new List<Device>();
            var cli = new BluetoothClient();
            BluetoothDeviceInfo[] peers = cli.DiscoverDevices();
            //BluetoothDeviceInfo[] peersInRange = cli.DiscoverDevicesInRange();
            //BluetoothDeviceInfo[] peersInRange2 = cli.DiscoverDevices(255, false, false, false, true);
            foreach (BluetoothDeviceInfo peer in peers)
            {
                if(peer.Remembered) //mettre a faux pour avoir la liste réelle.
                {
                    //@toDo : changer l'id du Device 
                    listDevice.Add(new Device(42, peer.DeviceName, peer.DeviceAddress.ToString(), "test@test.com"));
                }
            }
            return listDevice;
        }
    }
}
