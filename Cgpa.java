import java.util.*;
public class Cgpa
{
 public static void main(String[] args) 
 {
  Scanner sc=new Scanner(System.in);
  int tot=0;
  System.out.print("21CS601 : ");
  int n1=sc.nextInt();
  tot+=n1*3;
  System.out.print("21CS602 : ");
  n1=sc.nextInt();
  tot+=n1*3;
  System.out.print("21CS611 : ");
  n1=sc.nextInt();
  tot+=n1*2;
  System.out.print("21CS612 : ");
  n1=sc.nextInt();
  tot+=n1;
  System.out.print("21CS613 : ");
  n1=sc.nextInt();
  tot+=n1;
  System.out.print("21EN601 : ");
  n1=sc.nextInt();
  tot+=n1;
  System.out.print("21OBT02 : ");
  n1=sc.nextInt();
  tot+=n1*3;
  System.out.print("21PCS01 : ");
  n1=sc.nextInt();
  tot+=n1*3;
  System.out.print("21PIT05 : ");
  n1=sc.nextInt();
  tot+=n1*3;
// System.out.println("TOTAL: "+tot);
  double sgpa=(double)tot/(double)20;
  System.out.print("Last sem cgpa : ");
  double lastcgpa=sc.nextDouble();
  sc.close();
  double cgpa=(sgpa+(lastcgpa*5))/6;
  System.out.printf("SGPA %.2f",sgpa);
  System.out.printf("\nCGPA %.2f",cgpa);
  
 }
}