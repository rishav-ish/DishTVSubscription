import java.io.*;

public class clear{
	public void go() throws IOException, InterruptedException{
			
		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
	}
}