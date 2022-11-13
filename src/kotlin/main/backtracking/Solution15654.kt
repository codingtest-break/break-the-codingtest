package backtracking

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    tokenizer = StringTokenizer(readln(), " ")
    val nums = Array(n) {
        tokenizer.nextToken()
    }
    nums.sortBy {
        it.toInt()
    }
    print(Solution15654().solution(n,m, nums))
}

class Solution15654 {
    fun solution(n: Int, m: Int, nums: Array<String>): String {
        val sb = StringBuilder()
        val isVisited = BooleanArray(n)
        val selectedNums = IntArray(m)

        fun backTrack(depth : Int) {
            if (depth == m) {
                selectedNums.forEach { sb.append(nums[it]).append(' ') }
                sb.append('\n')
                return
            }

            for (i in 0 until n) {
                if (isVisited[i]) {
                    continue
                }

                isVisited[i] = true
                selectedNums[depth] = i
                backTrack(depth + 1)
                isVisited[i] = false
            }
        }
        backTrack(0)
        return sb.toString()
    }
}