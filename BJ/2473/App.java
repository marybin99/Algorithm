import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class App {
    static int N;
    static long[] arr, result;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(in.readLine());
        arr = new long[N];

        StringTokenizer st  = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        result = new long[3];

        twopointer();
        
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    public static void twopointer() {
        long dif = Long.MAX_VALUE;

        for(int i=0;i<N;i++) {
            int left = i+1;
            int right = N-1;

            while(left<right) {
                long sum = arr[i] + arr[left] + arr[right];
                long curdif = Math.abs(sum);

                if(curdif<dif) {
                    dif = curdif;
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if(sum>0) right--;
                else left++;
            }
        }
    }
}
