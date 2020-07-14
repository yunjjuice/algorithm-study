import Foundation

while true {
    let input = readLine()!.split(separator: " ").map{ Int(String($0))! }
    // component를 사용하면 시간초과가 남! 원인 분석해볼 것
    
    let m = input[0]
    let n = input[1]
    
    if m == 0, n == 0 {
        break
    }
    
    var rows = [Int](repeating: 0, count: m + 1)
    
    for i in 1...m {
        let cols = readLine()!.split(separator: " ").map{ Int(String($0))! }
        
        var arr = [Int](repeating: 0, count: n + 1)
        
        arr[1] = cols[0]
        
        if n > 1 {
            for j in 2...n {
                arr[j] = max(arr[j - 1], arr[j - 2] + cols[j - 1])
            }
        }
        
        rows[i] = arr[n]
    }
    
    var dp = [Int](repeating: 0, count: m + 1)
    dp[1] = rows[1]
    
    if m > 1 {
        for i in 2...m {
            dp[i] = max(dp[i - 1], dp[i - 2] + rows[i])
        }
    }
    
    print(dp[m])
}
