/*
	Remember while initializing the catalog file
	make sure that you write channel name and price 
	with comma in between and no spaces between comma
	other wise code will break
	
	e.g :- Indore tv,20
	
	@Rishav
*/


import java.io.*;
import java.util.*;

public class Channel{
	
	ArrayList<String> ch = new ArrayList<String>();
	ArrayList<Integer> price = new ArrayList<Integer>();

	public Channel(){

	try{
		File file = new File("channel.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line;

		while((line=br.readLine())!=null){
			 String words[] = line.split(",");
			
			 ch.add(words[0]);
			 price.add(Integer.parseInt(words[1]));
		}

		br.close();

	}catch(Exception ex){
		ex.printStackTrace();
		System.out.println("something don't look right");
	}				 
		
	}

	public void catalogue(){
		int index = 0;		

		Iterator ch_itr = ch.iterator();
		Iterator pr_itr = price.iterator();

		System.out.printf("\n%-6s %-80s %20s\n","index","Channel Name", "Price");
		
		while(ch_itr.hasNext())
			 System.out.printf("\n%-6d %-80s %20d",index,ch_itr.next(),pr_itr.next(),++index);
	}
	
	/* public void user_subscription(int[] sub){
		
		int index = 1;
		
		
		for(int i = 0;i<sub.length;++i){
			System.out.printf("\n%-6d %-80s %20d",index,ch.get(sub[i]),price.get(sub[i]),++index);
		}
		
	} */
	
	public void user_subscription(HashSet<Integer> hs){
		Iterator it =  hs.iterator();
		int index = 1;
		
		System.out.printf("\n%-6s %-80s %20s %20s\n","index","Channel Name","Price","Hash");
		
		while(it.hasNext()){
			int temp = (Integer) it.next();
			
			System.out.printf("\n%-6d %-80s %20d %20d",index,ch.get(temp),price.get(temp),temp,++index);
		}
	}
	
	public int getTotalBalance(HashSet<Integer> hs){
		
		int sum = 0;
		Iterator it = hs.iterator();
		
		/* for(int i = 0; i < sub.length; ++i)
				sum  = sum + price.get(sub[i]); */
			
		while(it.hasNext()){
			int temp = (Integer) it.next();
			
			sum = sum + price.get(temp);
		}
			
		return sum;
	}
	
	
	
	public int getTotalBalance(ArrayList<Integer> hs){
		
		int sum = 0;
		Iterator it = hs.iterator();
		
		/* for(int i = 0; i < sub.length; ++i)
				sum  = sum + price.get(sub[i]); */
			
		while(it.hasNext()){
			int temp = (Integer) it.next();
			
			sum = sum + price.get(temp);
		}
			
		return sum;
	}
	
	public int getSize(){
		return ch.size();
	}
}	 