/*144704 高橋 右*/
public class Trapezoid extends Shape {//継承
    private double lowerBaseWidth;
    private double upperBaseWidth;
    private double height;

    public Trapezoid(double lowerBaseWidth,double upperBaseWidth, double height){
        super("Trapezoid");
        //!!!Shapeコンストラクタに図形名をつける。!!!
        this.lowerBaseWidth = lowerBaseWidth;
        this.upperBaseWidth = upperBaseWidth;
        this.height = height;
    }

    public double getArea(){ //面積を求めるメソッド
        double area;
        area = (lowerBaseWidth + upperBaseWidth) * height / 2;//台形の面積
        return area;
    }
}