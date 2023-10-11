import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    static int N, K;
    static char[][] arr;
    static long[] memo = new long[16];
    static long[][] dp;
    static int[][] dpMod;
    static long p, q;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        arr = new char[N][];
        for(int i=0;i<N;i++) {
            arr[i] = in.readLine().toCharArray();
        }

        memo[1] = 1;
        for(int i=2;i<=N;i++) {
            memo[i] = memo[i-1] * i;
        }

        K = Integer.parseInt(in.readLine());

        q = memo[N];
        dp = new long[K][1<<N];
        dpMod = new int[K][N];

        for(int i=0;i<K;i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(dpMod[i], -1);
        }

        p = memoization(0, 0, 0);
        if(p == 0) {
            q = 1;
        } else {
            long gcd = getGCD(p, q);
            p /= gcd;
            q /= gcd;
        }

        System.out.println(p + "/" + q);
    }
    private static long getGCD(long a, long b) {
        if(a > b) {
            long tmp = a;
            a = b;
            b = tmp;
        }

        while(a % b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }
    private static long memoization(int flag, int mod, int cnt) {
        if(dp[mod][flag] != -1)
            return dp[mod][flag];

        if(cnt == N)
            return dp[mod][flag] = mod == 0 ? 1L:0;

        long sum = 0;

        for(int i=0;i<N;i++) {
            if((flag & (1 << i)) != 1 << i)
                sum += memoization(flag | (1<<i), getMod(mod, i), cnt + 1);
        }

        return dp[mod][flag] = sum;
    }
    private static int getMod(int mod, int n) {
        if(dpMod[mod][n] != -1) {
            return dpMod[mod][n];
        }

        int now = mod;
        for(int i=0;i<arr[n].length;i++) {
            now *= 10;
            now = (now + arr[n][i] - '0') % K;
        }

        return dpMod[mod][n] = now;
    }
}
