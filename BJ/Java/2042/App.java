import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static long[] arr, tree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        arr = new long[N+1];
        for(int i=1;i<=N;i++) {
            arr[i] = Long.parseLong(in.readLine());
        }

        int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, k);

        tree = new long[size];
        // tree = new long[N * 4];

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M+K;i++) {
            st = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a==1) {
                long tmp = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, tmp);
            } else if(a == 2) {
                sb.append(sum(1, N, 1, b, (int)c)+"\n");
            }
        }
        System.out.println(sb);
    }
    private static long init(int start, int end, int node) {
        if(start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }
    private static long sum(int start, int end, int node, int left, int right) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
    private static void update(int start, int end, int node, int idx, long tmp) {
        if(idx < start || idx > end) return;

        tree[node] += tmp;
        if(start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, tmp);
        update(mid + 1, end, node * 2 + 1, idx, tmp);
    }
}
