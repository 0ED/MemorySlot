import java.io.Serializable;

public class Dog implements Serializable
{
	private int age;
	private String name;

	public Dog(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
}
