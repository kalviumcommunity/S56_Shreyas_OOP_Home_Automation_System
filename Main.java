import java.util.*;

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

// Abstract base class for all smart devices
abstract class SmartDevice {
    private String deviceId;
    private boolean status;

    // Parameterized constructor for SmartDevice
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

    // New abstract method for status report
    public abstract String getStatusReport();
}

class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight() {
        super("DefaultLight");
        this.brightness = 50;
    }

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

    public SmartThermostat() {
        super("DefaultThermostat");
        this.temperature = 20;
    }

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

    public SmartSpeaker() {
        super("DefaultSpeaker");
        this.volume = 50;
    }

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
        DeviceManager.unregisterDevice();
        System.out.println(device.getDeviceId() + " removed from the home automation system.");
    }

    public void executeMorningRoutine() {
        System.out.println("Executing Morning Routine...");
        for (SmartDevice device : this.devices) {
            device.turnOn();
            device.performFunction();
            System.out.println(device.getStatusReport());
        }
    }

    public void executeAwayMode() {
        System.out.println("Executing Away Mode...");
        for (SmartDevice device : this.devices) {
            device.turnOff();
            System.out.println(device.getStatusReport());
        }
    }

    public static void getSummary() {
        System.out.println("Total devices across all systems: " + DeviceManager.getTotalDevices());
        System.out.println("Devices that are currently ON: " + DeviceManager.getDevicesOn());
    }
}

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
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
