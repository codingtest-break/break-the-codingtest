package backtracking

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readln())
    val nums = IntArray(n) {tokenizer.nextToken().toInt()}
    tokenizer = StringTokenizer(readln())
    val operationCounts = IntArray(4) {tokenizer.nextToken().toInt()}

    print(Solution14888().solution(n, nums, operationCounts))
}

class Solution14888 {
    fun solution(n: Int, nums: IntArray, operationCounts: IntArray): String {
        var max = Int.MIN_VALUE
        var min = Int.MAX_VALUE

        fun backTrack(depth: Int, sum: Int) {
            if (depth == n) {
                max = maxOf(sum, max)
                min = minOf(sum, min)
                return
            }

            repeat(4) {
                if (operationCounts[it] > 0) {
                    operationCounts[it]--
                    when (it) {
                        0 -> backTrack(depth + 1, sum + nums[depth])
                        1 -> backTrack(depth + 1, sum - nums[depth])
                        2 -> backTrack(depth + 1, sum * nums[depth])
                        3 -> backTrack(depth + 1, sum / nums[depth])
                    }
                    operationCounts[it]++
                }
            }
        }
        backTrack(1, nums[0])
        return "$max\n$min"
    }
}