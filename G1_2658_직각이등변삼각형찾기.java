import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r;
        int c;
        
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        @Override
        public String toString() {
            return "" + (r+1) + " " + (c+1);
        }
    }
    
    static int[][] arr;
    static Node s = new Node(-1, -1), e = new Node(-1, -1), o = new Node(-1, -1);
    static int f;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        arr = new int[10][10];
        
        for(int i=0; i<10; i++) {
            String inp = br.readLine();
            for(int j=0; j<10; j++) {
                arr[i][j] = inp.charAt(j) - '0';
                if(arr[i][j] == 1) {
                    e.r = i;
                    e.c = j;
                    if(s.r == -1) {
                        s.r = i;
                        s.c = j;
                    }
                }
            }
        }
        
        if(s.r == e.r && s.c == e.c) {
            System.out.println(0);
            return;
        }
        
        
        if(s.r != e.r && s.c != e.c) {
            
            if(arr[s.r][e.c] == 1) {
                if(s.c + 2 * (e.c - s.c) < 10 && arr[s.r][s.c + 2 * (e.c - s.c)] == 1) {
                    o.r = s.r;
                    o.c = s.c + 2 * (e.c - s.c);
                    f = 1;
                }
                else {
                    o.r = s.r;
                    o.c = e.c;
                    f = 2;
                }
            }
            else if(arr[e.r][s.c] == 1) {
                if(e.c - 2 * (e.c - s.c) > -1 && arr[e.r][e.c - 2 * (e.c - s.c)] == 1) {
                    o.r = e.r;
                    o.c = e.c - 2 * (e.c - s.c);
                    f = 3;
                }
                else {
                    o.r = e.r;
                    o.c = s.c;
                    f = 4;
                }
            }
            
        }
        else if(s.c == e.c) {
            
            if(s.c + (e.r - s.r) < 10 && arr[s.r][s.c + (e.r - s.r)] == 1) {
                o.r = s.r;
                o.c = s.c + (e.r - s.r);
                f = 5;
            }
            else if(e.c - (e.r - s.r) > -1 && arr[e.r][e.c - (e.r - s.r)] == 1) {
                o.r = e.r;
                o.c = e.c - (e.r - s.r);
                f = 6;
            }
            else if(e.c - (e.r - s.r) / 2 > -1 && arr[(e.r + s.r) / 2][e.c - (e.r - s.r) / 2] == 1) {
                o.r = (e.r + s.r) / 2;
                o.c = e.c - (e.r - s.r) / 2;
                f = 7;
            }
            else if(e.c + (e.r - s.r) / 2 < 10 && arr[(e.r + s.r) / 2][e.c + (e.r - s.r) / 2] == 1) {
                o.r = (e.r + s.r) / 2;
                o.c = e.c + (e.r - s.r) / 2;
                f = 8;
            }
            else {
                System.out.println(0);
                return;
            }
        }
        else {
            System.out.println(0);
            return;
        }
        
        solve();
    }
    
    public static void solve() {
        if(f == 1) {
            for(int i = 0; i <= e.r - s.r; i++) {
                if(s.c + i > 9 || o.c - i < 0) {
                    System.out.println(0);
                    return;
                }
                for(int j = s.c + i; j <= o.c - i; j++) {
                    if(s.r + i < 10 && arr[s.r + i][j] == 1) {
                        arr[s.r + i][j] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 2) {
            for(int i = 0; i <= e.r - s.r; i++) {
                if(e.c - i < 0) {
                    System.out.println(0);
                    return;
                }
                for(int j = e.c - i; j <= e.c; j++) {
                    if(e.r - i < 10 && arr[e.r - i][j] == 1) {
                        arr[e.r - i][j] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 3) {
            for(int i = 0; i <= e.r - s.r; i++) {
                if(s.c - i < 0 || s.c + i > 9) {
                    System.out.println(0);
                    return;
                }
                for(int j = s.c - i; j <= s.c + i; j++) {
                    if(s.r + i > -1 && arr[s.r + i][j] == 1) {
                        arr[s.r + i][j] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 4) {
            for(int i = 0; i <= e.r - s.r; i++) {
                if(s.c + i > 9) {
                    System.out.println(0);
                    return;
                }
                for(int j = s.c; j <= s.c + i; j++) {
                    if(s.r + i < 10 && arr[s.r + i][j] == 1) {
                        arr[s.r + i][j] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 5) {
            for(int i = 0; i <= e.r - s.r; i++) {
                if(e.c + i > 9) {
                    System.out.println(0);
                    return;
                }
                for(int j = e.c; j <= e.c + i; j++) {
                    if(e.r - i < 10 && arr[e.r - i][j] == 1) {
                        arr[e.r - i][j] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 6) {
            for(int i = 0; i <= e.r - s.r; i++) {
                if(s.c - i < 0) {
                    System.out.println(0);
                    return;
                }
                for(int j =s.c - i; j <= s.c; j++) {
                    if(s.r + i < 10 && arr[s.r + i][j] == 1) {
                        arr[s.r + i][j] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 7) {
            for(int i = 0; i <= e.c - o.c; i++) {
                if(o.r + i > 9 || o.r - i < 0) {
                    System.out.println(0);
                    return;
                }
                for(int j = o.r - i; j <= o.r + i; j++) {
                    if(o.c + i < 10 && arr[j][o.c + i] == 1) {
                        arr[j][o.c + i] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        else if(f == 8) {
            for(int i = 0; i <= o.c - e.c; i++) {
                if(o.r + i > 9 || o.r - i < 0) {
                    System.out.println(0);
                    return;
                }
                for(int j = o.r - i; j <= o.r + i; j++) {
                    if(o.c - i > -1 && arr[j][o.c - i] == 1) {
                        arr[j][o.c - i] = 0;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(arr[i][j] == 1) {
                    System.out.println(0);
                    return;
                }
            }
        }
                
        System.out.println(s);
        if(e.r < o.r || (e.r == o.r && e.c < o.c)) {
            System.out.println(e);
            System.out.println(o);
        }
        else {
            System.out.println(o);
            System.out.println(e);
        }
        
    }
}