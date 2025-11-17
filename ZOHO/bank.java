package ZOHO;
import java.util.*;
class Customers 
{
    int id;
    String name;
    String password;
    int acc_num;
    double balance;
    int pin;
    Customers(int id, String name, String password,int pin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.acc_num = 1001 + id;
        this.balance = 10000.00;
        this.pin = pin;
    }
}
public class bank 
{
    static Customers[] cust = new Customers[100];
    static int custc = 0;
    public static void addCus(String name, String pwd,int pin) 
    {
        String ans = "";
        for(char ch : pwd.toCharArray())
        {
            if(Character.isDigit(ch))
            {
                if(ch=='9')
                    ans += '0';
                else
                {
                    int nu = ch-'0';
                    nu++;
                    ans += String.valueOf(nu);
                }
            }
            else if(Character.isLetter(ch))
            {
                int nu = (int)ch;
                if(nu==90)
                    nu = 65;
                else if(nu==122)
                    nu = 97;
                else
                    nu++;
                ans += String.valueOf((char)nu);
            }
        }
        cust[custc] = new Customers(custc, name, ans,pin);
        System.out.println("Successfully account added !!");
        System.out.println("Your Account number : " + cust[custc].acc_num);
        custc++;
    }
    public static Customers isLog(int acc,String name,String pwd)
    {
        String ans = "";
        for(char ch : pwd.toCharArray())
        {
            if(Character.isDigit(ch))
            {
                if(ch=='9')
                    ans += '0';
                else
                {
                    int nu = ch-'0';
                    nu++;
                    ans += String.valueOf(nu);
                }
            }
            else if(Character.isLetter(ch))
            {
                int nu = (int)ch;
                if(nu==90)
                    nu = 65;
                else if(nu==122)
                    nu = 97;
                else
                    nu++;
                ans += String.valueOf((char)nu);
            }
        }
        for(int i=0;i<custc;i++)
        {
            if(cust[i].password.equals(ans) && cust[i].acc_num==acc && cust[i].name.equals(name) && cust[i]!=null)
                return (cust[i]);
        }
        return null;
    }
    public static void transfer(Customers curr,Scanner sc)
    {
        System.out.print("\nReceiver Account Number : ");
        int acc = sc.nextInt();
        System.out.print("\nAmount : ");
        double nu = (double)sc.nextInt();
        System.out.print("\nPin : ");
        int pin = sc.nextInt();
        if(curr.pin!=pin)
            System.out.println("Invalid pin !!");
        else
        {
            Customers c = null;
            boolean found = false;
            for(int i=0;i<custc;i++)
            {
                if(acc==cust[i].acc_num)
                {
                    c = cust[i];
                    found = true;
                    break;
                }
            }
            if(found)
            {
                if(curr.balance < nu)
                    System.out.println("Insufficient balance !!");
                else
                {
                    c.balance += nu;
                    curr.balance -= nu;
                    System.out.println("Your balance : "+curr.balance);
                }
            }
            else
                System.out.println("Receiver account number invalid !!");
        }
    }
    public static void optionMenu(Customers curr,Scanner sc)
    {
        while(true)
        {
            System.out.println("Welcome "+curr.name+" your balance is "+curr.balance+"\n");
            System.out.println("1 - Deposit");
            System.out.println("2 - Withdrawl");
            System.out.println("3 - Transfer");
            System.out.println("4 - Logout");
            int inp = sc.nextInt();
            if(inp==1)
            {
                System.out.print("Amount : ");
                double amt = (double)sc.nextInt();
                curr.balance += amt;
                System.out.println("\nRupees "+amt+" added successfully !!");
                System.out.println("Your current balance "+curr.balance);
            }
            else if(inp==2)
            {
                System.out.print("Amount : ");
                double amt = (double)sc.nextInt();
                System.out.print("\nPin : ");
                int pi = sc.nextInt();
                if(curr.pin==pi)
                {
                    curr.balance -= amt;
                    System.out.println("\nRupees "+curr.balance+" successfully withdrawl !!");
                }
                else
                    System.out.println("Invalid pin !!");
            }
            else if(inp==3)
                transfer(curr,sc);
            else
            {
                System.out.println("Logged out successfully !!");
                break;
            }
        }
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            System.out.println("Welcome to Bank Mark !!");
            System.out.println("1 - Add Customer");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            int inp = sc.nextInt();
            if (inp == 1) 
            {
                sc.nextLine();
                System.out.print("Name : ");
                String name = sc.nextLine();
                System.out.print("\nPassword : ");
                String pwd = sc.nextLine();
                System.out.print("\nSet 4 digit pin : ");
                int pin = sc.nextInt();
                addCus(name, pwd,pin);
            } 
            else if (inp == 2) 
            {
                System.out.print("\nAccount number : ");
                int acc = sc.nextInt();
                sc.nextLine();
                System.out.print("\nName : ");
                String name = sc.nextLine();
                System.out.print("\nPassword : ");
                String pwd = sc.nextLine();
                Customers curr = isLog(acc,name,pwd);
                if(curr!=null)
                    optionMenu(curr,sc);
                else
                    System.out.println("Invaild Account number or password");
            } 
            else 
            {
                System.out.println("Thank you for visiting !!");
                break;
            }
        }
    }
}
