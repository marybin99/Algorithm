import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        boolean result = isCross(x1, y1, x2, y2, x3, y3, x4, y4);
        if(result) System.out.println(1);
        else System.out.println(0);
    }
    
    private static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long tmp = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if(tmp > 0) return 1;
        else if(tmp < 0) return -1;
        
        return 0;
    }

    private static boolean isOver(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        if(Math.min(x1, x2)<=Math.max(x3, x4) && Math.min(x3, x4)<=Math.max(x1, x2) 
            && Math.min(y1, y2)<=Math.max(y3, y4) && Math.min(y3, y4)<=Math.max(y1, y2)) 
                return true;
        return false;
    }

    private static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int abc = CCW(x1, y1, x2, y2, x3, y3);
        int abd = CCW(x1, y1, x2, y2, x4, y4);
        int cda = CCW(x1, y1, x3, y3, x4, y4);
        int cdb = CCW(x2, y2, x3, y3, x4, y4);

        if(abc * abd == 0 && cda * cdb == 0) {
            return isOver(x1, y1, x2, y2, x3, y3, x4, y4);
        } else if(abc * abd <= 0 && cda * cdb <= 0) {
            return true;
        }
        return false;
    }
}
