import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class App {
    static class Node {
        int to;
        int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    static int N, h;
    static List<Node>[] list;
    static int[][] dp;
    static int[] dis;
    static int[] depth;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());

        list = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, w));
            list[to].add(new Node(from, w));
        }

        h = getTreeH();
        depth = new int[N+1];
        dis = new int[N+1];
        dp = new int[N+1][h];

        init(1,1,0);
        fillParents();

        int M = Integer.parseInt(in.readLine());
        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = LCA(a,b);
            sb.append(dis[a] + dis[b] - 2 * dis[result]).append("\n");
        }

        System.out.println(sb);
    }
    private static int getTreeH() {
        return (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
    }
    private static void init(int cur, int h, int pa) {
        depth[cur] = h;
        for(Node node : list[cur]) {
            if(node.to != pa) {
                dis[node.to] = dis[cur] + node.w;
                init(node.to, h+1, cur);
                dp[node.to][0] = cur;
            }
        }
    }
    private static void fillParents() {
        for(int i=1;i<h;i++) {
            for(int j=1;j<N+1;j++) {
                dp[j][i] = dp[dp[j][i-1]][i-1];
            }
        }
    }
    private static int LCA(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];

        if(ah < bh) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i=h-1;i>=0;i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = dp[a][i];
            }
        }

        if(a == b) return a;

        for(int i=h-1;i>=0;i--) {
            if(dp[a][i] != dp[b][i]) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }
        return dp[a][0];
    }
}
