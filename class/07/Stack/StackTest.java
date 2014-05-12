public class StackTest{
	public void run(String[] args){
		
		Stack_144704 stack = new Stack_144704(); 
		
		for(int i = 0; i < args.length; i++){
			stack.push(args[i]);
		}
		
		while(stack.getSize() != 0){
			String value = stack.pop();
			System.out.println(value);
		}
	}
	public static void main(String[] args){
		StackTest test = new StackTest();
		test.run(args);
	}
}