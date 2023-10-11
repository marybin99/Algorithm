import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class App {
    static int N, S;
    static long cnt;
    static int[] arr;
    static ArrayList<Integer> left = new ArrayList<>();
    static ArrayList<Integer> right = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeSum(0,0,N/2, left);
        makeSum(0,N/2, N, right);

        Collections.sort(left);
        Collections.sort(right);

        cnt = 0;
        calc();

        if(S == 0) {
            System.out.println(cnt - 1);
        } else {
            System.out.println(cnt);
        }
    }

    private static void calc() {
        int pl = 0;
        int pr = right.size() - 1;

        while(true) {
            if(pl == left.size() || pr < 0) break;

            int cl = left.get(pl);
            int cr = right.get(pr);

            if(cl + cr == S) {
                long left_cnt = 0;
                while(pl < left.size() && left.get(pl) == cl) {
                    left_cnt++;
                    pl++;
                }

                long right_cnt = 0;
                while(0 <= pr && right.get(pr) == cr) {
                    right_cnt++;
                    pr--;
                }
                cnt += left_cnt * right_cnt;
            }
            
            if(cl + cr > S) {
                pr--;
            }

            if(cl + cr < S) {
                pl++;
            }
        }

    }
    private static void makeSum(int sum, int start, int end, ArrayList<Integer> list) {
        if(start == end) {
            list.add(sum);
            return;
        }
        makeSum(sum, start+1, end, list);
        makeSum(sum + arr[start], start + 1, end, list);
    }
}
