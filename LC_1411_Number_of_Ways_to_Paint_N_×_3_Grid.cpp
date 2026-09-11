class Solution {
public:
    int numOfWays(int n) {
        const long long MOD = 1000000007;

        long long aba = 6;
        long long abc = 6;

        for (int i = 1; i < n; i++) {
            long long nextAba = (aba * 3 + abc * 2) % MOD;
            long long nextAbc = (aba * 2 + abc * 2) % MOD;

            aba = nextAba;
            abc = nextAbc;
        }

        return (aba + abc) % MOD;
    }
};