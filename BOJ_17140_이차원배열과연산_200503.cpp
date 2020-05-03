/* 17140. 이차원 배열과 연산 Gold 4
https://www.acmicpc.net/problem/17140
 */
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int arr[100][100];
int row[100] = {3, 3, 3, }; //각 행의 열 길이 -> csize 재설정
int col[100] = {3, 3, 3, };
int rsize=3, csize=3;

int main() {
    ios_base :: sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int r, c, k;
    cin >> r >> c >> k;
    r--; c--;
    for (int i=0; i<rsize; i++) {
        for (int j=0; j<csize; j++) {
            cin >> arr[i][j];
        }
    }
    int answer = -1;
    vector<pair<int, int>> v; //first가 횟수, second가 숫자
    v.reserve(105);
    int num[101] = {0,};
    
    while (answer++ < 100) {
        if (arr[r][c] == k) {
            break;
        }
        if (rsize >= csize) { //R연산
            for (int i=0; i<rsize; i++) { //모든 행 정렬
                for (int j=0; j<csize; j++) { //각 수의 등장 횟수 알아내기
                    num[arr[i][j]]++;
                }
                
                for (int k=1; k<101; k++) { //등장 횟수 & 수 pair vector에 넣기 (0은 제외)
                    if (num[k] != 0) {
                        v.push_back({num[k], k});
                    }
                }
                sort(v.begin(), v.end()); //오름차순 정렬
                for (int k=0; k<(v.size()>50? 50:v.size()); k++) { //arr에 다시 넣기, 100개까지만
                    arr[i][k*2] = v[k].second;
                    arr[i][k*2+1] = v[k].first;
                }
                for (int k=v.size()*2; k<csize; k++) {
                    arr[i][k] = 0;
                }
                
                row[i] = (v.size() > 50? 100 : v.size()*2);
                
                //원상복구
                v.clear();
                fill(&num[0], &num[101], 0);
            } //end of for rsize
            if (rsize < 100) {
                fill(&row[rsize], &row[100], 0);
            }

            //csize 다시 배치; 행마다의 열 길이가 변했으므로
            csize = *max_element(&row[0], &row[100]);
        }else{ //C연산
            for (int j=0; j<csize; j++) { //모든 열 정렬
                for (int i=0; i<rsize; i++) { //각 수의 등장 횟수 알아내기
                    num[arr[i][j]]++;
                }
                
                for (int k=1; k<101; k++) { //등장 횟수 & 수 pair vector에 넣기 (0은 제외)
                    if (num[k] != 0) {
                        v.push_back({num[k], k});
                    }
                }
                sort(v.begin(), v.end()); //오름차순 정렬
                for (int k=0; k<(v.size()>50? 50:v.size()); k++) { //arr에 다시 넣기, 100개까지만
                    arr[k*2][j] = v[k].second;
                    arr[k*2+1][j] = v[k].first;
                }
                for (int k=v.size()*2; k<csize; k++) {
                    arr[k][j] = 0;
                }
                
                col[j] = (v.size() > 50? 100 : v.size()*2);
                
                //원상복구
                v.clear();
                fill(&num[0], &num[101], 0);
            } //end of for csize
            if(csize < 100){
                fill(&col[csize], &col[100], 0);
            }
            
            //rsize 다시 배치
            rsize = *max_element(&col[0], &col[100]);
        }
    }
    
    cout << (answer > 100? -1 : answer);
}
