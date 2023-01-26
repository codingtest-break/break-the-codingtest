package DP

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        println(Solution_9461().solution(n))
    }
}

class Solution_9461 {
    fun solution(n: Int): Long {
        val dp = LongArray(101)
        dp[1] = 1
        dp[2] = 1
        dp[3] = 1
        dp[4] = 2
        dp[5] = 2

        for (i in 6..n) {
            dp[i] = dp[i-1] + dp[i-5]
        }

        return dp[n]
    }
}