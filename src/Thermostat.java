public class Thermostat extends Device{
    private int temperature ;

    public Thermostat(String nameOfDevice, String protocol) {
        super(nameOfDevice, protocol);
        this.temperature = 20 ; // default = 20
    }

    // getter
    public int getTemperature(){
        return temperature ;
    }

    @Override
    public String getType() throws Exception {
        return "thermostat";
    }


    @Override
    public void setProperty(String property, String statusValue) throws Exception {
        if (property.equals("status")){
            if (statusValue.equals("on") || statusValue.equals("off")){
                this.status = statusValue;
            }
        }
        if (property.equals("temperature")){
            try {
                int temp = Integer.parseInt(statusValue);
                this.temperature = temp ;
            }catch (Exception e){
                return;
            }
        }

    }

}
