package DP

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n+1)
    repeat(n) {
        arr[it+1] = readln().toInt()
    }
    println(Solution_2156().solution(n, arr))
}

class Solution_2156 {
    fun solution(n: Int, arr: IntArray): Int {
        val dp = IntArray(n+1)
        return when (n) {
            1 -> arr[1]
            2 -> arr[1] + arr[2]
            else -> {
                dp[1] = arr[1]
                dp[2] = dp[1] + arr[2]
                for (i in 3..n) {
                    dp[i] = maxOf(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i], dp[i-1])
                }
                dp.max()
            }
        }
    }

}