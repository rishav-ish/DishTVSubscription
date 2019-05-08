/*
	Customer class
	programmed by Rishav
*/


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Customer{
	
	Scanner input = new Scanner(System.in);

	ArrayList<String> mob = new ArrayList<String>();
	
	ArrayList<String> name = new ArrayList<String>();			//user name...
	ArrayList<String> password = new ArrayList<String>();       //user password....
	HashSet<Integer> subscribe = new HashSet<Integer>();		//user subscription...
	private String m;
	private String n;
	
	
	public Customer(){
		
		try{
			
			File file = new File("user_details.txt");
			// File file2 = new File("user_subscription.txt");
			
			BufferedReader user = new BufferedReader(new FileReader(file));
			// BufferedReader user_s = new BufferedReader(new FileReader(file2));
			
			
			String temp;

			while((temp=user.readLine())!=null) {
				
				String words[] = temp.split(",");
				
				mob.add(words[0]);
				name.add(words[1]);
				password.add(words[2]);


				// subscribe.add(user_s.readLine());
			}
			
			user.close();
			// user_s.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public boolean createUser(){
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("\nEnter your mobile Number without any 0 or country code prefix");
			m = br.readLine();
			
			MobileNumberValidation mnv = new MobileNumberValidation();
			
			
			
			if(mob.contains(m)){
				System.out.println("\nUser already exist");
				return false;
			}
			
			boolean truth =  mnv.isValid(m);
			
			if(truth == false){
				System.out.println("Not a valid mobile number...please retry");
				return false;
			}
			
			System.out.println("\nEnter your name");
		    n = br.readLine();
			
			while(n.isEmpty() || n.contains(",")){
				System.out.println("Name can't be empty or containing comma... please retry");
				n = br.readLine();
				
				/* if(n.contains(",")){
						System.out.println("Password can't contain comma please make new password");
						n = input.next();
					} */
			}
			
			
			System.out.println("\nEnter your password, it should not contain spaces and comma in between");
			String p = br.readLine();
			
			
			while(p.isEmpty() || p.contains(",")){
				//if(p.isEmpty()){
					System.out.println("Password can't be empty or contaning comma... please retry");
					p = input.next();
				//}
				
					/* if(p.contains(",")){
						System.out.println("Password can't contain comma please make new password");
						p = input.next();
					} */
				
				
			}
			
			
			String temp = m + "," + n + "," + p;
			
			File file = new File("user_details.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			
			bw.write(temp);
			bw.newLine();
			
			
			bw.close();
			
				
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return true;
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
		System.out.println("4.exit");
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
				System.exit(0);
				
			default:
				System.out.println("\nPlease enter your choice wisely\n");
		}
		
		
		System.out.println("\n\nPlease press enter...");
		
		System.in.read();
		input = null;
		input = new Scanner(System.in);
				
		//display();
		}while(true);
		
	}
	

	
	public void getSubscription() throws Exception{
		
		Channel ch = new Channel();
		// Scanner input = new Scanner(System.in);
		
		int max = ch.getSize();
		
		
		System.out.println("\nPlease enter the number of channels you want to choose from " + max + " channels.");
		int size = 0;
		
		try{
			
			size = input.nextInt();
			String garbage = input.nextLine();            //to hold anything extra putten by user...
			
		}catch(InputMismatchException ie){
			System.out.println("\n***Please ensure following while giving inputs***");
			System.out.println("1.You should enter the integer according to the index and press enter");
			System.out.println("2.Dont't enter any other characters inbetween");
			
			input = null;
			input = new Scanner(System.in);
			return;
		}catch(NoSuchElementException ex){
			System.out.println("Something is not looking right");
			System.out.println("I don't want to terminate but...you know that ....!ahh");
			System.exit(0);
		}
		
		if(size > max || size < 1){
			System.out.println("Please choose between 1 and " + max + " inclusive.");
			return;
		}
		
		ch.catalogue();
		
		System.out.println("\n\nEnter the index of " + size + " channels that your want to choose from the above catalogue");
		System.out.println("NOTE-if you add same channel multiple times,it will be added only once");
		// int temp[] = new int[size];
		
		
		
		int temp1 = 0;
		HashSet<Integer> sub = new HashSet<Integer>();
		HashSet<Integer> markPrescence = new HashSet<Integer>();
		
		try{
			for(int i = 0; i< size;++i){
				
				temp1 = input.nextInt();
			
				if(temp1 >= 0 && temp1 <= max){
					if(subscribe.contains(temp1))
						markPrescence.add(temp1);
					else
						sub.add(temp1);
				}else{
					System.out.println("\n" + temp1 + ", can't be added since it is not present...");
					break;
				}
			}
			
			String garbage = input.nextLine();
			
			subscribe.addAll(sub);
			
		}catch(InputMismatchException iex){
			System.out.println("\n***Please ensure following while giving inputs***");
			System.out.println("1.You should enter the integer according to the index and press enter");
			System.out.println("2.Dont't enter any other characters inbetween");
			System.out.println("Note:- Nothing get added now please retry with correct inputs");
			input = null;
			input = new Scanner(System.in);
			return;
		}catch(NoSuchElementException ex){
			System.out.println("Something is not looking right");
			System.out.println("I don't want to terminate but...you know that ....!ahh");
			System.exit(0);
		}
		
		
		// Channel ch = new Channel
		
		if(!markPrescence.isEmpty()){
			System.out.println("\nFollwing channel are already present so can't be added");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			
			ch.user_subscription(markPrescence);
		
			System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------");
		}

		
		System.out.println("\n\n-> " + sub.size() + " channels got added.");
		System.out.println("-> Current Amount = Rs. " + ch.getTotalBalance(sub));
		System.out.println("-> Total Amount Due = Rs. " + ch.getTotalBalance(subscribe));
		System.out.println("-> you're now  subscribed to " + subscribe.size() + " channels in total.");
		
		String file = m + ".txt";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
		
		Iterator it = subscribe.iterator();
		
		/* for(int i = 0;i<size;++i){
			bw.write(Integer.toString(temp[i]));
			bw.newLine();
		} */
		
		while(it.hasNext()){
			int temp = (int) it.next();
			bw.write( Integer.toString(temp));
			bw.newLine();
		}
		
		bw.close();
		
		
	}
	
	public void showSubscription() throws Exception{
		new clear().go();
		System.out.println("you are currently subscribe to following channels");
		
		String temp = m + ".txt";
		
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(temp));
			
			String line;
			
			while((line=br.readLine())!=null){
				subscribe.add(Integer.parseInt(line));
			}
			
			br.close();
			
			Channel ch = new Channel();
			
			System.out.println("\nYou are subscribed to following channels");
			
			ch.user_subscription(subscribe);
			
		}catch(FileNotFoundException fex){
			System.out.println("It's look like you don't have any subscription please first do it.");
			System.out.println("Press enter to go back");
			System.in.read();
			display();
		}
	}
	
	public void removeSubscription() throws Exception{
		
		if(subscribe.isEmpty()){
			System.out.println("It's look like you are not subscribed to any channel.");
			return;
		}
		
		showSubscription();
		
		int max = subscribe.size();
		
		System.out.println("\n\nPlease enter the number of channels you want to remove");
		System.out.println("\nNOTE- if you try to remove same subscribed channel multiple times, it will be removed only once"); 
		int size = 0;
		
		try{
			
			size = input.nextInt();
			String garbage = input.nextLine();              //to hold any thing extra putten by user...
			
		}catch(InputMismatchException ie){
			System.out.println("\n***Please ensure following while giving inputs***");
			System.out.println("1.You should enter the integer according to the index and press enter");
			System.out.println("2.Dont't enter any other characters inbetween");
			System.out.println("Note:- Nothing get removed now please retry with correct inputs");
			input = null;
			input = new Scanner(System.in);
			return;
		}catch(NoSuchElementException ex){
			System.out.println("Something is not looking right");
			System.out.println("I don't want to terminate but...you know that ....!ahh");
			System.exit(0);
		}
			
		
		if(size < 1 || size > max){
			System.out.println("Please enter the number between 1 and " + max + " inclusive.");
			return;
		}
		
		System.out.println("Enter the " + size + " channels hash code on extreme right");
		
		int channel[] = new int[size];
		ArrayList<Integer> del = new ArrayList<Integer>();
		
		try{
			
			for(int i = 0 ;i<size;++i){
			
				int temp;
			
				temp = input.nextInt();
			
				if(!subscribe.contains(temp)){
					System.out.println("you are not subscibed to channel hash " + temp + ". Please retry since no channel will be deleted");
					return;
				}else
					del.add(temp);
			
		
			
			}
			
			String garbage = input.nextLine();
			
		}catch(InputMismatchException ie){
			System.out.println("\n***Please ensure following while giving inputs***");
			System.out.println("1.You should enter the integer according to the index and press enter");
			System.out.println("2.Dont't enter any other characters inbetween");
			
			input = null;
			input = new Scanner(System.in);
			return;
		}catch(NoSuchElementException ex){
			System.out.println("Something is not looking right");
			System.out.println("I don't want to terminate but...you know that ....!ahh");
			System.exit(0);
		}
			
		
		for(int i = 0;i<del.size();++i)
			subscribe.remove(del.get(i));
		
		
		// System.out.println("Successfully Deleted");
		
		String file = m + ".txt";
		
		
		File file1 = new File(file);
		
		if(file1.delete()){
			System.out.println("\nSuccessfully deleted");
			file1.createNewFile();
		}
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file1,true));
		
		Iterator it = subscribe.iterator();
		
		/* for(int i = 0;i<size;++i){
			bw.write(Integer.toString(temp[i]));
			bw.newLine();
		} */
		
		while(it.hasNext()){
			int temp = (int) it.next();
			bw.write( Integer.toString(temp));
			bw.newLine();
		}
		
		bw.close();
		
	}
	
	public boolean login(String mob,String password){
		if(this.mob.contains(mob)){
			
			int index = this.mob.indexOf(mob);
			
			// System.out.println(this.password.get(index));
			
			if(password.equals(this.password.get(index))){
				m = mob;
				n = this.name.get(index);
				
				
				//loading subscription done by users...
				
				String temp = m + ".txt";
				
				
				
				try{
					
					BufferedReader br = new BufferedReader(new FileReader(temp));
			
					String line;
			
					while((line=br.readLine())!=null){
							subscribe.add(Integer.parseInt(line));
					}
					
					br.close();
				}catch(FileNotFoundException fex){
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				
				return true;
			}else{
				System.out.println("Wrong password");
				return false;
			}
			
			
		}else{
			System.out.println("Sorry, user doesn't exist");
			return false;
		}
	}
	
	
	
	
}

