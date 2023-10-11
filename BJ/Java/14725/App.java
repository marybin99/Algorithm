import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Node {
    HashMap<String, Node> map = new HashMap<>();
}

public class App {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        Node root = new Node();

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int size = Integer.parseInt(st.nextToken());
            Node now = root;

            for(int j=0;j<size;j++) {
                String eat = st.nextToken();

                if(!now.map.containsKey(eat))
                    now.map.put(eat, new Node());
                now = now.map.get(eat);
            }
        }

        print(root, "");
    }
    private static void print(Node root, String bar) {
        Object[] key = root.map.keySet().toArray();
        Arrays.sort(key);

        for(Object s: key) {
            System.out.println(bar + s);
            print(root.map.get(s), bar + "--");
        }
    }
}
