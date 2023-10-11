import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        long[] arr = new long[N+1];
        long[] pow = new long[N+1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        pow[0] = 1;
        for(int i=1;i<=N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
            pow[i] = (pow[i-1] * 2) % 1000000007;
        }

        Arrays.sort(arr); // 이게 더 빠른데..?
        // Arrays.parallelSort(arr);

        long result = 0;
        for(int i=1;i<=N;i++) {
            result += (pow[i-1] - pow[N-i]) * arr[i];
            result %= 1000000007;
        }

        System.out.println(result);
    }
}
