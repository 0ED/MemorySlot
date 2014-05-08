public class LinkedListDemo{
	
	public LinkedListDemo(String[] args){
		ListNode_144704 root = buildList(args); 
		showList(root);
	}
	
	
	public ListNode_144704 buildList(String[] args){ //ListNode_144704はポインタの型名
		
		
		//root住所に1文字目を格納
		ListNode_144704 root = new ListNode_144704(args[0]);
		
		for(int i = 1; i < args.length; i++){
			
			//1. leaf住所に文字を入れる
			ListNode_144704 next = new ListNode_144704(args[i]);
            
			
			//2. root住所のポインタをleaf住所のポインタにする
			next.setNext(root.getNext());
			
			
			//3. leaf住所のポインタをroot住所のポインタにする。
			root.setNext(next);
			
		}
		
		return root;
	}
	
	
	
	public void showList(ListNode_144704 root){
		
		ListNode_144704 node = root;
		
		while(node != null){
			System.out.println(node.getValue());
			node = node.getNext();
		}
	}
	
	
	
	public static void main(String[] args){
		LinkedListDemo demo = new LinkedListDemo(args);
	}
}
