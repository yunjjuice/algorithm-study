/* 1808. 지희의 고장난 계산기 D4
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4yC3pqCegDFAUx&categoryId=AV4yC3pqCegDFAUx&categoryType=CODE

 1. 약수 찾기
 2. 찾은 약수들 중 가장 큰 수가 타겟과 같은 경우 -> 바로 출력
 3. 찾은 약수들로 계속 나눠가며 1이 될 경우 정답 갱신
    -> 중복 검사: q에 넣은 숫자는 set에 넣어두기
 */
#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <functional>
#include <unordered_set>
using namespace std;

int target;
bool usable[10];
vector<pair<int, int> > nums;

void makeNum(int dec, int n, int push)  { //dec는 자릿수, n은 누적 숫자, push는 버튼을 누른 횟수
    if (target / dec < 1)   return;
    push++;
    dec *= 10;
    for (int i = 0; i < 10; i++) {
        if (usable[i]) { //i번을 누를 수 있다
            if (target % (n + i) == 0) { //n+1가 target의 약수
                nums.push_back({ n + i, push });
            }
            makeNum(dec, (n + i) * 10, push);
        }
    }
}

void makeNum()  {
    for (int i = 1; i < 10; i++) {
        if (usable[i]) { //i번을 누를 수 있다
            if (target % (i) == 0) { //i가 target의 약수
                nums.push_back({ i, 1 });
            }
            makeNum(10, i * 10, 1);
        }
    }
}

int main()  {
    //    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int TC;
    scanf("%d", &TC);

    for (int testCase = 1; testCase <= TC; testCase++) {
        for (int i = 0; i < 10; i++) {
            int n;
            scanf("%d", &n);
            if (n == 1) usable[i] = true;
            else    usable[i] = false;
        }
        scanf("%d", &target);
        //end of input

        nums.clear();
        makeNum();
        sort(nums.begin(), nums.end(), greater<pair<int, int> >());

        if (target == nums[0].first) {
            printf("#%d %d\n", testCase, nums[0].second + 1);
            continue;
        }

        queue<pair<int, int> > q;
        unordered_set<int> s;
        int min = 0x3f3f3f3f; //대충 inf
        q.push({ target, 0 });
        s.insert(target);
        while (!q.empty()) {
            int n = q.front().first, p = q.front().second;
            q.pop();
            if (n == 1 && min > p)
                min = p; //정답 갱신
            for (int i = 0; i < nums.size(); i++) {
                if (n % nums[i].first == 0 && s.find(n / nums[i].first) == s.end()) {
                    q.push({ n / nums[i].first, p + nums[i].second + 1 });
                    s.insert(n / nums[i].first);
                }
            }
        }

        if(min == 0x3f3f3f3f)   min = -1;
        printf("#%d %d\n", testCase, min);
    }
}

