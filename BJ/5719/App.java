import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class App {
    static int N;
    static List<Integer>[] parent;
    static int[] nodeArr;
    static int[][] edgeInfo;
    static boolean[][] isEdge;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken()); // 장소 수
            int M = Integer.parseInt(st.nextToken()); // 도로 수

            if(N == 0 && M == 0) break;

            st = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(st.nextToken()); // 시작점
            int D = Integer.parseInt(st.nextToken()); // 도착점

            parent = new ArrayList[N];
            nodeArr = new int[N];
            edgeInfo = new int[N][N];
            isEdge = new boolean[N][N];
            for(int i=0;i<N;i++) {
                parent[i] = new ArrayList<>();
                nodeArr[i] = Integer.MAX_VALUE;
            }

            for(int i=0;i<M;i++) {
                st = new StringTokenizer(in.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken()); // U에서 V로 가는 도로의 길이

                edgeInfo[U][V] = P;
                isEdge[U][V] = true;
            }

            dijkstra(S, D);
            backTracking(S, D);

            for(int i=0;i<N;i++) {
                nodeArr[i] = Integer.MAX_VALUE;
            }

            sb.append(dijkstra(S, D) + "\n");
        }
        System.out.println(sb);
    }
    private static void backTracking(int start, int now) {
        if(now == start)    return;
        for(int i: parent[now]) {
            if(isEdge[i][now]) {
                isEdge[i][now] = false;
                backTracking(start, i);
            }
        }
    }
    private static int dijkstra(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        nodeArr[start] = 0;

        while(!queue.isEmpty()) {
            int p = queue.poll();

            for(int i=0;i<N;i++) {
                if(nodeArr[i] == nodeArr[p] + edgeInfo[p][i] && isEdge[p][i]) {
                    parent[i].add(p);
                } else if(isEdge[p][i] && nodeArr[i] > nodeArr[p] + edgeInfo[p][i]) {
                    nodeArr[i] = nodeArr[p] + edgeInfo[p][i];
                    queue.add(i);
                    parent[i].clear();
                    parent[i].add(p);
                }
            }
        }
        return nodeArr[end] != Integer.MAX_VALUE ? nodeArr[end] : -1;
    }
}
