import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());

        int[] seq = new int[N];
        int[] LIS = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = seq[0];
        int len = 1;
        
        for(int i=1;i<N;i++) {
            int key = seq[i];
            
            if(LIS[len-1] < key) {
                len++;
                LIS[len-1] = key;
            } else {
                int lo = 0;
                int hi = len;
                while(lo < hi) {
                    int mid = (lo+hi) >>> 1;

                    if(LIS[mid] < key) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }

                LIS[lo] = key;
            }
        }
        System.out.println(len);
    }
}
