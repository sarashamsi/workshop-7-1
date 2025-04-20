package house;

public class Light extends Device{

    private int brightness;

    // constructor
    public Light(String nameOfDevice, String protocol) {
        super(nameOfDevice, protocol);
        this.brightness = 50 ; // default = 50
    }
    // getter
    public int getBrightness(){
        return brightness ;
    }


    @Override
    public String getType() throws Exception {
        return "light";
    }

    @Override
    public void setProperty(String property, String statusValue) throws Exception {

        if (property.equals("status")){
            if (statusValue.equals("on") || statusValue.equals("off")){
                this.status = statusValue ;
            }
        }
        if (property.equals("brightness")){
            try {
                int br = Integer.parseInt(statusValue);
                this.brightness = br ;
            } catch (Exception e){
                return;
            }
        }
    }
}
