package ZOHO;
import java.util.*;
public class SimpleMail 
{
    static class User 
    {
        int id;
        String name;
        String email;
        String password;
        User(int id, String name, String email, String password) 
        {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }
    static class Mail 
    {
        int id;
        String owner;
        String folder;
        String subject;
        String body;
        String other;
        Mail(int id, String owner, String folder, String subject, String body, String other) 
        {
            this.id = id;
            this.owner = owner;
            this.folder = folder;
            this.subject = subject;
            this.body = body;
            this.other = other;
        }
    }
    static class MailFact 
    {
        private int count = 1;
        Mail create(String owner, String folder, String subject, String body, String other) 
        {
            return new Mail(count++, owner, folder, subject, body, other);
        }
    }
    static final int MAX_USERS = 50;
    static final int MAX_MAILS = 200;
    static User[] users = new User[MAX_USERS];
    static Mail[] mails = new Mail[MAX_MAILS];
    static int userc = 0;
    static int mailc = 0;
    static User curr = null;
    static boolean emailValid(String email) 
    {
        return email.matches("^[a-zA-Z0-9._]+@gmail\\.com$");
    }
    static boolean passValid(String password) 
    {
        return password.length()>6;
    }
    static boolean emailCheck(String email) 
    {
        for (int i = 0; i < userc; i++) 
        {
            if (users[i].email.equals(email))
                return true;
        }
        return false;
    }
    static void signup(Scanner sc) 
    {
        if (userc >= MAX_USERS) 
        {
            System.out.println("DB full");
            return;
        }
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.print("\nEmail : ");
        String email = sc.nextLine();
        System.out.print("\nPassword : ");
        String password = sc.nextLine();
        if (!emailValid(email)) 
        {
            System.out.println("Email invalid !!");
            return;
        }
        if (emailCheck(email)) 
        {
            System.out.println("Email already exsist !!");
            return;
        }
        if (!passValid(password)) 
        {
            System.out.println("Weak Password !!");
            return;
        }
        users[userc++] = new User(userc, name, email, password);
        System.out.println("Signup successfull !!");
    }
    static void login(Scanner sc)
    {
        System.out.println("Email : ");
        String em = sc.nextLine();
        System.out.println("Password : ");
        String pass = sc.nextLine();
        for(int i=0;i<userc;i++)
        {
            if(users[i].email.equals(em) && users[i].password.equals(pass))
            {
                curr = users[i];
                System.out.println("Login successfull !!");
                System.out.println("Welcome : "+curr);
                return;
            }
        }
        System.out.println("Invalid email or password !!");
    }
    static void compose(Scanner sc)
    {
        if(mailc>=MAX_MAILS)
        {
            System.out.println("Mail DB full !!");
            return;
        }
        System.out.println("To : ");
        String to = sc.nextLine();
        System.out.println("Subject : ");
        String sub = sc.nextLine();
        System.out.println("Body : ");
        String bd = sc.nextLine();
        boolean found = false;
        for(int i=0;i<userc;i++)
        {
            if(users[i].email.equals(to))
                found = true;
        }
        if(found)
        {
            mails[mailc++] = new Mail(mailc,curr.email,found?"SENT":"FAILED",sub,bd,to);
            System.out.println("Mail sent !!");
        }
        else
        {
            System.out.println("Invalid receiver !!");
        }
    }
    static void view(String folder)
    {
        for(int i=0;i<mailc;i++)
        {
            Mail m = mails[i];
            if(m.owner.equals(curr.email) && m.folder.equals(folder))
            {
                System.out.println("["+m.subject+"]"+m.body+" ("+m.other+")");
            }
        }
    }
    static void readBySubject(Scanner sc)
    {
        System.out.print("Subject: "); String sub=sc.nextLine();
        for(int i=0;i<mailc;i++)
        {
            Mail m=mails[i];
            if(m.owner.equals(curr.email) && m.subject.equals(sub) && !m.folder.equals("TRASH"))
            {
                System.out.println("Mail Content: "+m.body);
            }
        }
    }
    static void trashBySubject(Scanner sc)
    {
        System.out.print("Subject to Trash: "); String sub=sc.nextLine();
        for(int i=0;i<mailc;i++)
        {
            Mail m=mails[i];
            if(m.owner.equals(curr.email) && m.subject.equals(sub))
            {
                mails[i] = new Mail(m.id,m.owner,"TRASH",m.subject,m.body,m.other);
                System.out.println("Moved to Trash.");
                return;
            }
        }
        System.out.println("Mail not found.");
    }
    static void filterByMailId(Scanner sc)
    {
        System.out.print("Enter mail-id (other party email): ");
        String id=sc.nextLine();
        for(int i=0;i<mailc;i++)
        {
            Mail m=mails[i];
            if(m.owner.equals(curr.email) && m.other.equals(id) && !m.folder.equals("TRASH"))
            {
                System.out.println(m.folder+": "+m.subject+" "+m.body);
            }
        }
    }
    static void searchText(Scanner sc)
    {
        System.out.print("Enter search text: ");
        String text=sc.nextLine();
        for(int i=0;i<mailc;i++)
        {
            Mail m=mails[i];
            if(m.owner.equals(curr.email) && !m.folder.equals("TRASH") &&
               (m.subject.contains(text)||m.body.contains(text)))
               {
                System.out.println(m.folder+": "+m.subject+" "+m.body);
            }
        }
    }
    static void deleteAccount(Scanner sc)
    {
        System.out.print("Confirm password: "); 
        String p=sc.nextLine();
        if(!curr.password.equals(p))
        {
            System.out.println("Wrong password."); 
            return;
        }
        for(int i=0;i<userc;i++)
        {
            if(users[i]==curr){
                for(int j=i;j<userc-1;j++) users[j]=users[j+1];
                userc--; break;
            }
        }
        for(int i=0;i<mailc;)
        {
            if(mails[i].owner.equals(curr.email))
            {
                for(int j=i;j<mailc-1;j++) mails[j]=mails[j+1];
                mailc--;
            } 
            else 
                i++;
        }
        curr=null;
        System.out.println("Account deleted.");
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(true){
            if(curr==null){
                System.out.println("\n1.Signup \n2.Login \n3.Exit");
                int c=sc.nextInt(); sc.nextLine();
                if(c==1) signup(sc);
                else if(c==2) login(sc);
                else break;
            } 
            else 
            {
                System.out.println("\nWelcome "+curr.email);
                System.out.println("1.Compose \n2.Inbox \n3.Sent \n4.Failed \n5.Trash \n6.ReadBySubject");
                System.out.println("7.TrashBySubject \n8.FilterByMailId \n9.SearchText \n10.DeleteAccount \n11.Logout");
                int c=sc.nextInt(); sc.nextLine();
                switch(c)
                {
                    case 1: 
                            compose(sc); 
                            break;
                    case 2: 
                            view("INBOX"); 
                            break;
                    case 3: 
                            view("SENT"); 
                            break;
                    case 4: 
                            view("FAILED"); 
                            break;
                    case 5: 
                            view("TRASH"); 
                            break;
                    case 6: 
                            readBySubject(sc); 
                            break;
                    case 7: 
                            trashBySubject(sc); 
                            break;
                    case 8: 
                            filterByMailId(sc); 
                            break;
                    case 9: 
                            searchText(sc); 
                            break;
                    case 10: 
                            deleteAccount(sc); 
                            break;
                    case 11: 
                            curr=null; 
                            break;
                }
            }
        }
    }
}
