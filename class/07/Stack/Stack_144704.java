public class Stack_144704 extends LinkedList {
    void push(String args){
		add(args);
	}
	
	String pop(){
        String args = get(getLength()-1); //要素の最後を見る
        remove(getLength()-1); //要素の最後を消す
		return args;
	}
	
	int getSize() {
		return getLength();
	}
}