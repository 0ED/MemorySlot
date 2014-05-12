/*144704 高橋 右*/
public class Rectangle extends Shape {//継承
    private double width;
    private double height;

    public Rectangle(double width, double height){
        super("Rectangle");
        //!!!Shapeコンストラクタに図形名をつける。!!!
        this.width = width;
        this.height = height;
    }

    public double getArea(){ //面積を求めるメソッド
        double area;
        area = width * height;//四角形の面積
        return area;
    }
}