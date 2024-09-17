import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class for all smart devices
abstract class SmartDevice {
    private String deviceId; // Private data member for encapsulation
    private boolean status; // Private data member for device status

    private static int totalDevices = 0; // Private static member to track total devices
    private static int devicesOn = 0; // Private static member to track devices that are ON

    // Constructor to initialize SmartDevice with a deviceId and default status
    // (off)
    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
        this.status = false; // Default to off
        totalDevices++;
    }

    // Public getter to access the device ID
    public String getDeviceId() {
        return this.deviceId;
    }

    // Public method to check if the device is ON
    public boolean isOn() {
        return this.status;
    }

    // Public method to turn on the device and update the count of devices ON
    public void turnOn() {
        if (!this.status) {
            this.status = true;
            devicesOn++;
        }
        System.out.println(this.deviceId + " is now ON.");
    }

    // Public method to turn off the device and update the count of devices ON
    public void turnOff() {
        if (this.status) {
            this.status = false;
            devicesOn--;
        }
        System.out.println(this.deviceId + " is now OFF.");
    }

    // Abstract method for performing specific functions, to be implemented by
    // subclasses
    public abstract void performFunction();

    // Static public method to get total devices across all systems
    public static int getTotalDevices() {
        return totalDevices;
    }

    // Static public method to get the count of devices currently ON
    public static int getDevicesOn() {
        return devicesOn;
    }
}

// SmartLight class inheriting from SmartDevice
class SmartLight extends SmartDevice {
    private int brightness; // Private data member for brightness

    // Constructor for SmartLight with default brightness
    public SmartLight(String deviceId) {
        super(deviceId);
        this.brightness = 50; // Default brightness set to 50%
    }

    // Public getter for brightness
    public int getBrightness() {
        return brightness;
    }

    // Public setter to modify brightness
    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println(this.getDeviceId() + " brightness set to " + this.brightness + "%.");
    }

    // Implementing the abstract method performFunction
    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is providing light at " + this.brightness + "% brightness.");
        } else {
            System.out.println(this.getDeviceId() + " is off, no light.");
        }
    }
}

// SmartThermostat class inheriting from SmartDevice
class SmartThermostat extends SmartDevice {
    private int temperature; // Private data member for temperature

    // Constructor for SmartThermostat with default temperature
    public SmartThermostat(String deviceId) {
        super(deviceId);
        this.temperature = 20; // Default temperature set to 20°C
    }

    // Public getter for temperature
    public int getTemperature() {
        return this.temperature;
    }

    // Public setter to modify temperature
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println(this.getDeviceId() + " temperature set to " + this.temperature + " degrees.");
    }

    // Implementing the abstract method performFunction
    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is regulating temperature to " + this.temperature + " degrees.");
        } else {
            System.out.println(this.getDeviceId() + " is off, not regulating temperature.");
        }
    }
}

// Home Automation System class to manage multiple smart devices
class HomeAutomationSystem {
    private List<SmartDevice> devices; // Private data member to store devices
    private static int totalDevicesInSystem = 0; // Private static data member to track total devices in system

    // Constructor to initialize the HomeAutomationSystem
    public HomeAutomationSystem() {
        this.devices = new ArrayList<>();
    }

    // Public method to add a device to the system
    public void addDevice(SmartDevice device) {
        this.devices.add(device);
        totalDevicesInSystem++;
        System.out.println(device.getDeviceId() + " added to the home automation system.");
    }

    // Public method to remove a device from the system
    public void removeDevice(SmartDevice device) {
        this.devices.remove(device);
        totalDevicesInSystem--;
        System.out.println(device.getDeviceId() + " removed from the home automation system.");
    }

    // Public method to execute the morning routine (turn on certain devices)
    public void executeMorningRoutine() {
        System.out.println("Executing Morning Routine...");
        for (SmartDevice device : this.devices) {
            if (device instanceof SmartLight || device instanceof SmartThermostat) {
                device.turnOn();
                device.performFunction();
            }
        }
    }

    // Public method to execute away mode (turn off and secure certain devices)
    public void executeAwayMode() {
        System.out.println("Executing Away Mode...");
        for (SmartDevice device : this.devices) {
            if (device instanceof SmartLight || device instanceof SmartSecurityCamera) {
                device.turnOff();
                device.performFunction();
            }
        }
    }

    // Static public method to display a summary of all devices
    public static void getSummary() {
        System.out.println("Total devices across all systems: " + SmartDevice.getTotalDevices());
        System.out.println("Devices that are currently ON: " + SmartDevice.getDevicesOn());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomeAutomationSystem homeSystem = new HomeAutomationSystem();

        SmartDevice[] devicesArray = {
                new SmartLight("Light1"),
                new SmartThermostat("Thermostat")
        };

        // Adding devices to the home automation system
        for (SmartDevice device : devicesArray) {
            homeSystem.addDevice(device);
        }

        // Displaying the summary of devices
        HomeAutomationSystem.getSummary();

        // Interactive menu to execute routines
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Execute Morning Routine");
            System.out.println("2. Execute Away Mode");
            System.out.println("3. Show Summary");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    homeSystem.executeMorningRoutine();
                    break;
                case 2:
                    homeSystem.executeAwayMode();
                    break;
                case 3:
                    HomeAutomationSystem.getSummary();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
