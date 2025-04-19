import java.util.ArrayList;
import java.util.HashMap;

public class CentralPanel {
    public HashMap<String,Device> devices = new HashMap<>();
    public ArrayList<Rule> rules = new ArrayList<>() ;



    public void addDevice(String name, Device device) {
        devices.put(name, device);
    }
    public void removeDevice(String name) {
        devices.remove(name);
        for (int i=rules.size()-1 ; i>=0 ; i--){
            Rule rule = rules.get(i);
            if (rule.getNameOfDevice().equals(name)){
                rules.remove(i);
            }
        }
    }
    public boolean hasRuleForDeviceAndTime(String deviceName, String time) {
        for (Rule rule : rules) {
            if (rule.getNameOfDevice().equals(deviceName) && rule.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }
    public void addRule(Rule rule) {
        rules.add(rule);
    }
    public void listDevices() throws Exception {
        if (devices.isEmpty()) {
            System.out.println();
            return;
        }
        for (Device device : devices.values()) {
            String type = device.getType();
            String line;
            if (type.equals("light")) {
                Light light = (Light) device;
                line = String.format("%s %s %d%% %s",
                        light.getNameOfDevice(), light.getStatus(), light.getBrightness(), light.getProtocol());
            } else {
                Thermostat thermo = (Thermostat) device;
                line = String.format("%s %s %dC %s",
                        thermo.getNameOfDevice(), thermo.getStatus(), thermo.getTemperature(), thermo.getProtocol());
            }
            System.out.println(line);
        }
    }

    public void listRules() {
        if (rules.isEmpty()) {
            System.out.println();
            return;
        }
        for (Rule rule : rules) {
            System.out.printf("%s %s %s%n", rule.getNameOfDevice(), rule.getTime(), rule.getAction());
        }
    }

    public void checkRules(String time) throws Exception {
        for (Rule rule : rules) {
            if (rule.getTime().equals(time)) {
                Device device = devices.get(rule.getNameOfDevice());
                if (device != null) {
                    device.setProperty("status", rule.getAction());
                }
            }
        }
    }
}



