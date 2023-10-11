import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class App {
    static List<Integer>[] list;
    static int[] parent, depth;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        parent = new int[N+1];
        depth = new int[N+1];
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

        init(1,1,0);

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
    private static void init(int cur, int h, int pa) {
        depth[cur] = h;
        parent[cur] = pa;
        for(int next: list[cur]) {
            if(next != pa) {
                init(next, h+1, cur);
            }
        }
    }
    private static int LCA(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];

        while(ah > bh) {
            a = parent[a];
            ah--;
        }

        while(bh > ah) {
            b = parent[b];
            bh--;
        }
        
        while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
    }
}
