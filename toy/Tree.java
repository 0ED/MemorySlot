public class Tree
{
    Node root = null;
    int depth=0;
    
    void insert(int data) {
        if(root == null) root = new Node(data, depth); //枝がないとき、ノードに情報を与える
        else insert(root, data, depth+1); //枝があるとき今のノードのデータと加えたいデータを比べる
    }
    
    void insert(Node node, int data, int depth) { //現在のノード 加えたいデータ
        
        //加えたいデータの方が小さい場合、左に
        if(data < node.data) {
            if(node.left == null) node.left = new Node(data, depth);
            else insert(node.left, data, depth+1);
        }
        
        //加えたいデータの方が大きい場合、右に
        else {
            if(node.right == null) node.right = new Node(data, depth);
            else insert(node.right, data, depth+1);
        }
    }
    
    void print(Node node) {
        if(node == null) {
            return;
        }
        print(node.left);
        System.out.printf("データ: %d", node.data); //左側がなかったら
        System.out.printf(" 深さ: %d\n", node.depth);
        print(node.right);
    }
    
    public static void main(String[] args) {
        int[] array = {2,3,4,6,1,3,9,5,2,3};
        Tree tree = new Tree();
        
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }
        tree.print(tree.root);
        System.out.println();
    }
}



class Node {
    Node left;
    Node right;
    int data;
    int depth;
    
    public Node(int data, int depth) {
        this.data = data;
        this.depth = depth;
    }
}