package DP

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val stringTokenizer = StringTokenizer(readln())
    val arr = IntArray(n+1) {
        if(it == 0) 0 else stringTokenizer.nextToken().toInt()
    }
    print(Solution11053().solution(n, arr))
}

class Solution11053 {
    fun solution(n: Int, arr: IntArray): Int {
        val dp = IntArray(1001){1}
        for (i in 2..n) {
            for (j in 1..i) {
                if (arr[i] > arr[j]) {
                    dp[i] = maxOf(dp[i], dp[j]+1)
                }
            }
        }
        return dp.max()
    }
}