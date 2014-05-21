import java.util.*;
public class LinkedListDemo2{
    
    public void run(){
        List list = new LinkedList();
        list.add(new Integer(5));
        list.add(new Float(-14.14f));
        list.add("Hello");
        list.add(new Long(120000000));
        list.add(new Double(-23.45e-11));
        printList(list);
        list.add(1, "String");
        printList(list);
        Object removedObject = list.remove(3);
        printList(list);
    }
    
    private void printList(List list){
        for(int i = 0; i < list.size(); i++){
            Object object = list.get(i);
            System.out.print(object + " ");
        }
        System.out.println(list.size());
    }
    
    public static void main(String[] args) {
        LinkedListDemo2 app = new LinkedListDemo2();
        app.run();
    }
}