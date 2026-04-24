import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double X = Double.parseDouble(st.nextToken());
        double Y = Double.parseDouble(st.nextToken());
        double C = Double.parseDouble(st.nextToken());
        
        double s = 0, e = Math.min(X, Y);
        double a, b, res;

        while(e - s >= 0.001) {
            double mid = (s + e) / 2;

            a = Math.sqrt(Math.pow(X, 2) - Math.pow(mid, 2));
            b = Math.sqrt(Math.pow(Y, 2) - Math.pow(mid, 2));

            res = (a * b) / (a + b);

            if(res >= C) {
                s = mid;
            }
            else {
                e = mid;
            }
        }

        System.out.println(String.format("%.3f", e));
    }
}