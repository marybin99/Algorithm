import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N;
    static int[] number;
    static boolean[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        number = new int[N+1];
        dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=1;i<=N;i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // dp
        palindrome();

        int M = Integer.parseInt(in.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if(dp[S][E]) sb.append("1\n");
            else sb.append("0\n");
        }
        System.out.println(sb);
    }
    public static void palindrome() {
        for(int i=1;i<=N;i++) {
            dp[i][i] = true;
        }
        for(int i=1;i<=N-1;i++) {
            if(number[i] == number[i+1])
                dp[i][i+1] = true;
        }
        for(int i=2;i<N;i++) {
            for(int j=1;j<=N-i;j++) {
                if(number[j] == number[j+i] && dp[j+1][j+i-1]) 
                    dp[j][j+i] = true;
            }
        }
    }
}
