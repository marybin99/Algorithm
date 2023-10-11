import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

class Point {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

public class App {
    static Point root = new Point(Long.MAX_VALUE, Long.MAX_VALUE);
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        ArrayList<Point> points = new ArrayList<>();

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new Point(x, y));
        }
        int cnt = 0;
        cnt = grahamScan(points);

        System.out.println(cnt);
    }

    private static int grahamScan(ArrayList<Point> points) {
        for(int i=0;i<points.size();i++) {
            if(points.get(i).y < root.y) {
                root = points.get(i);
            } else if(points.get(i).y == root.y) {
                if(points.get(i).x < root.x) {
                    root = points.get(i);
                }
            }
        }

        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int result = CCW(root, p1, p2);

                if(result > 0) return -1;
                else if(result < 0) return 1;
                else {
                    long d1 = dist(root, p1);
                    long d2 = dist(root, p2);

                    if(d1 > d2) return 1;
                }
                return -1;
            }
        });

        Stack<Point> stack = new Stack<>();
        stack.add(root);

        for(int i=1;i<points.size();i++) {
            while(stack.size() > 1 && (CCW(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0)) {
                stack.pop();
            }
            stack.add(points.get(i));
        }
        
        return stack.size();
    }

    static long dist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    static int CCW(Point p1, Point p2, Point p3) {
        long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);

        if(result > 0) return 1;
        else if(result < 0) return -1;
        else return 0;
    }
}
