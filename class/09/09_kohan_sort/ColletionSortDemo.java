import java.util.*;

public class ColletionSortDemo {
    public void run() {
        List list = new ArrayList();
        Random random = new Random();
        
        for (int i=0; i < 100; i++) {
            int value = random.nextInt();
            list.add(value);
        }
        Collections.sort(list);
        for (int i=0; i < 100; i++) {
            System.out.println(list.get(i));
        }
    }
    public static void main(String[] args) {
        ColletionSortDemo app = new ColletionSortDemo();
        app.run();
    }
}