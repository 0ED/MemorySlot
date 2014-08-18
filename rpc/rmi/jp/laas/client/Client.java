import org.laas.Map;
import org.laas.HashMap;

public class Client 
{
	public static void main(String[] args) 
	{Client client = new Client();hoge();Map<String,Integer> aHashMap = new HashMap<String,Integer>();aHashMap.put("rudds",52); System.out.println(aHashMap.get("rudds"));hoge();}
	void hoge()
	{
		Map<String,Integer> aMap = new HashMap<String,Integer>();
		aMap.put("rudds",52);
		System.out.println(aMap.get("rudds"));
	}
}
