package DP

fun main() {
    val n = readln().toInt()
    print(Solution2839().solution(n))
}

class Solution2839 {
    private val dp = IntArray(5001)
    fun solution(n: Int): Int {
        dp[1] = -1
        dp[2] = -1
        dp[3] = 1
        dp[4] = -1
        dp[5] = 1

        solve(n)

        return dp[n]
    }

    private fun solve(num : Int) {
        for (i in 6..num) {
            if (dp[i-3] > 0 && dp[i-5] > 0) {
                dp[i] = minOf(dp[i-3], dp[i-5]) + 1
            } else {
                if (dp[i-3] <= 0 && dp[i-5] <= 0) {
                    dp[i] = -1
                }
                else if (dp[i-3] <= 0) {
                    dp[i] = dp[i-5] + 1
                } else {
                    dp[i] = dp[i-3] + 1
                }
            }
        }
    }
}