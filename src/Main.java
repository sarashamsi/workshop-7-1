import house.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int q = Integer.parseInt(scan.nextLine()) ;
        CentralPanel centralPanel = new CentralPanel();
        System.out.println();

        for (int i=0 ; i<q ;i++){
            String input = scan.nextLine().trim();
            String[] part = input.split(" ");
            String str = part[0] ;

            switch (str){
                case "add_device":
                    addDevice(centralPanel,part);
                    break;
                case "set_device":
                    setDevice(centralPanel, part);
                    break;
                case "remove_device":
                    removeDevice(centralPanel, part);
                    break;
                case "list_devices":
                    centralPanel.listDevices();
                    break;
                case "add_rule":
                    addRule(centralPanel, part);
                    break;
                case "check_rules":
                    checkRules(centralPanel, part);
                    break;
                case "list_rules":
                    centralPanel.listRules();
                    break;
                default:
                    break;
            }
        }

    }


    public static void addDevice (CentralPanel centralPanel, String[] input){
        String type = input[1];
        String name = input[2];
        String protocol = input[3];

        if (!type.equals("light") && !type.equals("thermostat")) {
            System.out.println("invalid input");
            return;
        }
        if (!protocol.equals("WiFi") && !protocol.equals("Bluetooth")) {
            System.out.println("invalid input");
            return;
        }
        if (centralPanel.devices.containsKey(name)) {
            System.out.println("duplicate device name");
            return;
        }

        Device device;
        if (type.equals("light")) {
            device = new Light(name, protocol);
        } else {
            device = new Thermostat(name, protocol);
        }
        centralPanel.addDevice(name, device);
        System.out.println("device added successfully");
    }
    public static void setDevice(CentralPanel centralPanel , String[] input) throws Exception {
        String name = input[1];
        String property = input[2];
        String value = input[3];

        if (!centralPanel.devices.containsKey(name)) {
            System.out.println("device not found");
            return;
        }
        Device device = centralPanel.devices.get(name);
        if (device instanceof Light){
            if (property.equals("status") || property.equals("brightness")){
                device.setProperty(property , value);
                System.out.println("device updated successfully");
            } else {
                System.out.println("invalid property");
                return;
            }
        }
        if (device instanceof Thermostat){
            if (property.equals("status") || property.equals("temperature")){
                device.setProperty(property , value);
                System.out.println("device updated successfully");
            } else {
                System.out.println("invalid property");
                return;
            }
        }

    }
    public static void removeDevice (CentralPanel centralPanel , String[] input){

        String name = input[1];
        if (!centralPanel.devices.containsKey(name)) {
            System.out.println("device not found");
            return;
        }
        centralPanel.removeDevice(name);
        System.out.println("device removed successfully");
    }

    public static void addRule( CentralPanel centralPanel , String[] input){

        String deviceName = input[1];
        String time = input[2];
        String action = input[3];

        if (!centralPanel.devices.containsKey(deviceName)) {
            System.out.println("device not found");
            return;
        }
        if (!action.equals("on") && !action.equals("off")) {
            System.out.println("invalid action");
            return;
        }
        if (centralPanel.hasRuleForDeviceAndTime(deviceName, time)) {
            System.out.println("duplicate rule");
            return;
        }
        if (!validateTime(time)){
            System.out.println("invalid time");
            return;
        }

        centralPanel.addRule(new Rule(deviceName, time, action));
        System.out.println("rule added successfully");
    }

    public static void checkRules (CentralPanel centralPanel , String[] input){
        String time = input[1];
        if (!validateTime(time)) {
            System.out.println("invalid time");
            return;
        }
        try {
            centralPanel.checkRules(time);
            System.out.println("rules checked");
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }

    public static boolean validateTime(String time) {
        String[] timeParts = time.split(":");

        try {
            int hour = Integer.parseInt(timeParts[0]);
            int min = Integer.parseInt(timeParts[1]);
            // format -> HH:mm
            String format = String.format("%02d:%02d", hour, min);
            return hour >= 0 && hour <= 23 && min >= 0 && min <= 59;
        } catch (Exception e) {
            return false;
        }
    }

}