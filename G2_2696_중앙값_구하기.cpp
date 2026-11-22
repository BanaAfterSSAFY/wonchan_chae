#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while(T--) {
        int M;
        cin >> M;

        priority_queue<int> left;
        priority_queue<int, vector<int>, greater<int>> right;

        vector<int> answer;

        for(int i = 0; i < M; i++) {
            int x;
            cin >> x;

            if(left.empty() || x <= left.top()) {
                left.push(x);
            }
            else {
                right.push(x);
            }

            if(left.size() > right.size() + 1) {
                right.push(left.top());
                left.pop();
            }
            else if(right.size() > left.size()) {
                left.push(right.top());
                right.pop();
            }

            if(i % 2 == 0) {
                answer.push_back(left.top());
            }
        }

        cout << answer.size() << '\n';

        for(int i = 0; i < answer.size(); i++) {
            cout << answer[i];

            if((i + 1) % 10 == 0 || i == answer.size() - 1) {
                cout << '\n';
            }
            else {
                cout << ' ';
            }
        }
    }

    return 0;
}