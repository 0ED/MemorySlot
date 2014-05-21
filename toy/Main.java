import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.IOException;
import java.lang.SecurityException;

public class Main
{
	public static void main(String[] args)
	{
		try {
			ObjectOutput out=new ObjectOutputStream(new FileOutputStream("dog.obj"));
			Dog dog=new Dog("pochi",10);
			out.writeObject(dog);
			out.flush();
			out.close();
		}
		catch(SecurityException e) {}
		catch(IOException e) {}
	}
}
