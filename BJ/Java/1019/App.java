import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    static int[] counter = new int[10];
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        int start = 1;
        int digit = 1;

        while(start<=N) {
            while(N % 10 != 9 && start <= N) {
                count(N, digit);
                N--;
            }

            if(N < start) break;

            while(start % 10 != 0 && start <= N) {
                count(start, digit);
                start++;
            }

            start /= 10;
            N /= 10;

            for(int i=0;i<10;i++) {
                counter[i] += (N-start+1) * digit;
            }
            digit *= 10;
        }
        
        for(int i=0;i<10;i++){
            System.out.printf(counter[i]+" ");
        }
    }

    private static void count(int num, int digit) {
        while(num > 0) {
            counter[num % 10] += digit;
            num /=  10;
        }
    }
}
