/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習09_02
 提出日： 12/3
 ファイル名： LinkedHashMapDemo_144704.java
 プログラムの動作説明：
    文字と数字を対応させたリストを追加した順序に表示するプログラム
 実行結果：
 ============================================
 Task $ java LinkedHashMapDemo_144704
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 one => 1two => 2three => 3four => 4five => 5
 one => 10two => 2three => 3four => 4five => 5
 two => 2three => 3four => 4five => 5
 ============================================
 */
import java.util.*;
public class LinkedHashMapDemo_144704{
    public void run(){
        Map map = new LinkedHashMap(); 
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
        LinkedHashMapDemo_144704 app = new LinkedHashMapDemo_144704();
        app.run();
    }

}