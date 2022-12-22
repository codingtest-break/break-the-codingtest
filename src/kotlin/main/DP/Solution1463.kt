package DP

private const val INF = Long.MAX_VALUE

fun main() {
    val n = readln().toInt()
    print(Solution1463().solution(n))
}

class Solution1463 {
    private val dp = LongArray(1_000_001){ INF }
    fun solution(n: Int): Long {
        dp[1] = 0
        dp[2] = 1
        dp[3] = 1
        solve(n)
        return dp[n]
    }

    private fun solve(n: Int) {
        for (i in 4..n) {
            dp[i] = minOf(
               if (i % 3 == 0) dp[i / 3] else INF,
                if (i % 2 == 0) dp[i / 2] else INF,
                dp[i - 1]) + 1
        }
    }
}