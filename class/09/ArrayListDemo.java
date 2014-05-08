import java.util.*;
public class ArrayListDemo{
    public void run(){
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        System.out.println(list);
        list.add(1, "TWO"); //1番目の要素を"TWO"にして、PUSHする
        System.out.println(list);
        String removedObject = (String)list.remove(2);
        System.out.println(list);
    }
    
    public static void main(String[] args) {
        ArrayListDemo app = new ArrayListDemo();
        app.run();
    }
}