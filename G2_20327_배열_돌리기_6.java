import java.io.*;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] arr;
    static int[] ans;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inp;
        inp = br.readLine().split(" ");
        
        N = Integer.parseInt(inp[0]);
        R = Integer.parseInt(inp[1]);
        
        L = (int) Math.pow(2, N);
        
        arr = new int[L][L];
        
        for(int i=0; i<L; i++) {
            inp = br.readLine().split(" ");
            for(int j=0; j<L; j++) {
                arr[i][j] = Integer.parseInt(inp[j]);
            }
        }
        
        for(int i=0; i<R; i++) {
            inp = br.readLine().split(" ");
            int k = Integer.parseInt(inp[0]);
            int l = Integer.parseInt(inp[1]);
                        
            if(k == 1)
                solve1(l);
            else if(k == 2)
                solve2(l);
            else if(k == 3)
                solve3(l);
            else if(k == 4)
                solve4(l);
            else if(k == 5)
                solve5(l);
            else if(k == 6)
                solve6(l);
            else if(k == 7)
                solve7(l);
            else if(k == 8)
                solve8(l);
        }
        
        for(int i=0; i<L; i++) {
            for(int j=0; j<L; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void solve2(int l) {
        int s = (int) Math.pow(2, l);
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L; j+=s) {
                int tmp;
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s/2; p++) {
                        tmp = arr[i+q][j+p];
                        arr[i+q][j+p] = arr[i+q][j+s-1-p];
                        arr[i+q][j+s-1-p] = tmp;
                    }
                }
                
            }
        }
    }
    
    public static void solve1(int l) {
        int s = (int) Math.pow(2, l);
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L; j+=s) {
                int tmp;
                for(int q=0; q<s/2; q++) {
                    for(int p=0; p<s; p++) {
                        tmp = arr[i+q][j+p];
                        arr[i+q][j+p] = arr[i+s-1-q][j+p];
                        arr[i+s-1-q][j+p] = tmp;
                    }
                }
                
            }
        }
    }
    
    public static void solve3(int l) {
        int s = (int) Math.pow(2, l);
        int[][] tmp = new int[L][L];
        
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L; j+=s) {
                
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s; p++) {
                        tmp[i+p][j+s-1-q] = arr[i+q][j+p];
                    }
                }
            }
        }
        arr = tmp;
    }
    
    public static void solve4(int l) {
        int s = (int) Math.pow(2, l);
        int[][] tmp = new int[L][L];
        
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L; j+=s) {
                
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s; p++) {
                        tmp[i+s-1-p][j+q] = arr[i+q][j+p];
                    }
                }
            }
        }
        arr = tmp;
    }
    
    public static void solve5(int l) {
        int s = (int) Math.pow(2, l);
        
        for(int i=0; i<L/2; i+=s) {
            for(int j=0; j<L; j+=s) {
                int tmp;
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s; p++) {
                        tmp = arr[i+q][j+p];
                        arr[i+q][j+p] = arr[L-1-i -s+1 + q][j+p];
                        arr[L-1-i -s+1 +q][j+p] = tmp;
                    }
                }
            }
        }
    }
    
    public static void solve6(int l) {
        int s = (int) Math.pow(2, l);
        
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L/2; j+=s) {
                int tmp;
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s; p++) {
                        tmp = arr[i+q][j+p];
                        arr[i+q][j+p] = arr[i+q][L-1-j -s+1 +p];
                        arr[i+q][L-1-j -s+1 +p] = tmp;
                    }
                }
            }
        }
    }
    
    public static void solve7(int l) {
        int s = (int) Math.pow(2, l);
        int[][] tmp = new int[L][L];
        
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L; j+=s) {
                
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s; p++) {
                        tmp[j+q][L-1-i+p - s+1] = arr[i+q][j+p];
                    }
                }
            }
        }
        arr = tmp;
    }
    
    public static void solve8(int l) {
        int s = (int) Math.pow(2, l);
        int[][] tmp = new int[L][L];
        
        for(int i=0; i<L; i+=s) {
            for(int j=0; j<L; j+=s) {
                
                for(int q=0; q<s; q++) {
                    for(int p=0; p<s; p++) {
                        tmp[L-1-j+q - s+1][i+p] = arr[i+q][j+p];
                    }
                }
            }
        }
        arr = tmp;
    }
}