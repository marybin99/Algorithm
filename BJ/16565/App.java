import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int[][] nCk = new int[53][53];

        for(int i=0;i<=52;i++) {
            nCk[i][0] = 1;
            nCk[i][i] = 1;
            for(int j=1;j<i;j++) {
                nCk[i][j] = nCk[i-1][j-1] + nCk[i-1][j];
                nCk[i][j] %= 10007;
                nCk[i][i-j] = nCk[i][j];
            }
        }
        int result = 0;
        for(int i=4;i<=N;i+=4) {
            if((i/4)%2 == 1) {
                result = (result + nCk[13][i/4] * nCk[52-i][N-i]) % 10007;
            } else {
                result = (result - nCk[13][i/4] * nCk[52-i][N-i]) % 10007;
            }
        }
        if(result < 0) result += 10007;

        System.out.println(result);
    }
}
