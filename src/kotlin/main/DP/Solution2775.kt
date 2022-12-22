package DP

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val k = readln().toInt()
        val n = readln().toInt()
        println(Solution2775().solution(k, n))
    }
}

class Solution2775 {
    private lateinit var dp: Array<IntArray>
    fun solution(k: Int, n: Int): Int {
        dp = Array(k+1){IntArray(n+1)}
        for (i in 1.. n) {
            dp[0][i] = i
        }

        fun solve(floor: Int) {
            for (i in 1..n) {
                when(i) {
                    1 -> dp[floor][i] = dp[floor-1][1]
                    else -> dp[floor][i] = dp[floor][i - 1] + dp[floor-1][i]
                }
            }
        }

        for(i in 1..k) {
            solve(i)
        }

        return dp[k][n]
    }

}