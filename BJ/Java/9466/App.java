import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N, cnt;
    static int[] arr;
    static boolean[] visited, finished;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int tc = 0;tc<T;tc++){
            N = Integer.parseInt(in.readLine());
            cnt = 0;
            arr = new int[N+1];
            visited = new boolean[N+1];
            finished = new boolean[N+1];

            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int i=1;i<N+1;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=1;i<N+1;i++) {
                if(!finished[i]) dfs(i);
            }

            sb.append(N-cnt+"\n");
        }
        System.out.println(sb);
    }
    static void dfs(int now) {
        if(visited[now]) {
            finished[now] = true;
            cnt++;
        } else {
            visited[now] = true;
        }

        if(!finished[arr[now]]) dfs(arr[now]);
        
        visited[now] = false;
        finished[now] = true;
    }
}
