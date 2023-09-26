import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class App {
    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<N;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    private static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for(int child: graph[node]) {
            if(!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1];
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
