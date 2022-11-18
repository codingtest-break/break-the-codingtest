package backtracking

import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readln())
    val nums = IntArray(n) {tokenizer.nextToken().toInt()}
    print(Solution10819().solution(n, nums))
}

class Solution10819 {
    fun solution(n: Int, nums: IntArray): Int {
        val isVisited = BooleanArray(n)
        val pickedNums = IntArray(n)
        var result = Int.MIN_VALUE

        fun backTrack(depth: Int, diffSum: Int) {
            if (depth == n) {
                result = maxOf(result, diffSum)
                return
            }
            fun operation(diffSum: Int, depth: Int, nums: IntArray, pickedNums: IntArray) {
                fun pickNumbers() {
                    for (i in 0 until n) {
                        if (isVisited[i]) {
                            continue
                        }

                        var sum = diffSum
                        isVisited[i] = true
                        pickedNums[depth] = nums[i]
                        sum += (if (depth > 0) abs(nums[i] - pickedNums[depth - 1]) else diffSum)

                        backTrack(depth + 1, sum)
                        isVisited[i] = false
                    }
                }
                pickNumbers()
            }
            operation(diffSum, depth, nums, pickedNums)
        }
        backTrack(0, 0)
        return result
    }
}