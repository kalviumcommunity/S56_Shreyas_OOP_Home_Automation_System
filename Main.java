import java.util.*;

// Abstract base class for all smart devices
abstract class SmartDevice {
    private String deviceId;
    private boolean status;

    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
        this.status = false; // Default to off
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public boolean isOn() {
        return this.status;
    }

    public void turnOn() {
        this.status = true;
        System.out.println(this.deviceId + " is now ON.");
    }

    public void turnOff() {
        this.status = false;
        System.out.println(this.deviceId + " is now OFF.");
    }

    public abstract void performFunction();
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

    // Additional method for SmartLight
    public void dimLight() {
        if (this.isOn()) {
            System.out.println(this.getDeviceId() + " light is dimmed.");
        } else {
            System.out.println(this.getDeviceId() + " is off, cannot dim light.");
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

    public void executeRoutine() {
        System.out.println("Executing Routine...");
        for (SmartDevice device : this.devices) {
            device.turnOn();
            device.performFunction();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HomeAutomationSystem homeSystem = new HomeAutomationSystem();

        // Creating and adding devices to the system
        SmartDevice light1 = new SmartLight("Light1");
        SmartDevice light2 = new SmartLight("Light2");

        homeSystem.addDevice(light1);
        homeSystem.addDevice(light2);

        // Interact with the devices
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Execute Routine");
            System.out.println("2. Dim Light1");
            System.out.println("3. Turn off Light2");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    homeSystem.executeRoutine();
                    break;
                case 2:
                    ((SmartLight) light1).dimLight();
                    break;
                case 3:
                    light2.turnOff();
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
