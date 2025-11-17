import java.util.*;
public class portal
{
	public static void main(String[] args) 
	{
	    Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		while(true)
		{
		    list.add(sc.nextLine());
		    if(list.get(list.size()-1).equals("}"))
		        break;
		}
		for(String ch : list)
		    System.out.println(ch);
        sc.close();
	}
}
