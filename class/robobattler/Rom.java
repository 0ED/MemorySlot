import java.awt.Point;
class Rom {
    
    private int max;
    private Point target;
    
    void set(int i,Point target) {
        this.max = i;
        this.target = target;
    }
    int getMax() {return max;}
    Point getPoint(){return target;}
}
