import java.util.*;
public class AbsoluteComparator implements Comparator{
    public int compare(Object object1, Object object2){
        Integer integer1 = (Integer)object1;
        Integer integer2 = (Integer)object2;
        int intValue1 = Math.abs( integer1.intValue() );
        int intValue2 = Math.abs( integer2.intValue() );
        if(intValue1 < intValue2){ return -1; }
        else if(intValue1 == intValue2){ return 0; }
        else{ return 1; }
    }
    public boolean equals(Object object){
        boolean equalsFlag = super.equals(object);
        return equalsFlag;w
    }
}