/* 예산 Level 3
 https://programmers.co.kr/learn/courses/30/lessons/43237
 */
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> budgets, int M) {
    long long sum = 0; int i;
    for(int b : budgets){
        sum += b;
    }
    if(sum <= M){
        return *max_element(budgets.begin(), budgets.end());
    } //전부 배정 가능할 때
    
    sort(budgets.begin(), budgets.end());
    int min = 1, max = budgets[budgets.size()-1], mid;
    while(min<=max){
        mid = (min+max)/2;
        sum=0;
        for(i=0; i<budgets.size(); i++){ //총 금액 계산
            if(budgets[i] > mid){
                sum += mid*(budgets.size()-i);
                break;
            }
            sum += budgets[i];
        }
        if(sum <= M){ //배정 가능
            min = mid+1;
            if(M-sum < budgets.size()-i) break;
        }else{
            max = mid-1;
        }
    }
    return mid;
}
