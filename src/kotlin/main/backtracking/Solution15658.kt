package backtracking

import java.util.StringTokenizer

const val VARIATION_OF_OP = 4

fun main() {
    val n = readln().toInt()
    var tokenizer = StringTokenizer(readln())
    val nums = IntArray(n) {tokenizer.nextToken().toInt()}
    tokenizer = StringTokenizer(readln())
    val countOfOp = IntArray(VARIATION_OF_OP) {tokenizer.nextToken().toInt()}
    print(Solution15658().solution(n, nums, countOfOp))
}
class Solution15658 {
    fun solution(n: Int, nums: IntArray, countOfOp: IntArray): String {
        var max = Int.MIN_VALUE
        var min = Int.MAX_VALUE

        fun backTrack(depth: Int, result : Int) {
            if (depth == n) {
                max = maxOf(max, result)
                min = minOf(min, result)
                return
            }

            for (i in 0 until VARIATION_OF_OP) {
                if (countOfOp[i] <= 0) {
                    continue
                }
                countOfOp[i]--
                when (i) {
                    0 -> {
                        backTrack(depth + 1, result + nums[depth])
                    }
                    1 -> {

                        backTrack(depth + 1, result - nums[depth])
                    }
                    2 -> {
                        backTrack(depth + 1, result * nums[depth])
                    }
                    else -> {
                        backTrack(depth + 1, result / nums[depth])
                    }
                }
                countOfOp[i]++
            }
        }
        backTrack(1, nums[0])
        return "$max\n$min"
    }
}