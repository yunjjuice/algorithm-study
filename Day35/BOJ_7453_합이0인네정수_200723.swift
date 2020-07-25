// 시간 초과
import Foundation

let n = Int(readLine()!)!

var a = [Int](repeating: 0, count: n)
var b = [Int](repeating: 0, count: n)
var c = [Int](repeating: 0, count: n)
var d = [Int](repeating: 0, count: n)

for i in 0..<n {
    let input = readLine()!.split(separator: " ").map { Int(String($0))! }
    a[i] = input[0]
    b[i] = input[1]
    c[i] = input[2]
    d[i] = input[3]
}

var ab = [Int](repeating: 0, count: n * n)
var cd = [Int](repeating: 0, count: n * n)

var abIndex = 0
var cdIndex = 0

for i in 0..<n {
    for j in 0..<n {
        ab[abIndex] = a[i] + b[j]
        cd[cdIndex] = c[i] + d[j]
        abIndex += 1
        cdIndex += 1
    }
}

ab.sort()
cd.sort()

var abCount = [Int](repeating: 0, count: n * n)
var abCountIndex = 0
abCount[0] = 1
for i in 1..<n * n {
    if ab[abCountIndex] == ab[i] {
        abCount[abCountIndex] += 1
    } else {
        abCountIndex += 1
        ab[abCountIndex] = ab[i]
        abCount[abCountIndex] = 1
    }
}

var cdCount = [Int](repeating: 0, count: n * n)
var cdCountIndex = 0
cdCount[0] = 1
for i in 1..<n * n {
    if cd[cdCountIndex] == cd[i] {
        cdCount[cdCountIndex] += 1
    } else {
        cdCountIndex += 1
        cd[cdCountIndex] = cd[i]
        cdCount[cdCountIndex] = 1
    }
}

// 투포인터
var j = cdCountIndex
var count = 0
for i in 0...abCountIndex {
    while j > 0, ab[i] + cd[j] > 0 {
        j -= 1
    }
    
    if ab[i] + cd[j] == 0 {
        count += abCount[i] * cdCount[j]
    }
}

print(count)
