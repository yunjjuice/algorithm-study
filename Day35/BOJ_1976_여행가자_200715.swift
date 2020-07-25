import Foundation

let N = Int(readLine()!)!
let M = Int(readLine()!)!

// make set
var p = [Int](repeating: 0, count: N + 1)
var rank = [Int](repeating: 0, count: N + 1)
for index in 1...N {
    p[index] = index
}

for i in 1...N {
    let input = readLine()!.split(separator: " ").map { Int(String($0))! }
    
    for (n, x) in input.enumerated() {
        if x == 1 {
            union(x: i, y: n + 1)
        }
    }
}

let plan = readLine()!.split(separator: " ").map { Int(String($0))! }

var str = "YES"
for index in 1..<plan.count {
    if findSet(x: plan[0]) != findSet(x: plan[index]) {
        str = "NO"
        break
    }
}

print(str)


func union(x: Int, y: Int) {
    let px = findSet(x: x)
    let py = findSet(x: y)
    
    if px != py {
        link(px: px, py: py)
    }
}

func findSet(x: Int) -> Int {
    if p[x] == x {
        return x
    }
    
    p[x] = findSet(x: p[x])
    return p[x]
}

func link(px: Int, py: Int) {
    if rank[px] > rank[py] {
        p[py] = px
    } else {
        p[px] = py
        if rank[px] == rank[py] {
            rank[py] += 1
        }
    }
}
