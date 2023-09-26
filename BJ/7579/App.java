import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int result = Integer.MAX_VALUE;

        int[] memory = new int[N];
        int[] cost = new int[N];
        int[][] dp = new int[N][100001];

        StringTokenizer st1 = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            memory[i] = Integer.parseInt(st1.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }

        for(int i=0;i<N;i++) {
            int mem = memory[i];
            int c = cost[i];

            for(int j=0;j<=10000;j++) {
                if(i==0) {
                    if(j>=c) dp[i][j] = mem;
                } else {
                    if(j>=c) dp[i][j] = Math.max(dp[i-1][j-c] + mem, dp[i-1][j]);
                    else dp[i][j] = dp[i-1][j];
                }

                if(dp[i][j] >= M) result = Math.min(result, j);
            }
        }
        System.out.println(result);
    }
}
