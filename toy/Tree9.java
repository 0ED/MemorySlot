public class Tree9 {
    int[] array = {1,3,2,5};
    int[][][] block = { //t x y
        {   {2, 0, 2},
            {0, 1, 0},
            {2, 0, 1}, //1,1
        },
        {   {3, 0, 3},
            {0, 2, 0},
            {6, 0, 4}, //0,0 0,2
        },
        {   {0, 2, 0},
            {2, 0, 2},
            {0, 2, 0}, //0,1 1,0 2,1 1,2
        },
        {   {0, 5, 0},
            {1, 0, 5},
            {0, 1, 0}, //0,1
        }
    };
    Node[][] root = new Node[3][3];
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    void start() {
        for (int t = 0; t < 4; t++) {
            for(int y=0; y<3; y++) {
                for(int x=0; x<3; x++) {
                    if(block[t][x][y]==array[t]) { //一致したら
                        insert(x,y,t);
                    }                  
                }
            }
        }
    }
    
    
    //一致している前提
    void insert(int x, int y, int t) { //今のターンのx,y
        if(root[x][y] == null) {
            root[x][y] = new Node(x,y,t); //枝がないとき、ノードに情報を与える
        }
        else {
            insert(root[x][y], x,y,t); //枝があるとき今のノードのデータと加えたいデータを比べる
            System.out.println("t="+t+" root="+root[x][y]);
        }
    }
    
    
    void insert(Node node, int x, int y, int t) { //現在のノード 加えたいデータ
        if(node.next[x][y] == null) {
            node.next[x][y] = new Node(x,y,t);
        }
        else insert(node.next[x][y], x, y, t+1);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    void print(Node node) {
        if(node == null) {
            return;
        }
        for(int y=0; y<3; y++) {
            for(int x=0; x<3; x++) {
                print(node.next[x][y]);
            }
        }
        System.out.printf("x,y,t = %d,%d,%d\n", node.x, node.y, node.t); //左側がなかったら
    }
    
    public static void main(String[] args) {
        Tree9 tree = new Tree9();
        
        for (int t = 0; t < 4; t++) {
            for(int y=0; y<3; y++) {
                for(int x=0; x<3; x++) {
                    System.out.print(tree.block[t][x][y]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        
        
        tree.start();
        
        for(int y=0; y<3; y++) {
            for(int x=0; x<3; x++) {
                tree.print(tree.root[x][y]);
            }
        }
        System.out.println();
    }
}










class Node {
    Node[][] next;
    int x,y,t;
    
    public Node(int x, int y, int t) {
        next = new Node[3][3];
        this.x = x;
        this.y = y;
        this.t = t;
    }
}