import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App {
    public static class Jewel {
        int m;
        int v;
        
        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Jewel> list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.add(new Jewel(m, v));
        }
        Collections.sort(list, (o1, o2)-> o1.m - o2.m);

        int[] weight = new int[K];
        for(int i=0;i<K;i++) {
            weight[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(weight);

        long result = 0;
        int idx = 0;
        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);
        for(int i=0;i<K;i++) {
            while(idx < N && list.get(idx).m <= weight[i]) {
                Jewel now = list.get(idx++);
                pq.add(new Jewel(now.m, now.v));
            }
            if(!pq.isEmpty()) result += pq.poll().v;
        }
        System.out.println(result);
    }
}
