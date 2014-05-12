import java.util.*;

public class ColletionSortDemo2 {
    public void run() {
        List list = new ArrayList();
        Random random = new Random();
        
        for (int i=0; i < 100; i++) {
            int value = random.nextInt();
            list.add(value);
        }
        AbsoluteComparator comparator = new AbsoluteComparator();
        Collections.sort(list, comparator);
        for (int i=0; i < 100; i++) {
            System.out.println(list.get(i));
        }
    }
    public static void main(String[] args) {
        ColletionSortDemo2 app = new ColletionSortDemo2();
        app.run();
    }
}