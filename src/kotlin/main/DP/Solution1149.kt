package DP

private const val RED = 0
private const val GREEN = 1
private const val BLUE = 2

fun main() {
    val n = readln().toInt()
    val costMap = Array(n+1) {IntArray(n)}
    repeat(n+1) { y ->
        if (y > 0) {
            costMap[y] = readln().split(" ").map { it.toInt() }.toIntArray()
        }
    }
    print(Solution1149().solution(n, costMap))
}

class Solution1149 {
    fun solution(n: Int, costMap: Array<IntArray>): Int {
        val dp = Array(n+1){IntArray(n)}
        dp[1][RED] = costMap[1][RED]
        dp[1][GREEN] = costMap[1][GREEN]
        dp[1][BLUE] = costMap[1][BLUE]

        for (i in 2..n) {
            dp[i][RED] = costMap[i][RED] + minOf(dp[i-1][GREEN], dp[i-1][BLUE])
            dp[i][GREEN] = costMap[i][GREEN] + minOf(dp[i-1][RED], dp[i-1][BLUE])
            dp[i][BLUE] = costMap[i][BLUE] + minOf(dp[i-1][RED], dp[i-1][GREEN])
        }

        return minOf(dp[n][RED], dp[n][GREEN], dp[n][BLUE])
    }
}