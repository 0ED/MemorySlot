public class Sample {
    public static void main(String[] args){
        int x[][] = {
            {2,4,5,3},{0,5},
            {2,3,5},{2},{2,3,4}
        }; //（4パネル）*（5/16ターン）
        int y[][] = {
            {3,5,4,5},{7,7},
            {4,3,5},{5},{5,5,6}
        }; //（4パネル）*（5/16ターン）
        

        
        
        Node root = new Node(x,y,0,0); //1~16
        for(int t = 1; t < x.length; t++){
            for(int panel = 1; panel < x[t].length; panel++){
                System.out.print("x["+t+"]["+panel+"]="+x[t][panel]);
                System.out.println("\ty["+t+"]["+panel+"]="+y[t][panel]);
                
                Node next = new Node(x,y,t,panel);
                next.setNext(root.getNext()); //最初はnull
                root.setNext(next[]);
            }
        }
    
        
        while(root != null){
			System.out.println("x = "+root.getX()+"\ty = "+root.getY());
			root = root.getNext();
		}
        
    }
}

class Node {
    //フィールド
    private int x=0,y=0;
    private Node[][] next = new Node[3][3];
    
    //コンストラクタ
    public Node(int[][] x, int[][] y, int t, int panel) {
        this.x = x[t][panel];
        this.y = y[t][panel];
	}
    
    //コンストラクタで入れた数字を返す
	int getX() {
		return this.x;
	}
    int getY() {
        return this.y;
    }
    
    
    
	//Nodeポインタを受け取る
	void setNext(Node next,int x,int y) {
		this.next[x][y] = next;
	}
	
	//Nodeポインタを返す
	Node getNext() {
		return this.next;
	}
	
    
}