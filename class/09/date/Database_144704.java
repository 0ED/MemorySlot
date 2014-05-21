/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習09_04
 提出日： 12/3
 ファイル名： Database_144704.java
 
 プログラムの動作説明：
    プログラムを実行すると、入力を求められる。それに対して、
    コマンド（add,list,listtel,find,remove,update,clear,quit）を
    入力して動作を行う。それぞれのコマンドの仕様を以下に記す。
    ------------------------------------------------------
    add 名前 電話番号     //データベースに値を追加
    list                //データベース（名前を辞書順に）の一覧を表示
    listtel             //データベース（電話番号を昇順に）の一覧を表示
    find 名前            //データベースに名前があれば、その名前の登録データを表示する
    remove 名前          //データベースに名前があれば、その名前の登録データを削除する
    update 名前 電話番号  //データベースに名前があれば、電話番号を更新する
    clear               //データベースをすべて削除
    quit                //プログラム終了
    ------------------------------------------------------
 
 工夫した点：
    list,listtel,findの表示を見やすくするため
    データベースのデータがあれば区切り線（-----------）を引き、
    なければ区切り線を引かないように作った。
 
 反省すべき点：
    listとfindのメソッドが似たような物になった。
    
 実行結果：
 ============================================
 Task $ java Database_144704
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 >add ddd 555
 >add zzz 111
 >add eee 222
 >list
 ddd 555
 eee 222
 zzz 111
 -----------------
 >listtel
 zzz=111
 eee=222
 ddd=555
 -----------------
 >find ddd
 ddd 555
 -----------------
 >remove ddd
 >update eee 2222222222222
 >list
 eee 2222222222222
 zzz 111
 -----------------
 >clear
 >list
 >a
 Not command
 >quit
 ============================================
 */
import java.util.*;

public class Database_144704 {
    public void run(){
        String line;
        Map map = new TreeMap();
        while(true) {
            System.out.print(">");
            line = System.console().readLine(); //文字読み込み
            String[] strings = line.split(" ");
            
            //コマンドごとに、処理する
            if (line.equals("quit")) break; //quitが入力されたら、終了
            else if (strings[0].equals("add")) {
                map.put(strings[1], strings[2]);
            }
            
            else if (strings[0].equals("list")) {
                list(map);
            }
            
            else if (strings[0].equals("listtel")) {
                listtel(map);
            }
            
            else if (strings[0].equals("find")) {
                find(map, strings[1]);
            }
            
            else if (strings[0].equals("remove")) {
                map.remove(strings[1]);
            }
            
            else if (strings[0].equals("update")) {
                //データベースに名前があれば、上書きする
                //そうでなければ、何もしない
                if(update(map,strings[1]) == true) {
                    map.put(strings[1], strings[2]);
                }
            }
    
            else if (strings[0].equals("clear")) {
                map.clear();
            }
            
            else System.out.println("Not command");
            
        }
        
    }
    
    
    private boolean update(Map map, String inputStr){
        //データベースの名前と入力した名前が同じなら、trueを返す
        boolean flag=false;
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); ){
            Map.Entry entry = (Map.Entry)i.next();
            Object key = entry.getKey();
            if(key.equals(inputStr)) flag = true; 
        }
        return flag;
    }
    
    
    private void listtel(Map map){
        List list = new ArrayList();
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); ){
            Map.Entry entry = (Map.Entry)i.next();
            list.add(entry); //を加える
        }
        
        TelephoneComparator comparator = new TelephoneComparator();
        Collections.sort(list, comparator);
        
        for(int i=0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        if(map.size() > 0)System.out.println("-----------------");
    }
    
    
    private void list(Map map){
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); ){
            Map.Entry entry = (Map.Entry)i.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " " + value);
        }
        if(map.size() > 0)System.out.println("-----------------");
    }
    
    //listメソッドとほとんど同じなので無駄を削減できると思ったが、新たにクラスを作成して、継承しないとたぶん無理なので、無駄を削減しなかった。（オーバーロードなども考えたが、実現不可だった）
    private void find(Map map, String inputStr){
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); ){
            Map.Entry entry = (Map.Entry)i.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals(inputStr)) {
                System.out.println(key + " " + value);
                System.out.println("-----------------");
            }
        }
    }
    
    
    public static void main(String[] args) {
        Database_144704 app = new Database_144704();
        app.run();
    }

}






class TelephoneComparator implements Comparator{
    public int compare(Object object1, Object object2){
        Map.Entry entry1 = (Map.Entry)object1;
        Map.Entry entry2 = (Map.Entry)object2;
        String telephone1 = (String)entry1.getValue();
        String telephone2 = (String)entry2.getValue();
        return telephone1.compareTo(telephone2);
    }
    public boolean equals(Object object1, Object object2){
        return object1.equals(object2);
    }
}