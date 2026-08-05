public class Solution {
    
    public static int solution(int n, int count) {
        long[][] arr = new long[n + 1][n + 1];
        long preventOutOfRange = 1000000007;
        long temp = 0;
        
        arr[1][1] = 1;
        
        for (int x = 2; x <= n; x++) { // x는 2 이상
            
            for (int y = 1; y <= x; y++) {
                
                if (y == 1) { // y가 1일 때 arr[x][y]를 만드는 방법 => arr[x][y] = arr[x - 1][y] * (2 * (x - 1));
                    temp = arr[x - 1][y] * (2 * (x - 1));
                } else if (y > 1 && y < x) { // y가 1보다 크고 x보다 작을 때 arr[x][y]를 만드는 방법 => arr[x][y] = arr[x - 1][y] * (2 * (x - 1)) + arr[x - 1][y - 1];
                    temp = arr[x - 1][y] * (2 * (x - 1)) + arr[x - 1][y - 1];
                } else { // y가 x일 때 arr[x][y]를 만드는 방법 => arr[x][y] = arr[x - 1][y - 1];
                    temp = arr[x - 1][y - 1];
                }
                
                arr[x][y] = temp % preventOutOfRange;
            }
        }
        
        return (int) arr[n][count];
    }
    
    public static void main(String[] args) {
        int n = 3;
        int count = 1;
        
        System.out.println(solution(n, count)); // 8
    }
}