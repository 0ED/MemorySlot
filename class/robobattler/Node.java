import java.awt.Point;
public class Node {
    
    public static final int YOU = 2;
    private MemoryData memory = new MemoryData();
    
    private Node[][] next;
    private int x,y;
    private int turn=0;
    
    public Node() {
        next = new Node[3][3];
        //turn++; //ノードが作られるということは深さが1増す
    }
    void setNext(Node node, int x, int y) {
        next[x][y] = node;
        this.x = x;
        this.y = y;
        turn++;
    }

    int getX(){return x;}
    int getY(){return y;}
    int getTurn() {return turn;}
    Node getNext() {return next[x][y];}
}