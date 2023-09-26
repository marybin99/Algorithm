import java.io.*;
import java.util.*;

public class App {
    static List<Integer>[] list;
    static int[] discovered, sccNum;
    static List<Queue<Integer>> result;
    static Stack<Integer> stack;
    static int sccCnt = 0, idx;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];
        discovered = new int[v+1];
        sccNum = new int[v+1];
        for(int i=1;i<v+1;i++) {
            list[i] = new ArrayList<>();
            discovered[i] = -1;
            sccNum[i] = -1;
        }

        for(int i=0;i<e;i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        result = new ArrayList<>();
        stack = new Stack<>();

        for(int i=1;i<v+1;i++) {
            if(discovered[i] == -1) {
                scc(i);
            }
        }

        System.out.println(sccCnt);

        Collections.sort(result, (o1,o2) -> o1.peek() - o2.peek());

        StringBuilder sb = new StringBuilder();
        for(Queue<Integer> queue: result) {
            while(!queue.isEmpty()) {
                sb.append(queue.poll() + " ");
            }
            sb.append(-1 + "\n");
        }

        System.out.println(sb);
    }
    private static int scc(int cur) {
        discovered[cur] = idx++;
        stack.push(cur);

        int r = discovered[cur];
        
        for(int i=0;i<list[cur].size();i++) {
            int next = list[cur].get(i);

            if(discovered[next] == -1) {
                r = Math.min(r, scc(next));
            } else if(sccNum[next] == -1) {
                r = Math.min(r, discovered[next]);
            }
        }

        if(r == discovered[cur]) {
            Queue<Integer> queue = new PriorityQueue<>();
            while(true) {
                int s = stack.pop();
                queue.add(s);
                sccNum[s] = sccCnt;
                if(s == cur) break;
            }
            result.add(queue);
            sccCnt++;
        }

        return r;
    }
}
