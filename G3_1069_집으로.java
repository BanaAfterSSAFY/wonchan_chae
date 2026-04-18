import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double X = Double.parseDouble(st.nextToken());
        double Y = Double.parseDouble(st.nextToken());
        double D = Double.parseDouble(st.nextToken());
        double T = Double.parseDouble(st.nextToken());

        double dist = Math.sqrt(X * X + Y * Y);

        double ans = dist;

        if(D <= T) {
            System.out.println(dist);
        }
        else if(D <= dist) {
            int tmp = (int) (dist / D);
            ans = Math.min(ans, (T * tmp) + (dist - D * tmp));
            ans = Math.min(ans, T * (tmp + 1));
        }
        else {
            ans = Math.min(ans, T + (D - dist));
            ans = Math.min(ans, T * 2);
        }

        System.out.println(ans);
    }
}