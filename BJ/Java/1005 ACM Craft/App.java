import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class App {
    static int[] time;
    static ArrayList<Integer>[] list;
    static int[] indegree;
    static int[] cost;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine()); // 테스트케이스 개수
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());

            int N = Integer.parseInt(st.nextToken()); // 건물개수
            int K = Integer.parseInt(st.nextToken()); // 건설순서 규칙 개수

            time = new int[N+1]; // 각 건물당 건설에 걸리는 시간
            list = new ArrayList[N+1];

            st = new StringTokenizer(in.readLine());
            for(int i=1;i<=N;i++) {
                time[i] = Integer.parseInt(st.nextToken());
                list[i] = new ArrayList<>();
            }

            indegree = new int[N+1];
            for(int i=0;i<K;i++) {
                st = new StringTokenizer(in.readLine());
                // 건설 순서 (건물 X를 지은 다음 건물 Y를 짓는 것이 가능)
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                list[X].add(Y);
                indegree[Y]++;
            }
            int W = Integer.parseInt(in.readLine()); // 승리하기 위해 건설해야 할 건물 번호

            cost = new int[N+1];
            topologySort();
            System.out.println(cost[W]);
        }
    }

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<indegree.length;i++) {
            if(indegree[i] == 0) {
                cost[i] = time[i];
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i=0;i<list[current].size();i++) {
                int next = list[current].get(i);
                cost[next] = Math.max(cost[current]+time[next], cost[next]);
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }
    }
}
