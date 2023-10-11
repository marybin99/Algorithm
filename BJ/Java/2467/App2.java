import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App2 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 투 포인터
        int left = 0;
        int right = N-1;
        int ml = 0, mr = 0;
        long min = Long.MAX_VALUE;
        while(left<right){
            long sum = arr[left] + arr[right];
            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                ml = left;
                mr = right;
            }
            if(sum>=0){
                right--;
            } else {
                left++;
            }
        }
        System.out.println(arr[ml] + " " + arr[mr]);
    }
}
