class Solution {
public:
    int minimumOneBitOperations(int n) {
        int answer = 0;

        while (n) {
            answer ^= n;
            n >>= 1;
        }

        return answer;
    }
};