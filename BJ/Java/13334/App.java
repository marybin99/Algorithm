import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App {
    static class Line implements Comparable<Line> {
        int h, o;
        Line(int h, int o) {
            this.h = h;
            this.o = o;
        }

        @Override
        public int compareTo(Line l) {
            return Integer.compare(this.o, l.o);
        }
    }
    static int N, len;
    static ArrayList<Line> lines = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if(h>o) {
                int tmp = h;
                h = o;
                o = tmp;
            }
            lines.add(new Line(h, o));
        }
        len = Integer.parseInt(in.readLine());
        
        Collections.sort(lines);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for(Line line: lines) {
            while(!pq.isEmpty() && pq.peek() < line.o - len) {
                pq.poll();
                cnt--;
            }

            if(line.h >= line.o - len) {
                cnt++;
                pq.add(line.h);
            }
            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
