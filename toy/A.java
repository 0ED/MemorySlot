import java.io.File;
import java.io.IOException;

public class A
{
	public static void main(String[] argv)
	{
		File aLogFile = null;
		Runtime aRun = null;
		Process aProcess = null;

		String[] exec = {"javac", "/Users/tasukutakahashi/iTask/java/toy/Sample.java"}; 
		ProcessBuilder aBuilder = new ProcessBuilder();
		aBuilder.command(exec);
		aLogFile = new File("error.txt");
		aBuilder.redirectErrorStream(true);
		aBuilder.redirectOutput(aLogFile);

		try {
			aProcess = aBuilder.start();
		}
		catch(SecurityException e) {}
		catch(IOException e) {}
		catch(NullPointerException e) { e.printStackTrace(); }
		catch(IndexOutOfBoundsException e) {}

		try {
			aProcess.waitFor();
		}
		catch(InterruptedException e) {}


		/*
		aRun = Runtime.getRuntime();

		try {
			aProcess = aRun.exec("/usr/bin/javac Sample.java 2> error.txt"); //javac ~/iTask/java/toy/Sample.java");
		}
		catch(SecurityException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
		catch(NullPointerException e) { e.printStackTrace(); }
		catch(IndexOutOfBoundsException e) { e.printStackTrace(); }

		try {
			aProcess.waitFor();
		}
		catch(InterruptedException e) {}
		*/
	}
}
