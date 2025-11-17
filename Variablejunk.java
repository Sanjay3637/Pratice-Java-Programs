import java.util.*;
public class Variablejunk 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split(";");
        for(int i=0;i<arr.length;i++)
        {
            arr[i] = arr[i].replace(" ","");
            arr[i] = arr[i].replace("int","");
            arr[i] = arr[i].replace("char","");
        }
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
            String[] a = arr[i].split(",");
            for(int j=0;j<a.length;j++)
            {
                if(!a[j].contains("="))
                {
                    list.add(a[j]+"=junk");
                }
                else
                {
                    list.add(a[j]);
                }
            }
        }
        System.out.println("Integers");
        for(String ch : list)
        {
            if(!ch.contains("'"))
                System.out.println(ch);
        }
        System.out.println("Characters");
        for(String ch : list)
        {
            if(ch.contains("'"))
                System.out.println(ch);
        }
        sc.close();
    }
}
