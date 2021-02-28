public class Subsystem{
    String name;
    Headmate[] list;
    
    public Subsystem(String name, Headmate... h){
        this.name = name;
        list = h;
    }
}
