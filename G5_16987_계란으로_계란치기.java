import java.io.*;
import java.util.*;

public class Main {

    static class Egg {
        int s;
        int w;

        Egg(int s, int w){
            this.s = s;
            this.w = w;
        }
    }

    static int N;
    static boolean[] check;
    static Egg[] eggs;
    static int Ans;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        eggs = new Egg[N];
        check = new boolean[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(s, w);
        }

        solve(0);

        System.out.println(Ans);
    }

    public static void solve(int idx) {
        if(Ans == N){
            return;
        }

        if(idx == N) {
            int cnt = 0;
            for(int i=0; i<N; i++){
                if(eggs[i].s <= 0){
                    cnt++;
                }
            }
            Ans = Math.max(Ans, cnt);
            return;
        }

        if(eggs[idx].s > 0) {
            boolean flag = false;

            for(int i=0; i<N; i++) {
                if(idx == i){
                    continue;
                }

                if(eggs[i].s <= 0) {
                    continue;
                }

                flag = true;

                eggs[idx].s -= eggs[i].w;
                eggs[i].s -= eggs[idx].w;

                solve(idx + 1);

                eggs[idx].s += eggs[i].w;
                eggs[i].s += eggs[idx].w;
            }

            if(flag == false) {
                solve(idx + 1);
            }
        }
        else {
            solve(idx + 1);
        }
    }
}