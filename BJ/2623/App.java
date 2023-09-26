import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class App {
    static int N;
    static int[] indegree;
    static ArrayList<Integer>[] list;
    static ArrayList<Integer> result;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        indegree = new int[N+1];

        for(int i=0;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(in.readLine());

            int num = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for(int j=1;j<num;j++) {
                int singer = Integer.parseInt(st.nextToken());
                list[before].add(singer);
                indegree[singer]++;

                before = singer;
            }
        }

        result = new ArrayList<>();
        topologySort();
    }

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();

        for(int i=1;i<=N;i++) {
            if(indegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int current = q.poll();
            result.add(current);

            for(int i=0;i<list[current].size();i++) {
                int next = list[current].get(i);
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }

        if(result.size() != N)  System.out.println("0");
        else {
            for(int i=0;i<result.size();i++) {
                System.out.println(result.get(i));
            }
        }
    }
}
