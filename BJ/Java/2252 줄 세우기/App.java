import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class App {
    static ArrayList<Integer>[] list;
    static int[] indegree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        indegree = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list[A].add(B);
            indegree[B]++;
        }

        topologySort();
    }

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=1;i<indegree.length;i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            System.out.print(q.peek()+" ");

            int current = q.poll();

            for(int i=0;i<list[current].size();i++) {
                int next = list[current].get(i);
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
