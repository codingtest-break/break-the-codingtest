package DP

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val tokenizer = StringTokenizer(readln())
    val arr = IntArray(n) { tokenizer.nextToken().toInt() }
    print(Solution_1912().solution(n, arr))
}

class Solution_1912 {
    fun solution(n: Int, arr: IntArray): Int {
        val dp = IntArray(n + 1){-987654321}
        dp[0] = arr[0]
        for (i in 1 until n) {
            dp[i] = maxOf(dp[i - 1] + arr[i], arr[i])
        }
        return dp.max()
    }
}