package DP

fun main() {
    val n = readln().toInt()
    val stairs = IntArray(n+1)
    for (i in 1..n) {
        stairs[i] = readln().toInt()
    }
    print(Solution2579().solution(n,stairs))
}

class Solution2579 {
    fun solution(n: Int, stairs: IntArray): Int {
        val dp = IntArray(301)
        dp[1] = stairs[1]
        if (n <= 1) {
            return dp[n]
        }
        dp[2] = stairs[1] + stairs[2]
        for (i in 3..n) {
            dp[i] = maxOf(dp[i-2] + stairs[i], dp[i-3] + stairs[i-1] + stairs[i])
        }
        return dp[n]
    }
}