import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] a = new int[N];
        st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        
        int[] dp = new int[799995];

        for(int i=0;i<N;i++) {
            for(int j=i+1;j<N;j++) {
                int idx = a[i] + a[j];
                if(idx > W) continue;

                dp[idx] = j;
                if(dp[W-idx] != 0 && dp[W-idx] > j) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }
}
