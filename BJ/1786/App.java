import java.io.*;
import java.util.*;

public class App {
    static List<Integer> list;
    static int cnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String T = in.readLine();
        String P = in.readLine();

        list = new ArrayList<>();

        Kmp(T, P);

        System.out.println(cnt);

        for(int i=0;i<cnt;i++) {
            System.out.println(list.get(i));
        }
    }
    private static void Kmp(String t, String p) {
        int pi[] = getPi(p);
        int j = 0;
        for(int i=0;i<t.length();i++) {
            while(j>0 && t.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            if(t.charAt(i) == p.charAt(j)) {
                if(j == p.length() - 1) { 
                    ++cnt;
                    list.add(i-j+1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
    }
    private static int[] getPi(String p) {
        int[] pi = new int[p.length()];
        int j = 0;
        for(int i=1;i<p.length();i++) {
            while(j>0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            if(p.charAt(i) == p.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
