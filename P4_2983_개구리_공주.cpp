#include <iostream>
#include <set>
#include <map>
using namespace std;

map<int, set<int>> Map1;
map<int, set<int>> Map2;

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N, M, CX, CY;
    cin >> N >> M;

    string s;
    cin >> s;

    for (int i = 0; i < N; i++)
    {
        int X, Y;
        cin >> X >> Y;
        if (i == 0)
        {
            CX = X;
            CY = Y;
            continue;
        }
        Map1[X - Y].insert({X});
        Map2[X + Y].insert({X});
    }

    for (int i = 0; i < M; i++)
    {
        set<int>::iterator it;
        int dx = -1, dy = -1;
        if (s[i] == 'A')
        {
            it = Map1[CX - CY].upper_bound(CX);
            if (it == Map1[CX - CY].end())
            {
                continue;
            }

            dx = *it;
            dy = dx - (CX - CY);
        }
        else if (s[i] == 'B')
        {
            it = Map2[CX + CY].upper_bound(CX);
            if (it == Map2[CX + CY].end())
            {
                continue;
            }

            dx = *it;
            dy = (CX + CY) - dx;
        }
        else if (s[i] == 'C')
        {
            it = Map2[CX + CY].lower_bound(CX);
            if (it == Map2[CX + CY].begin())
            {
                continue;
            }

            dx = *(--it);
            dy = (CX + CY) - dx;
        }
        else
        {
            it = Map1[CX - CY].lower_bound(CX);
            if (it == Map1[CX - CY].begin())
            {
                continue;
            }

            dx = *(--it);
            dy = dx - (CX - CY);
        }

        Map1[dx - dy].erase(dx);
        Map2[dx + dy].erase(dx);
        CX = dx;
        CY = dy;
    }

    cout << CX << " " << CY << "\n";
    return 0;
}