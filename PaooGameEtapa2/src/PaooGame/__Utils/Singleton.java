package PaooGame.__Utils;

public class Singleton {
    protected static Singleton instance;

    protected Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            instance=new Singleton();
        }
        return instance;
    }
}
