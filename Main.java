import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class for all smart devices
abstract class SmartDevice {
    private String deviceId;
    private boolean status;

    private static int totalDevices = 0;
    private static int devicesOn = 0;

    // Parameterized constructor for SmartDevice
    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
        this.status = false; // Default to off
        totalDevices++;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public boolean isOn() {
        return this.status;
    }

    public void turnOn() {
        if (!this.status) {
            this.status = true;
            devicesOn++;
        }
        System.out.println(this.deviceId + " is now ON.");
    }

    public void turnOff() {
        if (this.status) {
            this.status = false;
            devicesOn--;
        }
        System.out.println(this.deviceId + " is now OFF.");
    }

    public abstract void performFunction();

    // New abstract method for status report
    public abstract String getStatusReport();

    public static int getTotalDevices() {
        return totalDevices;
    }

    public static int getDevicesOn() {
        return devicesOn;
    }
}

class SmartLight extends SmartDevice {
    private int brightness;

    // Default constructor
    public SmartLight() {
        super("DefaultLight");
        this.brightness = 50;
    }

    // Parameterized constructor
    public SmartLight(String deviceId) {
        super(deviceId);
        this.brightness = 50;
    }

    public int getBrightness() {
        return brightness;
    }

    // Original method to set brightness by percentage
    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println(this.getDeviceId() + " brightness set to " + this.brightness + "%.");
    }

    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is providing light at " + this.brightness + "% brightness.");
        } else {
            System.out.println(this.getDeviceId() + " is off, no light.");
        }
    }

    @Override
    public String getStatusReport() {
        return "SmartLight [" + this.getDeviceId() + "] - Brightness: " + this.brightness + "%";
    }
}

// SmartThermostat class inheriting from SmartDevice
class SmartThermostat extends SmartDevice {
    private int temperature;

    // Default constructor for SmartThermostat
    public SmartThermostat() {
        super("DefaultThermostat"); // Default deviceId
        this.temperature = 20; // Default temperature set to 20°C
    }

    // Parameterized constructor for SmartThermostat
    public SmartThermostat(String deviceId) {
        super(deviceId);
        this.temperature = 20; // Default temperature set to 20°C
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println(this.getDeviceId() + " temperature set to " + this.temperature + " degrees.");
    }

    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is regulating temperature to " + this.temperature + " degrees.");
        } else {
            System.out.println(this.getDeviceId() + " is off, not regulating temperature.");
        }
    }

    @Override
    public String getStatusReport() {
        return "SmartThermostat [" + this.getDeviceId() + "] - Temperature: " + this.temperature + "°C";
    }
}

// SmartSpeaker class inheriting from SmartDevice (New class for hierarchical
// inheritance)
class SmartSpeaker extends SmartDevice {
    private int volume;

    // Default constructor for SmartSpeaker
    public SmartSpeaker() {
        super("DefaultSpeaker"); // Default deviceId
        this.volume = 50; // Default volume set to 50%
    }

    // Parameterized constructor for SmartSpeaker
    public SmartSpeaker(String deviceId) {
        super(deviceId);
        this.volume = 50; // Default volume set to 50%
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(this.getDeviceId() + " volume set to " + this.volume + "%.");
    }

    @Override
    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is playing music at " + this.volume + "% volume.");
        } else {
            System.out.println(this.getDeviceId() + " is off, not playing any music.");
        }
    }

    @Override
    public String getStatusReport() {
        return "SmartSpeaker [" + this.getDeviceId() + "] - Volume: " + this.volume + "%";
    }
}

// Home Automation System class to manage multiple smart devices
class HomeAutomationSystem {
    private List<SmartDevice> devices;
    private static int totalDevicesInSystem = 0;

    // Constructor to initialize the HomeAutomationSystem
    public HomeAutomationSystem() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        this.devices.add(device);
        totalDevicesInSystem++;
        System.out.println(device.getDeviceId() + " added to the home automation system.");
    }

    public void removeDevice(SmartDevice device) {
        this.devices.remove(device);
        totalDevicesInSystem--;
        System.out.println(device.getDeviceId() + " removed from the home automation system.");
    }

    public void executeMorningRoutine() {
        System.out.println("Executing Morning Routine...");
        for (SmartDevice device : this.devices) {
            device.turnOn();
            device.performFunction();
            System.out.println(device.getStatusReport()); // New behavior
        }
    }

    public void executeAwayMode() {
        System.out.println("Executing Away Mode...");
        for (SmartDevice device : this.devices) {
            device.turnOff();
            System.out.println(device.getStatusReport()); // New behavior
        }
    }

    public static void getSummary() {
        System.out.println("Total devices across all systems: " + SmartDevice.getTotalDevices());
        System.out.println("Devices that are currently ON: " + SmartDevice.getDevicesOn());
    }
}

// Main method for testing
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomeAutomationSystem homeSystem = new HomeAutomationSystem();

        // Using parameterized constructors
        SmartDevice light = new SmartLight("LivingRoomLight");
        SmartDevice thermostat = new SmartThermostat("BedroomThermostat");
        SmartDevice speaker = new SmartSpeaker("LivingRoomSpeaker"); // New SmartSpeaker

        // Using default constructors
        SmartDevice defaultLight = new SmartLight();
        SmartDevice defaultThermostat = new SmartThermostat();
        SmartDevice defaultSpeaker = new SmartSpeaker(); // New default speaker

        // Adding devices to the system
        homeSystem.addDevice(light);
        homeSystem.addDevice(thermostat);
        homeSystem.addDevice(speaker); // Added SmartSpeaker
        homeSystem.addDevice(defaultLight);
        homeSystem.addDevice(defaultThermostat);
        homeSystem.addDevice(defaultSpeaker); // Added default SmartSpeaker

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
