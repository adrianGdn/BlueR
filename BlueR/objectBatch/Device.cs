using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/// <summary>
/// objectBatch will contain all the object that we will need for this software.
/// </summary>
namespace objectBatch
{//New Branch
    /// <summary>
    /// Device is an object that will register informations about a device connected to this software in Bluetooth.
    /// </summary>
    public class Device
    {
        int idDevice;
        string deviceName;
        string idBluetooth;
        string addressMail;


        #region Constructors
        /// <summary>
        /// Allow you to create a Device object.
        /// </summary>
        /// <param name="idDevice">The id of the device, for find it. Is an int.</param>
        /// <param name="deviceName">The name of the device, for show it in a form. Is a string.</param>
        /// <param name="idBluetooth">The address of the device, for send differents things. Is a string.</param>
        /// <param name="addressMail">The mail address of the user of the device. This is a string.</param>
        public Device(int idDevice, string deviceName, string idBluetooth, string addressMail)
        {
            this.setIdDevice(idDevice);
            this.setDeviceName(deviceName);
            this.setDeviceAddress(idBluetooth);
            this.setAddressMail(addressMail);
        }
        #endregion
        #region Getters
        /// <summary>
        /// Allow you to get the device id.
        /// </summary>
        /// <returns>Return the current ID of that device. Is an int.</returns>
        public int getIdDevice()
        {
            return this.idDevice;
        }
        /// <summary>
        /// Allow you to get the device name.
        /// </summary>
        /// <returns>Return the current name of that device. Is a string.</returns>
        public string getDeviceName()
        {
            return this.deviceName;
        }
        /// <summary>
        /// Allow you to get the device address.
        /// </summary>
        /// <returns>Return the current address of that device. Is a string.</returns>
        public string getDeviceAddress()
        {
            return this.idBluetooth;
        }
        /// <summary>
        /// Allow you to get the user mail address of the device.
        /// </summary>
        /// <returns>The mail address of the user of the device. This is a string.</returns>
        public string getAddressMail()
        {
            return this.addressMail;
        }
        #endregion
        #region Setters
        /// <summary>
        /// Allow you to set the device ID.
        /// </summary>
        /// <param name="idDevice">The id of the device, for find it. Is an int.</param>
        protected void setIdDevice(int idDevice)
        {
            this.idDevice = idDevice;
        }
        /// <summary>
        /// Allow you to set the device name.
        /// </summary>
        /// <param name="deviceName">The name of the device, for show it in a form. Is a string.</param>
        public void setDeviceName(string deviceName)
        {
            this.deviceName = deviceName;
        }
        /// <summary>
        /// Allow you to set the device address.
        /// </summary>
        /// <param name="deviceAddress">The address of the device, for send different things. Is a string.</param>
        public void setDeviceAddress(string deviceAddress)
        {
            this.idBluetooth = deviceAddress;
        }
        /// <summary>
        /// Allow you to set the user mail address of the device.
        /// </summary>
        /// <param name="addressMail">The mail address of the user of the device. This is a string.</param>
        public void setAddressMail(string addressMail)
        {
            this.addressMail = addressMail;
        }
        #endregion
        #region Methods
        #endregion
    }
}
