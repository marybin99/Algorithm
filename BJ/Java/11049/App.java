import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N, min = Integer.MAX_VALUE;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        arr = new int[N+1];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = a; arr[i+1] = b;
        }

        dp = new int[N][N];

        for(int i=2;i<N+1;i++) {
            for(int j=0;j<N-i+1;j++) {
                dp[j][j+i-1] = min;
                for(int k=j; k<j+i-1; k++) { // 중간 지점 k (j~ j+i-1))
					int value = dp[j][k]  + dp[k+1][j+i-1] + (arr[j]*arr[k+1]*arr[j+i]);
					dp[j][j+i-1] = Math.min(dp[j][j+i-1], value);
				}
            }
        }
        System.out.println(dp[0][N-1]);

        System.out.println();
    }
}
