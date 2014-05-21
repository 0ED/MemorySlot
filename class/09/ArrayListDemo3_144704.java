/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習09_01
 提出日： 11/29
 ファイル名： ArrayListDemo3_144704.java
 プログラムの動作説明：
    ArrayListメソッドを使って、
    1~10の和を求めるプログラム
 
 実行結果：
 ===================================
 Task $ java ArrayListDemo3_144704
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 Total: 55
 ===================================
*/
import java.util.*;
    public class ArrayListDemo3_144704{
        public void run(){
        List list = new ArrayList();
        for(int i = 1; i <= 10; i++){
            list.add(new Integer(i)); 
        }
            
        int sum = 0;
        for(int j = 0; j < list.size(); j++){
            //list.get(j)はオブジェクト型なので、文字列へ変換
            String box = list.get(j).toString();
            sum += Integer.parseInt(box);
        }
        System.out.println("Total: " + sum);
    }
    public static void main(String[] args) {
        ArrayListDemo3_144704 app = new ArrayListDemo3_144704();
        app.run();
    }
}