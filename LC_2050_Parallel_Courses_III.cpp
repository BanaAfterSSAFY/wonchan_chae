#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumTime(int n, vector<vector<int>>& relations, vector<int>& time) {
        vector<vector<int>> graph(n);
        vector<int> indegree(n);
        vector<int> dp(n);

        for(auto& relation : relations) {
            int u = relation[0] - 1;
            int v = relation[1] - 1;

            graph[u].push_back(v);
            indegree[v]++;
        }

        queue<int> q;

        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                dp[i] = time[i];
                q.push(i);
            }
        }

        int answer = 0;

        while(!q.empty()) {
            int cur = q.front();
            q.pop();

            answer = max(answer, dp[cur]);

            for(int next : graph[cur]) {
                dp[next] = max(dp[next], dp[cur] + time[next]);

                indegree[next]--;

                if(indegree[next] == 0) {
                    q.push(next);
                }
            }
        }

        return answer;
    }
};