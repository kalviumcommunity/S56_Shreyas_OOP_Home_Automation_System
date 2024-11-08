import java.util.*;

// DEVICE MANAGER class to manage the total devices and devices that are ON
class DeviceManager {
    private static int totalDevices = 0;
    private static int devicesOn = 0;

    public static void registerDevice() {
        totalDevices++;
    }

    public static void unregisterDevice() {
        if (totalDevices > 0)
            totalDevices--;
    }

    public static void deviceTurnedOn() {
        devicesOn++;
    }

    public static void deviceTurnedOff() {
        if (devicesOn > 0)
            devicesOn--;
    }

    public static int getTotalDevices() {
        return totalDevices;
    }

    public static int getDevicesOn() {
        return devicesOn;
    }
}

// ABSTRACT BASE CLASS FOR ALL SMART DEVICES
abstract class SmartDevice {
    private String deviceId;
    private boolean status;

    // PARAMETERIZED CONSTRUCTOR FOR SMARTDEVICE
    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
        this.status = false; // Default to off
        DeviceManager.registerDevice();
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
            DeviceManager.deviceTurnedOn();
        }
        System.out.println(this.deviceId + " is now ON.");
    }

    public void turnOff() {
        if (this.status) {
            this.status = false;
            DeviceManager.deviceTurnedOff();
        }
        System.out.println(this.deviceId + " is now OFF.");
    }

    public abstract void performFunction();

    public abstract String getStatusReport();
}

    // DEVICE-SPECIFIC CLASSES
class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(String deviceId) {
        super(deviceId);
        this.brightness = 50;
    }

    public int getBrightness() {
        return brightness;
    }

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

class SmartThermostat extends SmartDevice {
    private int temperature;

    public SmartThermostat(String deviceId) {
        super(deviceId);
        this.temperature = 20;
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

class SmartSpeaker extends SmartDevice {
    private int volume;

    public SmartSpeaker(String deviceId) {
        super(deviceId);
        this.volume = 50;
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

// Routine interface to allow different routines to be implemented
interface Routine {
    void execute(List<SmartDevice> devices);
}

class MorningRoutine implements Routine {
    public void execute(List<SmartDevice> devices) {
        System.out.println("Executing Morning Routine...");
        for (SmartDevice device : devices) {
            device.turnOn();
            device.performFunction();
            System.out.println(device.getStatusReport());
        }
    }
}

class AwayModeRoutine implements Routine {
    public void execute(List<SmartDevice> devices) {
        System.out.println("Executing Away Mode...");
        for (SmartDevice device : devices) {
            device.turnOff();
            System.out.println(device.getStatusReport());
        }
    }
}

// HomeAutomationSystem to manage devices and routines
class HomeAutomationSystem {
    private List<SmartDevice> devices;
    private Map<String, Routine> routines;

    public HomeAutomationSystem() {
        this.devices = new ArrayList<>();
        this.routines = new HashMap<>();
    }

    public void addDevice(SmartDevice device) {
        this.devices.add(device);
        System.out.println(device.getDeviceId() + " added to the home automation system.");
    }

    public void removeDevice(SmartDevice device) {
        this.devices.remove(device);
        DeviceManager.unregisterDevice();
        System.out.println(device.getDeviceId() + " removed from the home automation system.");
    }

    public void addRoutine(String routineName, Routine routine) {
        this.routines.put(routineName, routine);
    }

    public void executeRoutine(String routineName) {
        Routine routine = this.routines.get(routineName);
        if (routine != null) {
            routine.execute(this.devices);
        } else {
            System.out.println("Routine not found: " + routineName);
        }
    }

    public static void getSummary() {
        System.out.println("Total devices across all systems: " + DeviceManager.getTotalDevices());
        System.out.println("Devices that are currently ON: " + DeviceManager.getDevicesOn());
    }
}

// Main class to interact with the system
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HomeAutomationSystem homeSystem = new HomeAutomationSystem();

        SmartDevice light = new SmartLight("LivingRoomLight");
        SmartDevice thermostat = new SmartThermostat("BedroomThermostat");
        SmartDevice speaker = new SmartSpeaker("LivingRoomSpeaker");

        homeSystem.addDevice(light);
        homeSystem.addDevice(thermostat);
        homeSystem.addDevice(speaker);

        // Adding routines to the system
        homeSystem.addRoutine("morning", new MorningRoutine());
        homeSystem.addRoutine("away", new AwayModeRoutine());

        HomeAutomationSystem.getSummary();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Execute Morning Routine");
            System.out.println("2. Execute Away Mode");
            System.out.println("3. Show Summary");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    homeSystem.executeRoutine("morning");
                    break;
                case 2:
                    homeSystem.executeRoutine("away");
                    break;
                case 3:
                    HomeAutomationSystem.getSummary();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
