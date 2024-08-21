import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class for all smart devices
abstract class SmartDevice {
    private String deviceId;
    private boolean status;

    private static int totalDevices = 0;
    private static int devicesOn = 0;

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

    // Static methods to access the static variables
    public static int getTotalDevices() {
        return totalDevices;
    }

    public static int getDevicesOn() {
        return devicesOn;
    }
}

// Smart Light class inheriting from SmartDevice
class SmartLight extends SmartDevice {
    public SmartLight(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is providing light.");
        } else {
            System.out.println(this.getDeviceId() + " is off, no light.");
        }
    }
}

// Smart Thermostat class inheriting from SmartDevice
class SmartThermostat extends SmartDevice {
    private int temperature;

    public SmartThermostat(String deviceId) {
        super(deviceId);
        this.temperature = 20; // Default temperature
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
}

// Smart Security Camera class inheriting from SmartDevice
class SmartSecurityCamera extends SmartDevice {
    public SmartSecurityCamera(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is monitoring and recording activity.");
        } else {
            System.out.println(this.getDeviceId() + " is off, not monitoring.");
        }
    }
}

// Smart Door Lock class inheriting from SmartDevice
class SmartDoorLock extends SmartDevice {
    public SmartDoorLock(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is locked.");
        } else {
            System.out.println(this.getDeviceId() + " is unlocked.");
        }
    }
}

// Smart Appliance class inheriting from SmartDevice
class SmartAppliance extends SmartDevice {
    public SmartAppliance(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " is operating.");
        } else {
            System.out.println(this.getDeviceId() + " is off, not operating.");
        }
    }
}

// Home Automation System class
class HomeAutomationSystem {
    private List<SmartDevice> devices;

    public HomeAutomationSystem() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        this.devices.add(device);
        System.out.println(device.getDeviceId() + " added to the home automation system.");
    }

    public void removeDevice(SmartDevice device) {
        this.devices.remove(device);
        System.out.println(device.getDeviceId() + " removed from the home automation system.");
    }

    public void executeMorningRoutine() {
        System.out.println("Executing Morning Routine...");
        for (SmartDevice device : this.devices) {
            if (device instanceof SmartLight || device instanceof SmartThermostat || device instanceof SmartAppliance) {
                device.turnOn();
                device.performFunction();
            }
        }
    }

    public void executeAwayMode() {
        System.out.println("Executing Away Mode...");
        for (SmartDevice device : this.devices) {
            if (device instanceof SmartLight || device instanceof SmartDoorLock
                    || device instanceof SmartSecurityCamera) {
                device.turnOff();
                device.performFunction();
            }
            if (device instanceof SmartDoorLock || device instanceof SmartSecurityCamera) {
                device.turnOn();
                device.performFunction();
            }
        }
    }

    public void executeNightRoutine() {
        System.out.println("Executing Night Routine...");
        for (SmartDevice device : this.devices) {
            if (device instanceof SmartLight || device instanceof SmartThermostat
                    || device instanceof SmartSecurityCamera) {
                device.turnOff();
                device.performFunction();
            }
            if (device instanceof SmartSecurityCamera) {
                device.turnOn();
                device.performFunction();
            }
        }
    }

    public void executeSecurityAlert() {
        System.out.println("Executing Security Alert...");
        for (SmartDevice device : this.devices) {
            if (device instanceof SmartDoorLock || device instanceof SmartSecurityCamera) {
                device.turnOn();
                device.performFunction();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomeAutomationSystem homeSystem = new HomeAutomationSystem();
        SmartDevice[] devicesArray = {
                new SmartLight("Light1"),
                new SmartThermostat("Thermostat"),
                new SmartSecurityCamera("Camera"),
                new SmartDoorLock("DoorLock"),
                new SmartAppliance("Appliance")
        };

        for (int i = 0; i < devicesArray.length; i++) {
            homeSystem.addDevice(devicesArray[i]);
        }

        System.out.println("Total devices: " + SmartDevice.getTotalDevices());
        System.out.println("Devices that are ON: " + SmartDevice.getDevicesOn());

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Execute Morning Routine");
            System.out.println("2. Execute Away Mode");
            System.out.println("3. Execute Night Routine");
            System.out.println("4. Execute Security Alert");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    homeSystem.executeMorningRoutine();
                    break;
                case 2:
                    homeSystem.executeAwayMode();
                    break;
                case 3:
                    homeSystem.executeNightRoutine();
                    break;
                case 4:
                    homeSystem.executeSecurityAlert();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Devices that are ON: " + SmartDevice.getDevicesOn());
        }
    }
}
