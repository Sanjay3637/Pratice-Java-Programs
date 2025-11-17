// import java.util.*;
// public class Main 
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         char[] arr = str.toCharArray();
//         int max = 0;
//         int count = 0;
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < arr.length; i++) 
//         {
//             count = 0;
//             for(int j = i+1;j<arr.length-1;j++)
//             {
//                 if(arr[i]==arr[j])
//                 {
//                     count++;
//                 }
//             }
//             if(max==count)
//             {
//                 sb.append(arr[i]);
//             }
//             else if(max<count)
//             {
//                 max = count;
//                 sb.setLength(0);
//                 sb.append(arr[i]);
//             }

//         }
//         System.out.print(sb.toString());
//         sc.close();
//     }
// }

// import java.util.Scanner;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int num = sc.nextInt();
//         int dem = sc.nextInt();
//         if(dem==0)
//         {
//             System.out.print("ERROR : can't divisible by 0");
//         }
//         else
//         {
//             System.out.print((double)num/dem);
//         }
//         sc.close();
//     }
// }

// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         if(str.contains("@"))
//         {
//             String[] arr = str.split("@");
//             String a2 = arr[1];
//             if(a2.contains("gmail") && a2.contains(".") && (a2.contains("com") || a2.contains("in")))
//             {
//                 System.out.print("Valid email address");
//             }
//             else
//             {
//                 System.out.print("Invalid email address");
//             }
//         }
//         else
//         {
//             System.out.println("Invalid email address");
//         }
//     }
// }

// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int sum = 1;
//         for(int i=1;i<=n;i++)
//         {
//             sum = sum * i;
//         }
//         System.out.print(sum);
//     }
// }

// import java.util.*;

// public class Main 
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);

//         int account_balance = 100000;

//         while (true) 
//         {
//             System.out.println("\nEnter 1 for deposit");
//             System.out.println("Enter 2 for withdraw");
//             System.out.println("Enter 3 for check balance");
//             System.out.println("Enter 4 for exit");

//             int n = sc.nextInt();

//             if (n == 1) 
//             {
//                 System.out.print("Enter the amount to deposit: ");
//                 int val = sc.nextInt();
//                 account_balance += val;
//                 System.out.println("Successfully deposited");
//                 System.out.println("Your current bank balance: " + account_balance);
//             } 
//             else if (n == 2)
//              {
//                 System.out.print("Enter the amount to withdraw: ");
//                 int amt = sc.nextInt();
//                 if (amt > account_balance) 
//                 {
//                     System.out.println("Insufficient balance to withdraw the amount: " + amt);
//                     System.out.println("Your current bank balance: " + account_balance
//                             + "\nEnter amount less than to your bank balance to withdraw");
//                 } 
//                 else 
//                 {
//                     account_balance -= amt;
//                     System.out.println("Successfully withdrawn the amount: " + amt);
//                     System.out.println("Your current bank balance: " + account_balance);
//                 }
//             } 
//             else if (n == 3) 
//             {
//                 System.out.println("Your current bank balance: " + account_balance);
//             } 
//             else if (n == 4) 
//             {
//                 System.out.println("Exiting...");
//                 break;
//             }
//              else
//              {
//                 System.out.println("Invalid option! Please try again.");
//             }
//         }
//         sc.close();
//     }
// }



// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String s1 = sc.nextLine();
//         String s2 = sc.nextLine();
//         String a1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//         String a2 = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
//         char[] arr1 = a1.toCharArray();
//         char[] arr2 = a2.toCharArray();
//         char[] str1 = s1.toCharArray();
//         char[] str2 = s2.toCharArray();
//         int sum = 0;
//         int sum2 = 0;
//         for(int i=0;i<str1.length;i++)
//         {
//             for(int j=0;j<arr1.length;j++)
//             {
//                 if(str1[i]==arr1[j])
//                 {
//                     sum += j;
//                 }
//             }
//         }
//         for(int i=0;i<str2.length;i++)
//         {
//             for(int j=0;j<arr2.length;j++)
//             {
//                 if(str2[i]==arr2[j])
//                 {
//                     sum2 += j;
//                 }
//             }
//         }
//         if(sum==sum2)
//         {
//             System.out.print("YES");
//         }
//         else
//         {
//             System.out.print("NO");
//         }
//     }
// }




// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         Random rn = new Random();
//         int secnum = rn.nextInt(100)+1;
//         int attempt = 10;
//         while(attempt>0)
//         {
//             System.out.print("\nEnter your guess : ");
//             int count = sc.nextInt();
//             if(count<secnum)
//             {
//                 System.out.println("Your guess is too low");
//             }
//             else if(count>secnum)
//             {
//                 System.out.println("Your guess is too high");
//             }
//             else
//             {
//                 System.out.println("Congratulations your guess is correct");
//                 break;
//             }
//             attempt--;
//             System.out.println("Your remaining attempts : " + attempt );
//         }
//     }
// }








// import java.util.*;
// public class Main 
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);
//         try
//         {
//             int n1 = sc.nextInt();
//             int n2 = sc.nextInt();
//             if(n2==0)
//             {
//                 System.out.print("can't able to divide by 0");
//             }
//             else
//             {
//                 System.out.print((double)n1/n2);
//             }
//         }
//         catch(InputMismatchException e)
//         {
//             System.out.println("Error : Please enter an valid number");
//         }
//         finally
//         {
//             sc.close();
//         }
//     }
// }








// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         Stack<Character> st = new Stack<>();
//         for(int i=0;i<str.length();i++)
//         {
//             st.push(str.charAt(i));
//         }
//         while (!st.isEmpty()) {
//             System.out.print(st.pop());
//         }
//         sc.close();
//     }
// }




//extra word = bthlo5t9
// input : hello
// output : oll_hbt_lo5_9






//write a program to find x power of n is odd or even
// 0 to 10 pow of 9 +7















// import java.math.BigInteger;
// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         long x = sc.nextLong();
//         long n = sc.nextLong();
//         BigInteger a = new BigInteger(String.valueOf(x));
//         BigInteger b = a.pow((int)n);
//         System.out.println("RESULT :  "+ b);
//         if(b.mod(BigInteger.TWO).equals(BigInteger.ZERO))
//         {
//             System.out.print("EVEN");
//         }
//         else
//         {
//             System.out.print("ODD");
//         }
//         sc.close();
//     }
// }







// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         System.out.print(str.trim());
//         sc.close();
//     }
// }











// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         char[] arr = str.toCharArray();
//         int start = 0;
//         int end = arr.length-1;
//         String vow = "aeiouAEIOU";
//         while(start<end)
//         {
//             if(vow.indexOf(arr[start])!=-1)
//             {
//                 start++;
//             }
//             if(vow.indexOf(arr[end])!=-1)
//             {
//                 end--;
//             }
//             else
//             {
//                 char temp = arr[start];
//                 arr[start] = arr[end];
//                 arr[end] = temp;
//                 start++;
//                 end--;
//             }
//         }
//         for(int i=0;i<arr.length;i++)
//         {
//             System.out.print(arr[i]);
//         }
//     }
// }











// import java.util.*;
// public class Main
// {
//     public static boolean Prime(int n)
//     {
//         boolean res = true;
//         if(n<=2 && n>0)
//         {
//             return true;
//         }

//         else
//         {
//             for(int i=2;i<=n/2;i++)
//             {
//                 if(n%i==0)
//                 {
//                     res = false;
//                 }
//             }
//         }
//         return res;
//     }
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int start = sc.nextInt();
//         int sum = start%9;
//         if(sum==0 && start!=0)
//         {
//             System.out.print("9");
//         }   
//         else
//         {
//             System.out.print(sum);
//         }
//     }
// }













// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         String[] arr = str.split(" ");
//         for(int i=0;i<arr.length;i++)
//         {
//             System.out.print(arr[i].substring(1) + "-"+arr[i].substring(0,1)+"ay"+" ");
//             sc.close();
//         }
//     }
// }







// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         String s = sc.nextLine();
//         System.out.print(str.replaceAll(s,""));
//         sc.close();
//     }
// }



// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         String str = "Sanjayu2";
//         System.out.print(str.matches("^[A-Za-z]{7,}\\d$"));
//     }
// }





// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         String str = "[({})]";
        
//     }
// }



// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         int max = 0;
//         for(int i=0;i<str.length();i++)
//         {
//             int index = str.indexOf(str.charAt(i));
//             int dex = str.lastIndexOf(str.charAt(i));
//             if(index!=dex)  
//             {
//                 int len = dex - index - 1;
//                 max = Math.max(max,len);
//             }
//         }
//         System.out.print(max);
//     }
// }







// import java.util.*;
// public class Main
// {
//     public static String Num(String str)
//     {
//         char[] a = str.toCharArray();
//         String alpha = "abcdefghijklmnopqrstuvwxyz";
//         char[] arr = alpha.toCharArray();
//         StringBuilder sb = new StringBuilder();
//         for(int i=0;i<a.length;i++)
//         {
//             for(int j=0;j<arr.length;j++)
//             {
//                 if(a[i]==arr[j]);
//                 {
//                     sb.append(j);
//                     break;
//                 }
//             }
//         }
//         return sb.toString();
//     }
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String first = sc.nextLine();
//         String second = sc.nextLine();
//         String target = sc.nextLine();
//         String fis = Num(first);
//         String sec = Num(second);
//         String tar = Num(target);
//         int a = Integer.parseInt(fis);
//         int b = Integer.parseInt(sec);
//         int c = Integer.parseInt(tar);
//         if((a+b)==c)
//         {
//             System.out.print("true");
//         }
//         else
//         {
//             System.out.print("false");
//         }
//     }
// }





// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         String[] arr = str.split(" ");
//         String s = "";
//         int count = -1;
//         int i = 0;
//         for(i=0;i<arr[0].length();i++)
//         {
//             for(int j=0;j<arr.length;j++)
//             {
                
//                 if(arr[j].contains(arr[0].substring(0,i+1)))
//                 {
//                     count = 1;
//                 }
//                 else
//                 {
//                     count = 0;
//                 }
//             }
//             if(count==1)
//             {
//                 s = arr[0].substring(0,i+1);
//             }
//         }
//         if(count==-1)
//         {
//             System.out.print("None");
//         }
//         else
//         {
//             System.out.print(s);
//         }
//         sc.close();
//     }
// }




// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = scanner.nextInt();
        
//         // dp[i] will store the number of ways to partition the number i
//         long[] dp = new long[n + 1];
//         dp[0] = 1;  // Base case: There is 1 way to partition 0 (using no parts).
        
//         // Start with each number from 1 to n, updating the dp array.
//         for (int i = 1; i <= n; i++) {
//             for (int j = i; j <= n; j++) {
//                 dp[j] += dp[j - i];
//             }
//         }

//         // The answer is stored in dp[n], which gives the number of ways to partition n
//         System.out.println(dp[n]);
        
//         scanner.close();
//     }
// }



// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int[] a = new int[n];
//         for(int i = 0; i < n; i++)
//         {
//             a[i] = sc.nextInt();
//         }
//         int num = sc.nextInt();
//         int max = 0;
//         int sum = 0;
//         for(int i=0;i<num;i++)
//         {
//             for(int j=0;j<n;j++)
//             {
//                 max = Math.max(max,a[j]);
//             }
//             for(int j=0;j<n;j++)
//             {
//                 if(a[j]==max)
//                 {
//                     a[j] = 0;
//                 }
//             }
//             sum += max;
//             max = 0;
//         }
//         System.out.print(sum);
//     }
// }







// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int[] arr = new int[n];
//         for(int i=0;i<n;i++)
//             arr[i] = sc.nextInt();
//         for(int i=0;i<n-1;i++)
//         {
//             if(arr[i]>arr[i+1])
//             {
//                 int temp = arr[i];
//                 arr[i] = arr[i+1];
//                 arr[i+1] = temp;
//             }
//         }
//         for(int ch : arr)
//         {
//             System.out.print(ch + " ");
//         }
//     }
// }







// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int[] arr = new int[n];
//         for(int i=0;i<n;i++)
//             arr[i] = sc.nextInt();
//         int count = 0;
//         int temp;
//         for(int i=0;i<n-1;i++)
//         {
//             for(int j=0;j<n-i-1;j++)
//             {
//                 if(arr[j]>arr[j+1])
//                 {
//                     temp = arr[j];
//                     arr[j] = arr[j+1];
//                     arr[j+1] = temp;
//                 }
//             }
//             count++;
//             System.out.println("Iteration : " + count);
//             for(int j=0;j<n;j++)
//             {
    //                 System.out.print(arr[j] + " ");
    //             }
    //             System.out.println();
    //         }
//         System.out.println("Soreted array : ");
//         for(int ch : arr)
//         {
//             System.out.print(ch + " ");
//         }
//         sc.close();
//     }
// }





// import java.util.*;

// public class Main {
//     public static int[] Mergesort(int[] n) 
//     {
//         if(n.length <= 1)
//         {
    //             return n;
    //         }
//         int mid = n.length / 2;
//         int[] first = Mergesort(Arrays.copyOfRange(n, 0, mid));
//         int[] last = Mergesort(Arrays.copyOfRange(n, mid, n.length));
//         return Merge(first, last);
//     }

//     public static int[] Merge(int[] first, int[] last) {
//         int i = 0, j = 0, k = 0;
//         int[] res = new int[first.length + last.length];
//         while (i < first.length && j < last.length) {
//             if (first[i] > last[j]) { // Corrected the typo here
//                 res[k] = last[j];
//                 k++;
//                 j++;
//             } else {
//                 res[k] = first[i];
//                 k++;
//                 i++;
//             }
//         }
//         while (i < first.length) {
    //             res[k] = first[i];
    //             k++;
//             i++;
//         }
//         while (j < last.length) {
    //             res[k] = last[j];
    //             k++;
//             j++;
//         }
//         return res;
//     }

//     public static void main(String[] args) {
    //         Scanner sc = new Scanner(System.in);
    //         int n = sc.nextInt();
    //         int[] arr = new int[n];
    //         for (int i = 0; i < n; i++) {
//             arr[i] = sc.nextInt();
//         }

//         System.out.print("Before Merge sort : ");
//         for (int ch : arr) {
    //             System.out.print(ch + " ");
//         }

//         arr = Mergesort(arr);
//         System.out.println();
//         System.out.print("After Merge sort : ");
//         for (int ch : arr) {
//             System.out.print(ch + " ");
//         }
//     }
// }




/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
// public class Main
// {
// 	public static void main(String[] args) {
    // 		int n=8973;
    // 		System.out.print("ANSWER : " + getSum(n));
// 	}
// 	public static int getSum(int n){
    // 	    if(n<=9)
    // 	    {
// 	        return n;
// 	    }
//         int sum=0;
// 	    while(n>0)
// 	    {
// 	        int rem=n%10;
// 	        sum+=rem;
// 	        n=n/10;
// 	    }
//         return getSum(sum);
// 	}
// }





// import java.util.*;
// public class Main
// {
    // 	public static int[] Quicksort(int[] arr,int low,int high)
    // 	{
// 		if(low>=high)
// 		{
// 			return arr;
// 		}
// 		int start = low;
// 		int end = high;
// 		int mid = (low+high)/2;
// 		int pivot = arr[mid];
// 		while(start<=end)
// 		{
// 			while(arr[start]<pivot)
// 			{
// 				start++;
// 			}
// 			while(arr[end]>pivot)
// 			{
    // 				end--;
    // 			}
// 			if(start<=end)
// 			{
// 				int temp = arr[start];
// 				arr[start] = arr[end];
// 				arr[end] = temp;
// 				start++;
// 				end--;
// 			}
// 		}
// 		Quicksort(arr,low,end);
// 		Quicksort(arr,start,high);
// 		return arr;
// 	}
// 	public static void main(String[] args)
// 	{
    // 		Scanner sc = new Scanner(System.in);
// 		int n = sc.nextInt();
// 		int[] arr = new int[n];
// 		for(int i=0;i<n;i++)
// 			arr[i] = sc.nextInt();
// 		int[] a = Quicksort(arr, 0, arr.length-1);
// 		System.out.println("Sorted Array : ");
// 		for(int ch : a)
// 			System.out.print(ch + " ");
// 		sc.close();
// 	}
// }









// import java.util.*;
// public class Main 
// {
//     public static void Bubblesort (int[] arr)
//     {
//         int count = 0;
//         for(int i=0;i<arr.length-1;i++)
//         {
    //             boolean swap = false;
//             for(int j=0;j<arr.length-i-1;j++)
//             {
    //                 if(arr[j]>arr[j+1])
    //                 {
//                     swap = true;
//                     int temp = arr[j];
//                     arr[j] = arr[j+1];
//                     arr[j+1] = temp;
//                 }
//             }
//             System.out.println("Phase : "+count + "->"+Arrays.toString(arr));
//             count++;
//             if(!swap)
//                 break;
//         }
//         System.out.println("Total phase count : " + --count);
//         System.out.println("Sorted Array : " + Arrays.toString(arr));
//     }
//     public static void Binarysearch(int[] arr,int tar)
//     {
//         System.out.println("Target element to found : " + tar);
//         int i=0,j=arr.length-1;
//         while(i<=j)
//         {
//             int mid = (i+j)/2;
//             if(arr[mid]==tar)
//             {
//                 System.out.println("Element found at index : " + mid);
//                 break;
//             }
//             else if(arr[mid]<tar)
//             {
//                 i = mid+1;
//             }
//             else if(arr[mid]>tar)
//             {
//                 j = mid-1;
//             }
//             else
//             {
//                 System.out.println("-1");
//             }
//         }
//     }
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);
//         // int n = sc.nextInt();
//         int[] arr = {64,34,25,12,22,11,90,5};
//         int tar = 25;
//         // for(int i=0;i<n;i++)
//         //     arr[i] = sc.nextInt();
//         Bubblesort(arr);
//         Binarysearch(arr,tar);
//     }
// }











// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = "  abcd457 gfd12  34gh12 56gdh  123g adc321gdj";
//         String[] arr = str.split("[a-zA-Z\\s+]");
//         int sum = 0;
//         ArrayList<Integer> list = new ArrayList<>();
//         for(String ch : arr)
//         {
//             if(!ch.isEmpty())
//             {
//                 sum += Integer.parseInt(ch);
//                 list.add(Integer.parseInt(ch));
//             }
//         }
//         System.out.println(list);
//         System.out.print("Sum = " + sum);
//         sc.close();
//     }
// }

//"  abcd457 gfd12  34gh12 56gdh  123g adc321gdj"
// [457,12,34,12,56,123,321]
// sum = 1015




//Reverse a string if the string contains any vowel it remains the same place
// apple
// alppe
















// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int[] arr = {49,1,3,200,2,4,70,5};
//         Arrays.sort(arr);
//         int count = 0;
//         for(int i=0;i<arr.length-1;i++)
//         {
//             if(Math.abs(arr[i]-arr[i+1])==1)
//             {
//                 count++;
//             }
//         }
//         System.out.print("output " + count);
//     }
// }




















// import java.util.*;
// public class Main
// {
//     public static int[] Prime(int[] arr,int n)
//     {
        
//         int count = 0;
//         int num = 2;
//         while(count!=n)
//         {
//             boolean b = true;
//             for(int i=2;i<=Math.sqrt(num);i++)
//             {
//                 if(num%i==0)
//                 {
//                     b = false;
//                     break;
//                 }
//             }
//             if(b==true)
//             {
//                 arr[count] = num;
//                 count++;
//             }
//             num++;
//         }
//         return arr;
//     }
//     public static int[] Fibo(int[] fib,int n)
//     {

//         fib[0] = 0; 
//         fib[1] = 1; 
//         int i = 2;
//         while (i != n) 
//         {
//             fib[i] = fib[i - 1] + fib[i - 2]; 
//             i++;  
//         }
//         return fib;
//     }
//     public static void main(String[] args)
//     {
//         int n = 7;
//         int[] arr = new int[n];
//         int[] fib = new int[n];
//         arr = Prime(arr,n);
//         fib = Fibo(fib,n);
//         System.out.print("OUTPUT : ");
//         for(int i=0;i<n;i++)
//         {
//             System.out.print(arr[i] + " " + fib[i] + " ");
//         }
//     }
// }



// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         Scanner sc = new Scanner(System.in);
//         int n = 9;
//         for(int i=0;i<n;i++)
//         {
//             for(int j=0;j<n;j++)
//             {
//                 if(i<j)
//                     System.out.print(n-i + " ");
//                 else
//                     System.out.print(n-j + " ");
//             }
//             for(int j=n-2;j>=0;j--)
//             {
//                 if(i<j)
//                     System.out.print(n-i + " ");
//                 else
//                     System.out.print(n-j + " ");
//             }
//             System.out.println();
//         }
//         for(int i=n-2;i>=0;i--)
//         {
//             for(int j=0;j<n;j++)
//             {
//                 if(i<j)
//                     System.out.print(n-i + " ");
//                 else
//                     System.out.print(n-j + " ");
//             }
//             for(int j=n-2;j>=0;j--)
//             {
//                 if(i<j)
//                     System.out.print(n-i + " ");
//                 else
//                     System.out.print(n-j + " ");
//             }
//             System.out.println();
//         }
//     }
// }





// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         String str = "[[5,2,3,1,7],[4,8,1,2,6],[9,1,0,3,2],[1,5,2,0,7]]";
//         str = str.replace(",","");
//         int row = 0;
//         int col = 0;
//         for(int i=1;i<str.length();i++)
//         {
//             if(str.charAt(i)=='[')
//             {
//                 row++;
//             }
//         }
//         for(int i=2;i<str.length();i++)
//         {
//             if(str.charAt(i)==']')
//             {
//                 break;
//             }
//             col++;
//         }
//         int[][] max = new int[row][col];
//         int[] fin = new int[row*col];
//         int c = 0;
//         for(int i=0;i<str.length();i++)
//         {
//             if(Character.isDigit(str.charAt(i)))
//             {
//                 String ch = String.valueOf(str.charAt(i));
//                 fin[c++] = Integer.parseInt(ch);
//             }
//         }
//         c = 0;
//         int left = 0;
//         int right = 0;
//         for(int i=0;i<row;i++)
//         {
//             for(int j=0;j<col;j++)
//             {
//                 max[i][j] = fin[c++];
//                 if(i==j)
//                     left += max[i][j];
//                 if(i+j==row-1)
//                     right += max[i][j];
//             }
//         }
//         if(left==row || right==row)
//             System.out.print("YES");
//         else
//             System.out.print("NO");
//     }
// }























// import java.util.*;
// public class Main
// {
//     public static void main(String[] args)
//     {
//         String str = "apple.banana,jackfruit:strawberrey";
//         String[] arr = str.split("[^a-zA-Zd++]");
//         System.out.print(Arrays.toString(arr));
//         // for(String ch : arr)
//         // {
//         //     if(!ch.isEmpty())
//         //         System.out.println(ch);
//         // }
//     }
// }













// import java.util.*;
// public class Main
// {
// 	public static void main(String[] args) 
// 	{
// 		int[] nums = {1,2,3,4};
// 		int c = 0;
//         int sum = 1;
// 		while(c!=nums.length)
// 		{
// 		    for(int i=0;i<nums.length;i++)
// 		    {
// 		        if(c!=i)
// 		        {
// 		            sum *= nums[i];
// 		        }
// 		    }
//             System.out.print(sum + " ");
//             sum = 1;
//             c++;
// 		}
// 	}
// }

















// import java.util.*;
// public class Main
// {
// 	public static void main(String[] args) 
// 	{
// 	    int[] arr = {3,2,8,1,2,4,7,5,6};
// 	    int k = 4;
//         int count = 0;
// 	    HashSet<Integer> list = new HashSet<>();
//         for(int ch : arr)
//             list.add(ch);
//         for(int ch : list)
//         {
//             if(count==list.size()-k)
//                 System.out.print("OUTPUT -> " + ch);
//             count++;
//         }
// 	}
// }









