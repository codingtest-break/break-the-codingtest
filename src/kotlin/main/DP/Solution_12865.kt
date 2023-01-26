package DP

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()
    val arr = Array(n+1) { Pair(0,0)}
    repeat(n) {
        tokenizer = StringTokenizer(readln())
        arr[it+1] = Pair(tokenizer.nextToken().toInt(), tokenizer.nextToken().toInt())
    }
    arr.sortWith(compareBy({it.first}, {it.second}))
    println(Solution_12865().solution(n,k,arr))
}

class Solution_12865 {
    fun solution(n: Int, k: Int, arr: Array<Pair<Int, Int>>): Int {
        return when {
            arr[1].first > k -> 0
            n == 1 -> arr[1].second
            else -> {
               val dp = Array(n+1){Pair(0,0)}
                val isPushed = BooleanArray(n+1)
               dp[1] = arr[1]
                var minIdx = 1
                isPushed[1] = true
                for (i in 2..n) {
                    var presentWeight = dp[i-1].first
                    var presentValue = dp[i-1].second
                    val idx = minIdx
                    for (j in idx until i) {
                        if (!isPushed[j]) {
                            continue
                        }
                        if ((presentWeight + arr[i].first > k) && isPushed[j]) {
                            presentWeight -= arr[j].first
                            presentValue -= arr[j].second
                            isPushed[minIdx++] = false
                        }
                    }
                    dp[i] = when {
                        (presentWeight + arr[i].first <= k) && presentValue + arr[i].second > dp[i-1].second -> {
                            isPushed[i] = true
                            Pair(presentWeight + arr[i].first, presentValue + arr[i].second)
                        }
                        else -> dp[i-1]
                    }
                }
                dp.maxBy { it.second }.second
            }
        }
    }
}