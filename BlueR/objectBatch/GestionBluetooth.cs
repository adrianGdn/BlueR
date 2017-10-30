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
        public static void Test()
        {
            //var test = IReceiverBluetoothService;
            BluetoothAddress addr = BluetoothAddress.Parse("001122334455");
            var cli = new BluetoothClient();
            BluetoothDeviceInfo[] peers = cli.DiscoverDevices();
            BluetoothDeviceInfo[] peersInRange = cli.DiscoverDevicesInRange();
            BluetoothDeviceInfo[] peersInRange2 = cli.DiscoverDevices(255, false, false, false, true);
            BluetoothDeviceInfo device = null;//= ... select one of peer()...
                                              //BluetoothAddress addr = device.DeviceAddress;

            // Résumé :
            //     Discovers accessible Bluetooth devices, optionally remembered and in-range or
            //     just in-range, and returns their names and addresses.
            //
            // Paramètres :
            //   maxDevices:
            //     The number of in-range devices to find before the inquiry may be stopped early.
            //     The result can contain more than this number of devices.
            //
            //   authenticated:
            //     True to return previously authenticated/paired devices.
            //
            //   remembered:
            //     True to return remembered devices.
            //
            //   unknown:
            //     True to return previously unknown devices.
            //
            //   discoverableOnly:
            //     True to return only the devices that are in range, and in discoverable mode.
            //     See the remarks section.
            //
            // Retourne :
            //     An array of BluetoothDeviceInfo objects describing the devices discovered.
            //
            // Notes :
            //     The discoverableOnly parameter is not supported on the Microsoft stack on WinXP
            //     as the stack there returns the remembered and Device-Inquiry-results already
            //     merged, it is however supported on Windows 7. It is supported on WM/CE and on
            //     Widcomm (both platforms). Note when that flag is set the other related flag values
            //     are ignored.
            //     To remove devices from the list of remembered/authenticated devices use InTheHand.Net.Bluetooth.BluetoothSecurity.RemoveDevice(InTheHand.Net.BluetoothAddress)
        }
    }
}
