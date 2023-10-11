import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class App {
    static int N, h;
    static List<Integer>[] list;
    static int[][] parent;
    static int[] depth;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        list = new ArrayList[N+1];
        for(int i=1;i<N+1;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        h = getTreeHeight();
        parent = new int[N+1][h];
        depth = new int[N+1];

        init(1,1,0);
        fillParents();

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(in.readLine());
        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a,b)+"\n");
        }

        System.out.println(sb);
    }
    private static int getTreeHeight() {
        return (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
    }
    private static void init(int cur, int h, int pa) {
        depth[cur] = h;
        for(int next: list[cur]) {
            if(next != pa) {
                init(next, h+1, cur);
                parent[next][0] = cur;
            }
        }
    }
    private static void fillParents() {
        for(int i=1;i<h;i++) {
            for(int j=1;j<N+1;j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    private static int LCA(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];

        if(bh > ah) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        for(int i=h-1;i>=0;i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }
        if(a == b) return a;

        for(int i=h-1;i>=0;i--) {
            if(parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
		return parent[a][0];
    }
}
