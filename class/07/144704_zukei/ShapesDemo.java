/*144704 高橋 右*/
public class ShapesDemo{
    public void run(){
        Shape[] shapes = new Shape[10];
        for(int i = 0; i < shapes.length; i++){
            int rand = (int)(Math.random() * 4);
            //0~1=0 1~2=1 2~3=2 3~4=3
            //randの数によって分ける（資料間違えてました）
            if(rand == 0)
                shapes[i] = buildRectangle();
            else if(rand == 1)
                shapes[i] = buildTriangle();
            else if(rand == 2)
                shapes[i] = buildTrapezoid();
            else if(rand == 3)
                shapes[i] = buildCircle();
        }
        
        //メイン出力
        for(int i = 0; i < shapes.length; i++){
            System.out.println(
                               i + ": " + shapes[i].getType() +
                               ": " + shapes[i].getArea()
                               );
        }
    }
    
    //四角形
    Shape buildRectangle() {
        Shape rect = new Rectangle(Math.random()*10,Math.random()*10);
        return rect;
    }
    
    //三角形
    Shape buildTriangle() {
        Shape trian = new Triangle(Math.random()*10,Math.random()*10);
        return trian;
    }
    
    //台形
    Shape buildTrapezoid() {
        Shape trape = new Trapezoid(Math.random()*10,Math.random()*10,Math.random()*10);
        return trape;
    }
    
    //円
    Shape buildCircle() {
        Shape cir = new Circle(Math.random()*10);
        return cir;
    }
    
    public static void main(String[] args) {
        ShapesDemo app = new ShapesDemo(); 
        app.run();
    }
}