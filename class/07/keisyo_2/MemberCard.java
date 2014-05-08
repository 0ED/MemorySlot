public class MemberCard{
    static int numberOfMembers = 0;
    private int memberId;
    private String name;
    int points;

    public MemberCard(String name){
        this(name, 50);
    }

    public MemberCard(String name, int points){
        numberOfMembers += 1;
        this.memberId = numberOfMembers;
        this.name = name;
        this.points = points;
    }

    public int usePoints(int point){
        if(this.points < point){
            point = this.points;
        }
        this.points = this.points - point;

        return point;
    }

    public int getId(){
        return memberId;
    }

    public void setId(int id){
        this.memberId = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addPoints(int point){
        this.points = this.points + point;
    }

    public void show(){
        System.out.println("会員番号は" + memberId + "番");
        System.out.println("会員氏名は" + name);
        System.out.println("累計ポイントは" + points + "点です．");
    }
}
