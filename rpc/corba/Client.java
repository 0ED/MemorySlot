import HelloMod.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class Client
{
	private static Hello aHello;
	
	public static void main(String args[])
	{
		try {
			ORB orb = ORB.init(args, null);
			
			// ネームサービスへの参照を取得
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// ネームサービスから、「Hello」という名前のサーバントを検索し、参照を取得
			String name = "Hello";
			aHello = HelloHelper.narrow(ncRef.resolve_str(name));
			System.out.println("------------");
			System.out.println("aHello.getMessage() is \"" + aHello.getMessage() + "\"");
		}
		catch (Exception e)
		{
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}
	}
}

