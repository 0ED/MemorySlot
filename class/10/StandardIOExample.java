import java.io.*;

class StandardIOExample {
    public static void main(String[] args) {
        String num1, num2;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("input 1st number: ");
            num1 = in.readLine();
            System.out.print("input 2st number: ");
            num2 = in.readLine();
            int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
            System.out.println("sum = " + sum);
        } catch (IOException e) {
            System.err.println("input error");
        } catch (NumberFormatException nfe) {
            System.err.println("invalid number error");
        }
    }
}