public class Rule {
    private String nameOfDevice ;
    private String time ;
    private String action ;

    // constructor
    public Rule(String nameOfDevice , String time , String action){
        this.nameOfDevice = nameOfDevice ;
        this.time = time ;
        this.action = action ;
    }

    // getters
    public String getNameOfDevice(){
        return nameOfDevice;
    }
    public String getTime(){
        return time;
    }
    public String getAction(){
        return action ;
    }
}
