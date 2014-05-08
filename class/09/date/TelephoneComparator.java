import java.util.*;
public class TelephoneComparator implements Comparator{
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