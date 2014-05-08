public abstract class Card {
    private static int numberOfMembers;
    private int id;
    private String name;
    protected int points;
    public Card(String name, int initPoints) {
        numberOfMembers++;
        id = numberOfMembers;
        this.name = name;
        this.points = initPoints;
    }
    // フィールドのgetterを用意する．
    public abstract void show();
    
    public int getId() {return id;}
    public String getName() {return name;}
    public int getPoints() {return points;}
}