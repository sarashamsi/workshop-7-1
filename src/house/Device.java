package house;

public abstract class Device {
    protected String nameOfDevice ;
    protected String protocol ;
    protected String status ;


    // constructor
    public Device(String nameOfDevice ,String protocol ){
        this.nameOfDevice = nameOfDevice ;
        this.protocol = protocol  ;
        this.status = "off" ; // default = off
    }

    // getters
    public String getNameOfDevice(){
        return nameOfDevice ;
    }
    public String getProtocol(){
        return protocol ;
    }
    public String getStatus(){
        return status ;
    }

    public abstract String getType (
    ) throws Exception ;
    public abstract void setProperty(String property, String statusValue) throws Exception;
}



