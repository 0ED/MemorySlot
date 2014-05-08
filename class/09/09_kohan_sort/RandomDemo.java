import java.util.Random;
public class RandomDemo{
    public void run(){
        Random random = new Random(144704);
        for (int i=0; i < 100; i++) {
            int value = random.nextInt();
            System.out.println("Random["+i+"] = "+value);
        }
    }
    public static void main(String[] args) {
        RandomDemo app = new RandomDemo();
        app.run();
    }
}