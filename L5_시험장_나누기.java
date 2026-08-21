import java.util.*;

class Solution {

    static int n, root;
    static int[] left = new int[10001];
    static int[] right = new int[10001];
    static int[] parent = new int[10001];
    static int[] value = new int[10001];
    static int count = 0;
    
    public int solution(int k, int[] num, int[][] links) {
        n = num.length;

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        for(int i = 0; i < n; i++) {
            left[i] = links[i][0];
            right[i] = links[i][1];
            value[i] = num[i];
            
            if(left[i] != -1) { 
                parent[left[i]] = i;
            }

            if(right[i] != -1) {
                parent[right[i]] = i;
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(parent[i] == -1) {
                root = i;
                break;
            }
        }
        
        int start = value[0];
        int end = (int)1e8;
        for(int i = 0; i < n; i++) {
            if(start < value[i]) {
                start = value[i];
            }
            
        }
        
        while(start < end) {
            int mid = (start + end) / 2;
            int temp = calculate(mid);
            
            if(temp <= k) {
                end = mid;
            }
            else {
                start = mid+1;
            }
        }
        
        return start;
    }
    
    static int calculate(int limit) {
        count = 0;
        solve(root, limit);
        count++;
        return count;
    }
    
    static int solve(int index, int limit) {
        int leftChild = 0;
        
        if(left[index] != -1) {
            leftChild = solve(left[index], limit);
        }
        
        int rightChild = 0;
        
        if(right[index] != -1) {
            rightChild = solve(right[index], limit);
        }
        
        if(rightChild + leftChild + value[index] <= limit) {
            return rightChild + leftChild + value[index];
        }
        else if (Math.min(rightChild, leftChild) + value[index] <= limit) {
            count++;
            return Math.min(rightChild, leftChild) + value[index];
        }
        else {
            count += 2;
            return value[index];
        }
    }
}