public class Example0704{
    public void run(){
        GoldMemberCard card1 = new GoldMemberCard("玉田", 1000);
        MemberCard card2 = new GoldMemberCard("水口", 1500);
        MemberCard card3 = new MemberCard("田中", 500);
        card1.addPoints(100);
        card2.addPoints(100);
        card3.addPoints(100);
        card1.show();
        card2.show();
        card3.show();
    }
    // mainメソッドは省略．
    public static void main(String args[]) {
        Example0704 app = new Example0704();
        app.run();
    }
}