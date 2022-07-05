package creational.singleton;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if(instance == null) {
            System.out.println("inside if getInstance()");
            instance = new Singleton();
        }
        return instance;
    }

    //todo altri metodi e variabili che devono essere condivisi globalmente in una singola istanza


    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
