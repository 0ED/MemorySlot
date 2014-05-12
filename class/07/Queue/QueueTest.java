public class QueueTest_144704 {
	public void run(String[] args){
		Queue_144704 queue = new Queue_144704();
		for(int i = 0; i < args.length; i++){
			queue.enqueue(args[i]);
		}
		while(queue.getSize() != 0){
			String value = queue.dequeue();
			System.out.println(value);
		}
	}
	public static void main(String[] args){
		QueueTest_144704 test = new QueueTest_144704();
		test.run(args);
	}
}