import java.io.*;

public class numbersprial {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long t = Long.parseLong(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) 
        {
            String[] arr = br.readLine().split(" ");
            long y = Long.parseLong(arr[0]);
            long x = Long.parseLong(arr[1]);
            long max = Math.max(x, y);
            long v;

            if (max % 2 == 0) 
            {
                if (y == max)
                    v = max * max - (x - 1);
                else
                    v = (max - 1) * (max - 1) + y;
            } 
            else 
            {
                if (x == max)
                    v = max * max - (y - 1);
                else
                    v = (max - 1) * (max - 1) + x;
            }
            sb.append(v).append("\n");
        }
        System.out.print(sb.toString());
    }
}
