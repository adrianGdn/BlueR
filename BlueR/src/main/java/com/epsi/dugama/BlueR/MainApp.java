package com.epsi.dugama.BlueR;

/**
 * Hello world!
 *
 */
public class MainApp 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        // Test connection
        DAO.testCoBDD();
        Device aTestDevice = new Device("Name", "123", "mail");
        DAO.addDevice(aTestDevice);
        System.out.println(DAO.getOneDevice(aTestDevice.getIdBluetooth()));
        DAO.deleteOneDevice(aTestDevice.getIdBluetooth());
    }
}
