/* 길 찾기 게임 Level 3
https://programmers.co.kr/learn/courses/30/lessons/42892
 */
#include <algorithm>
#include <vector>
using namespace std;

class Node{
public:
    int idx, x, y;
    int left=-1, right=-1;
};

bool cmpX(const Node& n1, const Node& n2){
    return n1.x < n2.x;
}

bool cmpY(const Node& n1, const Node& n2){
    return n1.y < n2.y;
}

int make_tree(vector<Node>& tree, int start, int end){
    if(start == end) return -1;
    if(end-start <= 1) return start;
    
    int mid = max_element(tree.begin()+start, tree.begin()+end, cmpY) - tree.begin();
    tree[mid].left = make_tree(tree, start, mid);
    tree[mid].right = make_tree(tree, mid+1, end);
    return mid;
}

void preorder(vector<Node>& tree, vector<int>& list, int now){
    list.push_back(tree[now].idx);
    if(tree[now].left != -1) preorder(tree, list, tree[now].left);
    if(tree[now].right != -1) preorder(tree, list, tree[now].right);
}

void postorder(vector<Node>& tree, vector<int>& list, int now){
    if(tree[now].left != -1) postorder(tree, list, tree[now].left);
    if(tree[now].right != -1) postorder(tree, list, tree[now].right);
    list.push_back(tree[now].idx);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<Node> tree(nodeinfo.size());
    for (int i=0; i<nodeinfo.size(); i++) {
        tree[i].idx = i+1;
        tree[i].x = nodeinfo[i][0];
        tree[i].y = nodeinfo[i][1];
    }
    sort(tree.begin(), tree.end(), cmpX);

    int top = make_tree(tree, 0, tree.size());

    vector<vector<int>> answer(2);
    answer[0].reserve(nodeinfo.size());
    answer[1].reserve(nodeinfo.size());
    preorder(tree, answer[0], top);
    postorder(tree, answer[1], top);
    
    return answer;
}
