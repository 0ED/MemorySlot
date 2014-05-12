import java.awt.*;
import javax.swing.*;
class FieldPanel extends JPanel {
    FieldPanel() { //親クラスのコンストラクタ
        setBackground(Color.WHITE);
    }
    
    private double theta(int i) {
        return 2.0 * Math.PI/100 * (double)i;
    }
    
    void run() {
        int dx=8, dy=5;
        
        
        while(true) {
            x+=dx; y+=dy;
            repaint();
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int width=360, height=360;
        
        int a=200;
        int x_center = width/2,  y_center = height/2;
        int x,y;
        int x_back=x_center, y_back=x_center;
        double r;
        for(int i=1; i<=100; i++) {
            r = (double)a * Math.sin( 2.0*theta(i) );
            x = x_center + (int)(r*Math.cos(theta(i)) );
            y = y_center + (int)(r*Math.sin(theta(i)) );
            g.drawLine(x_back, y_back, x, y);
            x_back = x; y_back = y;
        }
    }
}