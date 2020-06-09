package Worlds_Collide.__Utils;

/// singleton class used for the game for one single instance
public class Singleton {
    protected static Singleton instance;///< one instance of class

    protected Singleton(){}

    /// return existing instance or creates one and returns it
    public static Singleton getInstance(){
        if(instance == null){
            instance=new Singleton();
        }
        return instance;
    }
}
