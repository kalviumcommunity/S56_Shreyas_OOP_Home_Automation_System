import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SmartDevice {
    private String deviceId;
    private boolean status;

    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
        this.status = false; 
    }

    public String getDeviceId() {
        return this.deviceId; 
    }

    public boolean isOn() {
        return this.status; 
    }

    public void turnOn() {
        this.status = true; // Using 'this' to refer to the current object's status
        System.out.println(this.deviceId + " is now ON."); // Using 'this' to refer to the current object's deviceId
    }

    public void turnOff() {
        this.status = false; // Using 'this' to refer to the current object's status
        System.out.println(this.deviceId + " is now OFF."); // Using 'this' to refer to the current object's deviceId
    }

    public abstract void performFunction();
}

// Smart Light class inheriting from SmartDevice
class SmartLight extends SmartDevice {
    public SmartLight(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) { // Using 'this' to call the current object's method
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
        this.temperature = temperature; // Using 'this' to refer to the current object's temperature
        System.out.println(this.getDeviceId() + " temperature set to " + this.temperature + " degrees."); // Using
                                                                                                          // 'this' to
                                                                                                          // call the
                                                                                                          // current
                                                                                                          // object's
                                                                                                          // method and
                                                                                                          // attribute
    }

    public void performFunction() {
        if (this.isOn()) { // Using 'this' to call the current object's method
            System.out.println(this.getDeviceId() + " is regulating temperature to " + this.temperature + " degrees."); // Using
                                                                                                                        // 'this'
                                                                                                                        // to
                                                                                                                        // call
                                                                                                                        // the
                                                                                                                        // current
                                                                                                                        // object's
                                                                                                                        // method
                                                                                                                        // and
                                                                                                                        // attribute
        } else {
            System.out.println(this.getDeviceId() + " is off, not regulating temperature."); // Using 'this' to call the
                                                                                             // current object's method
        }
    }
}

// Smart Security Camera class inheriting from SmartDevice
class SmartSecurityCamera extends SmartDevice {
    public SmartSecurityCamera(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) { // Using 'this' to call the current object's method
            System.out.println(this.getDeviceId() + " is monitoring and recording activity."); // Using 'this' to call
                                                                                               // the current object's
                                                                                               // method
        } else {
            System.out.println(this.getDeviceId() + " is off, not monitoring."); // Using 'this' to call the current
                                                                                 // object's method
        }
    }
}

// Smart Door Lock class inheriting from SmartDevice
class SmartDoorLock extends SmartDevice {
    public SmartDoorLock(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) { // Using 'this' to call the current object's method
            System.out.println(this.getDeviceId() + " is locked."); // Using 'this' to call the current object's method
        } else {
            System.out.println(this.getDeviceId() + " is unlocked."); // Using 'this' to call the current object's
                                                                      // method
        }
    }
}

// Smart Appliance class inheriting from SmartDevice
class SmartAppliance extends SmartDevice {
    public SmartAppliance(String deviceId) {
        super(deviceId);
    }

    public void performFunction() {
        if (this.isOn()) { // Using 'this' to call the current object's method
            System.out.println(this.getDeviceId() + " is operating."); // Using 'this' to call the current object's
                                                                       // method
        } else {
            System.out.println(this.getDeviceId() + " is off, not operating."); // Using 'this' to call the current
                                                                                // object's method
        }
    }
}

// Home Automation System class
class HomeAutomationSystem {
    private List<SmartDevice> devices;

    public HomeAutomationSystem() {
        this.devices = new ArrayList<>(); // Using 'this' to refer to the current object's devices list
    }

    public void addDevice(SmartDevice device) {
        this.devices.add(device); // Using 'this' to refer to the current object's devices list
        System.out.println(device.getDeviceId() + " added to the home automation system.");
    }

    public void removeDevice(SmartDevice device) {
        this.devices.remove(device); // Using 'this' to refer to the current object's devices list
        System.out.println(device.getDeviceId() + " removed from the home automation system.");
    }

    public void executeMorningRoutine() {
        System.out.println("Executing Morning Routine...");
        for (SmartDevice device : this.devices) { // Using 'this' to refer to the current object's devices list
            if (device instanceof SmartLight || device instanceof SmartThermostat || device instanceof SmartAppliance) {
                device.turnOn();
                device.performFunction();
            }
        }
    }

    public void executeAwayMode() {
        System.out.println("Executing Away Mode...");
        for (SmartDevice device : this.devices) { // Using 'this' to refer to the current object's devices list
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
        for (SmartDevice device : this.devices) { // Using 'this' to refer to the current object's devices list
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
        for (SmartDevice device : this.devices) { // Using 'this' to refer to the current object's devices list
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

        // Create and add devices to the system
        SmartDevice light1 = new SmartLight("Light1");
        SmartDevice thermostat = new SmartThermostat("Thermostat");
        SmartDevice camera = new SmartSecurityCamera("Camera");
        SmartDevice doorLock = new SmartDoorLock("DoorLock");
        SmartDevice appliance = new SmartAppliance("Appliance");

        homeSystem.addDevice(light1);
        homeSystem.addDevice(thermostat);
        homeSystem.addDevice(camera);
        homeSystem.addDevice(doorLock);
        homeSystem.addDevice(appliance);

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
        }
    }
}
