import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int[] arr, tree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        arr = new int[N+1];
        tree = new int[N * 4];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, N, 1);
        int M = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1) {
                update(1, N, 1, b, c);
            } else if(a == 2) {
                sb.append(query(1, N, 1, b, c)+ "\n");
            }
        }
        System.out.println(sb);
    }
    private static void init(int start, int end, int node) {
        if(start == end) {
            tree[node] = start;
        } else {
            int mid = (start + end) / 2;
            init(start, mid, node * 2);
            init(mid + 1, end, node * 2 + 1);

            if(arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
                tree[node] = tree[node * 2];
            } else {
                tree[node] = tree[node * 2 + 1];
            }
        }
    }
    private static void update(int start, int end, int node, int idx, int value) {
        if(idx < start || idx > end) {
            return;
        }

        if(start == end) {
            tree[node] = idx;
            arr[idx] = value;
            return;
        }

        int mid = (start + end) / 2;

        update(start, mid, node * 2, idx, value);
        update(mid + 1, end, node * 2 + 1, idx, value);

        if(arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else {
            tree[node] = tree[node * 2 + 1];
        }
    }
    private static int query(int start, int end, int node, int left, int right) {
        if(left > end || right < start) {
            return -1;
        }

        if(left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int m1 = query(start, mid, node * 2, left, right);
        int m2 = query(mid + 1, end, node * 2 + 1, left, right);

        if(m1 == -1) {
            return m2;
        } else if(m2 == -1) {
            return m1;
        } else {
            if(arr[m1] <= arr[m2]) {
                return m1;
            } else {
                return m2;
            }
        }
    }
}
