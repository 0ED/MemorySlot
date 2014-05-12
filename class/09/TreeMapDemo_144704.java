/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習09_03
 提出日： 12/3
 ファイル名： TreeMapDemo_144704.java
 プログラムの動作説明：
    文字と数字を対応させたリストを辞書順に表示するプログラム
 実行結果：
 ============================================
 Task $ java TreeMapDemo_144704
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 five => 5four => 4one => 1three => 3two => 2
 five => 5four => 4one => 10three => 3two => 2
 five => 5four => 4three => 3two => 2
 ============================================
 */
import java.util.*;
public class TreeMapDemo_144704 {
    public void run(){
        Map map = new TreeMap(); //順序を保持しない
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
        TreeMapDemo_144704 app = new TreeMapDemo_144704();
        app.run();
    }

}