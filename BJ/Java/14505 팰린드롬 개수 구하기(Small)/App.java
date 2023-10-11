import java.io.*;

public class App {
    static long[][] dp;
    static char[] S;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String line = in.readLine();
        int len = line.length();
        int left = 0, right = 0;

        S = new char[1010];

        for(int i=0;i<len;i++) {
            S[i] = line.charAt(i);
        }

        dp = new long[1010][1010];

        for(int i=0;i<len;i++) {
            dp[i][i] = 1;

            if(S[i] == S[i+1]) {
                dp[i][i+1] = 3;
            } else {
                dp[i][i+1] = 2;
            }
        }

        for(int l=2;l<len;l++) {
            for(left=0;left<len;left++) {
                right = left+l;

                if(right > len) break;

                dp[left][right] = dp[left+1][right] + dp[left][right-1] - dp[left+1][right-1];

                if(S[left] == S[right]) {
                    dp[left][right] += dp[left+1][right-1] + 1;
                }
            }
        }

        System.out.println(dp[0][len-1]);
    }
}
