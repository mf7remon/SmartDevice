import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class SmartDevice {
    private String deviceName;
    private boolean status; // True for ON, False for OFF

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        this.status = false; // By default, devices are OFF
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean getStatus() {
        return status;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void turnOn(boolean on);
    public abstract void turnOff(boolean off);

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Method to print device status
    public void printStatus() {
        String state = (status) ? "ON" : "OFF";
        System.out.println(deviceName + " is currently " + state);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHome home = new SmartHome();
        boolean running = true;

        while (running) {
            System.out.println("\nSmart Home Menu:");
            System.out.println("1. Add Light Device");
            System.out.println("2. Add Thermostat Device");
            System.out.println("3. Turn On All Devices");
            System.out.println("4. Turn Off All Devices");
            System.out.println("5. View Device Status");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left by nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter Light Device Name: ");
                    String lightName = scanner.nextLine();
                    System.out.print("Enter Light Color: ");
                    String lightColor = scanner.nextLine();
                    Light light = new Light(lightName, lightColor);
                    home.addDevice(light);
                    break;

                case 2:
                    System.out.print("Enter Thermostat Device Name: ");
                    String thermostatName = scanner.nextLine();
                    System.out.print("Enter Thermostat Temperature: ");
                    double temperature = scanner.nextDouble();
                    ThermoStat thermostat = new ThermoStat(temperature, thermostatName);
                    home.addDevice(thermostat);
                    break;

                case 3:
                    home.turnOnAllDevices();
                    break;

                case 4:
                    home.turnOffAllDevices();
                    break;

                case 5:
                    home.printDeviceStatus();
                    break;

                case 6:
                    System.out.println("Exiting Smart Home...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }
}

class Light extends SmartDevice {
    public String color;

    public Light(String deviceName, String color) {
        super(deviceName);
        this.color = color;
    }

    @Override
    public void turnOn(boolean on) {
        if (!getStatus()) {
            System.out.println("The light is on");
            setStatus(true);
        } else {
            System.out.println("The light is already ON");
        }
    }

    @Override
    public void turnOff(boolean off) {
        if (getStatus()) {
            System.out.println("The light is off");
            setStatus(false);
        } else {
            System.out.println("The light is already OFF");
        }
    }

    // Override the printStatus method to include color
    @Override
    public void printStatus() {
        super.printStatus(); // This prints the name and status
        System.out.println("Color: " + color);
    }

    public void dimLight(int volume) {
        System.out.println("The dim light is set to " + volume);
    }
}


class ThermoStat extends SmartDevice {
    public double temperature;

    public ThermoStat(double temperature, String deviceName) {
        super(deviceName);
        this.temperature = temperature;
    }

    @Override
    public void turnOn(boolean on) {
        if (!getStatus()) {
            System.out.println("The thermostat is on now");
            setStatus(true);
        } else {
            System.out.println("The thermostat is already ON");
        }
    }

    @Override
    public void turnOff(boolean off) {
        if (getStatus()) {
            System.out.println("The thermostat is off now");
            setStatus(false);
        } else {
            System.out.println("The thermostat is already OFF");
        }
    }

    // Override the printStatus method to include temperature
    @Override
    public void printStatus() {
        super.printStatus(); // This prints the name and status
        System.out.println("Temperature: " + temperature + "°C");
    }
}


class SmartHome {
    private List<SmartDevice> devices = new ArrayList<>();

    void addDevice(SmartDevice device) {
        devices.add(device);
        System.out.println(device.getDeviceName() + " added to SmartHome.");
    }

    void turnOnAllDevices() {
        for (SmartDevice device : devices) {
            device.turnOn(true);
        }
    }

    void turnOffAllDevices() {
        for (SmartDevice device : devices) {
            device.turnOff(false);
        }
    }

    // Print status of all devices
    void printDeviceStatus() {
        for (SmartDevice device : devices) {
            device.printStatus();
        }
    }
}
