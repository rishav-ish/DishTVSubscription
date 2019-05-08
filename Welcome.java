
import java.io.*;
import java.util.*;

public class Welcome{
	
	// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner scan = new Scanner(System.in);
	Channel chan;
	
	public static void main(String[] args) throws Exception {
		
			Welcome w = new Welcome();
			
			w.init();
	}
	
	public void showBanner() {
		
		try{
			clear cls = new clear();
			cls.go();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		
		
		//dish tv subscription title printing...
		
		try{
			File file = new File("welcome.txt");
			BufferedReader print = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line=print.readLine())!=null){
				System.out.println(line);
			}
			
			print.close();
			
		}catch(FileNotFoundException fn){
			fn.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public void init() throws Exception{

		do{
			
		showBanner();
		
		System.out.println("\nPlease enter your choice...");
		System.out.println("1.Login");
		System.out.println("2.SignUp");
		System.out.println("3.Channel Catalogue");
		System.out.println("4.exit");
		System.out.println("Enter your choice ");
		
		int choice = -1;
		
		try{
			 choice = scan.nextInt();
		}catch(InputMismatchException ime){
			  choice = -1;
		}catch(NoSuchElementException ex){
			System.out.println("Something is not looking right");
			System.out.println("I don't want to terminate but...you know that ....!ahh");
			System.exit(0);
		}
		
		switch(choice){
			case 1:
				System.out.println("\nPlease enter your mobile number");
				String mob = scan.next();
				
				System.out.println("\nPlease enter your password");
				String password = new BufferedReader(new InputStreamReader(System.in)).readLine();
				
				Customer c = new Customer();
				
				if(c.login(mob,password)){
					showBanner();
					c.display();
				}
			
				break;
				
			case 2:
				Customer c1 = new Customer();
				
				if(c1.createUser()){
					showBanner();
					c1.display();
				}
			
				break;
				
			case 3:
				chan = new Channel();
				showBanner();
				chan.catalogue();
				
				
				
				break;
				
			case 4:
				System.exit(0);
				
			default:
				
				System.out.println("Please choose the correct option....!");
				
		}
		
				System.out.println("\n\nPlease press enter to go back");
				System.in.read();
				
				scan = null;
				scan = new Scanner(System.in);
				
		}while(true);	
		
	}
	
	
}