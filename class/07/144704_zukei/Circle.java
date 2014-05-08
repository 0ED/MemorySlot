/*144704 高橋 右*/
public class Circle extends Shape {//継承
    private double hankei;

    public Circle(double hankei){
        super("Circle");
        //!!!Shapeコンストラクタに図形名をつける。!!!
        this.hankei = hankei;
    }

    public double getArea(){ //面積を求めるメソッド
        double area;
        area = hankei * hankei * Math.PI;//円の面積
        return area;
    }
}