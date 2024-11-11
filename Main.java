import java.util.*;

// DEVICE TRACKING INTERFACE
interface DeviceTracker {
    void registerDevice();

    void unregisterDevice();

    void deviceTurnedOn();

    void deviceTurnedOff();

    int getTotalDevices();

    int getDevicesOn();
}

// DEVICE MANAGER IMPLEMENTING THE TRACKER INTERFACE
class DeviceManager implements DeviceTracker {
    private int totalDevices = 0;
    private int devicesOn = 0;

    public void registerDevice() {
        totalDevices++;
    }

    public void unregisterDevice() {
        if (totalDevices > 0)
            totalDevices--;
    }

    public void deviceTurnedOn() {
        devicesOn++;
    }

    public void deviceTurnedOff() {
        if (devicesOn > 0)
            devicesOn--;
    }

    public int getTotalDevices() {
        return totalDevices;
    }

    public int getDevicesOn() {
        return devicesOn;
    }
}

// SMART DEVICE INTERFACE
interface SmartDevice {
    String getDeviceId();

    boolean isOn();

    void turnOn();

    void turnOff();

    void performFunction();

    String getStatusReport();
}

// ABSTRACT SMART DEVICE IMPLEMENTING SMART DEVICE INTERFACE
abstract class AbstractSmartDevice implements SmartDevice {
    private String deviceId;
    private boolean status;
    private DeviceTracker deviceTracker;

    public AbstractSmartDevice(String deviceId, DeviceTracker deviceTracker) {
        this.deviceId = deviceId;
        this.status = false;
        this.deviceTracker = deviceTracker;
        deviceTracker.registerDevice();
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
            deviceTracker.deviceTurnedOn();
        }
        System.out.println(this.deviceId + " is now ON.");
    }

    public void turnOff() {
        if (this.status) {
            this.status = false;
            deviceTracker.deviceTurnedOff();
        }
        System.out.println(this.deviceId + " is now OFF.");
    }

    public abstract void performFunction();

    public abstract String getStatusReport();
}

// SMART LIGHT DEVICE
class SmartLight extends AbstractSmartDevice {
    private int brightness;

    public SmartLight(String deviceId, DeviceTracker deviceTracker) {
        super(deviceId, deviceTracker);
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

// SMART THERMOSTAT DEVICE
class SmartThermostat extends AbstractSmartDevice {
    private int temperature;

    public SmartThermostat(String deviceId, DeviceTracker deviceTracker) {
        super(deviceId, deviceTracker);
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

// SMART SPEAKER DEVICE
class SmartSpeaker extends AbstractSmartDevice {
    private int volume;

    public SmartSpeaker(String deviceId, DeviceTracker deviceTracker) {
        super(deviceId, deviceTracker);
        this.volume = 50;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(this.getDeviceId() + " volume set to " + this.volume + "%.");
    }

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

// ROUTINE INTERFACE
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

// HOME AUTOMATION SYSTEM TO MANAGE DEVICES AND ROUTINES
class HomeAutomationSystem {
    private List<SmartDevice> devices;
    private Map<String, Routine> routines;
    private DeviceTracker deviceTracker;

    public HomeAutomationSystem(DeviceTracker deviceTracker) {
        this.devices = new ArrayList<>();
        this.routines = new HashMap<>();
        this.deviceTracker = deviceTracker;
    }

    public void addDevice(SmartDevice device) {
        this.devices.add(device);
        System.out.println(device.getDeviceId() + " added to the home automation system.");
    }

    public void removeDevice(SmartDevice device) {
        this.devices.remove(device);
        deviceTracker.unregisterDevice();
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

    public void getSummary() {
        System.out.println("Total devices across all systems: " + deviceTracker.getTotalDevices());
        System.out.println("Devices that are currently ON: " + deviceTracker.getDevicesOn());
    }
}

// MAIN CLASS
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DeviceTracker deviceTracker = new DeviceManager();
        HomeAutomationSystem homeSystem = new HomeAutomationSystem(deviceTracker);

        SmartDevice light = new SmartLight("LivingRoomLight", deviceTracker);
        SmartDevice thermostat = new SmartThermostat("BedroomThermostat", deviceTracker);
        SmartDevice speaker = new SmartSpeaker("LivingRoomSpeaker", deviceTracker);

        homeSystem.addDevice(light);
        homeSystem.addDevice(thermostat);
        homeSystem.addDevice(speaker);

        homeSystem.addRoutine("morning", new MorningRoutine());
        homeSystem.addRoutine("away", new AwayModeRoutine());

        homeSystem.getSummary();

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
                    homeSystem.getSummary();
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
