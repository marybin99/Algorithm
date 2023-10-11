import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N, result;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        result = 0;
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        game(0);
        System.out.println(result);
    }
    public static void game(int cnt) {
        if(cnt == 5) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    result = Math.max(result, map[i][j]);
                }
            }
            return;
        }

        int[][] copy = new int[N][N];
        for(int i=0;i<N;i++) {
            copy[i] = map[i].clone();
        }

        for(int i=0;i<4;i++) {
            move(i);
            game(cnt+1);
            for(int j=0;j<N;j++) {
                map[j] = copy[j].clone();
            }
        }
    }
    public static void move(int dir) {
        switch(dir) {
            case 0: // 위로
                for(int i=0;i<N;i++) {
                    int now = 0;
                    int idx = 0;
                    for(int j=0;j<N;j++) {
                        if(map[j][i] != 0) {
                            if(now == map[j][i]) {
                                map[idx-1][i] = now * 2;
                                now = 0;
                                map[j][i] = 0;
                            } else {
                                now = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = now;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 1: // 왼쪽으로
                for(int i=0;i<N;i++) {
                    int now = 0;
                    int idx = N-1;
                    for(int j=N-1;j>=0;j--) {
                        if(map[j][i] != 0) {
                            if(now == map[j][i]) {
                                map[idx+1][i] = now * 2;
                                now = 0;
                                map[j][i] = 0;
                            } else {
                                now = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = now;
                                idx--;
                            }
                        }
                    }
                }
                break;
            case 2: // 오른쪽으로
                for(int i=0;i<N;i++) {
                    int now = 0;
                    int idx = 0;
                    for(int j=0;j<N;j++) {
                        if(map[i][j] != 0) {
                            if(now == map[i][j]) {
                                map[i][idx-1] = now * 2;
                                now = 0;
                                map[i][j] = 0;
                            } else {
                                now = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = now;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 3: // 아래로
                for(int i=0;i<N;i++) {
                    int now = 0;
                    int idx = N-1;
                    for(int j=N-1;j>=0;j--) {
                        if(map[i][j] != 0) {
                            if(now == map[i][j]) {
                                map[i][idx+1] = now * 2;
                                now = 0;
                                map[i][j] = 0;
                            } else {
                                now = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = now;
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
    }
}
