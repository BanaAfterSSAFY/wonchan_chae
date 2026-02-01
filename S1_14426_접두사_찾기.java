import java.io.*;
import java.util.*;

class Trie {
    char c;
    Map<Character, Trie> child = new HashMap<>();

    public Trie() {}

    public Trie(char a){
        c = a;
    }
}

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            Trie idx = trie;

            for(int j=0; j<s.length(); j++){
                char alpha = s.charAt(j);

                if(idx.child.containsKey(alpha) == false){
                    idx.child.put(alpha, new Trie(alpha));
                }

                idx = idx.child.get(alpha);
            }
        }

        int Ans = 0;

        for(int i=0; i<M; i++){
            String s = br.readLine();
            Trie idx = trie;

            for(int j=0; j<s.length(); j++){
                char alpha = s.charAt(j);

                if(idx.child.containsKey(alpha) == false){
                    break;
                }

                idx = idx.child.get(alpha);

                if(j == s.length() - 1){
                    Ans++;
                }
            }
        }
        System.out.println(Ans);
    }
}