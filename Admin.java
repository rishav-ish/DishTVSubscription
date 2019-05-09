import java.io.*;
import java.util.*;

public class Admin{
	
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<String> password = new ArrayList<String>();
	String n;
	
	public Admin()  {
		
		try{
			
			File file = new File("admin_details.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line=br.readLine())!=null){
				String temp[] = line.split(",");
				
				name.add(temp[0]);
				password.add(temp[1]);
			}
			
			br.close();
		
		}catch(FileNotFoundException fex){
			System.out.println("It's look like admin_details.txt file has been deleted");
			System.out.println("Please re-initialize it with name,password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void display() throws Exception{
		
		do {
		
		new clear().go();
		
		// Scanner input = new Scanner(System.in);
		
		System.out.println("\nWelcome " + n);
		
		System.out.println("\nChoose from the following");
		System.out.println("\n1.Choose Channels");
		System.out.println("2.Show subscribe channels");
		System.out.println("3.Remove subscribe channels");
		System.out.println("4.Log out");
		//System.out.println("5.exit");
		System.out.print("\nYour choice ");
		
		int choice = -1; // = input.nextInt();
		
		try{
			choice = input.nextInt();
			String garbage = input.nextLine();
		}catch(InputMismatchException iex){
			choice = -1;
		}catch(NoSuchElementException ex){
			System.out.println("Something is not looking right");
			System.out.println("I don't want to terminate but...you know that ....!ahh");
			System.exit(0);
		}
		
		switch(choice){
			case 1:
				getSubscription();
				
				break;
				
			case 2:
				showSubscription();
				
				break;
				
			case 3:
				removeSubscription();
				break;
				
			case 4:
				return;
				
			/* case 5:
				System.exit(0); */
				
			default:
				System.out.println("\nPlease enter your choice wisely\n");
		}
		
		
		System.out.println("\n\nPlease press enter...");
		
		System.in.read();
		String garbage = input.nextLine();
		input = null;
		input = new Scanner(System.in);
				
		//display();
		}while(true);
		
	}
	