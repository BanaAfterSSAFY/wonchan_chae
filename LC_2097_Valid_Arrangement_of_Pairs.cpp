#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<int>> validArrangement(vector<vector<int>>& pairs) {
        unordered_map<int, vector<int>> graph;
        unordered_map<int, int> indegree;
        unordered_map<int, int> outdegree;

        for(auto& pair : pairs) {
            graph[pair[0]].push_back(pair[1]);
            outdegree[pair[0]]++;
            indegree[pair[1]]++;
        }

        int start = pairs[0][0];

        for(auto& [node, degree] : outdegree) {
            if(degree == indegree[node] + 1) {
                start = node;
                break;
            }
        }

        vector<int> stack;
        vector<int> path;

        stack.push_back(start);

        while(!stack.empty()) {
            int cur = stack.back();

            if(!graph[cur].empty()) {
                int next = graph[cur].back();
                graph[cur].pop_back();
                stack.push_back(next);
            }
            else {
                path.push_back(cur);
                stack.pop_back();
            }
        }

        reverse(path.begin(), path.end());

        vector<vector<int>> answer;

        for(int i = 0; i + 1 < path.size(); i++) {
            answer.push_back({path[i], path[i + 1]});
        }

        return answer;
    }
};