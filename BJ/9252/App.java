import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    static String[] arr1;
    static String[] arr2;
    static int[][] dp = new int[1001][1001];
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        arr1 = in.readLine().split("");
        arr2 = in.readLine().split("");

        String result = lcs();

        System.out.println(result.length());
        if(result.length()!=0) System.out.println(result);
    }

    public static String lcs() {
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr2.length;j++){
                if(arr1[i].equals(arr2[j])) {
                    dp[i+1][j+1] = dp[i][j]+1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        int a = arr1.length;
        int b = arr2.length;
        String arr = "";

        while(a!=0 && b!=0) {
            if(dp[a-1][b] == dp[a][b]) {
                a-=1;
            } else if(dp[a][b-1] == dp[a][b]) {
                b-=1;
            } else {
                arr += arr1[a-1];
                a-=1;
                b-=1;
            }
        }

        String result = "";
        for(int i=arr.length()-1;i>=0;i--) {
            result += arr.charAt(i);
        }

        return result;
    }
}
