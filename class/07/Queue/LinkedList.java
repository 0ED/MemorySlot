/**
 * リスト構造を表すクラス．
 *
 * @author Haruaki Tamada
 */
public class LinkedList{
    private ListNode root;
    private int length = 0;

    /**
     * 引数で与えられた値をこのリストの一番最後に加える．
     */
    public void add(String value){
        ListNode newNode = new ListNode(value);
        if(root == null){
            root = newNode;
        }
        else{
            ListNode node = root;
            while(node.getNext() != null){
                node = node.getNext();
            }
            node.setNext(newNode);
        }
        length++;
    }

    /**
     * 現在のリストの長さを返す．
     */
    public int getLength(){
        return length;
    }

    /**
     * 引数で与えられたインデックスにある値を返す．リストの状態は変化しない．
     * インデックスが存在しない場合（このリストの範囲を超えている，もしくは負の値），
     * IndexOutOfBoundsException が投げられる．
     */
    public String get(int index){
        if(index < 0 || index >= length){
            throw new IndexOutOfBoundsException("out of bounds: " + index);
        }

        ListNode node = root;
        for(int i = 0; i < index; i++){
            node = node.getNext();
        }
        return node.getValue();
    }

    /**
     * 引数で与えられたインデックスにある値を削除する．
     * 削除に成功すると true を返し，失敗すると false を返す．
     * インデックスが存在しない場合（このリストの範囲を超えている，もしくは負の値），
     * IndexOutOfBoundsException が投げられる．
     */
    public boolean remove(int index){
        if(index < 0 || index >= length){
            throw new IndexOutOfBoundsException("out of bounds: " + index);
        }

        ListNode node = root;
        ListNode prev = null;
        for(int i = 0; i < index; i++){
            prev = node;
            node = node.getNext();
        }
        boolean flag = false;
        if(prev != null && node != null){
            prev.setNext(node.getNext());
            flag = true;
        }
        else if(prev != null && node == null){
            prev.setNext(null);
            flag = true;
        }
        else{
            root = node.getNext();
            flag = true;
        }
        if(flag){
            length--;
        }
        return flag;
    }

    /**
     * このリストに入っている要素をすべて出力する文字列を返す．
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        ListNode node = root;
        while(node != null){
            if(!first){
                sb.append(", ");
            }
            sb.append(node.getValue());
            first = false;
            node = node.getNext();
        }
        return new String(sb);
    }

    /**
     * テスト用．コマンドライン引数に 数字が与えれれば，そのインデックスの要素を表示して，削除する．
     * 数字以外であれば，その文字列を追加する．
     * コマンドライン引数に与えられたすべての引数に対して，上記操作を行う．
     * なお，1要素ごとに，現在のリストの状態をリストの長さとともに出力する．
     */
    public static void main(String[] args){
        LinkedList list = new LinkedList();

        for(int i = 0; i < args.length; i++){
            try{
                int index = Integer.parseInt(args[i]);
                System.out.println(list.get(index));
                list.remove(index);
            } catch(NumberFormatException e){
                list.add(args[i]);
            }
            System.out.println(list.getLength() + ": " + list);
        }
    }
}

class ListNode{
    private String value;
    private ListNode next;

    public ListNode(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setNext(ListNode node){
        this.next = node;
    }

    public ListNode getNext(){
        return next;
    }
}
