import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(in.readLine());
        long pi = N;

        for(long i = 2; i * i <= N; i++) {
            if(N % i == 0) pi = pi/i*(i-1);

            while(N % i == 0) N /= i;
        }

        if(N != 1) pi = pi/N * (N-1);

        System.out.println(pi);
    }
}
