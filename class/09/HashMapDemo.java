import java.util.*;
public class HashMapDemo{
    public void run(){
        Map map = new HashMap();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        System.out.println(map);
        map.put("one", 10);
        System.out.println(map);
        map.remove("one");
        System.out.println(map);
    }
    public static void main(String[] args) {
        HashMapDemo app = new HashMapDemo();
        app.run();
    }
}