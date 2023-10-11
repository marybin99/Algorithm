import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N+2];
        for(int i=1;i<N+1;i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int result = 0;
        for(int i=1;i<N+2;i++) {
            while(!stack.isEmpty()) {
                int top = stack.peek();
                if(arr[top] <= arr[i]) break;
                stack.pop();
                result = Math.max(result, arr[top]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        System.out.println(result);
    }
}
