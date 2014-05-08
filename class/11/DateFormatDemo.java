import java.util.*; //Dateクラスを使うため
import java.text.*; //DateFormatクラスを使うため
public class DateFormatDemo {
    public void run() {
        Date date = new Date();
        
        DateFormat formatter1 = DateFormat.getDateInstance(DateFormat.DEFAULT);
        String str1 = formatter1.format(date);
        System.out.println(str1);
        
        DateFormat formatter2 = DateFormat.getDateInstance(DateFormat.SHORT);
        String str2 = formatter2.format(date);
        System.out.println(str2);
        
        DateFormat formatter3 = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String str3 = formatter3.format(date);
        System.out.println(str3);
        
        DateFormat formatter4 = DateFormat.getDateInstance(DateFormat.LONG);
        String str4 = formatter4.format(date);
        System.out.println(str4);
        
        DateFormat formatter5 = DateFormat.getDateInstance(DateFormat.FULL);
        String str5 = formatter5.format(date);
        System.out.println(str5);
        
    }
    
    public static void main(String[] args) {
        DateFormatDemo app = new DateFormatDemo();
        app.run();
    }
}