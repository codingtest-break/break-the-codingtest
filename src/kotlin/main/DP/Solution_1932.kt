package DP

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val tri = Array(n + 1) { IntArray(n + 1) }
    for (y in 1..n) {
        val tokenizer = StringTokenizer(readln())
        for (x in 1..tokenizer.countTokens()) {
            tri[y][x] = tokenizer.nextToken().toInt()
        }
    }

    print(Solution_1932().solution(n, tri))
}

class Solution_1932 {
    fun solution(n: Int, tri: Array<IntArray>): Int {
        val dp = Array(n+1){IntArray(n+1)}
        when (n) {
            1 -> return tri[1][1]
            2 -> return maxOf(tri[1][1] + tri[2][1], tri[1][1] + tri[2][2])
        }
        dp[1][1] = tri[1][1]
        dp[2][1] = tri[1][1] + tri[2][1]
        dp[2][2] = tri[1][1] + tri[2][2]
        for (y in 3..n) {
            for (x in 1 ..y) {
                dp[y][x] = when (x) {
                    1  -> dp[y-1][x] + tri[y][x]
                    y -> dp[y-1][x-1] + tri[y][x]
                    else -> maxOf(dp[y-1][x-1], dp[y-1][x]) + tri[y][x]
                }
            }
        }
        return dp[n].max()
    }

}