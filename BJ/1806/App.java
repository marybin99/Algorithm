import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while(left <= N && right <= N) {
            if(sum >= S && min > right - left) {
                min = right - left;
            }
            if(sum < S) sum += arr[right++];
            else sum -= arr[left++];
        }

        if(min == Integer.MAX_VALUE) System.out.println("0");
        else System.out.println(min);
    }
}
