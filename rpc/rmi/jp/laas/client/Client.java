import org.google.HashMap;
public class Client 
{
	public static void main(String[] args) 
	{
		Map<String,Integer> aHashMap = new HashMap<String,Integer>();;
		aHashMap.put("rudds",52);
		System.out.println(aHashMap.get("rudds"));
	}
}
