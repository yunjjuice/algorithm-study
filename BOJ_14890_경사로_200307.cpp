/* 14890. 경사로 Gold 4
https://www.acmicpc.net/problem/14890
 -> 시뮬레이션
 이게 골드 4라고 ..? 암튼 코드가 좀 더럽네요 행 열 똑같으니까 한 쪽만 보셔도 될듯
 */
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL); //입출력 시간 단축 용
    int N, L;
    cin >> N >> L;

    int map[N][N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> map[i][j];
        }
    } //end of input

    int answer = 0;
    bool slope[100];
    for (int i = 0; i < N; i++) { //행 체크
        bool flag = true;
        fill(&slope[0], &slope[N], false); //initialize
        for (int j = 1; j < N; j++) {
            if (abs(map[i][j - 1] - map[i][j]) > 1) { //높이차가 1 초과 -> 못지나가
                flag = false; break;
            } else if (map[i][j - 1] == map[i][j])  continue; //높이 같으면 지나가
            //경사로!
            if (map[i][j - 1] < map[i][j]) { //경사로를 왼쪽에
                if (slope[j - 1]) {
                    flag = false;
                    break;
                }
                slope[j - 1] = true;
                for (int l = 1; l < L; l++) {
                    if (j - 1 - l < 0 || slope[j - l - 1] || map[i][j - 1] != map[i][j - 1 - l]) { //범위초과 or 경사로 이미 있음 or 높이 다름 -> 생성 불가
                        flag = false;
                        break;
                    }
                    slope[j - 1 - l] = true;
                }
                if (!flag) break;
            } else { //경사로를 오른쪽(나)에
                if (slope[j]) {
                    flag = false;
                    break;
                }
                slope[j] = true;
                for (int l = 1; l < L; l++) {
                    if (j + l >= N || map[i][j] != map[i][j + l]) { //범위 초과 or 높이 다름 -> 경사로 생성 불가
                        //왼쪽부터 검사하니까 경사로가 이미 있는지는 검사 안해도 ok
                        flag = false;
                        break;
                    }
                    slope[j + l] = true;
                }
                if (!flag) break;
                j += L - 1;
            }
        }
        if (flag)   answer++;
    }

    for (int i = 0; i < N; i++) { //열 체크
        bool flag = true;
        fill(&slope[0], &slope[N], false); //initialize
        for (int j = 1; j < N; j++) {
            if (abs(map[j - 1][i] - map[j][i]) > 1) { //높이차가 1 초과 -> 못지나가
                flag = false;   break;
            } else if (map[j - 1][i] == map[j][i])  continue; //높이 같으면 지나가
            //경사로!
            if (map[j - 1][i] < map[j][i]) { //경사로를 왼쪽에
                if (slope[j - 1]) {
                    flag = false;
                    break;
                }
                slope[j - 1] = true;
                for (int l = 1; l < L; l++) {
                    if (j - 1 - l < 0 || slope[j - l - 1] || map[j - 1][i] != map[j - 1 - l][i]) { //범위초과 or 경사로 이미 있음 or 높이 다름 -> 생성 불가
                        flag = false;
                        break;
                    }
                    slope[j - 1 - l] = true;
                }
                if (!flag)  break;
            } else { //경사로를 오른쪽(나)에
                if (slope[j]) {
                    flag = false;
                    break;
                }
                slope[j] = true;
                for (int l = 1; l < L; l++) {
                    if (j + l >= N || map[j][i] != map[j + 1][i]) { //범위 초과 or 높이 다름 -> 경사로 생성 불가
                        //왼쪽부터 검사하니까 경사로가 이미 있는지는 검사 안해도 ok
                        flag = false;
                        break;
                    }
                    slope[j+l] = true;
                }
                if (!flag)  break;
                j += L - 1;
            }
        }
        if(flag)    answer++;
    }
    cout << answer;
}
