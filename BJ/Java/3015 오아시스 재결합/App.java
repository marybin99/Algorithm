import java.io.*;
import java.util.Stack;

public class App {
    static class Pair {
        int height;
        int cnt;

        Pair(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        Stack<Pair> stack = new Stack<>();
        long cnt = 0;

        for(int i=0;i<N;i++) {
            int nano = Integer.parseInt(in.readLine());
            Pair pair = new Pair(nano, 1);

            while(!stack.empty() && stack.peek().height <= nano) {
                Pair pop = stack.pop();
                cnt += pop.cnt;

                if(pop.height == nano) pair.cnt += pop.cnt;
            }

            if(!stack.empty()) cnt++;

            stack.push(pair);
        }
        System.out.println(cnt);
    }
}
