package backtracking

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val tokenizer = StringTokenizer(readln())
    val nums = IntArray(n) {tokenizer.nextToken().toInt()}
    print(Solution16198().solution(n, nums))
}

class Solution16198 {
    fun solution(n: Int, nums: IntArray) : Int {
        val isVisited = BooleanArray(n)
        var max = Int.MIN_VALUE

        fun backTrack(depth : Int, result: Int) {
            if (depth == n - 2) {
                max = maxOf(max, result)
                return
            }

            for (i in 1 until n-1) {
                if (isVisited[i]) {
                    continue
                }

                isVisited[i] = true
                var left = i-1
                var right = i+1
                for (j in left downTo 0) {
                    if (!isVisited[j]) {
                        left = j
                        break
                    }
                }
                for (k in right until n) {
                    if (!isVisited[k]) {
                        right = k
                        break
                    }
                }
                backTrack(depth + 1, result + nums[left] * nums[right])
                isVisited[i] = false
            }
        }
        backTrack(0, 0)
        return max
    }
}