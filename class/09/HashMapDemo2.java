import java.util.*;
public class HashMapDemo2{
    public void run(){
        Map map = new HashMap(); //順序を保持しない
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        printMap(map);
        map.put("one", 10);
        printMap(map);
        map.remove("one");
        printMap(map);
    }
    private void printMap(Map map){
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); ){ //iterator()で順番に取ってくる
            //hasNext()は次の要素があるかどうか、next()は次の要素
            //返り値はObject
            Map.Entry entry = (Map.Entry)i.next(); 
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.print(key + " => " + value);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        HashMapDemo2 app = new HashMapDemo2();
        app.run();
    }

}