public class PointCard extends Card {
    //オーバーライド
    public void show() {
        System.out.println("Id = " + this.getId());
        System.out.println("Name = " + this.getName());
        System.out.println("Points = " + this.getPoints());
    }
    
    public PointCard(String name, int id, int initPoint) {
        super(name, initPoint);
    }
    public static void main(String[] args) {
        Card card1 = new PointCard("水口", 1, 1000);
        card1.show();
    }
}