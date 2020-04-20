/* 다리를 지나는 트럭 Level 2
https://programmers.co.kr/learn/courses/30/lessons/42583
 */
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 1;
    int first=0, last=1, nowWeight = truck_weights[0];
    vector<int> truck_times(truck_weights.size());
    for (; last<truck_weights.size(); answer++) {
        for (int i=first; i<last; i++) {
            truck_times[i]++;
        }
        if(truck_times[first] == bridge_length){
            nowWeight -= truck_weights[first++];
        }
        if(last-first >= bridge_length || nowWeight + truck_weights[last] > weight)    continue;
        nowWeight += truck_weights[last++];
    }
    
    answer += bridge_length - *min_element(truck_times.begin()+first, truck_times.end());
    return answer;
}
