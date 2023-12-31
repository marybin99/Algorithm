import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int[] arr, minTree, maxTree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }
        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        minInit(1, N, 1);
        maxInit(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(in.readLine());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            sb.append(minFind(1, N, 1, left, right) + " " + maxFind(1, N, 1, left, right) + "\n");
        }
        System.out.println(sb);
    }

    private static int minInit(int start, int end, int node) {
        if(start == end) {
            return minTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
    }
    private static int maxInit(int start, int end, int node) {
        if(start == end) {
            return maxTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(maxInit(start, mid, node * 2), maxInit(mid + 1, end, node * 2 + 1));
    }

    private static int minFind(int start, int end, int node, int left, int right) {
        if(right < start || left > end) {
            return Integer.MAX_VALUE;
        }

        if(left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start + end) / 2;
        return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
    }
    private static int maxFind(int start, int end, int node, int left, int right) {
        if(right < start || left > end) {
            return Integer.MIN_VALUE;
        }

        if(left <= start && end <= right) {
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        return Math.max(maxFind(start, mid, node * 2, left, right), maxFind(mid + 1, end, node * 2 + 1, left, right));
    }
}
