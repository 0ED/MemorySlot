/*144704 高橋 右*/
public class Triangle extends Shape {//継承
    private double baseWidth;
    private double height;

    public Triangle(double baseWidth, double height){
        super("Triangle");
        //!!!Shapeコンストラクタに図形名をつける。!!!
        this.baseWidth = baseWidth;
        this.height = height;
    }

    public double getArea(){ //面積を求めるメソッド
        double area;
        area = baseWidth * height / 2;//三角形の面積
        return area;
    }
}