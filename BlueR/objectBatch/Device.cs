using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/// <summary>
/// objectBatch will contain all the object that we will need for this software.
/// </summary>
namespace objectBatch
{
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
        /// <param name="idDevice">The id of the device, for find it. It's an int.</param>
        /// <param name="deviceName">The name of the device, for show it in a form. It's a string.</param>
        /// <param name="idBluetooth">The Bluetooth ID of the device, for send differents things. It's a string.</param>
        /// <param name="addressMail">The mail address of the user of the device. This is a string.</param>
        public Device(int idDevice, string deviceName, string idBluetooth, string addressMail)
        {
            this.setIdDevice(idDevice);
            this.setDeviceName(deviceName);
            this.setIdBluetooth(idBluetooth);
            this.setAddressMail(addressMail);
        }
        #endregion
        #region Getters
        /// <summary>
        /// Allow you to get the device ID.
        /// </summary>
        /// <returns>Return the current ID of that device. It's an int.</returns>
        public int getIdDevice()
        {
            return this.idDevice;
        }
        /// <summary>
        /// Allow you to get the device name.
        /// </summary>
        /// <returns>Return the current name of that device. It's a string.</returns>
        public string getDeviceName()
        {
            return this.deviceName;
        }
        /// <summary>
        /// Allow you to get the device Bluetooth ID.
        /// </summary>
        /// <returns>Return the current Bluetooth ID of that device. It's a string.</returns>
        public string getIdBluetooth()
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
        /// <param name="idDevice">The id of the device, for find it. It's an int.</param>
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
        /// Allow you to set the device Bluetooth ID.
        /// </summary>
        /// <param name="idBluetooth">The Bluetooth ID of the device, for send different things. It's a string.</param>
        public void setIdBluetooth(string idBluetooth)
        {
            this.idBluetooth = idBluetooth;
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
        /// <summary>
        /// Allow you to get a string that contain few attribute of that class.
        /// </summary>
        /// <returns>Return a sentence with few attribute.</returns>
        public override string ToString()
        {
            string msg;
            msg = "Le nom de l'appareil est : " + this.getDeviceName()
                + ", l'adresse mail associé à cette appareil est : " + this.getIdBluetooth()
                + ", l'identifiant BLuetooth de cette appareil est : ";            
            return msg;
        }
        #endregion
    }
}
