import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int mod = 1000000007;
    static long[] arr, tree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        tree = new long[N * 4];

        for(int i=1;i<=N;i++) {
            arr[i] = Long.parseLong(in.readLine());
        }

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M+K;i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                arr[b] = c;
                update(1, N, 1, b, c);
            } else if(a == 2) {
                sb.append(multiple(1, N, 1, b, (int)c) + "\n");
            }
        }
        System.out.println(sb);
    }
    private static long init(int start, int end, int node) {
        if(start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % mod;
    }
    private static long multiple(int start, int end, int node, int left, long right) {
        if(left > end || right < start) {
            return 1;
        }

        if(left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return (multiple(start, mid, node * 2, left, right) * multiple(mid + 1, end, node * 2 + 1, left, right)) % mod;
    }
    private static long update(int start, int end, int node, int idx, long value) {
        if(idx < start || idx > end) {
            return tree[node];
        }

        if(start == end) {
            return tree[node] = value;
        }

        int mid = (start + end) / 2;

        return tree[node] = (update(start, mid, node * 2, idx, value) * update(mid + 1, end, node * 2 + 1, idx, value)) % mod;
    }
}
